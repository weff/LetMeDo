package com.jtsoft.letmedo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.jtsoft.letmedo.custom.EmptyLayout;
import com.jtsoft.letmedo.utils.NetUtils;

/**
 * Created by admin on 2017/9/21.
 */

public class BaseActivity extends AppCompatActivity{

    private EmptyLayout mEmpty;
    private LinearLayout mFrame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
    }
    private Handler mHandler = new Handler();

    private void initView() {
        mEmpty = (EmptyLayout) findViewById(R.id.empty);
        mFrame = (LinearLayout) findViewById(R.id.frame);
        mEmpty.addView(mFrame);
        mEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新加载数据
                loadData();
            }
        });
    }

    private void loadData() {
        //模拟加载数据
        mEmpty.showLoading("正在加载，请稍后");
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (NetUtils.checkNetWork(BaseActivity.this)) {
                    // 失败
                    mEmpty.showError("加载失败，点击重新加载"); // 显示失败
                    mFrame.setVisibility(View.INVISIBLE);
                } else {
                    // 成功
                    mEmpty.showSuccess();
                    mFrame.setVisibility(View.VISIBLE);
                }
            }
        }, 3000);
    }
}
