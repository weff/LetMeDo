package com.jtsoft.letmedo.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.jtsoft.letmedo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xx on 2016/8/23.
 * 自定义Listview实现刷新加载
 */
public class ReFreshListView extends ListView {

    private int mStartY;
    private int mHeaderViewHeight;//头布局的高度
    private View mHeaderView;//头布局

    private static final int DOWN_PULL = 0;//下拉刷新的状态值
    private static final int RELEASE_REFRESH = 1;//松开刷新的状态值
    private static final int REFRESHING = 2;//正在刷新的状态值

    private int currentState = DOWN_PULL;//记录当前的状态，默认是下拉刷新
    private ProgressBar mPb;//进度圈
    private ImageView mIv_arrow;//箭头
    private TextView mTv_state;//文本状态
    private TextView mTv_time;//文本时间
    private RotateAnimation mUpAnimation;
    private RotateAnimation mDownAnimation;
    private OnFreshListener mOnFreshListener;//下拉刷新的接口实现类

    private boolean isLoadMore = false;//记录当前是否处于加载更多状态
    private View mFooterView;//脚布局
    private int mFooterViewHeight;//脚布局高度

    public ReFreshListView(Context context) {
        this(context,null);
    }

    public ReFreshListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ReFreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //当控件被创建时，就需要给它加上头布局
        initHeaderView();

        //当控件被创建时，就需要给它加上脚布局
        initFooterView();

        //初始化所需要的动画
        initAnimation();

