package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.OutTimeBean;
import com.jtsoft.letmedo.bean.ProCouponBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */

public class ProCouponAdapter extends BaseAdapter {
    private Context context;
    private List<OutTimeBean.ResponseBean.CouponListBean> lists = new ArrayList<>();

    public ProCouponAdapter(Context context, List<OutTimeBean.ResponseBean.CouponListBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_procoupon, parent, false);
            holder.tvMoney = (TextView) convertView.findViewById(R.id.coupon_money);
            holder.tvNewPersonal = (TextView) convertView.findViewById(R.id.coupon_newpersonal);
            holder.scene = (TextView) convertView.findViewById(R.id.scene);
            holder.offTime = (TextView) convertView.findViewById(R.id.cutoff_time);
//            holder.outTime = (TextView) convertView.findViewById(R.id.outtime);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        OutTimeBean.ResponseBean.CouponListBean couponListBean = lists.get(position);
        holder.tvMoney.setText(couponListBean.getCouponPrice() + "");
        holder.tvNewPersonal.setText(couponListBean.getCouponName());
        if (couponListBean.getFullPrice() == 0.0) {
            holder.scene.setText("全场通用");
        }else {
            holder.scene.setText("满" + couponListBean.getFullPrice() + "可用");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        long useEndTime = couponListBean.getUseEndTime();
        long useReceiveTime = couponListBean.getReceiveTime();
        String endTime = sdf.format(useEndTime);
        String receiveTime = sdf.format(useReceiveTime);
        holder.offTime.setText(endTime + "到期");
        return convertView;
    }
    class ViewHolder {
        private TextView tvMoney, tvNewPersonal, scene, offTime, outTime;
    }
}
