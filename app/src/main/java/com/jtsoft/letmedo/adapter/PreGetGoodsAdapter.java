package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.PreGetGoodsBean;

import java.util.List;

/**
 * Created by admin on 2017/9/2.
 * 待收货adapter
 */

public class PreGetGoodsAdapter extends BaseAdapter{
    private Context context;
    private List<PreGetGoodsBean.ResponseBean.OrderListBean> mDatas;
    private static final int TYPT_COUNT = 2;//item的类型种类
    private static final int TYPE_ONE = 0;//item里产品为一个的类型
    private static final int TYPE_MORE = 1;//item里产品为两个以上的类型
    private int currentType;//当前item的类型
    OneViewHolder oneHolder = null;
    MoreViewHolder moreHolder = null;
    private PreGetGoodsBean.ResponseBean.OrderListBean orderListBean;

    public PreGetGoodsAdapter(Context context, List<PreGetGoodsBean.ResponseBean.OrderListBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getViewTypeCount() {
        return TYPT_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.size() > 0) {
            if (mDatas.get(position).getOrderGoodsList().size() == 1) {
                return TYPE_ONE;
            } else {
                return TYPE_MORE;
            }
        }
        return -1;
    }


    @Override
    public int getCount() {
        Log.e("TAG", "mDatas:" + mDatas.size());
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        currentType = getItemViewType(position);
        //获取bean数据
        orderListBean = mDatas.get(position);
        //根据Type填充不同的布局
        //只有一种商品的情况
        if (currentType == TYPE_ONE) {
            if (convertView == null) {
                oneHolder = new OneViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_prepay_listview_one, parent, false);
                oneHolder.bill = (TextView) convertView.findViewById(R.id.bill_order);
                oneHolder.subject = (TextView) convertView.findViewById(R.id.subject_goods);
                oneHolder.ordernum = (TextView) convertView.findViewById(R.id.count_goods);
                oneHolder.orderprice = (TextView) convertView.findViewById(R.id.price_goods);
                oneHolder.Img = (ImageView) convertView.findViewById(R.id.picture);
                oneHolder.delete = (Button) convertView.findViewById(R.id.delete);
                oneHolder.pay = (Button) convertView.findViewById(R.id.pay);
                convertView.setTag(oneHolder);
            } else {
                oneHolder = (OneViewHolder) convertView.getTag();
            }
            oneHolder.delete.setText("确认收货");
            oneHolder.pay.setText("抢红包");
            oneHolder.bill.setText("订单号:" + orderListBean.getOrderCode());
            oneHolder.ordernum.setText("共 " + orderListBean.getOrderGoodsList().get(0).getNum() + " 件");
            oneHolder.subject.setText(orderListBean.getOrderGoodsList().get(0).getGoods().getName());
            Glide.with(context).load(orderListBean.getOrderGoodsList().get(0).getGoods().getImage()).into(oneHolder.Img);
            oneHolder.orderprice.setText("商品总价: ￥" + orderListBean.getOrderPrice());

            //两种以及以上商品的情况
        } else if (currentType == TYPE_MORE) {
            if (convertView == null) {
                moreHolder = new MoreViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_prepay_listview_more, parent, false);
                moreHolder.recyclerView = (RecyclerView) convertView.findViewById(R.id.recycleview);
                moreHolder.bill = (TextView) convertView.findViewById(R.id.bill_order);
                moreHolder.Img = (ImageView) convertView.findViewById(R.id.picture);
                moreHolder.ordernum = (TextView) convertView.findViewById(R.id.count_goods);
                moreHolder.orderprice = (TextView) convertView.findViewById(R.id.price_goods);
                moreHolder.delete = (Button) convertView.findViewById(R.id.delete);
                moreHolder.pay = (Button) convertView.findViewById(R.id.pay);
                convertView.setTag(moreHolder);
            } else {
                moreHolder = (MoreViewHolder) convertView.getTag();
            }
            //RecycleView的逻辑操作
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            moreHolder.recyclerView.setLayoutManager(manager);
            if (orderListBean.getOrderGoodsList().size() > 1) {
                List<PreGetGoodsBean.ResponseBean.OrderListBean.OrderGoodsListBean> orderGoodsList = orderListBean.getOrderGoodsList();
                PreGetGoodsRecycleViewAdapter adapter = new PreGetGoodsRecycleViewAdapter(context, orderGoodsList);
                moreHolder.recyclerView.setAdapter(adapter);
            }
            moreHolder.delete.setText("确认收货");
            moreHolder.pay.setText("抢红包");
            moreHolder.bill.setText(orderListBean.getOrderCode());
            moreHolder.ordernum.setText(orderListBean.getOrderGoodsList().size() + "");
            moreHolder.orderprice.setText(orderListBean.getOrderPrice() + "");
        }
        return convertView;
    }

    //一种商品
    class OneViewHolder {
        TextView subject, bill;
        TextView orderprice;
        ImageView Img;
        TextView ordernum;
        Button delete, pay;


    }

    //两种以上商品
    class MoreViewHolder {
        RecyclerView recyclerView;
        TextView orderprice, bill;
        ImageView Img;
        TextView ordernum;
        Button delete, pay;
    }
}
