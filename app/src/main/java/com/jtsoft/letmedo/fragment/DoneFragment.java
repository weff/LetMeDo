package com.jtsoft.letmedo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.adapter.DoneFragmentAdapter;
import com.jtsoft.letmedo.bean.DoneFragmentBean;
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
 * 已完成页面
 */

public class DoneFragment extends Fragment {

    private Context context;
    private View view;
    private ReFreshListView mListView;
    private String strToken;
    private int currentPage = 1;
    private int PAGE_SIZE = 10;//一般一页的数目大于屏幕的显示
    private List<DoneFragmentBean.ResponseBean.OrderListBean> orderList = new ArrayList<>();
    private DoneFragmentAdapter adapter;
    private int totalPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        view = LayoutInflater.from(context).inflate(R.layout.fragment_done, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //控件初始化
        initView();
        //数据初始化
        initData();
    }

    private void initData() {
        strToken = SharedpreferencesManager.getToken();
        initSpone(strToken, currentPage, PAGE_SIZE, 3, 0);

    }

    private void initSpone(String strToken, final int current_page, int pageSize, int i, int i1) {
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/listOrder.do?token=" + strToken + "&pageNo=" + current_page + "&pageSize=" + pageSize
                        + "&orderStatus=" + i + "&deliveryType=" + i1)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(context, "网络请求异常");
                        if (current_page == 1) {
                            currentPage = 1;
                        }else {
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
                final DoneFragmentBean doneFragmentBean = gson.fromJson(strJson, DoneFragmentBean.class);
                if (doneFragmentBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    final List<DoneFragmentBean.ResponseBean.OrderListBean> newList = doneFragmentBean.getResponse().getOrderList();
                    totalPage = doneFragmentBean.getResponse().getPage().getTotalPage();
                    Log.e("Tagss", orderList + ".....");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mListView.onFinish();
                            if(newList!=null && newList.size()>0){
                                orderList.addAll(newList);
                                setAdapter();
                            }
                        }
                    });

                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(context, (String) doneFragmentBean.getMessage());
                            mListView.onFinish();
                            return;
                        }
                    });
                }
            }
        });
    }

    /**
     * 本方法用来setAdapter，全局调用
     */
    private void setAdapter() {
        //检查adapter
        if(adapter == null){
            adapter = new DoneFragmentAdapter(context, orderList);
            mListView.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }

    //下拉刷新显示新数据
    private void initNewDatas() {
        orderList.clear();
        initSpone(strToken,1,10,3,0);
    }

    //加载更多数据
    public void initDatas() {
        currentPage++;
        initSpone(strToken, currentPage, PAGE_SIZE, 3, 0);
        Log.e("TAG","orderlist.size===" + orderList.size());
    }

    //控件初始化
    private void initView() {
        mListView = (ReFreshListView) view.findViewById(R.id.refreshlistview);
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
            if(currentPage>=totalPage){
                ToastUtil.showShort(context,"已经是最后的数据了");
                mListView.onFinish();
                return;
            }
            initDatas();
        }
    });

    }
}
