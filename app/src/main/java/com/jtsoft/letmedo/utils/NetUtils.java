package com.jtsoft.letmedo.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2017/6/23.
 * 判断是否有网络
 */

public class NetUtils {

    public static boolean checkNetWork(Context context){
                try{
                        ConnectivityManager connectactivity = (ConnectivityManager)context.
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                        if(connectactivity != null){
                //                获知网络管理的对象
                            NetworkInfo info = connectactivity.getActiveNetworkInfo();
                 //                判断当前网络是否已经连接
                                if(info.getState() == NetworkInfo.State.CONNECTED){
                                         return true ;
                                     }
                            }
                    }
                 catch (Exception e) {
                         // TODO: handle exception
                     }
                 return false ;
             }
}
