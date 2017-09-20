package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.CommonAdressBean;

import java.util.List;

/**
 * Created by admin on 2017/8/16.
 */

public class CityAdapter extends BaseAdapter{
    private Context context;
    private List<CommonAdressBean.ResponseBean.ProvincesBean.CitiesBean> mlist;
    public CityAdapter(Context context,List<CommonAdressBean.ResponseBean.ProvincesBean.CitiesBean> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_city,parent,false);
            holder.tvcity = (TextView)convertView.findViewById(R.id.city);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        CommonAdressBean.ResponseBean.ProvincesBean.CitiesBean citiesBean = mlist.get(position);
        holder.tvcity.setText(citiesBean.getCityName());
        return convertView;
    }

    class ViewHolder{
        private TextView tvcity;
    }
}
