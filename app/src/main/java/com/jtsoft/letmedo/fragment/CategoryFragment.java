package com.jtsoft.letmedo. fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.utils.Constant;


/**
 * Created by Administrator on 2017/6/21.
 *  分类页面
 */

public class CategoryFragment extends Fragment {

    private View view;
    private WebView mWebView;

    //视图初始化
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = getActivity();
        view = LayoutInflater.from(context).inflate(R.layout.fragment_category, null, false);
        return view;
    }
    //数据初始化
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //webview控件
        mWebView = (WebView) view.findViewById(R.id.webview);
        WebSettings settings = mWebView.getSettings();
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
        //webview进行加载url
        mWebView.loadUrl(Constant.CATEGORY);
    }
}
