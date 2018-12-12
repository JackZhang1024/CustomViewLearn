package com.lucky.customviewlearn.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;

public class RoundCornerColorDrawable extends ColorDrawable {
    private static final String TAG = "RoundCornerColor";
    private Paint mPaint;
    private RectF mRectF;
    private int mRound;
    private boolean mSetBorder;
    private boolean mSetBackground;

    public RoundCornerColorDrawable(int color) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
    }

    /**
     * 初始化区域
     */
    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        mRectF = new RectF(left, top, right, bottom);
        super.setBounds(left, top, right, bottom);
    }

    /**
     * 核心代码： 绘制圆角
     */
    @Override
    public void draw(Canvas canvas) {
        if (mSetBackground) {
            canvas.drawRoundRect(mRectF, mRound, mRound, mPaint);
        }
        if (mSetBorder) {
            canvas.drawRect(mRectF, mPaint);
        }

    }

    /**
     * 暴露给外面设置圆角的大小
     *
     * @param round
     */
    public void setRound(int round) {
        mSetBackground = true;
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mRound = round;
    }

    public void setBorder(int strokeWidth, int color) {
        mSetBorder = true;
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public void setBackground(int color) {
        mSetBackground = true;
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    /**
     * 根据画笔设定drawable的透明度
     */
    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    /**
     * 根据画笔设定drawable的颜色过滤器
     */
    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }


}
