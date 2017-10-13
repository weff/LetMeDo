package com.jtsoft.letmedo.spUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.jtsoft.letmedo.utils.NetWorkUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/6/22.
 * SP存储数据
 */

public class SharedpreferencesManager {

    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;
    public static String STATE_LOGIN;
    public final static String LOGIN = "login";
    public final static String FAST_LOGIN = "fast_login";
    static List<String> userinfo = new ArrayList<>();

    public SharedpreferencesManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(NetWorkUtils.FLAG_SHAREDPREFERENCES, Context.MODE_PRIVATE);
        Log.e("TAG", "mSharedPreferences---------->" + mSharedPreferences);
    }

    /**
     * 是否是第一次显示
     **/
    public void saveFirstShow(boolean first) {
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(NetWorkUtils.FLAG_FIRSTSHOW, first);
        mEditor.apply();
    }

    public boolean getFirstShow() {
        return mSharedPreferences.getBoolean(NetWorkUtils.FLAG_FIRSTSHOW, false);
    }

    /**
     * 存储Token
     **/
    public static void saveToken(String token) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(NetWorkUtils.FLAG_TOKEN, token);
        mEditor.apply();
    }

    public static String getToken() {
        return mSharedPreferences.getString(NetWorkUtils.FLAG_TOKEN, "");
    }
    /**
     * 是否已登陆
     */
    public static void saveIsLogin(boolean isLogin) {
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(NetWorkUtils.FLAG_ISLOGIN, isLogin);
        mEditor.apply();
    }

    public static boolean isLogin() {
        return mSharedPreferences.getBoolean(NetWorkUtils.FLAG_ISLOGIN, false);
    }


    //保存用户信息

    public static void saveUserphone(String userPhone) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(NetWorkUtils.FLAG_USERPHONE, userPhone);
        mEditor.apply();
    }

    public static String getUserphone() {
        return mSharedPreferences.getString(NetWorkUtils.FLAG_USERPHONE, "");
    }

    public static void saveUserPassword(String userPassword) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(NetWorkUtils.FLAG_USERPASSWORD, userPassword);
        mEditor.apply();
    }

    public static String getUserPassword() {

        return mSharedPreferences.getString(NetWorkUtils.FLAG_USERPASSWORD, "");
    }

    //存储购物车的商品数量
    public static void setCartNum(int cartNum) {
        mEditor = mSharedPreferences.edit();
        mEditor.putInt("cartNum",cartNum);
        mEditor.apply();
    }

    public static int getCartNum() {

        return mSharedPreferences.getInt("cartNum",-1);
    }

    public static void getuserinfo() {
//        userinfo.add(getUserphone());
//        userinfo.add(getUserPassword());
//        userinfo.add(getToken());
//        return userinfo;
        mSharedPreferences.edit().clear().commit();
    }

}
