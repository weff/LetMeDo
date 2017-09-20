package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.OrderDetailsBean;

import java.util.List;

/**
 * Created by admin on 2017/9/4.
 * 订单详情页面的商品adapter
 */

public class OrderDetailsAdapter extends BaseAdapter{
    private Context context;
    private List<OrderDetailsBean.ResponseBean.OrderBean.OrderGoodsListBean> mDatas;

    public OrderDetailsAdapter(Context context,List<OrderDetailsBean.ResponseBean.OrderBean.OrderGoodsListBean> mDatas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_mybill, parent, false);
            holder.goodsPic = (ImageView) convertView.findViewById(R.id.picture);
            holder.goodsname = (TextView) convertView.findViewById(R.id.goodsname);
            holder.goodsprice = (TextView) convertView.findViewById(R.id.goodsprice);
            holder.goodsnum = (TextView) convertView.findViewById(R.id.goodsnum);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        OrderDetailsBean.ResponseBean.OrderBean.OrderGoodsListBean orderGoodsListBean = mDatas.get(position);
        holder.goodsname.setText(orderGoodsListBean.getGoods().getName());
        Log.e("Goods",holder.goodsname + ":::");
        holder.goodsprice.setText("￥" + orderGoodsListBean.getGoods().getPrice());
        holder.goodsnum.setText("×" + orderGoodsListBean.getNum());
        Glide.with(context).load(orderGoodsListBean.getGoods().getImage()).into(holder.goodsPic);
        return convertView;
    }

    class ViewHolder {
        private ImageView goodsPic;
        private TextView goodsname, goodsprice, goodsnum;

    }
}
