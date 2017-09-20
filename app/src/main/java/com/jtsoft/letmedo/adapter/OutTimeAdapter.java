package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.OutTimeBean;
import com.jtsoft.letmedo.utils.NetWorkUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */

public class OutTimeAdapter extends BaseAdapter{
    private Context context;
    private List<OutTimeBean.ResponseBean.CouponListBean> lists = new ArrayList<>();
    private NetWorkUtils netWorkUtils = new NetWorkUtils();
    public OutTimeAdapter(Context context, List<OutTimeBean.ResponseBean.CouponListBean> lists) {
        this.context = context;
        this.lists = lists;
    }
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_outtime,parent,false);
            holder.mMoney = (TextView)convertView.findViewById(R.id.coupon_money);
            holder.newPersonal = (TextView)convertView.findViewById(R.id.coupon_newpersonal);
            holder.scene = (TextView)convertView.findViewById(R.id.scene);
            holder.offtime = (TextView)convertView.findViewById(R.id.cutoff_time);
//            holder.outtime = (TextView)convertView.findViewById(R.id.outtime);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        OutTimeBean.ResponseBean.CouponListBean couponListBean = lists.get(position);
        holder.mMoney.setText(couponListBean.getCouponPrice()+ "");
        holder.newPersonal.setText(couponListBean.getCouponName());
       if (couponListBean.getFullPrice() == 0.0) {
           holder.scene.setText("无门槛优惠券");
       }else {
           holder.scene.setText("满" + couponListBean.getFullPrice() + "可用");
       }
        long useEndTime = couponListBean.getUseEndTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String endTime = sdf.format(useEndTime);
        holder.offtime.setText(endTime + "到期");

        return convertView;
    }

    class ViewHolder{
        private TextView mMoney,newPersonal,scene,offtime;
    }
}
