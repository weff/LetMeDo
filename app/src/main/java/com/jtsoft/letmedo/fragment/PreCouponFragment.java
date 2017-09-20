package com.jtsoft.letmedo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.jtsoft.letmedo.MainActivity;
import com.jtsoft.letmedo.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jtsoft.letmedo.activity.CouponActivity;
import com.jtsoft.letmedo.adapter.PreCouponAdapter;
import com.jtsoft.letmedo.bean.PreCouponBean;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/13.
 * 未使用优惠券页面
 */

public class PreCouponFragment extends Fragment {

    private Context context;
    private View view;
    private ListView listView;
    private SharedpreferencesManager manager;
    private String strToken;
    private RelativeLayout relayout;
    private List<PreCouponBean.ResponseBean.CouponListBean> couponList;

    @Nullable
    //视图初始化
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        view = LayoutInflater.from(context).inflate(R.layout.fragment_precoupon, container, false);
        return view;
    }

    //控件、数据初始化
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //控件初始化
        initView();
        //数据初始化
        initData();
    }

    private void initData() {
        strToken = SharedpreferencesManager.getToken();
        Log.e("TAG", "strTOken========>" + strToken);
        //获取可用优惠券
        initGetCoupon(strToken, 1);
    }

    private void initGetCoupon(String strToken, int i) {
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/listCoupon.do?type=" + i + "&token=" + strToken)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(context, "网络异常");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final PreCouponBean preCouponBean = gson.fromJson(strJson, PreCouponBean.class);
                if (preCouponBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    couponList = preCouponBean.getResponse().getCouponList();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (couponList.size() == 0) {
                                relayout.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.INVISIBLE);
                            } else {
                                relayout.setVisibility(View.INVISIBLE);
                                listView.setVisibility(View.VISIBLE);
                                PreCouponAdapter adapter = new PreCouponAdapter(context, couponList);
                                listView.setAdapter(adapter);
                            }
                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(context, (String) preCouponBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        //listview控件
        relayout = (RelativeLayout) view.findViewById(R.id.coupon_null);
        listView = (ListView) view.findViewById(R.id.listview);
    }
}
