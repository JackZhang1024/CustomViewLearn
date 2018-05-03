package com.lucky.customviewlearn.eventdispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by zfz on 2017/7/7.
 */

public class ViewGroupA extends LinearLayout implements View.OnTouchListener, View.OnClickListener{
    private static final String TAG = "ViewGroupA";

    public ViewGroupA(Context context) {
        super(context);
    }

    public ViewGroupA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
        setOnTouchListener(this);
    }

    /**
     *dispatchTouchEvent onInterceptTouchEvent onTouchEvent默认返回的都是false
     *一般情况下不会去重写dispatchTouchEvent 而是重新onInterceptTouchEvent和onTouchEvent方法
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        Log.e(TAG, "dispatchTouchEvent: "+result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean result = super.onInterceptTouchEvent(event);
        Log.e(TAG, "onInterceptTouchEvent: "+result);
        return result;
        //return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        Log.e(TAG, "onTouchEvent: "+result);
        return result;
    }

    /**
    * 如果View设置了OnTouchListener事件, 在onTouch()方法中返回true 则onTouchEvent回调不执行
    * 如果onTouch()方法返回的是false, 则会回调onTouchEvent方法
    **/
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.e(TAG, "onTouch: ");
        return false;
        //return true;
    }

    /**
     * onClick事件是级别最低的 就是在onTouchEvent方法执行之后才会执行 如果onTouchEven方法不执行
     * 则onClick事件也不执行
     * */
    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick: ");
    }
}
