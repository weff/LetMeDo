package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jtsoft.letmedo.MainActivity;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.PreCouponBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 * 未使用优惠券adapter
 */

public class PreCouponAdapter extends BaseAdapter {

    private Context context;
    private List<PreCouponBean.ResponseBean.CouponListBean> lists;
    private RelativeLayout BgCoupon;
    private TextView mUseCoupon;

    public PreCouponAdapter(Context context, List<PreCouponBean.ResponseBean.CouponListBean> lists) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_precoupon, parent, false);
            //优惠券余额显示
            holder.tvMoney = (TextView) convertView.findViewById(R.id.coupon_money);
            //新人专享优惠
            holder.tvCoupon_NewPersonal = (TextView) convertView.findViewById(R.id.coupon_newpersonal);
            //使用场景
            holder.tvScene = (TextView) convertView.findViewById(R.id.scene);
            //到期时间
            holder.tvCutOffTime = (TextView) convertView.findViewById(R.id.cutoff_time);
            //剩余时间
//            holder.tvOutTime = (TextView) convertView.findViewById(R.id.outtime);
            //立即使用按钮
            holder.mUseCoupon = (Button) convertView.findViewById(R.id.coupon_use);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PreCouponBean.ResponseBean.CouponListBean bean = lists.get(position);
        holder.tvMoney.setText(bean.getCouponPrice() + "");
        holder.tvCoupon_NewPersonal.setText(bean.getCouponName());
        if (bean.getUseType() == 0) {
            if (bean.getFullPrice() == 0.0) {
                holder.tvScene.setText("无门槛优惠券");
            } else if (bean.getFullPrice() > 0) {
                holder.tvScene.setText("满" + bean.getFullPrice() + "元可用");
            }
        } else if (bean.getUseType() == 1) {
            holder.tvScene.setText("仅限<" + bean.getGoodsName() + ">可用");
        }

        holder.mUseCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //界面跳转
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
        long useEndTime = bean.getUseEndTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String endTime = sdf.format(useEndTime);
        holder.tvCutOffTime.setText(endTime + "到期");
        return convertView;
    }


    class ViewHolder {
        TextView tvMoney;
        TextView tvCoupon_NewPersonal;
        TextView tvScene;
        TextView tvCutOffTime;
        TextView tvOutTime;
        Button mUseCoupon;
    }
}
