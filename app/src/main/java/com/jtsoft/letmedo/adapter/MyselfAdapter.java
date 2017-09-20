package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.MySelfFragmentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragon on 2017/7/17.
 */
public class MyselfAdapter extends BaseAdapter{

    private Context context;
    private List<MySelfFragmentBean.ResponseBean.OrderListBean> lists;
    public MyselfAdapter(Context context, List<MySelfFragmentBean.ResponseBean.OrderListBean> lists) {
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
           convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_myself, parent, false);
            holder.order = (TextView)convertView.findViewById(R.id.orders);
            holder.totalPrice = (TextView)convertView.findViewById(R.id.totalprice);
            holder.delete = (Button)convertView.findViewById(R.id.delete);
            holder.share = (Button)convertView.findViewById(R.id.share);

            convertView.setTag(holder);
        }else {
           holder = (ViewHolder) convertView.getTag();
        }
        //获取bean类
        MySelfFragmentBean.ResponseBean.OrderListBean orderListBean = lists.get(position);
        //对控件进行赋值
        holder.order.setText("订单号:" + orderListBean.getOrderCode());
        holder.totalPrice.setText(orderListBean.getOrderPrice() + "");

        return convertView;
    }


    class ViewHolder{
        TextView order,totalPrice;
        Button delete,share;
    }
}
