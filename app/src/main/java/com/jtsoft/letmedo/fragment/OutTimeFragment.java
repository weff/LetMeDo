package com.jtsoft.letmedo.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.adapter.OutTimeAdapter;
import com.jtsoft.letmedo.bean.OutTimeBean;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/13.
 * 过期优惠券页面
 */

public class OutTimeFragment extends LazyLoadFragment{

    private Context context;
    private View view;
    private ListView listView;
    private SharedpreferencesManager manager;
    private List<OutTimeBean.ResponseBean.CouponListBean> couponList;
    private RelativeLayout layout;
    //页面加载
    @Override
    protected int setContentView() {
        context = getActivity();
        return R.layout.fragment_outtime;
    }

    //数据加载
    @Override
    protected void lazyLoad() {
        //控件初始化
        initView();
        //数据初始化
        initData();
    }

    private void initData() {
        String strToken = SharedpreferencesManager.getToken();
        initOutCoupon(strToken,3);
    }

    private void initOutCoupon(String strToken, int i) {
        final Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/listCoupon.do?type=" + i + "&token=" + strToken)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(getActivity(),R.string.no_net +"");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                OutTimeBean outTimeBean = gson.fromJson(strJson, OutTimeBean.class);
                if (outTimeBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    couponList = outTimeBean.getResponse().getCouponList();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (couponList.size() == 0) {
                                listView.setVisibility(View.INVISIBLE);
                            }else {
                                listView.setVisibility(View.VISIBLE);
                                OutTimeAdapter adapter = new OutTimeAdapter(context,couponList);
                                listView.setAdapter(adapter);
                            }
                        }
                    });
                }else {
                    final String message = (String) outTimeBean.getMessage();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(getActivity(), message);
                            return;
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        //listview控件
        listView = (ListView) getContentView().findViewById(R.id.listview);
        layout = (RelativeLayout) getContentView().findViewById(R.id.coupon_layout);
    }
}
