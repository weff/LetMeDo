package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.CommonAdressBean;
import com.jtsoft.letmedo.bean.GetAddressBean;

import java.util.List;


/**
 * Created by Administrator on 2017/6/26.
 * 地址管理的adapter
 */

public class CommonAdressAdapter extends BaseAdapter{
    private Context context;
    private List<GetAddressBean.ResponseBean.OrderAddressListBean> lists;
    public CommonAdressAdapter(Context context, List<GetAddressBean.ResponseBean.OrderAddressListBean> lists) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_commonadress,parent,false);
            holder.UserName = (TextView)convertView.findViewById(R.id.name_address);
            holder.UserPhone = (TextView)convertView.findViewById(R.id.phonenumber_address);
            holder.UserAdress = (TextView)convertView.findViewById(R.id.address);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        //获取相应位置的bean数据
        GetAddressBean.ResponseBean.OrderAddressListBean commonAdressBean = lists.get(position);
        holder.UserName.setText(commonAdressBean.getContactName());
        holder.UserPhone.setText(commonAdressBean.getContactPhone());
        holder.UserAdress.setText(commonAdressBean.getProvinceName() + commonAdressBean.getCityName() + commonAdressBean.getDistrictName() + commonAdressBean.getDetailAddress());
        return convertView;
    }
    class ViewHolder{
        private TextView UserName;
        private TextView UserPhone;
        private TextView UserAdress;
    }
}
