package com.jtsoft.letmedo.fragment;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.adapter.PreCouponAdapter;
import com.jtsoft.letmedo.bean.PreCouponBean;
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
 * 未使用优惠券页面
 */

public class PreCouponFragment extends LazyLoadFragment {

    private Context context;
    private View view;
    private ListView listView;
    private String strToken;
    private RelativeLayout relayout;
    private List<PreCouponBean.ResponseBean.CouponListBean> couponList;
    private ImageView pic;
    private TextView mNoCoupon;

    //加载页面
    @Override
    protected int setContentView() {
        context = getActivity();
        return R.layout.fragment_precoupon;
    }

    //加载数据
    @Override
    protected void lazyLoad() {
        //控件初始化
        initView();
    }

    private void initData() {
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
                        ToastUtil.showShort(context, R.string.no_net +"");
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
                            if (couponList.size() == 0 && couponList == null) {
                                relayout.setVisibility(View.VISIBLE);
                                pic.setVisibility(View.VISIBLE);
                                mNoCoupon.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.INVISIBLE);
                            } else {
                                relayout.setVisibility(View.INVISIBLE);
                                pic.setVisibility(View.INVISIBLE);
                                mNoCoupon.setVisibility(View.INVISIBLE);
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
        relayout = (RelativeLayout) getContentView().findViewById(R.id.coupon_null);
        //没有优惠券的显示图片
        pic = (ImageView) getContentView().findViewById(R.id.picture);
        //没有优惠券的字体提示
        mNoCoupon = (TextView) getContentView().findViewById(R.id.nocoupon);
        //listview控件
        listView = (ListView) getContentView().findViewById(R.id.listview);
        strToken = SharedpreferencesManager.getToken();
        Log.e("TAG", "strTOken========>" + strToken);
        //数据初始化
        initData();
    }
}
