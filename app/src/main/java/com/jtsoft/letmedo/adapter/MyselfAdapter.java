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

import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Dragon on 2017/7/17.
 * 门店自提页面
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
//            holder.delete = (Button)convertView.findViewById(R.id.delete);
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
        holder.share.setText("抢红包");
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        return convertView;
    }


    class ViewHolder{
        TextView order,totalPrice;
        Button delete,share;
    }

    //分享方法
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(context);

    }
}
