package com.jtsoft.letmedo.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.GoodsDetailBean;
import com.jtsoft.letmedo.bean.GoodsToCartsBean;
import com.jtsoft.letmedo.bean.ShoppingCartBean;
import com.jtsoft.letmedo.custom.MyTableTextView;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/6/21.
 * 商品详情页面
 */

public class ShopCartsDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView Back;
    private TextView Title;
    private TextView Edit;
    private ImageView shopImg;
    private TextView shopSubject;
    private LinearLayout Relayout;
    private TextView currentPrice;
    private TextView normal;
    private TextView goodsNum;
    private Button tocart;
    private Button buyview;
    private Intent intent;
    private TextView rule;
    private TextView ruleValue;
    private String strToken;
    private int goodsId = 103;
    private List<ShoppingCartBean.ResponseBean.ShoppingCartListBean> shoppingCartList;
    private TextView mPick;
    private JSONArray array;
    private ImageView mWebView;
    private String key;
    private String value;
    private LinearLayout mainLinerLayout;
    private RelativeLayout relativeLayout;
    private String goodsName;
    private double goodsPrice;
    private String goodsImg;
    private int num = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcartsdetail);
        //控件初始化
        initView();
        //从html获取传来的商品
//        getWebShop();
        //数据初始化
        initData();
    }

    private void initData() {
        Back.setOnClickListener(this);
        Title.setText(R.string.goodsdetail);
        Edit.setVisibility(View.INVISIBLE);
        //商品详情展示接口
        insponse(strToken, goodsId);
        tocart.setOnClickListener(this);
        buyview.setOnClickListener(this);
    }

    private void insponse(String strToken, final int goodsId) {
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/getGoods.do?token=" + strToken + "&goodsId=" + goodsId)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(ShopCartsDetailActivity.this, "网络请求异常");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final GoodsDetailBean goodsDetailBean = gson.fromJson(strJson, GoodsDetailBean.class);
                if (goodsDetailBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    //获取商品bean
                    final GoodsDetailBean.ResponseBean.GoodsBean goodsBean = goodsDetailBean.getResponse().getGoods();
                    //获取商品名字
                    goodsName = goodsBean.getName();
                    //获取商品价格
                    goodsPrice = goodsBean.getPrice();
                    //获取商品规格
                    final String spec = goodsBean.getSpec();
                    //获取商品url
                    final String detail = goodsBean.getDetail();

                    String[] split = detail.split("\"");
                    final String str = split[1];
                    Log.e("TAG", str);
                    //获取商品的图片
                    goodsImg = goodsBean.getImage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //获取商品的产地、包装
                            String param = goodsBean.getParam();
                            try {
                                array = new JSONArray(param);
                                Log.e("TAG", "array.length();:::" + array.length());
                                for (int i = 0; i < array.length(); i++) {
                                    relativeLayout = (RelativeLayout) LayoutInflater.from(ShopCartsDetailActivity.this).inflate(R.layout.table, null);
                                    MyTableTextView txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
                                    JSONObject jsonObject = (JSONObject) array.get(i);
                                    key = jsonObject.getString("key");
                                    txt.setText(key);
                                    MyTableTextView txt_value = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
                                    value = jsonObject.getString("value");
                                    txt_value.setText(value);
                                    mainLinerLayout.addView(relativeLayout);
                                    Log.e("TAG", "key:::" + key + ":::: value:" + value);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //商品图片
                            Glide.with(ShopCartsDetailActivity.this).load(goodsImg).into(shopImg);
                            //商品名称
                            shopSubject.setText(goodsName + "");
                            //商品价格
                            currentPrice.setText(goodsPrice + "");
                            //商品数量
                            goodsNum.setText(spec);
                            //获取网页图片，这里要获取图片的宽高，然后根据图片的宽高改变View的高度
                            Glide.with(ShopCartsDetailActivity.this).load(str).listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    int imageWidth = resource.getIntrinsicWidth();
                                    int imageHeight = resource.getIntrinsicHeight();
                                    int viewWidth = ShopCartsDetailActivity.this.getResources().getDisplayMetrics().widthPixels;
                                    //view高度等于 viewWidth * imageHeight / imageWidth
                                    int viewHeight = (int) (imageHeight * viewWidth *1.0f/ imageWidth);
                                    Log.e("TAG","图片的宽高："+imageWidth+" x "+imageHeight);
                                    Log.e("TAG","view宽高："+viewWidth+" x "+viewHeight);
                                    //设置view的高度
                                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    lp.height = viewHeight;
                                    mWebView.setLayoutParams(lp);
                                    return false;
                                }
                            }).into(mWebView);
                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(ShopCartsDetailActivity.this, (String) goodsDetailBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        //返回按键控件
        Back = (ImageView) findViewById(R.id.back_press);
        //标题
        Title = (TextView) findViewById(R.id.title_name);
        //编辑
        Edit = (TextView) findViewById(R.id.edit);
        Edit.setVisibility(View.INVISIBLE);
        //详情图片控件
        shopImg = (ImageView) findViewById(R.id.view_pager);
        //详情商品名称
        shopSubject = (TextView) findViewById(R.id.shop_subject);
        //布局控件
        Relayout = (LinearLayout) findViewById(R.id.backguand);
        //详情商品价格
        currentPrice = (TextView) findViewById(R.id.current_price);
        //详情商品规格
        normal = (TextView) findViewById(R.id.normal);
        //商品详情的显示数量
        goodsNum = (TextView) findViewById(R.id.count_shopdetail);
        //加入购物车按键控件
        tocart = (Button) findViewById(R.id.shop_cart);
        //立即购买控件
        buyview = (Button) findViewById(R.id.shop_direct);
        //规格父控件
        mainLinerLayout = (LinearLayout) this.findViewById(R.id.MyTable);
        //规格控件
        rule = (TextView) findViewById(R.id.list_1_1);
        //规格value控件
        ruleValue = (TextView) findViewById(R.id.list_1_2);
        //网页图片控件
        mWebView = (ImageView) findViewById(R.id.webview);

        strToken = SharedpreferencesManager.getToken();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_press:
                finish();
                break;
            case R.id.shop_cart:
                //加入购物车
                num++;
//                EventBus.getDefault().post(new ShopCartCountBean(num));
                initSponse(goodsId,1);
                break;
            case R.id.shop_direct:
                //立即购买，跳转到确认订单
                if (goodsPrice < 15) {
                    ToastUtil.showShort(ShopCartsDetailActivity.this,"购买商品的价格少于15元，无法进行配送；请先加入购物车");
                    return;
                }else {
                    intent = new Intent(ShopCartsDetailActivity.this,MyBillActivity.class);
                    intent.putExtra("goodsId",goodsId);
                    intent.putExtra("goodsName",goodsName);
                    intent.putExtra("goodsPrice",goodsPrice);
                    Log.e("GIG",goodsPrice + "");
                    intent.putExtra("goodsImg",goodsImg);
                    intent.putExtra("state",1);
                    startActivity(intent);
                }

                break;
            default:
                break;
        }

    }

    private void initSponse(final int goodsId, int i) {
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/saveShoppingCart.do?token=" + strToken + "&goodsId=" + goodsId + "&num=" + i)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(ShopCartsDetailActivity.this,"网络请求异常");
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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(ShopCartsDetailActivity.this,"加入购物车成功");
                            return;
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(ShopCartsDetailActivity.this, (String) goodsToCartsBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
