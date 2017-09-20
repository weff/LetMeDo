package com.jtsoft.letmedo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;

/**
 * Created by admin on 2017/8/8.
 * 账号安全页面
 */
public class AccountSafeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView Back;
    private TextView Tittle;
    private TextView Edit;
    private RelativeLayout mModifyPassword;
    private Button mQuit;
    private Intent intent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsafe);
        //控件初始化
        initView();
        //数据初始化
        initData();
    }

    private void initData() {
        Back.setOnClickListener(this);
        Tittle.setText(R.string.accountsafe);
        Edit.setVisibility(View.INVISIBLE);
        mModifyPassword.setOnClickListener(this);
        mQuit.setOnClickListener(this);
    }

    private void initView() {
        //返回控件
        Back = (ImageView) findViewById(R.id.back_press);
        //标题控件
        Tittle = (TextView) findViewById(R.id.title_name);
        //编辑控件
        Edit = (TextView) findViewById(R.id.edit);
        //修改密码控件
        mModifyPassword = (RelativeLayout) findViewById(R.id.modify_password);
        //退出控件
        mQuit = (Button) findViewById(R.id.quit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_press:
                finish();
                break;
            //修改密码
            case R.id.modify_password:
                intent = new Intent(this,ModifyPasswordActivity.class);
                startActivity(intent);
                break;
            //退出登录
            case R.id.quit:
                //删除个人信息，然后跳到登录页面
                SharedpreferencesManager.getuserinfo();
                SharedpreferencesManager.saveIsLogin(false);
                intent = new Intent(AccountSafeActivity.this,LoginActivity.class);
                startActivity(intent);
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
