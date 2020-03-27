package com.lucky.customviewlearn.scroller.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.lucky.customviewlearn.R;


// 简而言之，可以把getScrollx的值看成坐标。比如view向右边移动20px。那么得到的值就是view.getScrollx()的值就是-20；
// getScrollY 可以看成是一种内容的偏移量 距离内容最开始的原点位置的 这种偏移是代方向的
// getScrollY<0 表示内容向下偏移  getScrollY>0 表示内容向上偏移
// 下拉刷新和上拉加载的优化步骤
// 主要解决嵌套子ListView RecycleView ScrollView 时的滑动冲突问题
// 在必要的时候 将事件交给父布局进行进行处理
// 在边界条件下 在OnInterceptTouchEvent 中进行拦截 返回true
// 说明这种情况下 父布局接管了事件
public class SimpleRefreshTwoLayout extends ViewGroup {
    private static final String TAG = "SimpleRefreshLayout";
    private View mHeader;
    private View mFooter;
    private TextView pullText;
    private OnRefreshListener mRefreshListener;
    private int mLastMoveY;
    private int effectiveScrollY = 100;
    private Scroller mLayoutScroller;
    private boolean isPullDown = false;
    private int mLayoutContentHeight;
    private int mLastChildIndex = 0;


    public SimpleRefreshTwoLayout(Context context) {
        super(context);
    }

    public SimpleRefreshTwoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHeader = LayoutInflater.from(context).inflate(R.layout.item_header_layout, null);
        mFooter = LayoutInflater.from(context).inflate(R.layout.item_footer_layout, null);
        pullText = mHeader.findViewById(R.id.srl_tv_pull_down);
        mLayoutScroller = new Scroller(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLastChildIndex = getChildCount() - 1;
        Log.e(TAG, "onFinishInflate: mLastChildIndex " + mLastChildIndex);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mHeader.setLayoutParams(params);
        mFooter.setLayoutParams(params);
        // 这块仅仅是添加了View 但是具体怎么安排布局摆放 那是在父布局onLayout中决定的
        addView(mHeader);
        addView(mFooter);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int index = 0; index < getChildCount(); index++) {
            View child = getChildAt(index);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mLayoutContentHeight = 0;
        for (int index = 0; index < getChildCount(); index++) {
            View child = getChildAt(index);
            if (child == mHeader) {
                // 摆放头部布局位置
                child.layout(0, 0 - child.getMeasuredHeight(), child.getMeasuredWidth(), 0);
            } else if (child == mFooter) {
                // 摆放底部布局位置
                child.layout(0, mLayoutContentHeight, child.getMeasuredWidth(),
                        mLayoutContentHeight + child.getMeasuredHeight());
            } else {
                // 摆放中间内容部分位置
                child.layout(0, mLayoutContentHeight, child.getMeasuredWidth(), mLayoutContentHeight +
                        child.getMeasuredHeight());
                mLayoutContentHeight += child.getMeasuredHeight();
            }
        }
    }

    // 只有滑动到最顶部或者最顶部的时候，父布局SimpleRefreshLayout才会进行事件拦截
    // 展示顶部Header布局或者底部Footer布局 其他情况下不做事件拦截
    // 所以事情的关键在于如何判断是到顶部或者底部
    // 我们需要结合具体的子View通过子View的相关属性方法啦确定
    // 1. ListView
    // 2. RecycleView
    // 3. ScrollView
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int currentY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMoveY = currentY;
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int dy = mLastMoveY - currentY;
                if (dy < 0) {
                    Log.e(TAG, "onInterceptTouchEvent: dy<0 往下拉 " + dy);
                    View topChild = getChildAt(0);
                    Log.e(TAG, "onInterceptTouchEvent: topChild " + topChild.getClass().getSimpleName());
                } else {
                    Log.e(TAG, "onInterceptTouchEvent: dy>0 往上拉 " + dy);
                    View lastChild = getChildAt(mLastChildIndex);
                    Log.e(TAG, "onInterceptTouchEvent: lastChild " + lastChild.getClass().getSimpleName());
                }
                for (int index = 0; index < getChildCount(); index++) {
                    Log.e(TAG, "onInterceptTouchEvent: child  index " + index + " " + (getChildAt(index).getClass().getSimpleName()));
                }
                // 判断边界问题 如果到了最顶部或者最底部 那么我们（SimpleRefreshLayout）需要拦截事件 返回true
                // （然后交给SimpleRefreshLayout的onTouchEvent进行事件处理）
                // 如果没有到了边界 那么就返回false 让MOVE事件让子View进行处理

                // 问题来了 如何知道到顶部了或者底部了 我们已经做过试验了
                // 比如
                // ListView 到顶部就是
                // 第一个可见的firstVisibleItem的位置为0 同时第一个可见Item的getTop为0
                // ListView判断到底部条件就是
                // 1. 第一个可见的Item的position加上可见的Item的个数等于Item总条数
                // 2. 最后一个可见的ItemView的getBottom等于
                // ListView的高度

