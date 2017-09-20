package com.jtsoft.letmedo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.MyAccountBean;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2017/9/7.
 * 我的账户页面
 */

public class MyAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView Back;
    private TextView Tittle;
    private TextView Edit;
    private TextView mAmount;
    private TextView mMark;
    private RelativeLayout mRecharge1;
    private RelativeLayout mRecharge2;
    private RelativeLayout mRecharge3;
    private Button mBtRecharge;
    private String strToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
        //控件初始化
        initView();
        //数据初始化
        initData();
    }
    //  数据初始化
    private void initData() {
        //网络请求，获取用户余额信息
        insponse(strToken);
    }

    private void insponse(String strToken) {
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/getDeposit.do?token=" + strToken)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(MyAccountActivity.this,"网络请求异常");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final MyAccountBean myAccountBean = gson.fromJson(strJson, MyAccountBean.class);
                if (myAccountBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    //获取用户余额
                    final double preDeposit = myAccountBean.getResponse().getDeposit().getPreDeposit();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAmount.setText(preDeposit +"元");
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(MyAccountActivity.this, (String) myAccountBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    /*
    * 数据初始化
    * */
    private void initView() {
        //返回控件
        Back = (ImageView) findViewById(R.id.back_press);
        Back.setOnClickListener(this);
        //标题控件
        Tittle = (TextView) findViewById(R.id.title_name);
        Tittle.setText("我的账户");
        //编辑控件
        Edit = (TextView) findViewById(R.id.edit);
        Edit.setVisibility(View.INVISIBLE);
        //金额显示控件
        mAmount = (TextView) findViewById(R.id.my_account);
        //充值金额说明
        mMark = (TextView) findViewById(R.id.mark);
        mMark.setOnClickListener(this);
        //充值金额100元控件
        mRecharge1 = (RelativeLayout) findViewById(R.id.recharge1);
        mRecharge1.setOnClickListener(this);
        //充值金额500元控件
        mRecharge2 = (RelativeLayout) findViewById(R.id.recharge2);
        mRecharge2.setOnClickListener(this);
        //充值金额1000元控件
        mRecharge3 = (RelativeLayout) findViewById(R.id.recharge3);
        mRecharge3.setOnClickListener(this);
        //充值按钮控件
        mBtRecharge = (Button) findViewById(R.id.recharge_button);
        mBtRecharge.setOnClickListener(this);

        strToken = SharedpreferencesManager.getToken();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_press:
                finish();
                break;
            case R.id.mark:
                //弹出dialog
                showMarkDialog();
                break;
            case R.id.recharge1:
                //TODO
                break;
            case R.id.recharge2:
                //TODO
                break;
            case R.id.recharge3:
                //TODO
                break;
            case R.id.recharge_button:
                //弹出popup 进行充值
                //TODO
                break;
        }
    }

    private void showMarkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("九月推荐")
                .setMessage("xxxxx")
                .setNegativeButton("取消",null)
                .show();
    }
}
