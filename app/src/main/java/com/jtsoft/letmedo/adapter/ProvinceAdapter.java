package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.CommonAdressBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 2017/8/15.
 */

public class ProvinceAdapter extends BaseAdapter{
    private Context context;
    private List<CommonAdressBean.ResponseBean.ProvincesBean> mlist;
    public ProvinceAdapter(Context context,List<CommonAdressBean.ResponseBean.ProvincesBean> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_province,parent,false);
            holder.tv = (TextView)convertView.findViewById(R.id.province);
            convertView.setTag(holder);
        }else {
          holder = (ViewHolder) convertView.getTag();
        }
        CommonAdressBean.ResponseBean.ProvincesBean provincesBean = mlist.get(position);
        holder.tv.setText(provincesBean.getProvName());
        return convertView;
    }

    class ViewHolder{
        private TextView tv;
    }
}