                View childMiddle = getChildAt(0);
                if (childMiddle != null) {
                    if (childMiddle instanceof ListView) {
                        ListView listView = (ListView) childMiddle;
                        if (dy < 0) { // 往下拉
                            // 判断是否到最顶部
                            int firstVisiblePosition = listView.getFirstVisiblePosition();
                            if (firstVisiblePosition == 0) {
                                View firstVisibleChild = listView.getChildAt(firstVisiblePosition);
                                if (firstVisibleChild != null && firstVisibleChild.getTop() == 0) {
                                    intercept = true;
                                }
                            }
                        } else { // 往上拉
                            //判断是否到最底部
                            int lastVisiblePosition = listView.getLastVisiblePosition();
                            if (lastVisiblePosition == listView.getChildCount() - 1) {
                                View lastVisibleChild = listView.getChildAt(lastVisiblePosition);
                                if (lastVisibleChild.getBottom() == listView.getHeight()) {
                                    intercept = true;
                                }
                            }
                        }
                    } else if (childMiddle instanceof RecyclerView) {
                        RecyclerView recyclerView = (RecyclerView) childMiddle;
                        if (dy < 0) { // 往下拉
                            if (!recyclerView.canScrollVertically(-1)) {
                                // // 判断Recycle是否到最顶部
                                // RecycleView已经到顶部了 不能再往下拉了
                                Log.e(TAG, "onInterceptTouchEvent: RecyclerView 我已经到最顶部了 不能再往下拉了");
                                intercept = true;
                            }
                        } else { // 往上拉
                            if (!recyclerView.canScrollVertically(1)) {
                                // 判断Recycle是否到最底部
                                Log.e(TAG, "onInterceptTouchEvent: RecyclerView 我已经到最底部了 不能再往上拉了");
                                intercept = true;
                            }
                        }
                    } else if (childMiddle instanceof ScrollView) {
                        ScrollView scrollView = (ScrollView) childMiddle;
                        View childInScrollView = scrollView.getChildAt(0);
                        // 判断是否已经到最顶部了
                        Log.e(TAG, "onInterceptTouchEvent: ScrollView getScrollY " + scrollView.getScrollY());
                        // 有个错误点在这里 往上拉的时候 不应该就走到了 scrollView.getScroll()<=0 这样
                        // 如果按照这样来 那么就会拦截事件 这样就会拉起Footer 但是实际上并没有走到判断是否还有更多内容被上拉显示出来
                        // 所以需要在判断是否到顶或到底的时候进行方向判断
                        if (dy < 0) { // 往下拉
                            // 下拉的时候才判断是否到顶
                            if (scrollView.getScrollY() <= 0) {
                                Log.e(TAG, "onInterceptTouchEvent: ScrollView 我已经到最顶部了 不能再往下拉了");
                                intercept = true;
                            }
                        } else { // 往上拉
                            // 上来的时候才会判断是否到底
                            if (scrollView.getScrollY() + scrollView.getHeight() >= childInScrollView.getHeight()) {
                                // 判断是否已经到最底部了
                                Log.e(TAG, "onInterceptTouchEvent: ScrollView 我已经到最底部了 不能再往上拉了");
                                intercept = true;
                            }
                        }
                    } else {
                        intercept = false;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMoveY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int dy = mLastMoveY - y;
                if (dy < 0) {
                    // dy < 0 下拉刷新
                    Log.e(TAG, "onTouchEvent: 下拉刷新 " + getScrollY() + " dy " + dy);
                    isPullDown = true;
                    if (Math.abs(getScrollY()) <= mHeader.getMeasuredHeight() / 2) {
                        scrollBy(0, dy);
                        if (Math.abs(getScrollY()) >= effectiveScrollY) {
                            pullText.setText("松开刷新");
                        }
                    }
                } else {
                    // dy > 0 上拉加载
                    Log.e(TAG, "onTouchEvent: 上拉加载 " + getScrollY() + " dy " + dy);
                    // 下面的这句话可以这么立即 就是当前在Y轴上的偏移量加上本次Y轴移动的偏移量
                    // 也就是总共的偏移量
                    if (Math.abs(getScrollY()) + Math.abs(dy) < mFooter.getMeasuredHeight() / 2) {
                        scrollBy(0, dy);
                        isPullDown = false;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                // 松开手进行状态更新
                if (isPullDown) {
                    // 下拉刷新
                    if (Math.abs(getScrollY()) >= effectiveScrollY) {
                        if (mRefreshListener != null) {
                            mRefreshListener.onRefresh();
                        }
                        // 停止在距离屏幕顶部effectiveScrollY大小的位置上进行刷新等待
                        mLayoutScroller.startScroll(0, getScrollY(), 0, -getScrollY() - effectiveScrollY);
                        invalidate();
                    } else {
                        mLayoutScroller.startScroll(0, getScrollY(), 0, -getScrollY());
                        invalidate();
                    }
                } else {
                    // 上拉加载
                    if (Math.abs(getScrollY()) >= effectiveScrollY) {
                        if (mRefreshListener != null) {
                            mRefreshListener.onBottomRefresh();
                        }
                        mLayoutScroller.startScroll(0, getScrollY(), 0, -getScrollY() + effectiveScrollY);
                        invalidate();
                    } else {
                        mLayoutScroller.startScroll(0, getScrollY(), 0, -getScrollY());
                        invalidate();
                    }
                }
                break;
        }
        mLastMoveY = y;
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mLayoutScroller.computeScrollOffset()) {
            scrollTo(0, mLayoutScroller.getCurrY());
            invalidate();
        }
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.mRefreshListener = listener;
    }

    public void stopRefresh() {
        mLayoutScroller.startScroll(0, getScrollY(), 0, -getScrollY());
        invalidate();
    }

    public interface OnRefreshListener {

        void onRefresh();

        void onBottomRefresh();
    }


}
