package com.jtsoft.letmedo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jtsoft.letmedo.MainActivity;
import com.jtsoft.letmedo.R;

/**
 * Created by Dragon on 2017/7/17.
 * 个人中心页面
 */
public class PersonalActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView Back;
    private TextView Tittle;
    private TextView Edit;
    private RelativeLayout mPersonalMsg;
    private RelativeLayout mCountSafe;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity);
        //控件初始化
        initView();
        //数据初始化
        initData();
    }

    private void initData() {
        Back.setOnClickListener(this);
        Tittle.setText(R.string.personal);
        Edit.setVisibility(View.INVISIBLE);
        mPersonalMsg.setOnClickListener(this);
        mCountSafe.setOnClickListener(this);
    }

    private void initView() {
        //返回控件
        Back = (ImageView) findViewById(R.id.back_press);
        //标题控件
        Tittle = (TextView) findViewById(R.id.title_name);
        //编辑控件
        Edit = (TextView) findViewById(R.id.edit);
        //个人信息布局控件
        mPersonalMsg = (RelativeLayout) findViewById(R.id.personal_message);
        //账号安全布局控件
        mCountSafe = (RelativeLayout) findViewById(R.id.count_safe);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.back_press:
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("myfragment",4);
                startActivity(intent);
                finish();
                break;
            case R.id.personal_message:
                //跳转到个人信息页面
                intent = new Intent(this,PersonalMsgActivity.class);
                startActivity(intent);
                break;
            case R.id.count_safe:
                //跳转到账号安全页面
                intent = new Intent(this,AccountSafeActivity.class);
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
