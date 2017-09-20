package com.jtsoft.letmedo.spUtil;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by admin on 2017/9/15.
 */

public class SPUtil {
    //存储的sharedpreferences文件名
    private static String FILE_NAME = "app_setting";
    private static SharedPreferences sp;

    public static boolean putString(Context context, String key, String value) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.edit().putString(key, value).commit();
    }
    public static boolean putInt(Context context, String key, int value) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.edit().putInt(key, value).commit();
    }
    public static boolean putLong(Context context, String key, long value) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.edit().putLong(key, value).commit();
    }
    public static boolean putFloat(Context context, String key, float value) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.edit().putFloat(key, value).commit();
    }
    public static boolean putBoolean(Context context, String key, boolean value) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.edit().putBoolean(key, value).commit();
    }

    public static String getString(Context context, String key) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public static String getString(Context context, String key, String defaultStr) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defaultStr);
    }

    public static int getInt(Context context, String key) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }
    public static int getInt(Context context, String key,int defaultInt) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultInt);
    }

    public static boolean getBoolean(Context context, String key) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }
    public static boolean getBoolean(Context context, String key,boolean defaultBool) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultBool);
    }

    public static float getFloat(Context context, String key) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getFloat(key, 0f);
    }
    public static float getFloat(Context context, String key,float defaultFloat) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getFloat(key, defaultFloat);
    }

    public static long getLong(Context context, String key) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, 0l);
    }
    public static long getLong(Context context, String key,long defaultLong) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defaultLong);
    }

}