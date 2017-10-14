package com.jtsoft.letmedo.fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.activity.CommonAdressActivity;
import com.jtsoft.letmedo.activity.CouponActivity;
import com.jtsoft.letmedo.activity.MyAccountActivity;
import com.jtsoft.letmedo.activity.MyOrderActivity;
import com.jtsoft.letmedo.activity.PersonalActivity;
import com.jtsoft.letmedo.activity.SetPasswordActivity;
import com.jtsoft.letmedo.bean.GetCouponCount;
import com.jtsoft.letmedo.bean.GetGoodsNumBean;
import com.jtsoft.letmedo.bean.PersonalMsgBean;
import com.jtsoft.letmedo.custom.CircleImageView;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/6/21.
 * 我的页面
 */

public class MyFragment extends Fragment implements View.OnClickListener {

    private View view;
    private CircleImageView HeaderImg;
    private TextView AcountName;
    private RelativeLayout OrderBill;
    private LinearLayout PrePay;
    private LinearLayout PreConfirm;
    private LinearLayout ProPay;
    private LinearLayout ConService;
    private RelativeLayout CommonAdress;
    private RelativeLayout Coupon;
    private RelativeLayout Acount;
    private Intent intent;
    private Context context;
    private PrePayFragment prePayFragment;
    private PreGetGoodsFragment preGetGoodsFragment;
    private DoneFragment doneFragment;
    private TextView SetPassword;


    private String strToken;
    private Request request;
    private OkHttpClient client;
    private Gson gson;
    private RelativeLayout mCountDesign;
    private TextView mCouponCount;
    private RelativeLayout mPrePay;
    private TextView mPreCount;
    private RelativeLayout mPreGet;
    private TextView mPreGetCount;
    private RelativeLayout mProPay;
    private TextView mProPayCount;

