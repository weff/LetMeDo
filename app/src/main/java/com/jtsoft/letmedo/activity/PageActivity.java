package com.jtsoft.letmedo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;

/**
 * Created by admin on 2017/10/12.
 */

public class PageActivity extends AppCompatActivity{

    private WebView mWebView;
    private ImageView Back;
    private TextView Tittle;
    private TextView Edit;
    private String strToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        //控件初始化
        initView();
    }

    private void initView() {
        strToken = SharedpreferencesManager.getToken();
        //webview控件
        mWebView = (WebView) findViewById(R.id.webview);
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
        mWebView.addJavascriptInterface(new HomeObject(), "HomeObject");
        //返回控件
        Back = (ImageView) findViewById(R.id.back_press);
        //标题控件
        Tittle = (TextView) findViewById(R.id.title_name);
        //编辑控件
        Edit = (TextView) findViewById(R.id.edit);
        Edit.setVisibility(View.INVISIBLE);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //数据初始化
        initData();
    }

    private void initData() {
        String url = getIntent().getStringExtra("Pageurl");
        String tittle = getIntent().getStringExtra("PageTittle");
        Tittle.setText(tittle);
        mWebView.loadUrl(url);
    }
     //内部类 供js调用原生
    public class HomeObject{
        private Intent intent;
        private Bundle bundle;

        //跳到商品详情
        @JavascriptInterface
        public void oGoodsDetail(int goodsId, int storeId) {
            Log.e("TAG", "商品被调用了");
            intent = new Intent(PageActivity.this, ShopCartsDetailActivity.class);
            bundle = new Bundle();
            bundle.putInt("goodsId", goodsId);
            Log.e("TAG","首页的商品Id是：" + goodsId);
            bundle.putInt("storeId", storeId);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
