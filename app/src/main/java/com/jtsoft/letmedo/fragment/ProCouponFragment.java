package com.jtsoft.letmedo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.activity.CouponActivity;
import com.jtsoft.letmedo.adapter.ProCouponAdapter;
import com.jtsoft.letmedo.bean.OutTimeBean;
import com.jtsoft.letmedo.bean.ProCouponBean;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/13.
 * 已使用优惠券页面
 */

public class ProCouponFragment extends Fragment{

    private Context context;
    private View view;
    private ListView listView;
    private List<OutTimeBean.ResponseBean.CouponListBean> couponList;

    @Nullable
    //视图初始化
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        view = LayoutInflater.from(context).inflate(R.layout.fragment_procoupon, container, false);
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
        String strToken = SharedpreferencesManager.getToken();
        initDoneCoupon(strToken,2);
    }

    private void initDoneCoupon(String strToken, int i) {
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
                        ToastUtil.showShort(context,"网络异常");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final ProCouponBean proCouponBean = gson.fromJson(strJson, ProCouponBean.class);
                if (proCouponBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    couponList = proCouponBean.getResponse().getCouponList();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (couponList.size() == 0) {
                                listView.setVisibility(View.INVISIBLE);
                            }else {
                                listView.setVisibility(View.VISIBLE);
                                ProCouponAdapter adapter = new ProCouponAdapter(context,couponList);
                                listView.setAdapter(adapter);
                            }
                        }
                    });
                }else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(context, (String) proCouponBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        //lisstview控件
        listView = (ListView) view.findViewById(R.id.listview);
    }
}
