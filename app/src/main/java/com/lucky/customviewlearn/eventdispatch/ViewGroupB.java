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

public class ViewGroupB extends LinearLayout implements View.OnTouchListener, View.OnClickListener{
    private static final String TAG = "ViewGroupB";
    public ViewGroupB(Context context) {
        super(context);
    }

    public ViewGroupB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //setOnClickListener(this);
        //setOnTouchListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent: ");
        boolean result = super.dispatchTouchEvent(ev);
        Log.e(TAG, "dispatchTouchEvent: result "+result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.e(TAG, "onInterceptTouchEvent: ");
        boolean result = super.onInterceptTouchEvent(event);
        Log.e(TAG, "onInterceptTouchEvent: result"+result);
        return result;
        //return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: ");
        boolean result = super.onTouchEvent(event);
        Log.e(TAG, "onTouchEvent: result "+result);
        return result;
        //return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.e(TAG, "onTouch: ");
        return false;
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick: ");
    }
}
