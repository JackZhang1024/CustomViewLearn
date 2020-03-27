package com.lucky.customviewlearn.scroller.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.lucky.customviewlearn.R;


// 简而言之，可以把getScrollx的值看成坐标。比如view向右边移动20px。那么得到的值就是view.getScrollx()的值就是-20；
// getScrollY 可以看成是一种内容的偏移量 距离内容最开始的原点位置的 这种偏移是代方向的
// getScrollY<0 表示内容向下偏移  getScrollY>0 表示内容向上偏移
// 下拉刷新和上拉加载的优化步骤
public class SimpleRefreshLayout extends ViewGroup {
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


    public SimpleRefreshLayout(Context context) {
        super(context);
    }

    public SimpleRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHeader = LayoutInflater.from(context).inflate(R.layout.item_header_layout, null);
        mFooter = LayoutInflater.from(context).inflate(R.layout.item_footer_layout, null);
        pullText = mHeader.findViewById(R.id.srl_tv_pull_down);
        mLayoutScroller = new Scroller(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mHeader.setLayoutParams(params);
        mFooter.setLayoutParams(params);
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
