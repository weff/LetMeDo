package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.activity.AddCommonAdressActivity;
import com.jtsoft.letmedo.bean.CommonAdressBean;

import java.util.List;

/**
 * Created by admin on 2017/8/31.
 */

public class DistrictAdapter extends BaseAdapter{
    private Context context;
    private List<CommonAdressBean.ResponseBean.ProvincesBean.CitiesBean.DistrictsBean> mDatas;
    public DistrictAdapter(Context context,List<CommonAdressBean.ResponseBean.ProvincesBean.CitiesBean.DistrictsBean> mDatas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_district,parent,false);
            holder.district = (TextView)convertView.findViewById(R.id.district);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        String districtName = mDatas.get(position).getDistrictName();
        holder.district.setText(districtName);
        return convertView;
    }
    class ViewHolder{
        private TextView district;
    }
}
