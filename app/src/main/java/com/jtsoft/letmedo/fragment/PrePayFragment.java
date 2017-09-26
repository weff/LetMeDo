package com.jtsoft.letmedo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.activity.OrderDetailsActivity;
import com.jtsoft.letmedo.adapter.PrePayFragmentAdapter;
import com.jtsoft.letmedo.bean.BillOrderBean;
import com.jtsoft.letmedo.bean.CancelOrderBean;
import com.jtsoft.letmedo.custom.ReFreshListView;
import com.jtsoft.letmedo.spUtil.SharedpreferencesManager;
import com.jtsoft.letmedo.utils.Constant;
import com.jtsoft.letmedo.utils.Model.ToastUtil;
import com.jtsoft.letmedo.utils.NetWorkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/6/25.
 * 未付款页面
 */

public class PrePayFragment extends LazyLoadFragment implements PrePayFragmentAdapter.PayGoodsInterface{

    private View view;
    private Context context;
    private ReFreshListView mListView;
    private PrePayFragmentAdapter adapter;
    private List<BillOrderBean.ResponseBean.OrderListBean> orderList = new ArrayList<>();
    private int currentPage = 1;
    private int pageSize = 10;
    private int totalPage;
    private String strToken;


//    //视图初始化
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        context = getActivity();
//        view = LayoutInflater.from(context).inflate(R.layout.fragment_prepay, container, false);
//        return view;
//    }
    //加载布局显示
    @Override
    protected int setContentView() {
        context = getActivity();
        return R.layout.fragment_prepay;
    }

    //加载数据
    @Override
    protected void lazyLoad() {
        //控件初始化
        initView();
        //数据初始化
        initData();
    }

//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        //TODO
//        super.onActivityResult(requestCode, resultCode, data);
//    }

//    //控件、数据初始化
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        //控件初始化
//        initView();
//        //数据初始化
//        initData();
//    }

    private void initData() {
        strToken = SharedpreferencesManager.getToken();
        Log.e("TAG", "strTOKEN====" + strToken);
        initspone(strToken, currentPage, pageSize, 0, 0);
    }

    private void initspone(String strToken, final int current_Page, int pageSize, int i, int i1) {

        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/listOrder.do?orderStatus=" + i + "&deliveryType=" + i1 + "&token=" + strToken + "&pageSize=" + pageSize + "&pageNo=" + current_Page)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(context, "网络异常");
                        if (current_Page == 1) {
                            currentPage = 1;
                        } else {
                            currentPage--;
                        }
                        mListView.onFinish();
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final BillOrderBean billOrderBean = gson.fromJson(strJson, BillOrderBean.class);
                if (billOrderBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    final List<BillOrderBean.ResponseBean.OrderListBean> newList = billOrderBean.getResponse().getOrderList();
                    totalPage = billOrderBean.getResponse().getPage().getTotalPage();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mListView.onFinish();
                            if (newList != null && newList.size() > 0) {
                                orderList.addAll(newList);
                                setAdapter();
                            }
                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(context, (String) billOrderBean.getMessage());
                            mListView.onFinish();
                            return;
                        }
                    });
                }
            }
        });
    }

    private void setAdapter() {
        if (adapter == null) {
            adapter = new PrePayFragmentAdapter(context, orderList);
            adapter.setPayGoodsInterface(this);
            mListView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }


    private void initView() {
        mListView = (ReFreshListView) getContentView().findViewById(R.id.list);
        mListView.setOnFreshListener(new ReFreshListView.OnFreshListener() {
            @Override
            public void onDownPull() {
                if (totalPage == 1) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mListView.onFinish();
                        }
                    }, 2000);
                    return;
                }
                initNewDatas();
            }

            @Override
            public void onLoadMore() {
                if (currentPage >= totalPage) {
                    ToastUtil.showShort(context, "已经是最后的数据");
                    mListView.onFinish();
                    return;
                }
                initDatas();
            }
        });
    }

    //下拉刷新数据
    private void initNewDatas() {
        orderList.clear();
        initspone(strToken, 1, 10, 0, 0);
    }

    //加载更多数据
    private void initDatas() {
        currentPage++;
        initspone(strToken, currentPage, pageSize, 0, 0);
    }

    @Override
    public void doPay(int position) {
        int orderId = orderList.get(position).getOrderId();
        Intent intent = new Intent(context, OrderDetailsActivity.class);
        intent.putExtra("orderId",orderId);
        startActivity(intent);
    }

    @Override
    public void doCancel(final int position) {
        int orderId = orderList.get(position).getOrderId();
        //需要调用取消接口
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/cancelOrder.do?token=" + strToken + "&orderId=" + orderId)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(context,"网络请求异常");
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final CancelOrderBean cancelOrderBean = gson.fromJson(strJson, CancelOrderBean.class);
                if (cancelOrderBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            orderList.remove(position);
                            adapter.notifyDataSetChanged();
                            ToastUtil.showShort(context,"订单取消成功");
                        }
                    });
                }else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(context, (String) cancelOrderBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });

    }
}
