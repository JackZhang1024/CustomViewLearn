package com.lucky.customviewlearn.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;

public class RoundColorDrawable extends ColorDrawable {
    private Paint mPaint;
    private RectF mRectF;
    private int mRound;

    public RoundColorDrawable(int color) {
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
        canvas.drawRoundRect(mRectF, mRound, mRound, mPaint);
    }

    /**
     * 暴露给外面设置圆角的大小
     *
     * @param round
     */
    public void setRound(int round) {
        this.mRound = round;
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
