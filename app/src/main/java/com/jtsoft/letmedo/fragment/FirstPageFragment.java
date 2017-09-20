package com.jtsoft.letmedo. fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    String url = "";

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
        //swiprefresh控件
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        //webview控件
        mWebView = (WebView) view.findViewById(R.id.webview);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //重新刷新页面
                mWebView.loadUrl(mWebView.getUrl());
            }
        });

        mWebView.loadUrl(url);
        //添加javascript的支持
        mWebView.getSettings().setJavaScriptEnabled(true);
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
