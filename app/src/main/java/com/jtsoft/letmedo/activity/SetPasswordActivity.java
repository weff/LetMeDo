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
import com.jtsoft.letmedo.bean.JsonBeanSetPassword;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
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
 * Created by admin on 2017/8/2.
 * 设置登录密码
 */
public class SetPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView Back;
    private TextView Tittle;
    private TextView Edit;
    private Button mSet;
    private EditText etPassword;
    private EditText et2Password;
    private String strJson;
    private String strToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpassword);
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
        Tittle.setText(R.string.settingpassword);
//        strToken = manager.getToken();
      strToken = SharedpreferencesManager.getToken();
        Log.e("TOKEN", "TOKEN/////" + strToken);
        Back.setOnClickListener(this);
        Edit.setVisibility(View.INVISIBLE);
        mSet.setOnClickListener(this);
    }

    private void initView() {
        //返回按钮
        Back = (ImageView) findViewById(R.id.back_press);
        //标题控件
        Tittle = (TextView) findViewById(R.id.title_name);
        //编辑控件
        Edit = (TextView) findViewById(R.id.edit);
        //设置控件
        mSet = (Button) findViewById(R.id.set);
        //输入密码控件
        etPassword = (EditText) findViewById(R.id.etpassword);
        //再次输入密码控件
        et2Password = (EditText) findViewById(R.id.et2password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set:
                //保存密码，然后跳到首页
                String ETPassword = etPassword.getText().toString().trim();
                String ET2Password = et2Password.getText().toString().trim();
                if (!ValidateUtil.isPwd(ETPassword)) {
                    Toast.makeText(this, "请输入6-15位的密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!ET2Password.equals(ETPassword)) {
                    Toast.makeText(this, "两次密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Request request = new Request.Builder().url(Constant.CONSTANT + "/setLoginPassword.do?password=" + etPassword + "&token=" + strToken).build();
                    OkHttpClient client = new OkHttpClient();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showShort(SetPasswordActivity.this, "网络请求失败");
                                    return;
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String strJson = response.body().string();
                            Log.e("JSON", "JSON---->" + strJson);
                            Gson gson = new Gson();
                            final JsonBeanSetPassword jsonBeanSetPassword = gson.fromJson(strJson, JsonBeanSetPassword.class);
                            if (jsonBeanSetPassword.getCode() == NetWorkUtils.CODE_SUCCESS) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtil.showShort(SetPasswordActivity.this, "设置密码成功");
//                                        manager.STATE_LOGIN = SharedpreferencesManager.LOGIN;
//                                        manager.saveIsLogin(true);
                                        SharedpreferencesManager.STATE_LOGIN = SharedpreferencesManager.LOGIN;
                                        SharedpreferencesManager.saveIsLogin(true);
                                        Intent intent = new Intent(SetPasswordActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtil.showShort(SetPasswordActivity.this, (String) jsonBeanSetPassword.getMessage());
                                        return;
                                    }
                                });

                            }
                        }
                    });
                }
                break;
            case R.id.back_press:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
