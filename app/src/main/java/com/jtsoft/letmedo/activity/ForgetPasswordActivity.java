package com.jtsoft.letmedo.activity;

import android.content.Intent;
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
import com.jtsoft.letmedo.bean.JsonBeanLoginState;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.bean.JsonBeanForgetPassword;
import com.jtsoft.letmedo.bean.JsonBeanForgetPasswordCode;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;
import com.jtsoft.letmedo.utils.ValidateUtil;
import com.jtsoft.letmedo.webinterface.NetWorkInterface;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/6/21.
 * 忘记密码页面
 */

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView Back;
    private TextView Title;
    private TextView Edit;
    private EditText Phone;
    private Button GetCode;
    private EditText Code;
    private EditText Password;
    private EditText AgainPassword;
    private Button Confirm;
    private String strToken;
    private SharedpreferencesManager manager;
    private TimeCount timer;
    private String getCode;
    private String userPhoneNumber;
    private String userPassword;
    private String userCode;
    private String strJson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_froget);
        //控件初始化
        initView();
        //数据初始化
        initData();
    }

    private void initData() {
        Back.setOnClickListener(this);
        Title.setText(R.string.forgetpassword);
        Edit.setVisibility(View.INVISIBLE);
        GetCode.setOnClickListener(this);
        Confirm.setOnClickListener(this);
        timer = new TimeCount(60000,1000);
    }

    private void initView() {
        //返回控件
        Back = (ImageView) findViewById(R.id.back_press);
        Title = (TextView) findViewById(R.id.title_name);
        //标题控件
        //编辑控件
        Edit = (TextView) findViewById(R.id.edit);
        //手机号码控件
        Phone = (EditText) findViewById(R.id.phonenumber);
        //获取验证码控件
        GetCode = (Button) findViewById(R.id.get_code);
        //输入验证码控件
        Code = (EditText) findViewById(R.id.code);
        //输入密码控件
        Password = (EditText) findViewById(R.id.password);
        //再次确认输入密码控件
        AgainPassword = (EditText) findViewById(R.id.againpassword);
        //确认控件
        Confirm = (Button) findViewById(R.id.confirm);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_press:
                finish();
                break;
            case R.id.get_code:
                //获取验证码
                doGetCode();
                break;
            case R.id.confirm:
                userPhoneNumber = Phone.getText().toString().trim();
                userPassword = Password.getText().toString().trim();
                String User2Password = AgainPassword.getText().toString().trim();
                userCode = Code.getText().toString().trim();
                if (!ValidateUtil.isMobileNO(userPhoneNumber)) {
                    Toast.makeText(this,R.string.unusetelephone,Toast.LENGTH_SHORT).show();
                }else if (userCode.equals("")) {
                    Toast.makeText(this,R.string.notnullcode,Toast.LENGTH_SHORT).show();
                }else if (userPassword.equals("")) {
                    Toast.makeText(this,R.string.notnullnewpassword,Toast.LENGTH_SHORT).show();
                }else if (User2Password.equals("")) {
                    Toast.makeText(this,R.string.surenewpassword,Toast.LENGTH_SHORT).show();
                }else if (!User2Password.equals(userPassword)) {
                    Toast.makeText(this,R.string.samepassword,Toast.LENGTH_SHORT).show();
                    AgainPassword.setText("");
                }else {
//                    netWorkUtils.getForgetPassword(userPhoneNumber, userPassword, userCode);

                    Request request = new Request.Builder().url(Constant.CONSTANT + "/forgetLoginPassword.do?code=" + userCode + "&newPassword=" + userPassword + "&mobile=" + userPhoneNumber).build();
                    OkHttpClient client = new OkHttpClient();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showShort(ForgetPasswordActivity.this, "网络请求失败");
                                    return;
                                }
                            });
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String strJson = response.body().string();
                            Log.e("JSON", "JSON---->" + strJson);
                            Gson gson = new Gson();
                            final JsonBeanForgetPassword jsonBeanForgetPassword = gson.fromJson(strJson, JsonBeanForgetPassword.class);
                            if (jsonBeanForgetPassword.getCode() == NetWorkUtils.CODE_SUCCESS) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtil.showShort(ForgetPasswordActivity.this, "重新设置密码成功");
//                                        manager.STATE_LOGIN = SharedpreferencesManager.LOGIN;
//                                        manager.saveIsLogin(true);
                                        SharedpreferencesManager.STATE_LOGIN = SharedpreferencesManager.LOGIN;
                                        SharedpreferencesManager.saveIsLogin(true);
                                        Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtil.showShort(ForgetPasswordActivity.this, (String) jsonBeanForgetPassword.getMessage());
                                        return;
                                    }
                                });
                            }
                        }
                    });
                }
                break;
            default:
                break;
        }
    }

    private void doGetCode() {
        String userPhone = Phone.getText().toString().trim();
        if (!ValidateUtil.isMobileNO(Phone.getText().toString().trim())) {
            Toast.makeText(this,R.string.unusetelephone,Toast.LENGTH_SHORT).show();
            return;
        }
        if (timer != null) {
            timer.cancel();
        }
        //获取短信验证码
//            netWorkUtils.getForgetPasswordCode(userPhone);

        Request request = new Request.Builder().url(Constant.CONSTANT + "/sendVerifyCode.do?mobile=" + userPhone).build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(ForgetPasswordActivity.this, "网络请求失败");
                        return;
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Log.e("JSON", "JSON---->" + strJson);
                Gson gson = new Gson();
                final JsonBeanForgetPasswordCode jsonBeanForgetPasswordCode = gson.fromJson(strJson, JsonBeanForgetPasswordCode.class);
                if (jsonBeanForgetPasswordCode.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(ForgetPasswordActivity.this, "获取验证码成功");
                            return;
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(ForgetPasswordActivity.this, (String) jsonBeanForgetPasswordCode.getMessage());
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