        //给listview设置滚动监听
        this.setOnScrollListener(new OnScrollListener() {
            //当滚动状态改变时，该方法执行

            /**
             * OnScrollListener.SCROLL_STATE_FLING; 2 手指离开屏幕，listview惯性滑动的状态
             OnScrollListener.SCROLL_STATE_IDLE;0手指离开屏幕，listview处于停滞状态
             OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;1手指触摸屏幕，上下滚动的状态
             * @param view
             * @param scrollState
             */
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //当手指离开屏幕并且listview的最后一条数据展示出来时，进入加载更多状态，并且当前的状态不处于加载更多时
                int lastVisiblePosition = ReFreshListView.this.getLastVisiblePosition();
                if (scrollState != OnScrollListener.SCROLL_STATE_TOUCH_SCROLL && lastVisiblePosition == getCount() - 1&&!isLoadMore) {
                    System.out.println("加载更多");
                    isLoadMore = true;

                    //显示出脚布局,并且让listview显示到最后一行
                    mFooterView.setPadding(0,0,0,0);
                    ReFreshListView.this.setSelection(getCount() -1);

                    //将加载更多回调回去
                    if (mOnFreshListener != null) {
                        mOnFreshListener.onLoadMore();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void initFooterView() {
        mFooterView = View.inflate(getContext(), R.layout.hearfooter,null);
        mFooterView.measure(0, 0);
        mFooterViewHeight = mFooterView.getMeasuredHeight();
        mFooterView.setPadding(0,-mFooterViewHeight,0,0);
        this.addFooterView(mFooterView);
    }

    private void initAnimation() {
        //向上的动画
        mUpAnimation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation
                .RELATIVE_TO_SELF, 0.5f);
        mUpAnimation.setDuration(500);
        mUpAnimation.setFillAfter(true);
        //向下的动画
        mDownAnimation = new RotateAnimation(-180, -360, Animation.RELATIVE_TO_SELF, 0.5f, Animation
                .RELATIVE_TO_SELF, 0.5f);
        mDownAnimation.setDuration(500);
        mDownAnimation.setFillAfter(true);
    }

    private void initHeaderView() {
        mHeaderView = View.inflate(getContext(), R.layout.headerview,null);

        mPb = (ProgressBar) mHeaderView.findViewById(R.id.pb);
        mIv_arrow = (ImageView) mHeaderView.findViewById(R.id.iv_arrow);
        mTv_state = (TextView) mHeaderView.findViewById(R.id.tv_state);
        mTv_time = (TextView) mHeaderView.findViewById(R.id.tv_time);


        //获取头布局的高度
//        int height = headerView.getHeight();//在初始化时，它没有经过测量，宽高始终为0；
        mHeaderView.measure(0, 0);//让系统自己去测量一下view
        //获取测量过后的宽高
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();

        System.out.println("headerViewHeight:"+ mHeaderViewHeight);
        //将自身的paddingtop值设置为-自己的高度，进行隐藏操作
        mHeaderView.setPadding(0,-mHeaderViewHeight,0,0);
        this.addHeaderView(mHeaderView);
    }


    /**
     * 起始点 mstartY
     结束点 endY
     计算出间距 = endY - startY
     让头布局的paddingtop值+间距，计算将要显示的头布局的高度
     将计算后的paddingtop的值设置给头布局
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (currentState == REFRESHING) {
                    break;
                }

                int endY = (int) ev.getY();
                //计算出间距
                int diffY = endY - mStartY;
                //计算paddingtop的值
                int paddingtop = -mHeaderViewHeight + diffY;
                //当头布局要显示的时候，才进行自己处理触摸，否则还要将触摸事件传给listview

                //获取当前listview的可见条目的第一个索引值，如果是0，才能显示头布局
                int firstVisiblePosition = this.getFirstVisiblePosition();
//                System.out.println("firstVisiblePosition:"+firstVisiblePosition);
                if (paddingtop > -mHeaderViewHeight&&firstVisiblePosition == 0) {

                    if (paddingtop > 0&&currentState == DOWN_PULL) {//头布局完全显示，松开刷新
                        System.out.println("松开刷新");
                        currentState = RELEASE_REFRESH;
                        updateViewByState();
                    }else if (paddingtop <= 0 &&currentState == RELEASE_REFRESH){//头布局部分隐藏，下拉刷新
                        System.out.println("下拉刷新");
                        currentState = DOWN_PULL;
                        updateViewByState();
                    }

//                    System.out.println("paddingtop:"+paddingtop);
                    //将计算得到的paddingtop的值设置给头布局
                    mHeaderView.setPadding(0,paddingtop,0,0);
                    return true;//自己处理触摸事件，listview就不能上下滚动
                }



                break;
            case MotionEvent.ACTION_UP:
                //如果是下拉刷新直接隐藏头布局
                //如果是松开刷新，直接进入正在刷新的状态
                if (currentState == DOWN_PULL) {
                    mHeaderView.setPadding(0,-mHeaderViewHeight,0,0);
                } else if (currentState == RELEASE_REFRESH) {
                    currentState = REFRESHING;
                    updateViewByState();

                    //进入正在刷新，这=则通知activity进行对应的操作
                    if (mOnFreshListener != null) {
                        mOnFreshListener.onDownPull();
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);//将触摸事件传递给listview，它就上下滚动
    }


    //根据当前的状态刷新界面
    private void updateViewByState(){
        switch (currentState) {
            case DOWN_PULL://下拉刷新
                //修改文本
                mTv_state.setText("下拉刷新");
                //箭头执行向下的动画
                mIv_arrow.startAnimation(mDownAnimation);
                break;
            case RELEASE_REFRESH://松开刷新
                //修改文本
                mTv_state.setText("松开刷新");
                //箭头执行向上的动画
                mIv_arrow.startAnimation(mUpAnimation);
                break;
            case REFRESHING://正在刷新
                //箭头隐藏
                mIv_arrow.clearAnimation();
                mIv_arrow.setVisibility(INVISIBLE);
                //进度圈显示
                mPb.setVisibility(VISIBLE);
                //文本修改
                mTv_state.setText("正在刷新");
                //头布局刚好完全显示
                mHeaderView.setPadding(0,0,0,0);
                break;
        }
    }

    //当刷新完毕后，初始化所有的状态
    public void onFinish() {
        if (isLoadMore) {//加载更多
            //加载更多完成，隐藏脚布局，修改状态
            mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);
            isLoadMore = false;
        } else {//下拉刷新
            //箭头显示,进度圈隐藏，文本修改，头布局隐藏，状态修改,修改刷新时间
            mIv_arrow.setVisibility(VISIBLE);
            mPb.setVisibility(INVISIBLE);
            mTv_state.setText("下拉刷新");
            mHeaderView.setPadding(0,-mHeaderViewHeight,0,0);
            currentState = DOWN_PULL;
            mTv_time.setText("最新刷新时间："+getCurrnetTime());
        }



    }

    private String getCurrnetTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }


    public interface OnFreshListener{
        public void onDownPull();
        public void onLoadMore();
    }

    public void setOnFreshListener(OnFreshListener listener) {
        this.mOnFreshListener = listener;
    }
}
