package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.BillOrderBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/26.
 * 待付款的adapter
 */

public class PrePayFragmentAdapter extends BaseAdapter {
    private Context context;
    private List<BillOrderBean.ResponseBean.OrderListBean> mDatas;
    private static final int TYPT_COUNT = 2;//item的类型种类
    private static final int TYPE_ONE = 0;//item里产品为一个的类型
    private static final int TYPE_MORE = 1;//item里产品为两个以上的类型
    private int currentType;//当前item的类型
    OneViewHolder oneHolder = null;
    MoreViewHolder moreHolder = null;
    private PayGoodsInterface payGoodsInterface;
    private BillOrderBean.ResponseBean.OrderListBean orderListBean;


    public PrePayFragmentAdapter(Context context, List<BillOrderBean.ResponseBean.OrderListBean> mDatas) {
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
            oneHolder.bill.setText("订单号:" + orderListBean.getOrderCode());
            oneHolder.ordernum.setText("共 " + orderListBean.getOrderGoodsList().get(0).getNum() + " 件");
            oneHolder.subject.setText(orderListBean.getOrderGoodsList().get(0).getGoods().getName());
            Glide.with(context).load(orderListBean.getOrderGoodsList().get(0).getGoods().getImage()).into(oneHolder.Img);
            oneHolder.orderprice.setText("商品总价: ￥" + orderListBean.getOrderPrice());
            //去支付的点击事件
            oneHolder.pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  payGoodsInterface.doPay(position);
                }
            });
            //取消支付的点击事件
            oneHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   payGoodsInterface.doCancel(position);
                }
            });

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
                List<BillOrderBean.ResponseBean.OrderListBean.OrderGoodsListBean> newList = orderListBean.getOrderGoodsList();
                BillRecyclerViewAdapter adapter = new BillRecyclerViewAdapter(context, newList);
                moreHolder.recyclerView.setAdapter(adapter);
            }

            moreHolder.bill.setText("订单号:" + orderListBean.getOrderCode());
            moreHolder.ordernum.setText("共 " + orderListBean.getOrderGoodsList().size() + " 件");
            moreHolder.orderprice.setText("商品总价: ￥" + orderListBean.getOrderPrice() + "");
        }
        moreHolder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payGoodsInterface.doPay(position);
            }
        });
        moreHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payGoodsInterface.doCancel(position);
            }
        });

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
    public interface PayGoodsInterface{
        void doPay(int position);
        void doCancel(int position);
    }
    public void setPayGoodsInterface(PayGoodsInterface payGoodsInterface) {
        this.payGoodsInterface = payGoodsInterface;
    }
}
