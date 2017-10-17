package com.jtsoft.letmedo.common.pay.weixin;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.jtsoft.letmedo.common.utils.MD5Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jtsoft.letmedo.MyApplication;
import com.jtsoft.letmedo.wxapi.WXPayEntryActivity;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * 微信支付
 */
public class WXPay {

    private static WXPay mWXPay;
    private IWXAPI mWXApi;
    private String mPayParam;
    private WXPayResultCallBack mCallback;
    private Context mContext;
    private static Object INSTANCE_LOCK = new Object();
    private IWXAPI api;

    /**
     * API密钥，在商户平台设置
     */
    String WEIXIN_PAY_API_KEY = "3A939CA0A8EED49295B68670721285DE";

    public static final int NO_OR_LOW_WX = 0x0001;   //未安装微信或微信版本过低
    public static final int ERROR_PAY_PARAM = 0x0002;  //支付参数错误
    public static final int ERROR_PAY = 0x0003;  //支付失败

    public interface WXPayResultCallBack {
        void onSuccess(); //支付成功
        void onError(int error_code);   //支付失败
        void onCancel();    //支付取消
    }

    public WXPay(Context context) {
        mWXApi = WXAPIFactory.createWXAPI(context, null);
        mContext = context;
        //mWXApi.registerApp(wx_appid);
    }
/*
    public static void init(Context context) {
        if(mWXPay == null) {
            mWXPay = new WXPay(context);
        }
    }*/

    public static WXPay getInstance() {
        if (mWXPay == null)
            synchronized (INSTANCE_LOCK) {
                if (mWXPay == null) {
                    mWXPay = new WXPay(MyApplication.getInstance());
                }
            }
        return mWXPay;
    }

    private IWXAPI getWXApi() {
        return mWXApi;
    }
    /**
     * 发起微信支付
     */
    public void doPay(Context context, String appRequest, String paySerial, WXPayResultCallBack callback) {
        mCallback = callback;
        api = WXAPIFactory.createWXAPI(context, null);

        if (!check()) {
            if (mCallback != null) {
                mCallback.onError(NO_OR_LOW_WX);
            }
            return;
        } else {

            PayReq req = genPayReq(appRequest);
            //WXPayEntryActivity.setPayFromType(WXPayEntryActivity.PAY_FROM_TYPE_COUNTER);
            WXPayEntryActivity.setPaySerial(paySerial);
            req = genPayReq(appRequest);
            WXPayEntryActivity.setAppId(req.appId);
            if (req != null) {
                api.registerApp(req.appId);
                api.sendReq(req);
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
//            mWXApi.registerApp(req.appId);
//            WXPayEntryActivity.setAppId(req.appId);
//            WXPayEntryActivity.setPayFromType(WXPayEntryActivity.PAY_FROM_TYPE_COUNTER);
//            WXPayEntryActivity.setPaySerial(paySerial);
//            mWXApi.sendReq(req);
            } else if (mCallback != null) {
                mCallback.onError(ERROR_PAY_PARAM);
            }
            ((Activity)context).finish();
        }
    }
    /**
     * 微信支付-签名
     *
     * @param params
     * @return
     */
    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(WEIXIN_PAY_API_KEY);

        String appSign = MD5Util.GetMD5Code(sb.toString()).toUpperCase();
        Log.e("orion", appSign);
        return appSign;
    }

    /**
     * 2.生成微信支付需要的参数
     *
     * @param appRequest
     */
    private PayReq genPayReq(String appRequest) {
        try {
            PayReq req = new PayReq();
            //WeChatPayResponse r = HttpResultParseUtil.getWeChatPayWithJSON(appRequest);
            WeChatPayResponse r = new Gson().fromJson(appRequest, new TypeToken<WeChatPayResponse>() {
            }.getType());
            req.appId = r.getAppid();
            req.partnerId = r.getPartnerid();
            req.prepayId = r.getPrepayid();
            req.packageValue = r.getPackageValue();//"WXPay" /*"prepay_id="+resultunifiedorder.get("prepay_id")*/;
            req.nonceStr = r.getNoncestr();//genNonceStr();
            req.timeStamp = r.getTimestamp();//String.valueOf(genTimeStamp());
            req.sign = r.getSign();
            /*List<NameValuePair> signParams = new LinkedList<NameValuePair>();
            signParams.add(new BasicNameValuePair("appid", req.appId));
            signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
            signParams.add(new BasicNameValuePair("package", req.packageValue));
            signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
            signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
            signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));

            req.sign = genAppSign(signParams);*/
            return req;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //支付回调响应
    public void onResp(int error_code) {
        if(mCallback == null) {
            return;
        }

        if(error_code == 0) {   //成功
            mCallback.onSuccess();
        } else if(error_code == -1) {   //错误
            mCallback.onError(ERROR_PAY);
        } else if(error_code == -2) {   //取消
            mCallback.onCancel();
        }

        mCallback = null;
    }

    //检测是否支持微信支付
    private boolean check() {
        return mWXApi.isWXAppInstalled() && mWXApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }
}
