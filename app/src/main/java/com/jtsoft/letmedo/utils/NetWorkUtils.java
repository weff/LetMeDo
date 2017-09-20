package com.jtsoft.letmedo.utils;

import android.os.Message;

import com.google.gson.Gson;
import com.jtsoft.letmedo.webinterface.Listener;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/6/22.
 */

public class NetWorkUtils {

    public final static String FLAG_SHAREDPREFERENCES = "sharedpreferencesmanager_letmedo";
    public final static String FLAG_ISLOGIN = "sharedpreferencesmanager_islogin";
    public final static String FLAG_FIRSTSHOW = "sharedpreferencesmanager_firstshow";
    public final static String FLAG_TOKEN = "sharedpreferencesmanager_token";
    public final static String FLAG_EVALUATEJSON = "sharedpreferencesmanager_evaluatejson";
    public final static String FLAG_USERPHONE = "register_phone";
    public final static String FLAG_USERPASSWORD = "register_password";
    public final static int FLAG_EDITADDRESS = 4001;  //编辑地址的requestcode码
    public final static int FLAG_EDITADDRESS_OUT = 4002;//编辑地址回传的result码
    public final static int FLAG_EDITADDRESS_DELETE = 4003;//编辑地址 删除按键回传的result码
    public final static int FLAG_ADDADDRESS = 4004; //增加地址的requestcode码
    public final static int FLAG_ADDADDRESS_out = 4005;//增加地址回传的result码
    public final static int FLAG_PREPAYFRAGMENT = 4006;//商品详情的requestcode码
    public final static int FLAG_ORDETAIL = 4007;//商品详情的result码
    public final static int FLAG_ADD_DISTRICT = 4008;//添加地址-获取地图地址的requestcode码
    public final static int FLAG_ADD_DISTRICT_result = 4009;//添加地址-获取地图地址的result码
    public final static int FLAG_EDIT_DISTRICT = 4012;//编辑地址-获取地图地址的requestcode码
    public final static int FLAG_EDIT_DISTRICT_result = 4013;//编辑地址-获取地图地址的result码
    public final static int FLAG_GOODS_ADDRESS = 4010;//确认订单选择收货地址的requestcode码
    public final static int FALG_GOODS_ADDRESS_BACK = 4011;//确认订单选择收货地址的result码
    public final static int SHOPSTORE_ADDRESS = 10;//是否是确认订单选择的收货地址
    public final static int CODE_SUCCESS = 200;
    public final static int ADD = 4014;
    public final static int EDIT = 4015;
    public final static int USECOUPON = 11;
    public final static int USECOUPON_RESULT = 12;
    //注册获取的优惠券
    public final static int REGISTER_COUPON = 100;
    //领取的优惠券
    public final static int GET_COUPON = 101;
    //我的订单type
    public final static int TYPE0 = 0;
    public final static int TYPE1 = 1;
    public final static int TYPE2 = 2;
    public final static int TYPE3 = 3;
    public final static int TYPE4 = 4;
    private Listener listener = null;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private String strMessage = "数据请求失败，请检查网络！";
    private Gson gson = new Gson();
    private Message msg = Message.obtain();

}
