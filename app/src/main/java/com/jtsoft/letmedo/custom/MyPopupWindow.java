package com.jtsoft.letmedo.custom;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.jtsoft.letmedo.R;

/**
 * Created by admin on 2017/9/2.
 */

public class MyPopupWindow extends PopupWindow {
    private View mainview;
    public MyPopupWindow(Activity context, View.OnClickListener itemclick){
        super(context);
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        mainview=inflater.inflate(R.layout.activity_mapsearch, null);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setContentView(mainview);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.AnimBottom);
    }
}
