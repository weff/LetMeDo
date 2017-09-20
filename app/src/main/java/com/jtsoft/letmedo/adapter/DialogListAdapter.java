package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jtsoft.letmedo.R;

import java.util.List;


/**
 * Created by Administrator on 2017/6/27.
 */

public class DialogListAdapter extends BaseAdapter{
    private Context context;
    private List<String> mDatas;
    public DialogListAdapter(Context context, List<String> mDatas){
        this.context = context;
        this.mDatas = mDatas;
    }
    @Override
    public int getCount() {
        return mDatas.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_dialog,parent,false);
            holder.address = (TextView)convertView.findViewById(R.id.area);
            convertView.setTag(holder);
        }else {
             holder = (ViewHolder) convertView.getTag();
        }

        holder.address.setText(mDatas.get(position).toString());
        return convertView;
    }
    static class ViewHolder{
        private TextView address;
    }
}
