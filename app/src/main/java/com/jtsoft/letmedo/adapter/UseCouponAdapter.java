package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.GetUseCoupon;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by admin on 2017/8/28.
 *
 */

public class UseCouponAdapter extends BaseAdapter{
    private Context context;
    private List<GetUseCoupon.ResponseBean.CouponListBean> mDatas;
    private int state = 1;

    public UseCouponAdapter(Context context,List<GetUseCoupon.ResponseBean.CouponListBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_usecoupon,parent,false);
            holder.mExpireCoupon = (TextView)convertView.findViewById(R.id.cutoff_time);
            holder.mScene = (TextView)convertView.findViewById(R.id.scene);
            holder.mProject = (TextView)convertView.findViewById(R.id.coupon_newpersonal);
            holder.mCouponCount = (TextView)convertView.findViewById(R.id.coupon_money);
            holder.BgCoupon = (RelativeLayout)convertView.findViewById(R.id.background_coupon);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        GetUseCoupon.ResponseBean.CouponListBean couponListBean = mDatas.get(position);
        holder.mCouponCount.setText(couponListBean.getCouponPrice() + "");
        holder.mProject.setText(couponListBean.getCouponName());
        if (couponListBean.getFullPrice() == 0.0) {
            holder.mScene.setText("无门槛优惠券");
        } else {
            holder.mScene.setText("满" + couponListBean.getFullPrice() + "可用");
        }
        long useEndTime = couponListBean.getUseEndTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String endTime = sdf.format(useEndTime);
        holder.mExpireCoupon.setText(endTime + "到期");
        return convertView;
    }
    class ViewHolder{
        private RelativeLayout BgCoupon;
        private TextView mExpireCoupon,mScene,mProject,mCouponCount;
    }
}
