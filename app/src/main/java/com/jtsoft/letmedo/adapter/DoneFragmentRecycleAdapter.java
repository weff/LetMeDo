package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.DoneFragmentBean;

import java.util.List;

/**
 * Created by admin on 2017/9/6.
 */

public class DoneFragmentRecycleAdapter extends RecyclerView.Adapter<DoneFragmentRecycleAdapter.MyViewHolder>{

    private Context context;
    public List<DoneFragmentBean.ResponseBean.OrderListBean.OrderGoodsListBean> mDatas;
    public DoneFragmentRecycleAdapter(Context context,List<DoneFragmentBean.ResponseBean.OrderListBean.OrderGoodsListBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }
    @Override
    public DoneFragmentRecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recyclerview,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(DoneFragmentRecycleAdapter.MyViewHolder holder, int position) {
        Glide.with(context).load(mDatas.get(position).getGoods().getImage()).into(holder.Img);
        Log.e("IMG",mDatas.get(position).getGoods().getImage());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView Img;
        public MyViewHolder(View itemView) {
            super(itemView);
            Img = (ImageView)itemView.findViewById(R.id.goods);
        }
    }
}
