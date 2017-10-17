package com.jtsoft.letmedo.presenter;

import android.content.Context;

import com.jtsoft.letmedo.model.BaseModel;
import com.jtsoft.letmedo.common.utils.GenericsUtils;
import com.jtsoft.letmedo.view.MvpView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;


public class BasePresenter<T extends MvpView, V extends BaseModel> implements Presenter<T> {

    protected T mMvpView;
    private List<Subscriber> subscribers;
    protected Context context;
    protected V viewModel;

    public static final int TYPE_DEFAULT = 0x0000;

    public BasePresenter() {
        subscribers = new ArrayList<>();
    }


    public void init(Context context) {
        this.context = context;
    }

    public void createModel(){
        Class newClazz = GenericsUtils.getSuperClassGenricType(getClass(),1);
        viewModel = (V) BaseModel.attach(newClazz);
    }

    public V getViewModel(){
        return viewModel;
    }

    public <K> K getViewModel(Class<K> clazz){
        return BaseModel.attach(clazz);
    }

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
        createModel();
    }

    @Override
    public void detachView() {
        mMvpView = null;
        if (subscribers != null && subscribers.size() > 0) {
            for (Subscriber subscriber : subscribers) {
                subscriber.unsubscribe();
            }
        }
        subscribers = null;
    }

    public boolean isAttach() {
        boolean isAttach = false;
        if (mMvpView != null) {
            isAttach = true;
        }
        return isAttach;
    }

    /**
     * 添加到列表，在detachView时取消订阅。在子类定义的subscriber需要手动调用添加
     *
     * @param subscriber
     */
    public void addSubscriber(Subscriber subscriber) {
        if (subscribers != null) {
            subscribers.add(subscriber);
        }
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached())
            throw new BasePresenter.MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }


    protected void showViewLoading() {
        if (isViewAttached()) {
            this.mMvpView.showLoading();
        }
    }

    protected void hideViewLoading() {
        if (isViewAttached()) {
            this.mMvpView.hideLoading();
        }
    }
}
