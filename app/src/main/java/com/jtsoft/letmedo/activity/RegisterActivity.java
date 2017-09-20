package com.jtsoft.letmedo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jtsoft.letmedo.MainActivity;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.bean.JsonBeanUserRegister;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;
import com.jtsoft.letmedo.utils.ValidateUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/6/21.
 * 快捷注册页面
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Register;
    private TextView GetCode;
    private EditText Telphone;
    private EditText Code;
    private ImageView Back;
    private TextView Title;
    private TextView Edit;
    private TextView Agreement;


    //计时器
    private TimeCount timer;
    private String userPhone;
    private String userCode;
    private String strPhone;
    private String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //控件初始化
        initView();
        //数据初始化
        initData();
    }

    private void initData() {
        Title.setText(R.string.fastregister);
        Title.setTextColor(Color.parseColor("#008000"));
        Telphone.requestFocus();
        Back.setOnClickListener(this);
        GetCode.setOnClickListener(this);
        Register.setOnClickListener(this);
        timer = new TimeCount(60000, 1000);
    }

    private void initView() {
        //返回按键控件
        Back = (ImageView) findViewById(R.id.back_press);
        //标题控件
        Title = (TextView) findViewById(R.id.title_name);
        //编辑控件
        Edit = (TextView) findViewById(R.id.edit);
        Edit.setVisibility(View.INVISIBLE);
        //注册控件
        Register = (Button) findViewById(R.id.register);
        //获取验证码控件
        GetCode = (TextView) findViewById(R.id.get_code);
        //电话号码控件
        Telphone = (EditText) findViewById(R.id.telphone);
        //输入验证码控件
        Code = (EditText) findViewById(R.id.code);
        //同意注册控件
//        Agreement = (TextView) findViewById(R.id.agreement);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_press:
                finish();
                break;
            case R.id.get_code:
                strPhone = Telphone.getText().toString().trim();
                if (ValidateUtil.isMobileNO(Telphone.getText().toString())) {
                    //获取验证码
                    doGetCode();
                } else {
                    Toast.makeText(this, R.string.unusecode, Toast.LENGTH_SHORT).show();
                }
                break;
            //注册
            case R.id.register:
                userPhone = Telphone.getText().toString().trim();
                userCode = Code.getText().toString().trim();
                if (!ValidateUtil.isMobileNO(strPhone)) {
                    Toast.makeText(this, R.string.unusetelephone, Toast.LENGTH_SHORT).show();
                } else if (userCode.equals("")) {
                    Toast.makeText(this, R.string.supplycode, Toast.LENGTH_SHORT).show();
                } else {
                    Request request = new Request.Builder().url(Constant.CONSTANT + "/loginByMobileVerify.do?mobile=" + userPhone + "&code=" + userCode).build();
                    OkHttpClient client = new OkHttpClient();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String strJson = response.body().string();
                            Gson gson = new Gson();
                            final JsonBeanUserRegister jsonBeanUserRegister = gson.fromJson(strJson, JsonBeanUserRegister.class);
                            if (jsonBeanUserRegister.getCode() == NetWorkUtils.CODE_SUCCESS) {
                                token = jsonBeanUserRegister.getResponse().getToken();
                                Log.e("TAG","token--=====///" + token);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtil.showShort(RegisterActivity.this,"注册成功");
                                        SharedpreferencesManager.STATE_LOGIN = SharedpreferencesManager.FAST_LOGIN;
                                        SharedpreferencesManager.saveIsLogin(true);
                                        SharedpreferencesManager.saveToken(token);
                                        SharedpreferencesManager.saveUserphone(userPhone);
                                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtil.showShort(RegisterActivity.this, (String) jsonBeanUserRegister.getMessage());
                                    }
                                });
                            }
                        }
                    });
                }
                break;
//            case R.id.agree:
//                break;
            default:
                break;
        }
    }

    private void doGetCode() {
        if (!ValidateUtil.isMobileNO(Telphone.getText().toString())) {
            Toast.makeText(this, R.string.unusetelephone, Toast.LENGTH_SHORT).show();
            return;
        }
        if (timer != null) {
            timer.cancel();
        }
        Request request = new Request.Builder().url(Constant.CONSTANT + "/sendVerifyCode.do?mobile=" + strPhone).build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(RegisterActivity.this,"网络请求错误");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final JsonBeanUserRegister jsonBeanUserRegister = gson.fromJson(strJson, JsonBeanUserRegister.class);
                if (jsonBeanUserRegister.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(RegisterActivity.this,"获取验证码成功");
                            return;
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(RegisterActivity.this, (String) jsonBeanUserRegister.getMessage());
                            return;
                        }
                    });
                }
            }
        });
        timer.start();
    }


    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            GetCode.setText(R.string.resettest);
            GetCode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            GetCode.setClickable(false);
            GetCode.setText(millisUntilFinished / 1000 + "秒");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
