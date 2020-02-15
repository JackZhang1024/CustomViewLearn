package com.lucky.customviewlearn.view.nestedviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

// 嵌套的子ViewPager
public class ChildViewPager extends ViewPager {

    private static int MAX_LENGTH = 20;

    public ChildViewPager(Context context) {
        super(context);
    }

    public ChildViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private float x1;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 告知父控件 把事件下发给子控件处理
                // true 表示不要拦截事件 交给子控件
                getParent().requestDisallowInterceptTouchEvent(true);
                x1 = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                // 获取到当前显示也的下标
                int currentPosition = getCurrentItem();
                float x2 = ev.getX();
                if (currentPosition == 0) {
                    // 第一页
                    // 当 当前页在下标为0的时候 由父控件拦截触摸事件
                    if (x2 - x1 > MAX_LENGTH) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        // 交给子控件进行处理 父控件不进行处理
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                } else if (currentPosition == getAdapter().getCount() - 1) {
                    // 最后一页
                    // 当 当前页在最后一页的时候 由父控件拦截触摸事件
                    if (x1 - x2 > MAX_LENGTH) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                } else {
                    // 其他情况，由孩子拦截触摸事件
                    // 父控件不拦截事件
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


}
