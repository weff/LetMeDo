package com.jtsoft.letmedo.activity;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
                        ToastUtil.showShort(MyAccountActivity.this, R.string.no_net + "");
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
                            mAmount.setText(preDeposit + "元");
                        }
                    });
                } else {
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

    //给个标志，如果充值金额被选中，就给a赋值1,2,3，否则就是没选中
    int a = 0;

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
            //充值金额100被选中
            case R.id.recharge1:
                a = 1;
                mRecharge2.setBackgroundResource(R.drawable.square_shape_normal);
                mRecharge3.setBackgroundResource(R.drawable.square_shape_normal);
                //这TM不判断充值金额是否已经选中
                mRecharge1.setBackgroundResource(R.drawable.square_shape_select);
                break;
            //充值金额500被选中
            case R.id.recharge2:
                a = 2;
                mRecharge1.setBackgroundResource(R.drawable.square_shape_normal);
                mRecharge3.setBackgroundResource(R.drawable.square_shape_normal);
                mRecharge2.setBackgroundResource(R.drawable.square_shape_select);
                break;
            //充值金额1000被选中
            case R.id.recharge3:
                a = 3;
                mRecharge1.setBackgroundResource(R.drawable.square_shape_normal);
                mRecharge2.setBackgroundResource(R.drawable.square_shape_normal);
                mRecharge3.setBackgroundResource(R.drawable.square_shape_select);
                break;
            case R.id.recharge_button:
                //先判断充值金额是否已选
                //如果没有选择充值金额，就会提醒选择充值金额
                if (a == 1 || a == 2 || a == 3) {
                    //已选择充值金额，就可以点击，继而弹出弹窗
                    //弹出popup 进行充值
                    showRechargeWindow();
                } else {
                    ToastUtil.showShort(MyAccountActivity.this, "请选择充值金额");
                    return;
                }

                break;
        }
    }

    //弹出充值弹窗
    private void showRechargeWindow() {
        View view = LayoutInflater.from(MyAccountActivity.this).inflate(R.layout.window_recharge, null);
        final PopupWindow popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.popupAnimation);
        popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(view);
        //控件初始化
        //单选付款按钮
        //微信选择按钮
         final CheckBox mWXCheckBox = (CheckBox) view.findViewById(R.id.wxCheckBox);
        //支付宝选择按钮
       final CheckBox mAliPayCheckBox = (CheckBox) view.findViewById(R.id.alipayCheckBox);
        //微信支付父控件
       RelativeLayout mWXRelay = (RelativeLayout) view.findViewById(R.id.relay2);
        //支付宝支付父控件
        RelativeLayout mAliRelay = (RelativeLayout) view.findViewById(R.id.relay1);
        //叉去按钮
      ImageView mCancle = (ImageView) view.findViewById(R.id.pay_cancel);
        mCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        //先对选择按钮进行判断是否选中，继而判断充值金额的数据（如果a=1,就是充值100；a=2，就是充值500；a=3，就是充值1000）
        //判断选择按钮是否
        //微信支付控件
        mWXRelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWXCheckBox.isChecked();
                mWXCheckBox.setVisibility(View.VISIBLE);
                mWXCheckBox.setChecked(true);
                mAliPayCheckBox.setChecked(false);
                mAliPayCheckBox.setPressed(false);
                mAliPayCheckBox.setFocusable(false);
                mAliPayCheckBox.setVisibility(View.INVISIBLE);
            }
        });
        //支付宝支付控件
        mAliRelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAliPayCheckBox.isChecked();
                mAliPayCheckBox.setVisibility(View.VISIBLE);
                mAliPayCheckBox.setChecked(true);
                mWXCheckBox.setChecked(false);
                mWXCheckBox.setPressed(false);
                mWXCheckBox.setFocusable(false);
                mWXCheckBox.setVisibility(View.INVISIBLE);
            }
        });
        //点击确定按钮,然后先判断充值金额的多少，再进行逻辑充值
        //确认支付按钮
       Button mConfrimPay = (Button)view.findViewById(R.id.confirm_pay);
        mConfrimPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mWXCheckBox.isChecked()) {
                    mWXCheckBox.setChecked(true);
                    mAliPayCheckBox.setChecked(false);
                    //接着就是微信支付逻辑
//                    WetChatPay(jsonBeanAliPayText.getSignOrderStr());

                } else if (mAliPayCheckBox.isChecked()) {
                    mAliPayCheckBox.setChecked(true);
                    mWXCheckBox.setChecked(false);
                    //支付宝支付逻辑
//                    AliPay(orderId,5,strToken);
                }
            }
        });

    }

    private void showMarkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("九月推荐")
                .setMessage("xxxxx")
                .setNegativeButton("取消", null)
                .show();
    }
}
