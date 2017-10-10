package com.jtsoft.letmedo.fragment;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.adapter.MyselfAdapter;
import com.jtsoft.letmedo.bean.MySelfFragmentBean;
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
 * Created by Dragon on 2017/7/17.
 * 自提订单页面
 */
public class MySelfFragment extends LazyLoadFragment {

    private Context context;
    private View view;
    private ReFreshListView listView;
    private String strToken;

    public static int currentPage = 1;
    private static final int PAGE_SIZE = 10;//一般一页的数目大于屏幕的显示
    private List<MySelfFragmentBean.ResponseBean.OrderListBean> orderList = new ArrayList<>();
    private MyselfAdapter adapter;
    private int totalPage;

    //加载布局
    @Override
    protected int setContentView() {
        context = getActivity();
        return R.layout.fragment_myself;
    }

    //加载数据
    @Override
    protected void lazyLoad() {
        //控件初始化
        initView();
        //数据初始化
        initData();
    }

    private void initData() {
        strToken = SharedpreferencesManager.getToken();
        //自提订单接口
        initSpone(currentPage, PAGE_SIZE, strToken, 1);


    }

    private void initSpone(final int current_Page, int i, String strToken, int i1) {
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/listOrder.do?pageNo=" + currentPage + "&pageSize=" + i
                        + "&deliveryType=" + i1 + "&token=" + strToken)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(context, R.string.no_net +"");
                        listView.onFinish();
                        if (current_Page == 1) {
                            currentPage = 1;
                        } else {
                            currentPage--;
                        }
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Gson gson = new Gson();
                final MySelfFragmentBean mySelfFragmentBean = gson.fromJson(strJson, MySelfFragmentBean.class);
                final List<MySelfFragmentBean.ResponseBean.OrderListBean> newList = mySelfFragmentBean.getResponse().getOrderList();
                totalPage = mySelfFragmentBean.getResponse().getPage().getTotalPage();
                Log.e("Tagss", orderList + "");
                if (mySelfFragmentBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listView.onFinish();
                            if (newList != null && newList.size() > 0) {
                                orderList.addAll(newList);
                                setAdapter(orderList);
                            }

                        }
                    });

                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(context, (String) mySelfFragmentBean.getMessage());
                            return;
                        }
                    });
                }
            }
        });
    }

    private void setAdapter(final List<MySelfFragmentBean.ResponseBean.OrderListBean> orderList) {
        if (adapter == null) {
            adapter = new MyselfAdapter(context, orderList);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        //listview控件
        listView = (ReFreshListView) getContentView().findViewById(R.id.listview);
        listView.setOnFreshListener(new ReFreshListView.OnFreshListener() {
            @Override
            public void onDownPull() {
                if (totalPage == 1) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.onFinish();
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
                    listView.onFinish();
                    return;
                }
                loadData();
            }
        });
    }

    //下拉刷新请求数据
    private void initNewDatas() {
        initSpone(1,10,strToken,1);
    }


    //加载更多数据
    public void loadData() {
        currentPage++;
        initSpone(currentPage, 10, strToken, 1);
    }
}
