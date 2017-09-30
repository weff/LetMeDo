package com.jtsoft.letmedo.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jtsoft.letmedo.MainActivity;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.activity.MyBillActivity;
import com.jtsoft.letmedo.adapter.ShopCartsAdapter;
import com.jtsoft.letmedo.bean.DeleteShopCartBean;
import com.jtsoft.letmedo.bean.ShopCartCountBean;
import com.jtsoft.letmedo.bean.ShoppingCartBean;
import com.jtsoft.letmedo.bean.ShoppingCartCountBean;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/6/21.
 * 购物车页面
 */

public class ShopCartsFragment extends Fragment implements View.OnClickListener, ShopCartsAdapter.CheckInterface, ShopCartsAdapter.ModifyCountInterface {

    private View view;
    private ImageView Back;
    private TextView Title;
    private TextView Edit;
    private TextView FreedSend;
    private ListView ShopCartsListView;
    private CheckBox AllCheckBox;
    private Button ShopCartsConfirm;
    private TextView ShopCartsMoney;
    private Context context;
    private Intent intent;
    private ShopCartsAdapter adapter;
    private double totalPrice = 0.00;// 购买的商品总价
    public int totalCount;// 购买的商品总数量
    private RelativeLayout ShopNull;
    private RelativeLayout GoodsShop;
    private Button goMainPage;
    private double MONEY = 15.0;
    private String strToken;
    private List<ShoppingCartBean.ResponseBean.ShoppingCartListBean> shoppingCartList;
    private String result;
    private int cartId;
    private Request request;
    private int state;
    private ShoppingCartBean.ResponseBean.ShoppingCartListBean shoppingCartListBean;
    private View numView;

