package com.jtsoft.letmedo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.adapter.UseCouponAdapter;
import com.jtsoft.letmedo.bean.GetUseCoupon;
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
 * Created by admin on 2017/9/4.
 * 可使用优惠券页面
 */

public class UseCouponActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    private List<GetUseCoupon.ResponseBean.CouponListBean> couponList;
    private String strToken;
    private double couponPrice;
    private Button mUse;
    private Intent intent;
    private ImageView Back;
    private TextView Tittle;
    private TextView Edit;
    private int mCouponReceiveId;
    private String couponReceiveId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usecoupon);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //控件初始化
        initView();
        //数据初始化
        initData();
    }

    private void initData() {
        Back.setOnClickListener(this);
        Tittle.setText("可使用优惠券");
        Edit.setText("不使用优惠券");
        Edit.setOnClickListener(this);
        intent = getIntent();
        double totalprice = intent.getDoubleExtra("totalprice", -1);
        int goodsId = intent.getIntExtra("goodsId",-1);
        Log.e("TAG",goodsId + "goods---id");
        //可用优惠券接口请求
        initGetCoupon(strToken,totalprice,goodsId);

    }

    private void initView() {
        Back = (ImageView) findViewById(R.id.back_press);
        Tittle = (TextView) findViewById(R.id.title_name);
        Edit = (TextView) findViewById(R.id.edit);
        mListView = (ListView) findViewById(R.id.listview);
        strToken = SharedpreferencesManager.getToken();
    }

    //获取可用优惠券
    private void initGetCoupon(String strToken,double totalprice,int goodsId) {
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/listCouponByOrderPrice.do?token=" + strToken + "&fullPrice=" + totalprice + "&goodsIds=" + goodsId)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(UseCouponActivity.this, R.string.no_net +"");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final GetUseCoupon getUseCoupon = gson.fromJson(strJson, GetUseCoupon.class);
                if (getUseCoupon.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    //可用优惠券的集合
                    couponList = getUseCoupon.getResponse().getCouponList();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (couponList.size() == 0) {
                                return;
                            }else {
                                UseCouponAdapter adapter = new UseCouponAdapter(UseCouponActivity.this, couponList);
                                mListView.setAdapter(adapter);
                                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        //优惠券ID
                                        mCouponReceiveId = couponList.get(position).getCouponReceiveId();
                                        couponReceiveId = mCouponReceiveId + "";
                                        //优惠券价格
                                        couponPrice = couponList.get(position).getCouponPrice();
                                        intent = new Intent();
                                        intent.putExtra("couponReceiveId", couponReceiveId);
                                        intent.putExtra("couponPrice",couponPrice);
                                        setResult(NetWorkUtils.USECOUPON_RESULT,intent);
                                        finish();
                                    }
                                });
                            }
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(UseCouponActivity.this, (String) getUseCoupon.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_press:
                finish();
                break;
            case R.id.edit:
                intent = new Intent();
//                couponReceiveId = "";
//                intent.putExtra("couponReceiveId",couponReceiveId);
                intent.putExtra("couponReceiveId","");
                intent.putExtra("couponPrice",0.0);
                setResult(NetWorkUtils.USECOUPON_RESULT,intent);
                finish();
                break;
        }
    }
}
