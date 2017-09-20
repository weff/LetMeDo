package com.jtsoft.letmedo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jtsoft.letmedo.R;

import java.util.ArrayList;
import java.util.List;

import com.jtsoft.letmedo.adapter.CouponAdapter;
import com.jtsoft.letmedo.fragment.OutTimeFragment;
import com.jtsoft.letmedo.fragment.PreCouponFragment;
import com.jtsoft.letmedo.fragment.PrePayFragment;
import com.jtsoft.letmedo.fragment.ProCouponFragment;

/**
 * Created by Administrator on 2017/7/13.
 * 我的优惠券页面
 */

public class CouponActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView Back;
    private TextView Tittle;
    private TextView Edit;
    private TabLayout TabLayout;
    private ViewPager viewPager;
    List<Fragment> fragmentList = new ArrayList<>();
    private PreCouponFragment preCouponFragment;
    private ProCouponFragment proCouponFragment;
    private OutTimeFragment outTimeFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        if (preCouponFragment == null) {
            preCouponFragment = new PreCouponFragment();
        }
        if (proCouponFragment == null) {
            proCouponFragment = new ProCouponFragment();
        }
        if (outTimeFragment == null) {
            outTimeFragment = new OutTimeFragment();
        }
        //控件初始化
        initView();
        //数据初始化
        initData();
    }

    private void initData() {
        Back.setOnClickListener(this);
        Tittle.setText(R.string.mycoupon);
        Edit.setVisibility(View.INVISIBLE);
        TabLayout.setupWithViewPager(viewPager);
        CouponAdapter adapter = new CouponAdapter(getSupportFragmentManager());
        fragmentList.add(preCouponFragment);
        fragmentList.add(proCouponFragment);
        fragmentList.add(outTimeFragment);
        adapter.addFragment(fragmentList);
        viewPager.setAdapter(adapter);
        //默认显示索引为0的页面
       TabLayout.getTabAt(0).select();
    }

    private void initView() {
        //返回按键控件
        Back = (ImageView)findViewById(R.id.back_press);
        //标题控件
        Tittle = (TextView)findViewById(R.id.title_name);
        //编辑控件
        Edit = (TextView)findViewById(R.id.edit);
        //页签控件
        TabLayout = (TabLayout)findViewById(R.id.tablayout);
        //viewpager控件
        viewPager = (ViewPager)findViewById(R.id.view_pager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
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
