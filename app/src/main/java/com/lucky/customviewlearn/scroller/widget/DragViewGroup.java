package com.lucky.customviewlearn.scroller.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by zfz on 2018/1/5.
 */

public class DragViewGroup extends FrameLayout {
    private ViewDragHelper mViewDragHelper;
    private View mMainView, mMenuView;
    private int mWidth;

    public DragViewGroup(@NonNull Context context) {
        super(context);
        initView();
    }

    public DragViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DragViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mViewDragHelper = ViewDragHelper.create(this, callback);
    }

    // 在初始化xml布局之后
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mMenuView.getMeasuredWidth();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {

        //何时开始检测触摸时间
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            // 如果当前触摸的child是mMainView时开始检测
            return child == mMainView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;  // 一般用left来表示在水平方向上的移动 0表示在水平方向上不移动
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0; // 0表示在垂直方向上不移动 一般用top来表示在垂直方向上的移动
        }

        //拖动结束后调用
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (mMainView.getLeft() < 500) {
                //关闭菜单
                mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
                ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
            } else {
                //开启菜单
                mViewDragHelper.smoothSlideViewTo(mMainView, 300, 0);
                ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
            }
        }

        // 在用户触摸到View后调用
        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        //在拖拽状态变化时进行回调 比如idle dragging等状态
        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
        }

        // 在位置改变时进行回调， 常用于滑动时更改scale进行缩放等效果
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }
    };

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) { // 和Scroller的computeScrollOffset方法一样
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
