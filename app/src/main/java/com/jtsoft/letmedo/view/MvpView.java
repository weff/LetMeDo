package com.jtsoft.letmedo.view;

import android.support.annotation.StringRes;
import android.view.View;

import java.util.List;

public interface MvpView<T> {

    void showLoading();

    void hideLoading();

    void showError(String msg, View.OnClickListener onClickListener);

    void showError(@StringRes int msg, View.OnClickListener onClickListener);

    void showError(Throwable t, View.OnClickListener onClickListener);

    void showEmpty(@StringRes int msg, View.OnClickListener onClickListener);

    void showEmpty(@StringRes int msg, View.OnClickListener onClickListener, int imageId);

    void showNetError(View.OnClickListener onClickListener);

    void onRefreshComplete();

    void showView();

    void hideView();

    void refresh(List<T> data);

    void loadMore(List<T> data);

    void refresh(Object obj, int type, Object extra);
}
