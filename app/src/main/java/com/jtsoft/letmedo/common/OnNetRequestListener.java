package com.jtsoft.letmedo.common;

import com.jtsoft.letmedo.view.CommonView;
import com.jtsoft.letmedo.view.MvpView;
import com.jtsoft.letmedo.common.utils.GenericsUtils;
import java.util.List;

public class OnNetRequestListener<T> implements BaseRequestListener<T> {

    private MvpView mMvpView;

    private int viewType;

    private boolean isShowLoading = false;


    private Object extra;

    public Object getExtra() {
        return extra;
    }

    public MvpView getView() {
        return mMvpView;
    }


    public OnNetRequestListener() {
    }


    public OnNetRequestListener(MvpView mMvpView, boolean isShowLoading) {
        this.mMvpView = mMvpView;
        this.isShowLoading = isShowLoading;
    }

    public OnNetRequestListener(MvpView mMvpView,
                                boolean isShowLoading,
                                int viewType) {
        this.mMvpView = mMvpView;
        this.isShowLoading = isShowLoading;
        this.viewType = viewType;
    }

    public OnNetRequestListener(MvpView mMvpView,
                                boolean isShowLoading,
                                int viewType,
                                Object extra) {
        this.mMvpView = mMvpView;
        this.isShowLoading = isShowLoading;
        this.viewType = viewType;
        this.extra = extra;
    }

    @Override
    public void onStart() {
        if (getView() != null && isShowLoading) {
            getView().showLoading();
        }
    }

    @Override
    public void onFinish() {
        if (getView() != null) {
            if (isShowLoading) {
                getView().hideLoading();
            }
            getView().onRefreshComplete();
        }
    }

    @Override
    public void onSuccess(T data) {
        if (getView() != null && data != null) {
            if (data != null && GenericsUtils.isListType(data.getClass())) {
                    if (((List) data).size() > 0) {
                        getView().showView();
                        if (getView() instanceof CommonView)
                            ((CommonView) getView()).refresh((List) data);
                    } else {
                        getView().hideView();
                    }
            } else if (data != null && !GenericsUtils.isListType(data.getClass())) {
                List list = GenericsUtils.getListFiledValue(data);
                if (list != null/*&&list.size()>0*/) {
                    this.onSuccess((T) list);
                }
            }
        }
        if (getView() != null &&
                (data == null ||
                        (data != null &&
                                !GenericsUtils.isListType(data.getClass())))) {
            if (getView() instanceof CommonView)
                ((CommonView) getView()).refresh(data,viewType,extra);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (getView() != null) {
            getView().showError(t, null);
        }
    }

}
