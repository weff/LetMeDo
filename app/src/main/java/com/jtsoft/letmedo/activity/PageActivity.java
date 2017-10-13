package com.jtsoft.letmedo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.jtsoft.letmedo.R;

/**
 * Created by admin on 2017/10/12.
 */

public class PageActivity extends AppCompatActivity{

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        //控件初始化
        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webview);
        //数据初始化
        initData();
    }

    private void initData() {
        String url = getIntent().getStringExtra("url");
        mWebView.loadUrl(url);
    }
}
