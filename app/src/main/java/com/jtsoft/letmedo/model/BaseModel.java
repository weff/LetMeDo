package com.jtsoft.letmedo.model;

import android.util.Log;

import com.jtsoft.letmedo.common.DejiException;
import com.jtsoft.letmedo.common.OnNetRequestListener;
import com.jtsoft.letmedo.common.ShowApiResponse;
import com.jtsoft.letmedo.retrofit.RetrofitApi;
import com.jtsoft.letmedo.retrofit.RetrofitService;
import com.jtsoft.letmedo.utils.NetWorkUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;


public class BaseModel {
    private static final String TAG = "ViewModel";


    public static <T> T attach(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public RetrofitApi getRetrofitApi(){
        return RetrofitService.getInstance().createHttpShowApi();
    }

    protected Subscriber call(final Observable observable, final OnNetRequestListener listener) {
        if (observable != null) {
            Subscriber<ShowApiResponse> subscriber = new Subscriber<ShowApiResponse>() {
                @Override
                public void onCompleted() {
                    //仅成功后会回调
                    listener.onFinish();
                }

                @Override
                public void onError(Throwable e) {
                    Log.d("ViewModel", "e=" + e.getMessage());
                    listener.onFinish();
                }

                @Override
                public void onNext(ShowApiResponse r) {
                    if (r != null) {
                        if (r.code != NetWorkUtils.CODE_SUCCESS) {
                            listener.onFailure(new DejiException(r.message));
                        } else {
                            listener.onSuccess(r.response);
                        }
                    } else {
                        listener.onFailure(new DejiException("服务器返回错误"));
                    }
                }
            };

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(new Action0() {
                        @Override
                        public void call() {
                            listener.onStart();
                        }
                    })
                    .subscribe(subscriber);
            return subscriber;
        }
        return null;
    }

    protected Subscriber call2(final Observable observable, final OnNetRequestListener listener) {
        if (observable != null) {
            Subscriber<String> subscriber = new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    //仅成功后会回调
                    listener.onFinish();
                }

                @Override
                public void onError(Throwable e) {
                    Log.d("ViewModel", "e=" + e.getMessage());
                    listener.onFinish();
                }

                @Override
                public void onNext(String s) {
                    Log.d("call2", "s=" + s);
                }
            };

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(new Action0() {
                        @Override
                        public void call() {
                            listener.onStart();
                        }
                    })
                    .subscribe(subscriber);
            return subscriber;
        }
        return null;
    }
}
