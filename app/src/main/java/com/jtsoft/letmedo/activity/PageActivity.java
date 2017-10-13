package com.jtsoft.letmedo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jtsoft.letmedo.R;

/**
 * Created by admin on 2017/10/12.
 */

public class PageActivity extends AppCompatActivity{

    private WebView mWebView;
    private ImageView Back;
    private TextView Tittle;
    private TextView Edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        //控件初始化
        initView();
    }

    private void initView() {
        //webview控件
        mWebView = (WebView) findViewById(R.id.webview);
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
}
