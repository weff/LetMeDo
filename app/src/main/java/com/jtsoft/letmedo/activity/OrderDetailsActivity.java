package com.jtsoft.letmedo.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.jtsoft.letmedo.MainActivity;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.adapter.OrderDetailsAdapter;
import com.jtsoft.letmedo.bean.AlipayBean;
import com.jtsoft.letmedo.bean.BalancePayBean;
import com.jtsoft.letmedo.bean.CancelOrderBean;
import com.jtsoft.letmedo.bean.JsonBeanUserRegister;
import com.jtsoft.letmedo.bean.OrderDetailsBean;
import com.jtsoft.letmedo.custom.ListViewForScrollView;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;
import com.jtsoft.letmedo.utils.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/28.
 * 订单详情页面
 */

public class OrderDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    //微信APP_ID
    private static String WXAPP_ID = "wxa6e222a74f4e465b";
    private ImageView Back;
    private TextView Tittle;
    private TextView Edit;
    private TextView Orders;
    private TextView OrdersStates;
    private TextView Pick;
    private TextView GetMethod;
    private TextView Address;
    private TextView mTime;
    private ListViewForScrollView mListView;
    private RelativeLayout coupon;
    private TextView mDistance;
    private TextView mFreed;
    private TextView mActualPayment;
    private Button mDelete;
    private Button mPay;
    private TextView Mark;
    private AlertDialog dialog;
    private LayoutInflater inflater;
    private Button mConfrimPay;
    private CheckBox mWXCheckBox;
    private CheckBox mBalanceCheckBox;
    private ImageView mCancle;
    private IWXAPI mIwxapi;
    private CheckBox mAliPayCheckBox;
    private List<OrderDetailsBean.ResponseBean.OrderBean.OrderGoodsListBean> orderGoodsList;
    private TextView mRemainTime;
    private String orderCode;
    private int delStatus;
    private String contactPersion;
    private String contactMobile;
    private String deliveryAddress;
    private long createTime;
    private double orderPrice;
    private double couponPrice;
    private double distance;
    private TextView mGoodsPrice;
    private TextView mCouponPrice;
    private TextView mSendPrice;
    private double expressPrice;
    private String strToken;
    private int orderId;
    private MyCount mc;
    private long mMin;
    private long mSec;
    private Date date1;
    private long showMin;
    private long showSec;
    private long showTime;
    private RelativeLayout mRelay;
    private TextView mCode;
    private EditText etCode;
    private RelativeLayout mWXRelay;
    private RelativeLayout mAliRelay;
    private RelativeLayout mPayRelay;
    private static final int SDK_PAY_FLAG = 1;
    //计时器
    private TimeCount timer;
    private Intent intent;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        mIwxapi = WXAPIFactory.createWXAPI(this, WXAPP_ID, false);
        mIwxapi.registerApp(WXAPP_ID);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case SDK_PAY_FLAG:
                        PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                        String resultInfo = payResult.getResult();
                        String resultStatus = payResult.getResultStatus();
                        Log.e("TAG","resultStatus :" + resultStatus);
                        if (TextUtils.equals(resultStatus, "9000")) {
                            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                            Toast.makeText(OrderDetailsActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        } else {
                            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                            Toast.makeText(OrderDetailsActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        };
        //控件初始化
        initView();
        //数据初始化
        initData();
    }

    private void initData() {
        Tittle.setText(R.string.orderdetails);
        //获取从我的订单从过来的数据
        Intent intent = getIntent();
        orderId = intent.getIntExtra("orderId", -1);
        Log.e("TAG", "orderId44=====" + orderId);
        //获取订单详情页面展示接口
        initSpone(orderId);
        //立即支付弹出支付窗口
//        initPay();
        Back.setOnClickListener(this);
        Mark.setOnClickListener(this);
        timer = new TimeCount(60000, 1000);
    }

    private void initCancel(int orderId) {
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/cancelOrder.do?token=" + strToken + "&orderId=" + orderId)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(OrderDetailsActivity.this, "网络异常");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                CancelOrderBean cancelOrderBean = gson.fromJson(strJson, CancelOrderBean.class);
                if (cancelOrderBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    Intent intent = new Intent(OrderDetailsActivity.this, MainActivity.class);
                    intent.putExtra("myfragment", 3);
                    startActivity(intent);
                }
            }
        });
    }

    //获取订单详情页面展示接口
    private void initSpone(int orderId) {
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/getOrder.do?token=" + strToken + "&orderId=" + orderId)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(OrderDetailsActivity.this, "网络异常");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final OrderDetailsBean orderDetailsBean = gson.fromJson(strJson, OrderDetailsBean.class);
                if (orderDetailsBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    //订单详情，商品集合
                    orderGoodsList = orderDetailsBean.getResponse().getOrder().getOrderGoodsList();
                    //获取订单号
                    orderCode = orderDetailsBean.getResponse().getOrder().getOrderCode();
                    //获取商品状态
                    delStatus = orderDetailsBean.getResponse().getOrder().getDelStatus();
                    //获取联系人
                    contactPersion = orderDetailsBean.getResponse().getOrder().getContactPersion();
                    //获取联系电话
                    contactMobile = orderDetailsBean.getResponse().getOrder().getContactMobile();
                    //获取收货人地址
                    deliveryAddress = orderDetailsBean.getResponse().getOrder().getDeliveryAddress();
                    //获取下单时间
                    createTime = orderDetailsBean.getResponse().getOrder().getCreateTime();
                    //获取商品金额
                    orderPrice = orderDetailsBean.getResponse().getOrder().getOrderPrice();
                    //获取优惠券金额
                    couponPrice = orderDetailsBean.getResponse().getOrder().getCouponPrice();
                    //获取配送距离
                    distance = orderDetailsBean.getResponse().getOrder().getDistance();
                    //获取配送费
                    expressPrice = orderDetailsBean.getResponse().getOrder().getExpressPrice();
                    Log.e("TAGG", "createTime:" + createTime + "orderPrice:" + orderPrice + "couponPrice:" + couponPrice + "distance:" + distance + "expressPrice:" + expressPrice);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            OrderDetailsAdapter adapter = new OrderDetailsAdapter(OrderDetailsActivity.this, orderGoodsList);
                            mListView.setAdapter(adapter);
                            //订单号赋值
                            Orders.setText("订单号：" + orderCode);
                            //状态赋值
                            if (delStatus == 0) {
                                OrdersStates.setText("状态：待付款");
                            } else if (delStatus == 1) {
                                OrdersStates.setText("状态：待收货");
                            } else if (delStatus == 3) {
                                OrdersStates.setText("状态：已收货");
                            } else if (delStatus == 4) {
                                OrdersStates.setText("状态：已取消");
                            }
                            //联系人
                            Pick.setText(contactPersion);
                            //联系电话
                            GetMethod.setText(contactMobile);
                            //收货人地址
                            Address.setText(deliveryAddress);

                            //获取下单时间
                            date1 = new Date(createTime);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String data = sdf.format(date1);
                            mTime.setText(data + "");

                            long timestamp = orderDetailsBean.getTimestamp();
                            //当前时间
                            Date date2 = new Date(timestamp);
                            //时间差
                            long diff = date2.getTime() - date1.getTime();//这样得到的差值是微秒级别
                            showTime = 1 * 60 * 60 * 1000 - diff;
                            //倒计时开始
                            mc = new MyCount(showTime, 1000);
                            mc.start();

                            //商品金额
                            mGoodsPrice.setText("" + orderPrice);
                            //优惠券减去价钱
                            mCouponPrice.setText("- ￥" + couponPrice);
                            //配送距离显示
                            double mDistance = distance / 1000;
                            String format = String.format("%.2f", mDistance);
                            OrderDetailsActivity.this.mDistance.setText(format + "");
                            //配送费显示
                            mSendPrice.setText(expressPrice + "");
                            //实付款显示
                            double payResult = orderPrice + expressPrice;
                            mActualPayment.setText("￥" + payResult);
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(OrderDetailsActivity.this, (String) orderDetailsBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });

    }

    class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mMin = millisUntilFinished / 1000 / 60;
            mSec = millisUntilFinished / 1000 % 60;
            mRemainTime.setText(" 去支付(还剩余" + mMin + "分" + mSec + "秒) ");
        }

        @Override
        public void onFinish() {
            if (mMin == 0 && mSec == 0) {
                finish();
            }
        }
    }

    private void initView() {
        //返回按键控件
        Back = (ImageView) findViewById(R.id.back_press);
        //标题控件
        Tittle = (TextView) findViewById(R.id.title_name);
        //编辑控件
        Edit = (TextView) findViewById(R.id.edit);
        Edit.setVisibility(View.INVISIBLE);
        //订单号控件
        Orders = (TextView) findViewById(R.id.orders);
        //状态控件
        OrdersStates = (TextView) findViewById(R.id.orderstate);
        //自提或者收件人控件
        Pick = (TextView) findViewById(R.id.pick);
        //电话或者自提
        GetMethod = (TextView) findViewById(R.id.getMethod);
        //地址
        Address = (TextView) findViewById(R.id.address);
        //下单时间控件
        mTime = (TextView) findViewById(R.id.time);
        //显示未付款倒计时时间空间
        mRemainTime = (TextView) findViewById(R.id.show_remaintime);
        //订单商品控件
        mListView = (ListViewForScrollView) findViewById(R.id.listview);
        //商品金额
        mGoodsPrice = (TextView) findViewById(R.id.goodsprice);
        //优惠券布局控件
        coupon = (RelativeLayout) findViewById(R.id.coupon);
        //优惠券减去价格显示
        mCouponPrice = (TextView) findViewById(R.id.pay_coupon);
        //配送距离控件
        mDistance = (TextView) findViewById(R.id.distance);
        //配送条件控件
        Mark = (TextView) findViewById(R.id.mark);
        //配送费控件
        mSendPrice = (TextView) findViewById(R.id.send_money);
        //配送费控件
        mFreed = (TextView) findViewById(R.id.send_money);
        //实际付款控件
        mActualPayment = (TextView) findViewById(R.id.Actual_payment);
        //删除订单控件
        mDelete = (Button) findViewById(R.id.delete);
        //付款控件
        mPay = (Button) findViewById(R.id.pay);
        mPay.setOnClickListener(this);
        strToken = SharedpreferencesManager.getToken();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_press:
                finish();
                break;
            //配送条件控件
            case R.id.mark:
                //弹出配送条件弹窗
                showMarkWindow();
                break;
            //取消订单
            case R.id.delete:
                //取消订单接口
                initCancel(orderId);
                break;
            case R.id.pay:
                //付款弹窗
                showPopupWindow();
                break;
            default:
                break;
        }
    }

    //弹出支付弹窗
    private void showPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_pay, null);
        final PopupWindow popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.popupAnimation);
        popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(view);

        //单选付款按钮
        //微信选择按钮
        mWXCheckBox = (CheckBox) view.findViewById(R.id.wxCheckBox);
        //余额选择按钮
        mBalanceCheckBox = (CheckBox) view.findViewById(R.id.balanceCheckBox);
        //支付宝选择按钮
        mAliPayCheckBox = (CheckBox) view.findViewById(R.id.alipayCheckBox);
        //余额支付时，弹出的验证码布局
        mRelay = (RelativeLayout) view.findViewById(R.id.relay);
        //微信支付父控件
        mWXRelay = (RelativeLayout) view.findViewById(R.id.relay2);
        //支付宝支付父控件
        mAliRelay = (RelativeLayout) view.findViewById(R.id.relay1);
        //余额支付父控件
        mPayRelay = (RelativeLayout) view.findViewById(R.id.relay3);
        //获取验证码控件
        mCode = (TextView) view.findViewById(R.id.code);
        //输入验证码控件
        etCode = (EditText) view.findViewById(R.id.et_code);
        //叉去按钮
        mCancle = (ImageView) view.findViewById(R.id.pay_cancel);

        //微信支付控件
        mWXRelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWXCheckBox.isChecked();
                mWXCheckBox.setVisibility(View.VISIBLE);
                mWXCheckBox.setChecked(true);
                mAliPayCheckBox.setChecked(false);
                mAliPayCheckBox.setPressed(false);
                mAliPayCheckBox.setFocusable(false);
                mAliPayCheckBox.setVisibility(View.INVISIBLE);
                mBalanceCheckBox.setChecked(false);
                mBalanceCheckBox.setPressed(false);
                mBalanceCheckBox.setFocusable(false);
                mBalanceCheckBox.setVisibility(View.INVISIBLE);
                mRelay.setVisibility(View.GONE);
            }
        });
        //支付宝支付控件
        mAliRelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAliPayCheckBox.isChecked();
                mAliPayCheckBox.setVisibility(View.VISIBLE);
                mAliPayCheckBox.setChecked(true);
                mWXCheckBox.setChecked(false);
                mWXCheckBox.setPressed(false);
                mWXCheckBox.setFocusable(false);
                mWXCheckBox.setVisibility(View.INVISIBLE);
                mBalanceCheckBox.setChecked(false);
                mBalanceCheckBox.setPressed(false);
                mBalanceCheckBox.setFocusable(false);
                mBalanceCheckBox.setVisibility(View.INVISIBLE);
                mRelay.setVisibility(View.GONE);
            }
        });
        //余额支付
        mPayRelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBalanceCheckBox.isChecked();
                mBalanceCheckBox.setVisibility(View.VISIBLE);
                mBalanceCheckBox.setChecked(true);
                mAliPayCheckBox.setChecked(false);
                mAliPayCheckBox.setPressed(false);
                mAliPayCheckBox.setFocusable(false);
                mAliPayCheckBox.setVisibility(View.INVISIBLE);
                mWXCheckBox.setChecked(false);
                mWXCheckBox.setPressed(false);
                mWXCheckBox.setFocusable(false);
                mWXCheckBox.setVisibility(View.INVISIBLE);
                mRelay.setVisibility(View.VISIBLE);
            }
        });

        //验证码按钮点击获取验证码逻辑
        mCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timer != null) {
                    timer.cancel();
                }
                Request request = new Request.Builder()
                        .url(Constant.CONSTANT + "/sendVerifyCode.do?mobile=" + SharedpreferencesManager.getUserphone())
                        .build();
                OkHttpClient client = new OkHttpClient();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showShort(OrderDetailsActivity.this, "网络异常");
                                return;
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String strJson = response.body().string();
                        Gson gson = new Gson();
                        final JsonBeanUserRegister jsonBeanUserRegister = gson.fromJson(strJson, JsonBeanUserRegister.class);
                        if (jsonBeanUserRegister.getCode() == NetWorkUtils.CODE_SUCCESS) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showShort(OrderDetailsActivity.this, "获取验证码成功");
                                    return;
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showShort(OrderDetailsActivity.this, (String) jsonBeanUserRegister.getMessage());
                                    return;
                                }
                            });
                        }
                    }
                });
                timer.start();
            }
        });

        //确认支付按钮
        mConfrimPay = (Button) view.findViewById(R.id.confirm_pay);
        mConfrimPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mWXCheckBox.isChecked()) {
                    mWXCheckBox.setChecked(true);
                    mAliPayCheckBox.setChecked(false);
                    mBalanceCheckBox.setChecked(false);
                    mRelay.setVisibility(View.GONE);
                    //接着就是微信支付逻辑
//                    WetChatPay(jsonBeanAliPayText.getSignOrderStr());

                } else if (mBalanceCheckBox.isChecked()) {
                    mBalanceCheckBox.setChecked(true);
                    mAliPayCheckBox.setChecked(false);
                    mWXCheckBox.setChecked(false);
                    mRelay.setVisibility(View.VISIBLE);
                    //余额支付逻辑
                    if (etCode.getText().toString().trim().equals("")) {
                        ToastUtil.showShort(OrderDetailsActivity.this, "验证码不能为空");
                        return;
                    } else {
                        //进行余额支付逻辑
                        BalancePay(strToken,orderId,3,etCode.getText().toString());
                    }
                } else if (mAliPayCheckBox.isChecked()) {
                    mAliPayCheckBox.setChecked(true);
                    mWXCheckBox.setChecked(false);
                    mBalanceCheckBox.setChecked(false);
                    mRelay.setVisibility(View.GONE);
                    //支付宝支付逻辑
                    AliPay(orderId,5,strToken);
                }
            }
        });


        mCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }
    //支付宝支付逻辑
    private void AliPay(int orderId, int i, String strToken) {
        final Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/payOrder.do?orderId=" + orderId + "&payType=" + i + "&token=" + strToken)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                AlipayBean alipayBean = gson.fromJson(strJson, AlipayBean.class);
                if (alipayBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    String orderStr = alipayBean.getResponse().getOrderStr();
                    Log.e("TAG","orderStr===" + orderStr);
                            PayTask alipay = new PayTask(OrderDetailsActivity.this);
                            Map<String, String> result = alipay.payV2(orderStr, true);
                            Message msg = Message.obtain();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            mHandler.sendMessage(msg);
                }
            }
        });
    }

    //余额支付逻辑
    private void BalancePay(String strToken, int orderId, int i, String s) {
        Log.e("TAG","orderId==" + orderId + ":i==" + i + ":s==" + s);
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/payOrder.do?token=" + strToken + "&orderId=" + orderId
                 + "&payType=" + i + "&smsCode=" + s)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(OrderDetailsActivity.this, "网络异常");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final BalancePayBean balancePayBean = gson.fromJson(strJson, BalancePayBean.class);
                if (balancePayBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(OrderDetailsActivity.this,"支付成功");
                            intent = new Intent(OrderDetailsActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(OrderDetailsActivity.this, (String) balancePayBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    //微信支付逻辑
    private void WetChatPay(String payInfo) {
        String str = payInfo;
        //TODO
        PayReq mpayReq = new PayReq();
        mIwxapi.sendReq(mpayReq);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderDetailsActivity.this, MainActivity.class);
        intent.putExtra("myfragment", 3);
        startActivity(intent);
    }


    //弹出配送条件的窗口
    private void showMarkWindow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetailsActivity.this);
        builder.setTitle("运费计算规则")
                .setMessage("1.三公里范围内，订单不满49元配送4元;" +
                        "     2.三公里范围内，满49元包邮")
                .setNegativeButton("取消", null)
                .show();
    }

    //获取验证码倒计时
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            mCode.setText(R.string.resettest);
            mCode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            mCode.setClickable(false);
            mCode.setText(millisUntilFinished / 1000 + "秒");
        }
    }
}