    //视图初始化
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        strToken = SharedpreferencesManager.getToken();
        view = LayoutInflater.from(context).inflate(R.layout.fragment_shopcarts, null, false);
        return view;
    }

    //    //数据初始化
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("TAG", "onActivityCreated");
        //控件初始化
        initView();

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("TAG", "onStart");
        //数据初始化
        initData();
        //全选方法
        AllCheck();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        strToken = SharedpreferencesManager.getToken();
        initSponse();
    }

    private void AllCheck() {
        AllCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shoppingCartList.size() != 0) {
                    if (AllCheckBox.isChecked()) {
                        for (int i = 0; i < shoppingCartList.size(); i++) {
                            shoppingCartList.get(i).setChoosed(true);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < shoppingCartList.size(); i++) {
                            shoppingCartList.get(i).setChoosed(false);
                        }
                        adapter.notifyDataSetChanged();
                    }
                    //统计数量与总价
                    statistics();
                }
            }
        });

    }

    /**
     * 单选
     *
     * @param position  组元素位置
     * @param isChecked 组元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {

        shoppingCartList.get(position).setChoosed(isChecked);

        if (isAllCheck()) {
            AllCheckBox.setChecked(true);
        } else {
            AllCheckBox.setChecked(false);
        }
        adapter.notifyDataSetChanged();
        statistics();
    }

    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {

        for (ShoppingCartBean.ResponseBean.ShoppingCartListBean group : shoppingCartList) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    /**
     * 统计操作
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
     * 3.给底部的textView进行数据填充
     */
    public void statistics() {
        totalPrice = 0.00;
        totalCount = 0;
        if (shoppingCartList != null && shoppingCartList.size() > 0) {
            for (int i = 0; i < shoppingCartList.size(); i++) {
                ShoppingCartBean.ResponseBean.ShoppingCartListBean Bean = shoppingCartList.get(i);
                totalCount += Bean.getNum();
                Log.d("TAG", "totalCount1----------" + totalCount);
                if (Bean.isChoosed()) {
                    totalPrice += Bean.getGoods().getPrice() * (Bean.getNum());
                }
            }
        } else {
            totalCount = 0;
        }
        Log.d("TAG", "totalCount2----------" + totalCount);
        EventBus.getDefault().post(new ShopCartCountBean(totalCount));
        result = String.format("%.2f", totalPrice);
        ShopCartsMoney.setText(result + "");
        if (totalPrice >= MONEY) {
            FreedSend.setText("已包邮");
        } else {
            result = String.format("%.2f", (MONEY - totalPrice));
            FreedSend.setText("还差" + result + "元包邮");
        }

    }


    /**
     * 增加
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        //待做先判断商品是否加入到服务器成功，如果成功再在页面的加号按钮数量加一
        shoppingCartListBean = shoppingCartList.get(position);
        int goodsId = shoppingCartListBean.getGoodsId();
        numView = showCountView;
        initShopCartsNum(strToken, goodsId, 1);
    }

    //购物车中对商品加操作
    private void initShopCartsNum(String strToken, int goodsId, final int currentCount) {
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/saveShoppingCart.do?token=" + strToken + "&goodsId=" + goodsId + "&num=" + currentCount)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(context, R.string.no_net +"");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final ShoppingCartCountBean shoppingCartCountBean = gson.fromJson(strJson, ShoppingCartCountBean.class);
                if (shoppingCartCountBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int currentCount = shoppingCartListBean.getNum();
                            currentCount++;
                            shoppingCartListBean.setNum(currentCount);
                            ((TextView) numView).setText(currentCount + "");
                            adapter.notifyDataSetChanged();
                            statistics();
                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            return;
                        }
                    });
                }
            }
        });
    }

    /**
     * 删减
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        shoppingCartListBean = shoppingCartList.get(position);
        int goodsId = shoppingCartListBean.getGoodsId();
        numView = showCountView;
        initShopCartsCount(strToken, goodsId, -1);
    }

    //对购物车商品减的操作
    private void initShopCartsCount(String strToken, int goodsId, int i) {
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/saveShoppingCart.do?token=" + strToken + "&goodsId=" + goodsId + "&num=" + i)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(context, R.string.no_net +"");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final ShoppingCartCountBean shoppingCartCountBean = gson.fromJson(strJson, ShoppingCartCountBean.class);
                if (shoppingCartCountBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int currentCount = shoppingCartListBean.getNum();
                            if (currentCount <= 1) {
                                return;
                            }
                            currentCount--;
                            shoppingCartListBean.setNum(currentCount);
                            ((TextView) numView).setText(currentCount + "");
                            adapter.notifyDataSetChanged();
                            statistics();
                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            return;
                        }
                    });
                }
            }
        });
    }

    /**
     * 删除
     *
     * @param position
     */
    @Override
    public void childDelete(int position) {
        shoppingCartList.remove(position);
        adapter.notifyDataSetChanged();
        statistics();
    }


    private void initData() {
        //网络请求
        initSponse();
    }

    private void initSponse() {
        request = new Request.Builder()
                .url(Constant.CONSTANT + "/listShoppingCart.do?token=" + strToken)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(context, R.string.no_net +"");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final ShoppingCartBean shoppingCartBean = gson.fromJson(strJson, ShoppingCartBean.class);
                if (shoppingCartBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    shoppingCartList = shoppingCartBean.getResponse().getShoppingCartList();
                    Log.e("TAG", shoppingCartList + "555555");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initShow();
                            statistics();
                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            ToastUtil.showShort(context, (String) shoppingCartBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    private void initShow() {
        Back.setOnClickListener(this);
        Title.setText(R.string.shopcarts);
        if (shoppingCartList.size() > 0) {
            GoodsShop.setVisibility(View.VISIBLE);
            ShopNull.setVisibility(View.INVISIBLE);
            ShopCartsConfirm.setOnClickListener(this);
            adapter = new ShopCartsAdapter(context);
            Edit.setVisibility(View.VISIBLE);
            Edit.setText(R.string.edit);
            ShopCartsConfirm.setText("结单");
            ShopCartsConfirm.setBackgroundColor(getResources().getColor(R.color.green));
            Edit.setOnClickListener(this);
            adapter.setCheckInterface(this);
            adapter.setModifyCountInterface(this);
            ShopCartsListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            adapter.setShoppingCartBeanList(shoppingCartList);
            //初始化时  购物车商品默认全部选中
            isAllChecked();
        } else {
            ShopNull.setVisibility(View.VISIBLE);
            GoodsShop.setVisibility(View.INVISIBLE);
            goMainPage.setOnClickListener(this);
            Edit.setVisibility(View.INVISIBLE);
        }
    }

    private void isAllChecked() {
        AllCheckBox.setChecked(true);
        for (int i = 0; i < shoppingCartList.size(); i++) {
            shoppingCartList.get(i).setChoosed(true);
        }
        adapter.notifyDataSetChanged();
        statistics();
    }

    private void initView() {
        //购物车不为空时的布局控件
        GoodsShop = (RelativeLayout) view.findViewById(R.id.shop_goods);
        //去逛逛控件
        goMainPage = (Button) view.findViewById(R.id.go_mainpage);
        //购物车为空时的布局控件
        ShopNull = (RelativeLayout) view.findViewById(R.id.null_shopcarts);
        //返回按键控件
        Back = (ImageView) view.findViewById(R.id.back_press);
        //标题控件
        Title = (TextView) view.findViewById(R.id.title_name);
        //编辑控件
        Edit = (TextView) view.findViewById(R.id.edit);
        //免邮费控件
        FreedSend = (TextView) view.findViewById(R.id.freedom);
        //购物车的listview控件
        ShopCartsListView = (ListView) view.findViewById(R.id.shopcarts_list);
        //全选按钮控件
        AllCheckBox = (CheckBox) view.findViewById(R.id.all_checkbox);
        //结算控件
        ShopCartsConfirm = (Button) view.findViewById(R.id.confirm);
        ShopCartsConfirm.setText("结单");
        //购物车总价控件
        ShopCartsMoney = (TextView) view.findViewById(R.id.shopcarts_money);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                if ("删除".equals(ShopCartsConfirm.getText().toString().trim())) {
                    //执行删除操作
                    //先判断是否有商品被选中
                    if ((int) totalPrice == 0) {
                        Log.e("TAG", "totalP==" + (int) totalPrice);
                        Toast.makeText(context, "请选择要删除的商品", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        //执行删除操作
                        //弹出提示框(是否要执行删除操作)
                        showDialog();
                    }
                } else if ("结单".equals(ShopCartsConfirm.getText().toString().trim())) {
                    //判断有没有商品被选中
                    if ((int) totalPrice == 0) {
                        Toast.makeText(context, "未选中商品", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        //如果有商品被选中，跳到订单
                        if (totalPrice < 15.0) {
                            ToastUtil.showShort(context, "所选商品未满15元，无法进行配送");
                            return;
                        } else {
                            skipbill();
                        }

                    }
                }
                break;
            case R.id.go_mainpage:
                //跳转到首页
                ((MainActivity) getActivity()).setFirstPageFragment();
                break;
            case R.id.edit:
                if ("编辑".equals(Edit.getText().toString().trim())) {
                    Edit.setText("完成");
                    ShopCartsConfirm.setText("删除");
                    ShopCartsConfirm.setBackgroundColor(getResources().getColor(R.color.freedom_text));
                    //商品全部未选中
                    AllNoChecked();
                } else if ("完成".equals(Edit.getText().toString().trim())) {
                    Edit.setText("编辑");
                    ShopCartsConfirm.setText("结单");
                    ShopCartsConfirm.setBackgroundColor(getResources().getColor(R.color.green));
                    isAllChecked();
                }
                break;
            case R.id.back_press:
                //跳转到首页
                ((MainActivity) getActivity()).setFirstPageFragment();
                break;
            default:
                break;
        }
    }

    //跳到确认订单页面
    private void skipbill() {
        List<ShoppingCartBean.ResponseBean.ShoppingCartListBean> newList = new ArrayList<>();
        newList.clear();
        for (int i = 0; i < shoppingCartList.size(); i++) {
            if (shoppingCartList.get(i).isChoosed()) {
                newList.add(shoppingCartList.get(i));
            }
        }
        intent = new Intent(context, MyBillActivity.class);
        //序列化对象 传递数据
        intent.putExtra("Datas", (Serializable) newList);
        intent.putExtra("state", 2);
        startActivity(intent);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示")
                .setMessage("您确定要删除选中的商品？")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyDataSetChanged();
                        for (int i = 0; i < shoppingCartList.size(); i++) {
                            if (shoppingCartList.get(i).isChoosed()) {
                                cartId = shoppingCartList.get(i).getCartId();
                                initDelete(strToken, cartId);
                            }
                        }
                    }
                })
                .show();
    }

    private void initDelete(String strToken, int cartId) {
        final Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/batchDeleteShoppingCart.do?token=" + strToken + "&cartId=" + cartId)
                .build();
        final OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(context, R.string.no_net +"");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final DeleteShopCartBean deleteShopCartBean = gson.fromJson(strJson, DeleteShopCartBean.class);
                if (deleteShopCartBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    initSponse();
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            ToastUtil.showShort(context, (String) deleteShopCartBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });

    }

    private void AllNoChecked() {
        AllCheckBox.setChecked(false);
        for (int i = 0; i < shoppingCartList.size(); i++) {
            shoppingCartList.get(i).setChoosed(false);
        }
        adapter.notifyDataSetChanged();
        statistics();
    }
}
