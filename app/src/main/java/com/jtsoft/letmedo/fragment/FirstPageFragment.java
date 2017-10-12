package com.jtsoft.letmedo.fragment;

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

import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.activity.ShopCartsDetailActivity;
import com.jtsoft.letmedo.utils.Constant;


/**
 * Created by Administrator on 2017/6/21.
 * 首页页面
 */

public class FirstPageFragment extends Fragment {

    private View view;
    private WebView mWebView;
    private Context context;

    //视图初始化
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        view = LayoutInflater.from(context).inflate(R.layout.fragment_firstpage, null, false);
        return view;
    }

    //数据初始化

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //webview控件
        mWebView = (WebView) view.findViewById(R.id.webview);
        WebSettings settings = mWebView.getSettings();
        mWebView.loadUrl(Constant.FIRST_PAGE);
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
        mWebView.addJavascriptInterface(new HomeObject(), "HomeObject");
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
            Log.e("TAG","首页的商品Id是：" + goodsId);
            bundle.putInt("storeId", storeId);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        //加入到购物车
        @JavascriptInterface
        public void toShopCarts(int goodsId) {}
        //充值有礼跳转到我的账户页面
        @JavascriptInterface
        public void toMyAcount() {}
        //跳转到及时送
        @JavascriptInterface
        public void toTimeDelivery() {}
    }
}