    //视图初始化
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        strToken = SharedpreferencesManager.getToken();
        view = LayoutInflater.from(context).inflate(R.layout.fragment_my, null, false);
        return view;
    }

    //数据初始化
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //提示设置登录密码的控件
        SetPassword = (TextView) view.findViewById(R.id.setpassword);
        //判断是否是快捷登录，如果是，就显示提醒用户设置登录密码；否则隐藏此提示
        if (SharedpreferencesManager.isLogin()) {
            if (SharedpreferencesManager.STATE_LOGIN == SharedpreferencesManager.FAST_LOGIN) {
                SetPassword.setVisibility(View.VISIBLE);
                SetPassword.setOnClickListener(this);
            } else if (SharedpreferencesManager.STATE_LOGIN == SharedpreferencesManager.LOGIN) {
                SetPassword.setVisibility(View.INVISIBLE);
            }
        }

        if (preGetGoodsFragment == null) {
            preGetGoodsFragment = new PreGetGoodsFragment();
        }
        if (prePayFragment == null) {
            prePayFragment = new PrePayFragment();
        }
        if (doneFragment == null) {
            doneFragment = new DoneFragment();
        }

        //控件初始化
        initView();

    }

    @Override
    public void onStart() {
        super.onStart();
        //数据初始化
        initData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            strToken = SharedpreferencesManager.getToken();
            getGoodsNum(strToken);
        }
    }

    private void initData() {
        OrderBill.setOnClickListener(this);
        PrePay.setOnClickListener(this);
        PreConfirm.setOnClickListener(this);
        ProPay.setOnClickListener(this);
        ConService.setOnClickListener(this);
        Coupon.setOnClickListener(this);
        CommonAdress.setOnClickListener(this);
        HeaderImg.setOnClickListener(this);
        Acount.setOnClickListener(this);

        //获取用户个人信息
        getAccountMsg(strToken);
        //获取优惠券数量
        getCouponCount(strToken);
        //获取待收货，待付款等商品数量
        getGoodsNum(strToken);
    }

    private void getGoodsNum(String strToken) {
        request = new Request.Builder()
                .url(Constant.CONSTANT + "/getOrdersAndCouponCount.do?token=" + strToken)
                .build();
        client = new OkHttpClient();
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
                gson = new Gson();
                final GetGoodsNumBean getGoodsNumBean = gson.fromJson(strJson, GetGoodsNumBean.class);
                if (getGoodsNumBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //未付款的订单数量
                            int unpaidCount = getGoodsNumBean.getResponse().getUnpaidCount();
                            //待收货的订单数量
                            int paidCount = getGoodsNumBean.getResponse().getPaidCount();
                            //已完成的订单数量
                            int receivedCount = getGoodsNumBean.getResponse().getReceivedCount();
                            if (unpaidCount == 0) {
                                mPrePay.setVisibility(View.INVISIBLE);
                            } else {
                                mPrePay.setVisibility(View.VISIBLE);
                                mPreCount.setText(unpaidCount + "");
                            }
                            if (paidCount == 0) {
                                mPreGet.setVisibility(View.INVISIBLE);
                            } else {
                                mPreGet.setVisibility(View.VISIBLE);
                                mPreGetCount.setText(paidCount + "");
                            }
                            if (receivedCount == 0) {
                                mProPay.setVisibility(View.INVISIBLE);
                            } else {
                                mProPay.setVisibility(View.VISIBLE);
                                mProPayCount.setText(receivedCount + "");
                            }
                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            ToastUtil.showShort(context, (String) getGoodsNumBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    private void getCouponCount(String strToken) {
        request = new Request.Builder()
                .url(Constant.CONSTANT + "/getCouponNum.do?token=" + strToken)
                .build();
        client = new OkHttpClient();
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
                gson = new Gson();
                final GetCouponCount getCouponCount = gson.fromJson(strJson, GetCouponCount.class);
                if (getCouponCount.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int availableCouponNum = getCouponCount.getResponse().getAvailableCouponNum();
                            mCouponCount.setText(availableCouponNum + "");
                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            ToastUtil.showShort(context, (String) getCouponCount.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    private void getAccountMsg(String strToken) {
        request = new Request.Builder()
                .url(Constant.CONSTANT + "/getCustomerInfo.do?token=" + strToken)
                .build();
        client = new OkHttpClient();
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
                gson = new Gson();
                final PersonalMsgBean personalMsgBean = gson.fromJson(strJson, PersonalMsgBean.class);
                if (personalMsgBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AcountName.setText(personalMsgBean.getResponse().getCustomerNickname());
                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            ToastUtil.showShort(context, (String) personalMsgBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        //头像控件
        HeaderImg = (CircleImageView) view.findViewById(R.id.about_my_personal);
        //用户账号控件
        AcountName = (TextView) view.findViewById(R.id.name_my);
        //订单控件
        OrderBill = (RelativeLayout) view.findViewById(R.id.my_bill_order);
        //待付款控件
        PrePay = (LinearLayout) view.findViewById(R.id.about_my_waiting_to_pay);
        //待收货控件
        PreConfirm = (LinearLayout) view.findViewById(R.id.about_my_waiting_to_confirm);
        //已完成控件
        ProPay = (LinearLayout) view.findViewById(R.id.about_my_already_donw);
        //售后服务控件
        ConService = (LinearLayout) view.findViewById(R.id.contact_service);
        //地址管理控件
        CommonAdress = (RelativeLayout) view.findViewById(R.id.common_address);
        //优惠券控件
        Coupon = (RelativeLayout) view.findViewById(R.id.my_game);
        //账户余额控件
        Acount = (RelativeLayout) view.findViewById(R.id.my_account);
        //可使用优惠券的控件
        mCountDesign = (RelativeLayout) view.findViewById(R.id.countdesign);
        //可使用优惠券数量控件
        mCouponCount = (TextView) view.findViewById(R.id.pay_count);
        //待付款数字显示父控件
        mPrePay = (RelativeLayout) view.findViewById(R.id.count_pre_pay);
        //待付款数字显示
        mPreCount = (TextView) view.findViewById(R.id.pre_pay_count);
        //待收货数字显示父控件
        mPreGet = (RelativeLayout) view.findViewById(R.id.count_pre_get);
        //待收货数字显示
        mPreGetCount = (TextView) view.findViewById(R.id.pre_get_count);
        //已完成数字显示父控件
        mProPay = (RelativeLayout) view.findViewById(R.id.count_pro_get);
        //已完成数字显示控件
        mProPayCount = (TextView) view.findViewById(R.id.pro_get_count);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //进入设置登录密码页面
            case R.id.setpassword:
                intent = new Intent(context, SetPasswordActivity.class);
                startActivity(intent);
                break;
            //进入订单页面
            case R.id.my_bill_order:
                intent = new Intent(context, MyOrderActivity.class);
                intent.putExtra("MyOrderActivity", 1);
                startActivity(intent);
                break;
            //进入地址管理页面
            case R.id.common_address:
                intent = new Intent(context, CommonAdressActivity.class);
                startActivity(intent);
                break;
            //待付款
            case R.id.about_my_waiting_to_pay:
                intent = new Intent(context, MyOrderActivity.class);
                intent.putExtra("MyOrderActivity", 1);
                startActivity(intent);
                break;
            //待收货
            case R.id.about_my_waiting_to_confirm:
                intent = new Intent(context, MyOrderActivity.class);
                intent.putExtra("MyOrderActivity", 2);
                startActivity(intent);
                break;
            //已完成
            case R.id.about_my_already_donw:
                intent = new Intent(context, MyOrderActivity.class);
                intent.putExtra("MyOrderActivity", 3);
                startActivity(intent);

                break;
            //售后服务
            case R.id.contact_service:
                showdialog();
                break;
            //优惠券
            case R.id.my_game:
//                //跳转到未用优惠券
                intent = new Intent(context, CouponActivity.class);
                startActivity(intent);
                break;
            case R.id.about_my_personal:
                //点击头像，进入个人中心页面
                intent = new Intent(context, PersonalActivity.class);
                startActivity(intent);
                break;
            case R.id.my_account:
                //跳到充值页面
                intent = new Intent(context, MyAccountActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void showdialog() {
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("客服电话")
                .setMessage("17327724683")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //拨打电话
                        call("17327724683");

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        getActivity().startActivity(intent);
    }
}
