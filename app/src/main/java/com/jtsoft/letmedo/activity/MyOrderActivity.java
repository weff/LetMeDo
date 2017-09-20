package com.jtsoft.letmedo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.adapter.ViewPagerAdapter;
import com.jtsoft.letmedo.fragment.DoneFragment;
import com.jtsoft.letmedo.fragment.MySelfFragment;
import com.jtsoft.letmedo.fragment.PreGetGoodsFragment;
import com.jtsoft.letmedo.fragment.PrePayFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/6/25.
 * 我的订单页面
 */

public class MyOrderActivity extends AppCompatActivity{

    private ImageView Back;
    private TextView Title;
    private TextView Edit;
    private TabLayout TabLayout;
    private ViewPager ViewPager;
    private PrePayFragment prePayFragment;
    private PreGetGoodsFragment preGetGoodsFragment;
    private DoneFragment doneFragment;
    private MySelfFragment mySelfFragment;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        //控件初始化
        initView();
        //数据初始化
        initData();
    }

    private void initData() {
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Title.setText(R.string.myorder);
        //TabLayout与ViewPager关联
        TabLayout.setupWithViewPager(ViewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(fragmentList);
        ViewPager.setAdapter(viewPagerAdapter);
        ViewPager.setOffscreenPageLimit(4);
        Intent intent = getIntent();
        int myOrderActivity = intent.getIntExtra("MyOrderActivity", -1);
        if (myOrderActivity == 1) {
            TabLayout.getTabAt(0).select();
        }else if (myOrderActivity == 2) {
            TabLayout.getTabAt(1).select();
        }else if (myOrderActivity == 3) {
            TabLayout.getTabAt(2).select();
        }

    }


    private void initView() {
        //返回控件
        Back = (ImageView) findViewById(R.id.back_press);
        //标题控件
        Title = (TextView) findViewById(R.id.title_name);
        //编辑控件
        Edit = (TextView) findViewById(R.id.edit);
        Edit.setVisibility(View.INVISIBLE);
        //Tablayout布局控件
        TabLayout = (TabLayout) findViewById(R.id.tablayout);
        //viewpager控件
        ViewPager = (ViewPager) findViewById(R.id.view_pager);

        //定义一个集合，添加fragment
        fragmentList = new ArrayList<>();
        //对fragment进行判空，如果没有，需要创建
        if (prePayFragment == null) {
            prePayFragment = new PrePayFragment();
            fragmentList.add(prePayFragment);
        }
        if (preGetGoodsFragment == null) {
            preGetGoodsFragment = new PreGetGoodsFragment();
            fragmentList.add(preGetGoodsFragment);
        }
        if (doneFragment == null) {
            doneFragment = new DoneFragment();
            fragmentList.add(doneFragment);
        }
        if (mySelfFragment == null) {
            mySelfFragment = new MySelfFragment();
            fragmentList.add(mySelfFragment);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
