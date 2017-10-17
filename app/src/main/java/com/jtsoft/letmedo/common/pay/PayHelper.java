package com.jtsoft.letmedo.common.pay;

import android.content.Context;

import com.jtsoft.letmedo.common.pay.alipay.Alipay;
import com.jtsoft.letmedo.common.pay.weixin.WXPay;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public class PayHelper {

    private volatile static PayHelper INSTANCE;

    private static Object INSTANCE_LOCK = new Object();

    public static final int PAYMENT_TYPE_CASH_ALIPAY = 5;// 支付宝
    public static final int PAYMENT_TYPE_CASH_WXPAY = 6;// 微信支付
    public static final int PAYMENT_TYPE_CASH_REMAINING = 13;// 余额支付


    public static PayHelper getInstance() {
        if (INSTANCE == null)
            synchronized (INSTANCE_LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new PayHelper();
                }
            }
        return INSTANCE;
    }

    public void entryPay(Context context, String appRequest, String paySerial, int type, PayHelperCallBack callBack) {
        switch (type) {
            case PAYMENT_TYPE_CASH_ALIPAY:
                doAlipay(context, appRequest, callBack);
                break;
            case PAYMENT_TYPE_CASH_WXPAY:
                doWXPay(context, appRequest, paySerial, callBack);
                break;
        }
    }

    public interface PayHelperCallBack {
        void onSuccess(); //支付成功

        void onError(int error_code);   //支付失败

        void onDealing();

        void onCancel();    //支付取消
    }

    /**
     * 微信支付
     *
     * @param appRequest 支付服务生成的支付参数
     */
    private void doWXPay(Context context, String appRequest, final String paySerial, final PayHelperCallBack callBack) {
        if (callBack == null) {
            return;
        }
        WXPay.getInstance().doPay(context,appRequest, paySerial, new WXPay.WXPayResultCallBack() {
            @Override
            public void onSuccess() {
                if (callBack != null)
                    callBack.onSuccess();
            }

            @Override
            public void onError(int error_code) {
                if (callBack != null)
                    callBack.onError(error_code);
            }

            @Override
            public void onCancel() {
                if (callBack != null)
                    callBack.onCancel();
            }
        });
    }

    /**
     * 支付宝支付
     *
     * @param appRequest 支付服务生成的支付参数
     */
    private void doAlipay(Context context, String appRequest, final PayHelperCallBack callBack) {
        if (callBack == null) {
            return;
        }
        new Alipay(context, appRequest, new Alipay.AlipayResultCallBack() {
            @Override
            public void onSuccess() {
                if (callBack != null)
                    callBack.onSuccess();
            }

            @Override
            public void onDealing() {
                if (callBack != null)
                    callBack.onDealing();
            }

            @Override
            public void onError(int error_code) {
                if (callBack != null)
                    callBack.onError(error_code);

            }

            @Override
            public void onCancel() {
                if (callBack != null)
                    callBack.onCancel();
            }
        }).doPay();
    }


}
