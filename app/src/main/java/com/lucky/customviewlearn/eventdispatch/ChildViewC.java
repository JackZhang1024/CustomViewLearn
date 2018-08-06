package com.lucky.customviewlearn.eventdispatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zfz on 2017/7/7.
 */

public class ChildViewC extends View implements View.OnTouchListener, View.OnClickListener {
    private static final String TAG = "ChildViewC";
    private Paint mPaint;
    private int mWidth, mHeight;


    public ChildViewC(Context context) {
        super(context);
    }

    public ChildViewC(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        //setOnClickListener(this);
        //setOnTouchListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制圆形
//      float centerX = mWidth/2;
//      float CenterY = mHeight/2;
//      float radius  = Math.min(mWidth,mHeight)/2*4/5;
//      canvas.drawCircle(centerX, CenterY, radius, mPaint);

        // 绘制矩形
        // canvas.translate 移动的是画布 不是父布局
        // 将画布的原点坐标移动到画布的中心位置
        canvas.translate(mWidth / 2, mHeight / 2);
        Rect rect = new Rect(-mWidth / 2, -mHeight / 2, mWidth / 2, mHeight / 2);
        canvas.drawRect(rect, mPaint);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "dispatchTouchEvent: ");
        boolean result = super.dispatchTouchEvent(event);
        Log.e(TAG, "dispatchTouchEvent: result "+result);
        return result;
    }

    /**
     * 1 当子View对Touch事件不进行消费的时候(返回值是false)
     * 不会进行点击事件的处理
     * 2 Touch事件是否消费掉于是否注册事件没有关系
     * return super.onTouchEvent(event);
     * childViewC的onTouchEvent如果返回的是true name就不会再向上传递事件处理了 意思就是
     * ViewGroupB的onTouchEvent就不会再调用
     * ViewGroupA的onTouchEvent也不会再调用
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: ");
        boolean result = super.onTouchEvent(event);
        Log.e(TAG, "onTouchEvent: result " + result);
        //return result;
        return false;
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
