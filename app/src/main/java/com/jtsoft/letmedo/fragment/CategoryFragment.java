package com.jtsoft.letmedo. fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.activity.ShopCartsDetailActivity;
import com.jtsoft.letmedo.bean.GoodsToCartsBean;
import com.jtsoft.letmedo.bean.ShopCartCountBean;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/6/21.
 *  分类页面
 */

public class CategoryFragment extends Fragment {

    private View view;
    private WebView mWebView;
    private ImageView Back;
    private TextView Tittle;
    private TextView Edit;
    private Context context;
    private String strToken;

    //视图初始化
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        view = LayoutInflater.from(context).inflate(R.layout.fragment_category, null, false);
        return view;
    }
    //数据初始化
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        strToken = SharedpreferencesManager.getToken();
        //标题栏控件
        Back = (ImageView) view.findViewById(R.id.back_press);
        Tittle = (TextView) view.findViewById(R.id.title_name);
        Edit = (TextView) view.findViewById(R.id.edit);
        Back.setVisibility(View.INVISIBLE);
        Tittle.setText("品类");
        Edit.setVisibility(View.INVISIBLE);
        //webview控件
        mWebView = (WebView) view.findViewById(R.id.webview);
        WebSettings settings = mWebView.getSettings();
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);//设置不从缓存中加载网页
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//适应内容大小
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setUseWideViewPort(true);//关键点
        settings.setDisplayZoomControls(true);
        settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        settings.setAllowFileAccess(true); // 允许访问文件
        settings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        settings.setLoadWithOverviewMode(true);
        //取消滚动条
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        //触摸焦点起作用
        mWebView.requestFocus();
        //webview进行加载url
        mWebView.loadUrl(Constant.CATEGORY);
        mWebView.addJavascriptInterface(new HomeObject(),"HomeObject");
    }

    //内部类 供js调用原生
    public class HomeObject {
        //跳到商品详情
        @JavascriptInterface
        public void oGoodsDetail(int goodsId, int storeId) {
            Log.e("TAG", "商品被调用了");
            Intent intent = new Intent(context, ShopCartsDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("goodsId", goodsId);
            Log.e("TAG","品类里的商品Id是：" + goodsId);
            bundle.putInt("storeId", storeId);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        //加入到购物车
        @JavascriptInterface
        public void addCar(int goodsId,int shopId) {
            initSponse(goodsId,1,1003);
        }
    }

    private void initSponse(int goodsId, int i, int i1) {
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/saveShoppingCart.do?token=" + strToken + "&goodsId=" + goodsId + "&num=" + i + "&shopId=" + i1)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(context,R.string.no_net +"");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final GoodsToCartsBean goodsToCartsBean = gson.fromJson(strJson, GoodsToCartsBean.class);
                if (goodsToCartsBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(context,"加入购物车成功");
                            SharedpreferencesManager.setCartNum(SharedpreferencesManager.getCartNum() + 1);
                            EventBus.getDefault().post(new ShopCartCountBean(SharedpreferencesManager.getCartNum()));
                            return;
                        }
                    });
                }else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(context, (String) goodsToCartsBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }
}
