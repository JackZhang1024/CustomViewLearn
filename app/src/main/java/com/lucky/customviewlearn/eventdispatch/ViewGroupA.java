package com.lucky.customviewlearn.eventdispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by zfz on 2017/7/7.
 */

public class ViewGroupA extends LinearLayout {
    private static final String TAG = "ViewGroupA";

    public ViewGroupA(Context context) {
        super(context);
    }

    public ViewGroupA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //dispatchTouchEvent onInterceptTouchEvent onTouchEvent默认返回的都是false
    //一般情况下不会去重写dispatchTouchEvent 而是重新onInterceptTouchEvent和onTouchEvent方法
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.e(TAG, "onInterceptTouchEvent: ");
        return super.onInterceptTouchEvent(event);
        //return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }
}
