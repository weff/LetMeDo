package com.jtsoft.letmedo.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jtsoft.letmedo.R;

/**
 * Created by admin on 2017/7/31.
 * 自定义布局  无网络  无数据的显示
 */
public class EmptyLayout extends FrameLayout {

    private Context mContext;
    private View mEmptyView;
    private View mBindView;
    private View mErrorView;
    private Button mBtnReset;
    private View mLoadingView;
    private View loadingView;
    private TextView mEmptyText;
    private TextView tvLoadingText;

    public EmptyLayout(Context context) {
        this(context, null);
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //居中
        params.gravity = Gravity.CENTER;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EmptyLayout, 0, 0);
        //数据为空时的布局
        int emptyLayout = ta.getResourceId(R.styleable.EmptyLayout_elEmptyLayout, R.layout.layout_empty);
        mEmptyView = View.inflate(context, emptyLayout, null);
        mEmptyText =(TextView)mEmptyView.findViewById(R.id.tvEmptyText);
        addView(mEmptyView,params);

        //加载中的布局
        int loadingLayout = ta.getResourceId(R.styleable.EmptyLayout_elLoadingLayout, R.layout.layout_loading);
        mLoadingView = View.inflate(context, loadingLayout, null);
        tvLoadingText =(TextView)mLoadingView.findViewById(R.id.tvLoadingText);
        addView(mLoadingView,params);

        //错误时的布局
        int errorLayout = ta.getResourceId(R.styleable.EmptyLayout_elErrorLayout, R.layout.layout_error);
        mErrorView = View.inflate(context, errorLayout, null);
        mBtnReset =(Button)mErrorView.findViewById(R.id.btnReset);
        addView(mErrorView, params);

        //全部隐藏
        setGone();
    }

    /**
     * 全部隐藏
     */
    private void setGone() {
        mEmptyView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return mBindView.getVisibility()==VISIBLE?mBindView.canScrollVertically(direction):super.canScrollVertically(direction);
    }

    //正在加载
    public void showLoading(String text) {
        if (mBindView != null) mBindView.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(text)) tvLoadingText.setText(text);
        setGone();
        mLoadingView.setVisibility(View.VISIBLE);
    }

    public void showLoading() {
        showLoading(null);
    }

    //加载失败
    public void showError() {
        showError(null);
    }

    public void showError(String text) {
        if (mBindView != null) mBindView.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(text)) mBtnReset.setText(text);
        setGone();
        mErrorView.setVisibility(View.VISIBLE);
    }

    //加载成功
    public void showSuccess() {
        if (mBindView != null) mBindView.setVisibility(View.VISIBLE);
        setGone();
    }
}
