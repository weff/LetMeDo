package com.jtsoft.letmedo. fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jtsoft.letmedo.R;


/**
 * Created by Administrator on 2017/6/21.
 */

public class CategoryFragment extends Fragment {

    private View view;
    //视图初始化
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = getActivity();
        view = LayoutInflater.from(context).inflate(R.layout.fragment_category, null, false);
        return view;
    }
    //数据初始化
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
