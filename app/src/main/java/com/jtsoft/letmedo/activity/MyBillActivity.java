package com.jtsoft.letmedo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.bean.GetSendConditionBean;
import com.jtsoft.letmedo.bean.GetUseCoupon;
import com.jtsoft.letmedo.bean.GoodsOrderDetailBean;
import com.jtsoft.letmedo.bean.ShoppingCartBean;
import com.jtsoft.letmedo.bean.SubmitOrderBean;
import com.jtsoft.letmedo.custom.ListViewForScrollView;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2017/8/18.
 * 确认订单
 */

public class MyBillActivity extends AppCompatActivity implements View.OnClickListener {

    private ScrollView mScrollView;
    private ImageView Back;
    private TextView Tittle;
    private TextView Edit;
    private ListViewForScrollView mListView;
    private Intent intent;
    private RelativeLayout seleceAddress;
    private RelativeLayout selectCoupon;
    private TextView CouponNum;
    private TextView mGoodsMoney;
    private TextView distance;
    private TextView mMark;
    private TextView distanceMoney;
    private TextView mPayMoney;
    private Button mOrder;
    private String result;
    private List<ShoppingCartBean.ResponseBean.ShoppingCartListBean> datas;
    private double latitude;
    private double longitude;
    private String strToken;
    private TextView mShopName;
    private int state;
    private RelativeLayout address_oen;
    private TextView contentName;
    private TextView contentPhone;
    private TextView contentAddress;
    private String contentname;
    private String contentphone;
    private String contentaddress;
    private String provinceName;
    private String cityName;
    private String districtName;
    private String resultDistance;
    private List<GetUseCoupon.ResponseBean.CouponListBean> couponList;
    private int addressID;
    private double couponPrice;
    private String b = "";
    private String a = "";
    private LinearLayout mMyBillLayout;
    private TextView CouponPrice;
    private int deliveryPrice;
    private String shopCartId;
    private int sendPrice;
    private double TotalGoodsPrice;
    private int CartId;
    private int goodsId;
    private ImageView goodsPic;
    private TextView goodsname;
    private TextView goodsprice;
    private TextView goodsnum;
    private String mGoodsId;
    private String couponReceiveId = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybill);

        //控件初始化
        initView();
        //数据初始化
        initData();
    }

    private void initData() {
        Edit.setVisibility(View.INVISIBLE);
        Tittle.setText("确认订单");
        Back.setOnClickListener(this);
        //从商品详情传递的数据
        Intent intent = getIntent();
        state = intent.getIntExtra("state", -1);
        if (state == 1) {
            mListView.setVisibility(View.INVISIBLE);
            goodsPic.setVisibility(View.VISIBLE);
            goodsname.setVisibility(View.VISIBLE);
            goodsprice.setVisibility(View.VISIBLE);
            goodsnum.setVisibility(View.VISIBLE);
            goodsId = intent.getIntExtra("goodsId", -1);
            mGoodsId = String.valueOf(goodsId);
            String goodsName = intent.getStringExtra("goodsName");
            double goodsPrice = intent.getDoubleExtra("goodsPrice", -1);
            String goodsImg = intent.getStringExtra("goodsImg");
            Log.e("GIG", goodsPrice + "");
            mGoodsMoney.setText("￥" + goodsPrice);
            TotalGoodsPrice = goodsPrice;
            //将数据展示到控件上
            Glide.with(MyBillActivity.this).load(goodsImg).into(goodsPic);
            goodsname.setText(goodsName);
            goodsprice.setText(goodsPrice + "");
            goodsnum.setText("×" + 1);
        } else {
            mListView.setVisibility(View.VISIBLE);
            goodsPic.setVisibility(View.INVISIBLE);
            goodsname.setVisibility(View.INVISIBLE);
            goodsprice.setVisibility(View.INVISIBLE);
            goodsnum.setVisibility(View.INVISIBLE);
            datas = (List<ShoppingCartBean.ResponseBean.ShoppingCartListBean>) intent.getSerializableExtra("Datas");
            //从购物车获取数据
            //获取购物车传过来商品的ID
            for (int i = 0; i < datas.size(); i++) {
                //获取每种商品的价格
                double univalent = datas.get(i).getNum() * datas.get(i).getGoods().getPrice();
                TotalGoodsPrice += univalent;
                CartId = datas.get(i).getCartId();
                int GoodsId = datas.get(i).getGoodsId();
                b += CartId + ",";
                a += GoodsId + ",";
            }
            Log.e("TAG", "totalprice" + TotalGoodsPrice);
            shopCartId = b.substring(0, b.length() - 1);
            mGoodsId = a.substring(0, a.length() - 1);
            //购买商品总金额
            result = String.format("%.2f", TotalGoodsPrice);
            mGoodsMoney.setText("￥" + result);
            Log.e("TAG", "b======" + shopCartId);
            MyBillAdapter adapter = new MyBillAdapter(this);
            mListView.setAdapter(adapter);
        }
        //获取实际付款订单
        GetPayMoney();
    }

    private void GetPayMoney() {
        //实际付款总金额
        double mTotalPrice = TotalGoodsPrice + sendPrice - couponPrice;
        result = String.format("%.2f", mTotalPrice);
        mPayMoney.setText("" + result);
    }

    private void initView() {
        //scrollview控件
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        //返回控件
        Back = (ImageView) findViewById(R.id.back_press);
        //标题控件
        Tittle = (TextView) findViewById(R.id.title_name);
        //编辑控件
        Edit = (TextView) findViewById(R.id.edit);
        //listview控件
        mListView = (ListViewForScrollView) findViewById(R.id.listview);
        mListView.setVisibility(View.INVISIBLE);
        //单个商品的控件
        goodsPic = (ImageView) findViewById(R.id.picture);
        goodsPic.setVisibility(View.INVISIBLE);
        goodsname = (TextView) findViewById(R.id.goodsname);
        goodsname.setVisibility(View.INVISIBLE);
        goodsprice = (TextView) findViewById(R.id.goodsprice);
        goodsprice.setVisibility(View.INVISIBLE);
        goodsnum = (TextView) findViewById(R.id.goodsnum);
        goodsnum.setVisibility(View.INVISIBLE);
        //选择收货地址控件
        seleceAddress = (RelativeLayout) findViewById(R.id.address_null);
        seleceAddress.setVisibility(View.VISIBLE);
        seleceAddress.setOnClickListener(this);
        //选择优惠券控件
        selectCoupon = (RelativeLayout) findViewById(R.id.coupon);
        selectCoupon.setOnClickListener(this);
        //优惠券可使用数量
        CouponNum = (TextView) findViewById(R.id.couponNum);
        //选取优惠券的价格
        CouponPrice = (TextView) findViewById(R.id.coupon_ticket);
        //商品金额控件
        mGoodsMoney = (TextView) findViewById(R.id.goodsmoney);
        //配送距离控件
        distance = (TextView) findViewById(R.id.distance);
        //配送条件控件
        mMark = (TextView) findViewById(R.id.mark);
        mMark.setOnClickListener(this);
        //配送费控件
        distanceMoney = (TextView) findViewById(R.id.send_money);
        //配送店铺控件
        mShopName = (TextView) findViewById(R.id.shopName);
        //实付款控件
        mPayMoney = (TextView) findViewById(R.id.Actual_payment);
        //立即下单按钮
        mOrder = (Button) findViewById(R.id.order);
        mOrder.setOnClickListener(this);
        //有收货地址的控件
        address_oen = (RelativeLayout) findViewById(R.id.edit_address);
        address_oen.setVisibility(View.INVISIBLE);
        address_oen.setOnClickListener(this);
        //收货人姓名
        contentName = (TextView) findViewById(R.id.name_address);
        //收货联系电话
        contentPhone = (TextView) findViewById(R.id.phonenumber_address);
        //收货地址
        contentAddress = (TextView) findViewById(R.id.address);
        //整个页面的布局控件
        mMyBillLayout = (LinearLayout) findViewById(R.id.mybii_layout);
        //获取token值
        strToken = SharedpreferencesManager.getToken();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_press:
                //返回到购物车
                finish();
                break;
            //收货地址
            case R.id.address_null:
                //跳到地址管理，且把选择的地址返回显示并且调配送费接口
                intent = new Intent(this, CommonAdressActivity.class);
                intent.putExtra("goodsaddress", NetWorkUtils.SHOPSTORE_ADDRESS);
                startActivityForResult(intent, NetWorkUtils.FLAG_GOODS_ADDRESS);
                break;
            case R.id.edit_address:
                //跳到地址管理，且把选择的地址返回显示并且调配送费接口
                intent = new Intent(this, CommonAdressActivity.class);
                intent.putExtra("goodsaddress", NetWorkUtils.SHOPSTORE_ADDRESS);
                startActivityForResult(intent, NetWorkUtils.FLAG_GOODS_ADDRESS);
                break;
            //优惠券
            case R.id.coupon:
                //弹出显示优惠券页面
                intent = new Intent(MyBillActivity.this, UseCouponActivity.class);
                intent.putExtra("totalprice", TotalGoodsPrice);
                intent.putExtra("goodsId", mGoodsId);
                startActivityForResult(intent, NetWorkUtils.USECOUPON);
                break;
            //配送条件
            case R.id.mark:
                //弹出显示配送条件页面
                showMarkWindow();
                break;
            //立即下单
            case R.id.order:
                //到订单详情页面
                //首先进行判断收货地址是否为空
                if (seleceAddress.getVisibility() == View.VISIBLE) {
                    ToastUtil.showShort(this, "请先选择地址");
                    return;
                } else {
                    //跳到订单详情
                    if (state == 1) {
                        initsubmitGoods();//mGoodsId, couponReceiveId, "", addressID
                    } else {
                        Log.e("TTAAGG", "goodsIds：" + shopCartId + "couponId：" + couponReceiveId + "addressID：" + addressID);
                        initsubmit();//购物车下单接口shopCartId, couponReceiveId, "", addressID
                    }

                }
                break;
        }

    }

    private void initsubmitGoods() {//final String mGoodsId, Integer couponId, String s, int addressID
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/submitGoodsOrder.do?token=" + strToken + "&goodsId=" + mGoodsId + "&couponReceiveId=" + couponReceiveId
                        + "&remarke=" + "" + "&addressId=" + addressID)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(MyBillActivity.this, "网络请求异常");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final GoodsOrderDetailBean goodsOrderDetailBean = gson.fromJson(strJson, GoodsOrderDetailBean.class);
                if (goodsOrderDetailBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    int orderId = goodsOrderDetailBean.getResponse().getOrderId();
                    intent = new Intent(MyBillActivity.this, OrderDetailsActivity.class);
                    intent.putExtra("orderId", orderId);
                    startActivity(intent);
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(MyBillActivity.this, (String) goodsOrderDetailBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    //弹出配送条件的窗口
    private void showMarkWindow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyBillActivity.this);
        builder.setTitle("运费计算规则")
                .setMessage("1.三公里范围内，订单不满49元配送4元;" +
                        "     2.三公里范围内，满49元包邮")
                .setNegativeButton("取消", null)
                .show();
    }

    //跳转到订单详情
    private void initsubmit() {//String b, Integer couponReceiveId, String s, int addressID
        Log.e("TAG", "shoppingCartIds :" + b + "couponReceiveId :" + couponReceiveId + "remarke:" + "" + "addressId:" + addressID);
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/submitShoppingCartOrder.do?token=" + strToken + "&shoppingCartIds=" + b + "&couponReceiveId="
                        + couponReceiveId + "&remarke=" + "" + "&addressId=" + addressID)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(MyBillActivity.this, "网络请求异常");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final SubmitOrderBean submitOrderBean = gson.fromJson(strJson, SubmitOrderBean.class);
                if (submitOrderBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    //从确认订单跳转到订单详情页
                    int orderId = submitOrderBean.getResponse().getOrderId();
                    intent = new Intent(MyBillActivity.this, OrderDetailsActivity.class);
                    intent.putExtra("orderId", orderId);
                    Log.e("TAG", "orderId=====" + orderId);
                    startActivity(intent);
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(MyBillActivity.this, (String) submitOrderBean.getMessage());
                            return;
                        }
                    });
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //添加收货地址的返回结果
        if (requestCode == NetWorkUtils.FLAG_GOODS_ADDRESS) {
            if (resultCode == NetWorkUtils.FALG_GOODS_ADDRESS_BACK) {
                latitude = data.getDoubleExtra("latitude", -1);
                longitude = data.getDoubleExtra("longitude", -1);
                contentname = data.getStringExtra("contentname");
                contentphone = data.getStringExtra("contentphone");
                contentaddress = data.getStringExtra("contentaddress");
                provinceName = data.getStringExtra("provinceName");
                cityName = data.getStringExtra("cityName");
                districtName = data.getStringExtra("districtName");
                addressID = data.getIntExtra("addressID", -1);
                Log.e("TAG", "latitude:" + latitude);
                //调用获取配送费接口
                initsendcount(latitude, longitude, strToken, TotalGoodsPrice);
            }
        }
        //添加优惠券的返回结果
        if (requestCode == NetWorkUtils.USECOUPON) {
            if (resultCode == NetWorkUtils.USECOUPON_RESULT) {
                couponReceiveId = data.getStringExtra("couponReceiveId");
                Log.e("TAG", "couponReceiveId--=====" + couponReceiveId);
                couponPrice = data.getDoubleExtra("couponPrice", -1);
                CouponPrice.setText("- ￥" + couponPrice);
                //实际付款总金额
                GetPayMoney();

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initsendcount(double latitude, double longitude, String strToken, final double totalprice) {
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/getDeliveryInfo.do?longitude=" + longitude + "&latitude=" + latitude + "&productPrice=" + totalprice + "&token=" + strToken)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showShort(MyBillActivity.this, "网络请求异常");
                                return;
                            }
                        });

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final GetSendConditionBean getSendConditionBean = gson.fromJson(strJson, GetSendConditionBean.class);
                if (getSendConditionBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    deliveryPrice = getSendConditionBean.getResponse().getDeliveryInfo().getDeliveryPrice();
                    final double distanceCount = getSendConditionBean.getResponse().getDeliveryInfo().getDistance();
                    resultDistance = String.format("%.2f", distanceCount);
                    final String shopName = getSendConditionBean.getResponse().getDeliveryInfo().getShopName();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            seleceAddress.setVisibility(View.INVISIBLE);
                            address_oen.setVisibility(View.VISIBLE);
                            contentName.setText(contentname);
                            contentPhone.setText(contentphone);
                            contentAddress.setText(provinceName + cityName + districtName + contentaddress);
                            distance.setText(resultDistance + "公里");
                            //展示配送费控件
                            distanceMoney.setText(deliveryPrice + "");
                            //商铺名
                            mShopName.setText(shopName);

                            GetPayMoney();

                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(MyBillActivity.this, (String) getSendConditionBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }


    //内部类adapter
    private class MyBillAdapter extends BaseAdapter {
        private Context context;

        public MyBillAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
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
                convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_mybill, parent, false);
                holder.goodsPic = (ImageView) convertView.findViewById(R.id.picture);
                holder.goodsname = (TextView) convertView.findViewById(R.id.goodsname);
                holder.goodsprice = (TextView) convertView.findViewById(R.id.goodsprice);
                holder.goodsnum = (TextView) convertView.findViewById(R.id.goodsnum);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            ShoppingCartBean.ResponseBean.ShoppingCartListBean listBean = datas.get(position);
            holder.goodsname.setText(listBean.getGoods().getName());
            holder.goodsprice.setText("￥" + listBean.getGoods().getPrice());
            holder.goodsnum.setText("×" + listBean.getNum());
            Glide.with(context).load(listBean.getGoods().getImage()).into(holder.goodsPic);
            return convertView;
        }

        class ViewHolder {
            private ImageView goodsPic;
            private TextView goodsname, goodsprice, goodsnum;

        }
    }
}
