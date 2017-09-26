package com.jtsoft.letmedo.fragment;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.google.gson.Gson;
import com.jtsoft.letmedo.R;
import com.jtsoft.letmedo.adapter.PreGetGoodsAdapter;
import com.jtsoft.letmedo.bean.PreGetGoodsBean;
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
 * 待收货页面
 */

public class PreGetGoodsFragment extends LazyLoadFragment{

    private View view;
    private Context context;
    private ReFreshListView mListView;
    private String strToken;
    private int currentPage = 1;
    private int pageSize = 10;//一般一页的数目大于屏幕的显示
    private List<PreGetGoodsBean.ResponseBean.OrderListBean> orderList = new ArrayList<>();
    private PreGetGoodsAdapter adapter;
    private int totalPage;


//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        context = getActivity();
//        view = LayoutInflater.from(context).inflate(R.layout.fragment_pregetgoods, container, false);
//        return view;
//    }

    //加载布局
    @Override
    protected int setContentView() {
        context = getActivity();
        return R.layout.fragment_pregetgoods;
    }

    @Override
    protected void lazyLoad() {
        //控件初始化
        initView();
        //数据初始化
        initData();
    }

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
        initSpone(strToken,currentPage,pageSize,1,0);
    }

    private void initSpone(String strToken, final int current_page, int pageSize, int i, int i1) {
        Request request = new Request.Builder()
                .url(Constant.CONSTANT + "/listOrder.do?token=" + strToken + "&pageNo=" + current_page + "&pageSize=" + pageSize + "&orderStatus=" + i + "&deliveryType=" + i1)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShort(context,"网络请求异常");
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
                final PreGetGoodsBean preGetGoodsBean = gson.fromJson(strJson, PreGetGoodsBean.class);
                if (preGetGoodsBean.getCode() == NetWorkUtils.CODE_SUCCESS) {
                    final List<PreGetGoodsBean.ResponseBean.OrderListBean> newList = preGetGoodsBean.getResponse().getOrderList();
                    totalPage = preGetGoodsBean.getResponse().getPage().getTotalPage();
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
                }else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(context, (String) preGetGoodsBean.getMessage());
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
            adapter = new PreGetGoodsAdapter(context,orderList);
            mListView.setAdapter(adapter);
        }
        initDatas();

    }

    //网络请求数据
    public void initDatas() {
        currentPage++;
        initSpone(strToken,currentPage,pageSize,1,0);

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
                initNewsDatas();
            }

            @Override
            public void onLoadMore() {
                if (currentPage >= totalPage) {
                    ToastUtil.showShort(context,"已经是最后的数据");
                    mListView.onFinish();
                    return;
                }
                initDatas();
            }
        });
    }

    //这是下拉刷新的数据请求
    private void initNewsDatas() {
        orderList.clear();
        initSpone(strToken,1,pageSize,1,0);
    }

}
