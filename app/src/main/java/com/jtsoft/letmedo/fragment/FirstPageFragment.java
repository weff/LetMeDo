package com.jtsoft.letmedo. fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jtsoft.letmedo.R;


/**
 * Created by Administrator on 2017/6/21.
 * 首页页面
 */

public class FirstPageFragment extends Fragment {

    private View view;
    private WebView mWebView;
    private SwipeRefreshLayout swipeLayout;
    String url = "http://openapi.ibiaoke.com:58080/los-web/index?clientType=android";

    //视图初始化
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = getActivity();
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
        mWebView.loadUrl(url);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //添加javascript的支持
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setUseWideViewPort(true);//关键点
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        settings.setAllowFileAccess(true); // 允许访问文件
        settings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        settings.setLoadWithOverviewMode(true);
        //取消滚动条
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        //触摸焦点起作用
        mWebView.requestFocus();
        //点击链接继续在当前browser中相应
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
