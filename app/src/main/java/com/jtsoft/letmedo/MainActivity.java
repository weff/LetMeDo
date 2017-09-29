package com.jtsoft.letmedo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jtsoft.letmedo.activity.LoginActivity;
import com.jtsoft.letmedo.bean.ShopCartCountBean;
import com.jtsoft.letmedo.fragment.CategoryFragment;
import com.jtsoft.letmedo.fragment.FirstPageFragment;
import com.jtsoft.letmedo.fragment.MyFragment;
import com.jtsoft.letmedo.fragment.ShopCartsFragment;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout mainFragment;
    public static RadioButton mFristPage;
    private RadioButton mCategoryPage;
    public static RadioButton mShopCartsPage;
    private TextView mPayCount;
    private RadioButton mMyPage;
    private CategoryFragment categoryfragment;
    private ShopCartsFragment shopcartsfragment;
    private MyFragment myfragment;
    private FrameLayout mFrameFirst;
    private FrameLayout mFrameCategory;
    private FrameLayout mFrameShopCarts;
    private FrameLayout mFrameMy;
    public static TextView mTextFist;
    private TextView mTextCategory;
    public static TextView mTextShopCarts;
    private TextView mTextMy;
    private FragmentTransaction transaction;
    private long exitTime;
    private Intent intent;
    private RelativeLayout Design;
    private FirstPageFragment firstpagefragment;
    private SharedpreferencesManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
            intent = getIntent();
            int myfragment = intent.getIntExtra("myfragment", -1);
            if (myfragment == 4) {
                setMyfragment();
            } else if (myfragment == 3) {
                setShopCartFragment();
            }
    }

    /**
     * 更改购物车数量
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ShopCartCountBean bean) {
            if (bean != null) {
                if (bean.getNum() == 0) {
                    Design.setVisibility(View.INVISIBLE);
                }else {
                    Design.setVisibility(View.VISIBLE);
                    mPayCount.setText("" + bean.getNum());
                }

            }
    }

    private void initData() {
        //打开app 默认进入首页
        mFristPage.setChecked(true);
        mTextFist.setTextColor(getResources().getColor(R.color.button_bk));
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mainfragement, firstpagefragment)
                .add(R.id.mainfragement, categoryfragment)
                .add(R.id.mainfragement, shopcartsfragment)
                .add(R.id.mainfragement, myfragment)
                .show(firstpagefragment)
                .hide(categoryfragment)
                .hide(shopcartsfragment)
                .hide(myfragment)
                .commit();
    }

    //设置一个切换到首页的方法
    public void setFirstPageFragment() {
        //点击首页按钮时，给首页按钮设置被选中，其余按钮设置不被选中
        mFristPage.setChecked(true);
        mCategoryPage.setChecked(false);
        mShopCartsPage.setChecked(false);
        mMyPage.setChecked(false);
        getSupportFragmentManager().beginTransaction()
                .hide(categoryfragment)
                .hide(shopcartsfragment)
                .hide(myfragment)
                .show(firstpagefragment)
                .commit();
        mTextFist.setTextColor(getResources().getColor(R.color.button_bk));
        mTextCategory.setTextColor(Color.GRAY);
        mTextShopCarts.setTextColor(Color.GRAY);
        mTextMy.setTextColor(Color.GRAY);
    }

    //设置一个切换到我的页面的方法
    public void setMyfragment() {
        //我的按钮设置被选中，其余按钮设置不被选中
        mMyPage.setChecked(true);
        mFristPage.setChecked(false);
        mCategoryPage.setChecked(false);
        mShopCartsPage.setChecked(false);
        getSupportFragmentManager().beginTransaction()
                .hide(firstpagefragment)
                .hide(categoryfragment)
                .hide(shopcartsfragment)
                .show(myfragment)
                .commit();
        mTextMy.setTextColor(getResources().getColor(R.color.button_bk));
        mTextFist.setTextColor(Color.GRAY);
        mTextCategory.setTextColor(Color.GRAY);
        mTextShopCarts.setTextColor(Color.GRAY);
    }

    //设置一个切换到购物车页面的方法
    public void setShopCartFragment() {
        mShopCartsPage.setChecked(true);
        mFristPage.setChecked(false);
        mCategoryPage.setChecked(false);
        mMyPage.setChecked(false);
        getSupportFragmentManager().beginTransaction()
                .hide(firstpagefragment)
                .hide(categoryfragment)
                .hide(myfragment)
                .show(shopcartsfragment)
                .commit();
        mTextShopCarts.setTextColor(getResources().getColor(R.color.button_bk));
        mTextFist.setTextColor(Color.GRAY);
        mTextCategory.setTextColor(Color.GRAY);
        mTextMy.setTextColor(Color.GRAY);
    }


    private void initView() {
        //fragment控件
        mainFragment = (FrameLayout) findViewById(R.id.mainfragement);
        //首页父控件
        mFrameFirst = (FrameLayout) findViewById(R.id.rb1_parent);
        //首页文字控件
        mTextFist = (TextView) findViewById(R.id.text_button1);
        //首页控件
        mFristPage = (RadioButton) findViewById(R.id.firstpage);
        //分类的父控件
        mFrameCategory = (FrameLayout) findViewById(R.id.rb2_parent);
        //分类控件
        mCategoryPage = (RadioButton) findViewById(R.id.category);
        //分类文字控件
        mTextCategory = (TextView) findViewById(R.id.text_button2);
        //购物车父控件
        mFrameShopCarts = (FrameLayout) findViewById(R.id.rb3_parent);
        //购物车控件
        mShopCartsPage = (RadioButton) findViewById(R.id.shopcarts);
        //购物车文字控件
        mTextShopCarts = (TextView) findViewById(R.id.text_button3);
        //数量显示控件
        mPayCount = (TextView) findViewById(R.id.pay_count);
        //我的页面的父控件
        mFrameMy = (FrameLayout) findViewById(R.id.rb4_parent);
        //我的控件
        mMyPage = (RadioButton) findViewById(R.id.my);
        //我的文字的控件
        mTextMy = (TextView) findViewById(R.id.text_button4);
        //购物车数量布局
        Design = (RelativeLayout) findViewById(R.id.countdesign);

        manager = new SharedpreferencesManager(this);//创建manger对象 是为了SharedpreferencesManager里创建sp对象

        if (firstpagefragment == null) {
            firstpagefragment = new FirstPageFragment();
        }
        if (categoryfragment == null) {
            categoryfragment = new CategoryFragment();
        }
        if (shopcartsfragment == null) {
            shopcartsfragment = new ShopCartsFragment();
        }
        if (myfragment == null) {
            myfragment = new MyFragment();
        }
        //给控件设置点击监听
        mFrameFirst.setOnClickListener(this);
        mFristPage.setOnClickListener(this);
        mFrameCategory.setOnClickListener(this);
        mCategoryPage.setOnClickListener(this);
        mFrameShopCarts.setOnClickListener(this);
        mShopCartsPage.setOnClickListener(this);
        mFrameMy.setOnClickListener(this);
        mMyPage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb1_parent:
            case R.id.firstpage:
                //点击首页按钮时，给首页按钮设置被选中，其余按钮设置不被选中
                mFristPage.setChecked(true);
                mCategoryPage.setChecked(false);
                mShopCartsPage.setChecked(false);
                mMyPage.setChecked(false);
                getSupportFragmentManager().beginTransaction()
                        .hide(categoryfragment)
                        .hide(shopcartsfragment)
                        .hide(myfragment)
                        .show(firstpagefragment)
                        .commit();
                mTextFist.setTextColor(getResources().getColor(R.color.button_bk));
                mTextCategory.setTextColor(Color.GRAY);
                mTextShopCarts.setTextColor(Color.GRAY);
                mTextMy.setTextColor(Color.GRAY);
                break;
            case R.id.rb2_parent:
            case R.id.category:
                //品类按钮设置被选中，其余按钮设置不被选中
                mCategoryPage.setChecked(true);
                mFristPage.setChecked(false);
                mShopCartsPage.setChecked(false);
                mMyPage.setChecked(false);
                getSupportFragmentManager().beginTransaction()
                        .hide(firstpagefragment)
                        .hide(shopcartsfragment)
                        .hide(myfragment)
                        .show(categoryfragment)
                        .commit();
                mTextCategory.setTextColor(getResources().getColor(R.color.button_bk));
                mTextFist.setTextColor(Color.GRAY);
                mTextShopCarts.setTextColor(Color.GRAY);
                mTextMy.setTextColor(Color.GRAY);
                break;
            //购物车设置被选中，其余按钮设置不被选中
            case R.id.rb3_parent:
            case R.id.shopcarts:
                mShopCartsPage.setChecked(true);
                mFristPage.setChecked(false);
                mCategoryPage.setChecked(false);
                mMyPage.setChecked(false);
                if (SharedpreferencesManager.isLogin()) {
                    getSupportFragmentManager().beginTransaction()
                            .hide(firstpagefragment)
                            .hide(categoryfragment)
                            .hide(myfragment)
                            .show(shopcartsfragment)
                            .commit();
                    mTextShopCarts.setTextColor(getResources().getColor(R.color.button_bk));
                    mTextFist.setTextColor(Color.GRAY);
                    mTextCategory.setTextColor(Color.GRAY);
                    mTextMy.setTextColor(Color.GRAY);
                } else {
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rb4_parent:
            case R.id.my:
                //我的按钮设置被选中，其余按钮设置不被选中
                mMyPage.setChecked(true);
                mFristPage.setChecked(false);
                mCategoryPage.setChecked(false);
                mShopCartsPage.setChecked(false);
                if (SharedpreferencesManager.isLogin()) {
                    getSupportFragmentManager().beginTransaction()
                            .hide(firstpagefragment)
                            .hide(categoryfragment)
                            .hide(shopcartsfragment)
                            .show(myfragment)
                            .commit();
                    mTextMy.setTextColor(getResources().getColor(R.color.button_bk));
                    mTextFist.setTextColor(Color.GRAY);
                    mTextCategory.setTextColor(Color.GRAY);
                    mTextShopCarts.setTextColor(Color.GRAY);
                } else {
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
            {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
