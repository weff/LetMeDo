package com.jtsoft.letmedo.utils;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/22.
 * 网络请求工具页面
 */

public class OKHttpUtil {
    private static OkHttpClient client;

    private OKHttpUtil() {}

    public static OkHttpClient getInstance() {
        if (client == null) {
            synchronized (OKHttpUtil.class) {
                if (client == null)
                    client = new OkHttpClient();
            }
        }
        return client;
    }

    /**
     * Get请求
     *
     * @param url
     * @param callback
     */
    public static void doGet(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }

    /**
     * post请求
     *
     * @param url
     * @param value
     * @return
     * @throws IOException
     */
    public static <T> String post(String url, T value) {
        Gson gson = new Gson();
        String json = gson.toJson(value);
//        String json = JSON.toJSONString(value);
        // 携带表单参数
        FormBody formBody = new FormBody.Builder().add("params", "" + json).build();
        Request request = new Request.Builder().url(getAbsoluteUrl(url)).post(formBody).build();
        Response response;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return Constant.CONSTANT + relativeUrl;
    }
}
