package com.jtsoft.letmedo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    //定义一个集合，盛装fragment
    List<Fragment> mLists = new ArrayList<>();

    //定义一个方法，用来添加fragment
    public void addFragment(List<Fragment> list) {
       this.mLists.addAll(list);
        notifyDataSetChanged();
    }
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public int getCount() {
        return mLists.size();
    }
    String [] tabTittle = {"待付款","收货待","已完成","门店自提"};

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTittle[position];
    }
}
