package com.jtsoft.letmedo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 * 我的优惠券的adapter
 */

public class CouponAdapter extends FragmentPagerAdapter{
    List<Fragment> mLists = new ArrayList<>();
    public CouponAdapter(FragmentManager fm) {
        super(fm);
    }
    public void addFragment(List<Fragment> list) {
        this.mLists.addAll(list);
        notifyDataSetChanged();
        return ;
    }
    @Override
    public Fragment getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public int getCount() {
        return mLists.size();
    }
    String[] TabLayout = {"可使用","已使用","已过期"};
    @Override
    public CharSequence getPageTitle(int position) {
        return TabLayout[position];
    }
}
