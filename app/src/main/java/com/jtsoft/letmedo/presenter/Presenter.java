package com.jtsoft.letmedo.presenter;


import com.jtsoft.letmedo.view.MvpView;

public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);
    void detachView();
}
