package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.activity.OrderDetailsActivity;
import com.jtsoft.letmedo.bean.BillOrderBean;
import com.jtsoft.letmedo.bean.BillOrderitemBean;
import com.jtsoft.letmedo.bean.DoneFragmentBean;

import java.io.Serializable;
import java.util.List;


/**
 * Created by Administrator on 2017/6/28.
 */

class BillRecyclerViewAdapter extends RecyclerView.Adapter<BillRecyclerViewAdapter.MyViewHolder>{
    private Context context;
    private List<BillOrderBean.ResponseBean.OrderListBean.OrderGoodsListBean> lists;
    public BillRecyclerViewAdapter(Context context, List<BillOrderBean.ResponseBean.OrderListBean.OrderGoodsListBean> lists) {
        this.context = context;
        this.lists = lists;
    }
    @Override
    public BillRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recyclerview,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(BillRecyclerViewAdapter.MyViewHolder holder, int position) {
        Glide.with(context).load(lists.get(position).getGoods().getImage()).into(holder.Img);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView Img;
        public MyViewHolder(View itemView) {
            super(itemView);
            Img = (ImageView) itemView.findViewById(R.id.goods);
        }
    }
}