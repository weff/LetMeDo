package com.jtsoft.letmedo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
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
 * Created by Administrator on 2017/6/21.
 * 登录页面
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView Back;
    private TextView Title;
    private TextView Edit;
    private EditText EtPhoneNumber;
    private EditText EtPassword;
    private Button Login;
    private TextView JumpToRegister;
    private TextView JumpToForgetPassword;
    private String phoneNumber;
    private String password;
    private Intent intent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        Title.setText(R.string.login);
        //隐藏编辑控件
        Edit.setVisibility(View.INVISIBLE);
        JumpToRegister.setText(R.string.fastregister);
        JumpToForgetPassword.setText(R.string.forgetpassword);
        EtPhoneNumber.setOnClickListener(this);
        EtPassword.setOnClickListener(this);
        Login.setOnClickListener(this);
        JumpToRegister.setOnClickListener(this);
        JumpToForgetPassword.setOnClickListener(this);

    }

    private void initView() {
        //返回按键控件
        Back = (ImageView) findViewById(R.id.back_press);
        //标题控件
        Title = (TextView) findViewById(R.id.title_name);
        //编辑页面
        Edit = (TextView) findViewById(R.id.edit);
        //输入手机号控件
        EtPhoneNumber = (EditText) findViewById(R.id.phonenumber);
        //输入密码控件
        EtPassword = (EditText) findViewById(R.id.password);
        //登录控件
        Login = (Button) findViewById(R.id.login);
        //跳转到注册页面控件
        JumpToRegister = (TextView) findViewById(R.id.jumpToRegister);
        //跳转到忘记密码页面控件
        JumpToForgetPassword = (TextView) findViewById(R.id.jumpToForgetPasswd);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.back_press:
                finish();
                break;
            //登录
            case R.id.login:
                phoneNumber = EtPhoneNumber.getText().toString().trim();
                password = EtPassword.getText().toString().trim();
                if (phoneNumber.length() == 0 && phoneNumber.equals("")) {
                    Toast.makeText(this, R.string.notnulltelephone, Toast.LENGTH_SHORT).show();
                }
                if (password.length() == 0 && password.equals("")) {
                    Toast.makeText(this, R.string.notnullpassword, Toast.LENGTH_SHORT).show();
                } else {
                    Request request = new Request.Builder().url(Constant.CONSTANT + "/login.do?userName=" + phoneNumber + "&password=" + password).build();
                    OkHttpClient client = new OkHttpClient();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showShort(LoginActivity.this, "网络请求失败");
                                    return;
                                }
                            });
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String strJson = response.body().string();
                            Log.e("JSON", "JSON---->" + strJson);
                            Gson gson = new Gson();
                            final JsonBeanLoginState jsonBeanLoginState = gson.fromJson(strJson, JsonBeanLoginState.class);
                            final String strToken = jsonBeanLoginState.getResponse().getToken();
                            if (jsonBeanLoginState.getCode() == NetWorkUtils.CODE_SUCCESS) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtil.showShort(LoginActivity.this, "登录成功");
                                        SharedpreferencesManager.STATE_LOGIN = SharedpreferencesManager.LOGIN;
                                        SharedpreferencesManager.saveIsLogin(true);
                                        SharedpreferencesManager.saveToken(strToken);
                                        SharedpreferencesManager.saveUserphone(phoneNumber);
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtil.showShort(LoginActivity.this, (String) jsonBeanLoginState.getMessage());
                                        return;
                                    }
                                });
                            }
                        }
                    });
                }
                break;
            //跳转到快捷注册页面
            case R.id.jumpToRegister:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            //跳转到忘记密码页面
            case R.id.jumpToForgetPasswd:
                intent = new Intent(this, ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }


    @Override
    public void onBackPressed() {
        intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
