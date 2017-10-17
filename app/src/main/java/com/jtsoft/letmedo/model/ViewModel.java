package com.jtsoft.letmedo.model;

import com.jtsoft.letmedo.common.OnNetRequestListener;

import rx.Subscriber;

import static com.jtsoft.letmedo.spUtil.SharedpreferencesManager.getToken;

/**
 * Created by wy on 2017/7/25.
 */

public class ViewModel extends BaseModel {

    public Subscriber getPayOrder(Integer orderId, int payType, OnNetRequestListener listener) {
        return call(getRetrofitApi().getPayOrder(orderId, payType,getToken()), listener);
    }
}
