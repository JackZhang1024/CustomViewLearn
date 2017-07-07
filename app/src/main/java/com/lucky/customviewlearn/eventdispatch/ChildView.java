package com.lucky.customviewlearn.eventdispatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zfz on 2017/7/7.
 */

public class ChildView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;


    public ChildView(Context context) {
        super(context);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
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
        canvas.translate(mWidth/2, mHeight/2); // 将画布的原点坐标移动到画布的中心位置
        Rect rect = new Rect(-mWidth/2, -mHeight/2, mWidth/2, mHeight/2);
        canvas.drawRect(rect, mPaint);

    }


    //1 当子View对Touch事件不进行消费的时候(返回值是false)
    // 不会进行点击事件的处理
    //2 Touch事件是否消费掉于是否注册事件没有关系
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

}
