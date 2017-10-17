package com.jtsoft.letmedo.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.jtsoft.letmedo.view.CommonView;
import com.jtsoft.letmedo.common.utils.ToastUtils;
import com.jtsoft.letmedo.presenter.ViewPresenter;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity implements CommonView {

    protected Context mContext;

    private ViewPresenter presenter;

    public ViewPresenter getPresenter() {
        return presenter;
    }

    private LayoutInflater inflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = LayoutInflater.from(this);
        initPresenter();
    }


    protected void initPresenter() {
        presenter = new ViewPresenter();
        presenter.init(this);
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void refresh(List data) {

    }

    @Override
    public void refresh(Object obj, int type, Object extra) {

    }

    @Override
    public void loadMore(List data) {

    }

    @Override
    public void showError(String msg, View.OnClickListener onClickListener) {
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(this, msg);
        }
    }

    @Override
    public void showError(@StringRes int msg, View.OnClickListener onClickListener) {
        ToastUtils.show(this, msg);
    }

    @Override
    public void showError(Throwable t, View.OnClickListener onClickListener) {
        if (t != null) {
            this.showError(t.getMessage(), null);
        }
    }

    @Override
    public void showEmpty(@StringRes int msg, View.OnClickListener onClickListener) {
        return;
    }

    @Override
    public void showEmpty(@StringRes int msg, View.OnClickListener onClickListener, int imageId) {
        return;
    }

    @Override
    public void showNetError(View.OnClickListener onClickListener) {
        this.showError("网络异常，请重试！", null);
    }

    @Override
    public void onRefreshComplete() {
        return;
    }

    @Override
    public void showView() {

    }

    @Override
    public void hideView() {

    }

    protected void onResume() {
        super.onResume();

    }

    protected void onPause() {
        super.onPause();

    }
}