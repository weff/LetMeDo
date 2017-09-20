package com.jtsoft.letmedo.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.activity.OrderDetailsActivity;
import com.jtsoft.letmedo.bean.PreGetGoodsBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/9/2.
 */

public class PreGetGoodsRecycleViewAdapter extends RecyclerView.Adapter<PreGetGoodsRecycleViewAdapter.MyViewHolder> {
    private Context context;
    private List<PreGetGoodsBean.ResponseBean.OrderListBean.OrderGoodsListBean> lists;
    public PreGetGoodsRecycleViewAdapter(Context context, List<PreGetGoodsBean.ResponseBean.OrderListBean.OrderGoodsListBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_recyclerview, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            Glide.with(context).load(lists.get(position).getGoods().getImage()).into(holder.Img);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView Img;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            Img = (ImageView) itemView.findViewById(R.id.goods);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, OrderDetailsActivity.class);
            intent.putExtra("lists", (Serializable) lists);
            context.startActivity(intent);
        }
    }

}
