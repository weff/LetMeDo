package com.jtsoft.letmedo.activity;

import android.content.Intent;
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
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.JsonBeanForgetPasswordCode;
import com.jtsoft.letmedo.bean.ModifyPasswordBean;
import com.jtsoft.letmedo.bean.ModifyPersonalMsgBean;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;
import com.jtsoft.letmedo.webinterface.NetWorkInterface;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2017/8/8.
 * 修改登录密码页面
 */
public class ModifyPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView Back;
    private TextView Tittle;
    private TextView Edit;
    private EditText mOldPassword;
    private EditText mNewPassword;
    private EditText mNew2Password;
    private Button mModifyPassword;

    private String etNewPassword;
    private String strToken;
    private String etOldPassword;
    private SharedpreferencesManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifypassword);
        manager = new SharedpreferencesManager(this);
        //控件初始化
        initView();
        //数据初始化
        initData();
    }

    private void initData() {
        Back.setOnClickListener(this);
        Tittle.setText(R.string.modifypassword);
        Edit.setVisibility(View.INVISIBLE);
        mModifyPassword.setOnClickListener(this);
        strToken = manager.getToken();
    }

    private void initView() {
        //返回控件
        Back = (ImageView) findViewById(R.id.back_press);
        //标题控件
        Tittle = (TextView) findViewById(R.id.title_name);
        //编辑控件
        Edit = (TextView) findViewById(R.id.edit);
        //旧密码控件
        mOldPassword = (EditText) findViewById(R.id.oldpassword);
        //新密码控件
        mNewPassword = (EditText) findViewById(R.id.newpassword);
        //再次确认信密码控件
        mNew2Password = (EditText) findViewById(R.id.new2password);
        //修改密码button控件
        mModifyPassword = (Button) findViewById(R.id.modify_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_press:
                finish();
                break;
            case R.id.modify_password:
                etOldPassword = mOldPassword.getText().toString().trim();
                etNewPassword = mNewPassword.getText().toString().trim();
                String etNew2Password = mNew2Password.getText().toString().trim();
                if (mOldPassword.getText().toString().trim().equals("")) {
                    ToastUtil.showShort(this, "请输入原密码");
                }
                if (mOldPassword.getText().toString().trim().equals(mNewPassword)) {
                    ToastUtil.showShort(this, "新密码和旧密码不能相同");
                }
                if (!etNewPassword.equals(etNew2Password)) {
                    Toast.makeText(this, "两次输入的新密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    mNew2Password.setText("");
                    return;
                } else {

                    Request request = new Request.Builder().url(Constant.CONSTANT + "/updateLoginPassword.do?password=" + etOldPassword +"&newPassword=" + etNewPassword + "&token=" + strToken).build();
                    OkHttpClient client = new OkHttpClient();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showShort(ModifyPasswordActivity.this, "网络请求失败");
                                    return;
                                }
                            });
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String strJson = response.body().string();
                            Log.e("JSON", "JSON---->" + strJson);
                            Gson gson = new Gson();
                            final ModifyPasswordBean modifyPasswordBean = gson.fromJson(strJson, ModifyPasswordBean.class);
                            if (modifyPasswordBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ModifyPasswordActivity.this, "重新修改密码成功", Toast.LENGTH_SHORT).show();
                                        ModifyPasswordActivity.this.finish();
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtil.showShort(ModifyPasswordActivity.this, modifyPasswordBean.getMessage());
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
}
