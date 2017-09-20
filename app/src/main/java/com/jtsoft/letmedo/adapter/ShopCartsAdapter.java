package com.jtsoft.letmedo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.ShoppingCartBean;

import java.util.List;


/**
 * Created by Administrator on 2017/6/21.
 * 购物车的adapter
 */

public class ShopCartsAdapter extends BaseAdapter {

    private boolean isShow = true;//是否显示编辑/完成
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    private Context context;
    private List<ShoppingCartBean.ResponseBean.ShoppingCartListBean> shoppingCartBeanList;

    public ShopCartsAdapter(Context context) {
        this.context = context;
    }

    public void setShoppingCartBeanList(List<ShoppingCartBean.ResponseBean.ShoppingCartListBean> shoppingCartBeanList) {
        this.shoppingCartBeanList = shoppingCartBeanList;
        notifyDataSetChanged();
    }

    /**
     * 单选接口
     *
     * @param checkInterface
     */
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    /**
     * 改变商品数量接口
     *
     * @param modifyCountInterface
     */
    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getCount() {
        return shoppingCartBeanList == null ? 0 : shoppingCartBeanList.size();

    }


    @Override
    public Object getItem(int position) {
        return shoppingCartBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * 是否显示可编辑
     *
     * @param flag
     */
    public void isShow(boolean flag) {
        isShow = flag;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopcarts_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       final ShoppingCartBean.ResponseBean.ShoppingCartListBean shoppingCartBean = shoppingCartBeanList.get(position);
        holder.tv_commodity_name.setText(shoppingCartBean.getGoods().getName());
        holder.tv_price.setText("￥" + shoppingCartBean.getGoods().getPrice());
        holder.ck_chose.setChecked(shoppingCartBean.isChoosed());
        holder.tv_show_num.setText(shoppingCartBean.getNum() +"");
        Glide.with(context).load(shoppingCartBean.getGoods().getImage()).into(holder.iv_show_pic);

        //单选框按钮
        holder.ck_chose.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shoppingCartBean.setChoosed(((CheckBox) v).isChecked());
                        checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露接口
                    }
                }
        );

        //增加按钮
        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doIncrease(position, holder.tv_show_num, holder.ck_chose.isChecked());//暴露增加接口
            }
        });

        //删减按钮
        holder.iv_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doDecrease(position, holder.tv_show_num, holder.ck_chose.isChecked());//暴露删减接口
            }
        });

        return convertView;
    }


    //初始化控件
    class ViewHolder {
        ImageView iv_chose;
        ImageView iv_show_pic, iv_sub, iv_add;
        TextView tv_commodity_name, tv_fabric, tv_dress, tv_pants, tv_price, tv_num, tv_delete, tv_show_num;
        CheckBox ck_chose;
        RelativeLayout rl_edit;

        public ViewHolder(View itemView) {
            ck_chose = (CheckBox) itemView.findViewById(R.id.checkbox_shopcart);
            iv_show_pic = (ImageView) itemView.findViewById(R.id.shop_imgview);
            iv_sub = (ImageView) itemView.findViewById(R.id.sub_button);
            iv_add = (ImageView) itemView.findViewById(R.id.add_button);

            tv_commodity_name = (TextView) itemView.findViewById(R.id.shop_subject);
            tv_price = (TextView) itemView.findViewById(R.id.current_price);
            tv_show_num = (TextView) itemView.findViewById(R.id.num);

        }

    }


    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param position  元素位置
         * @param isChecked 元素选中与否
         */
        void checkGroup(int position, boolean isChecked);
    }


    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {

        /**
         * 增加操作
         *
         * @param position      组元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int position, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param position      组元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doDecrease(int position, View showCountView, boolean isChecked);

        /**
         * 删除子item
         *
         * @param position
         */
        void childDelete(int position);
    }
}
