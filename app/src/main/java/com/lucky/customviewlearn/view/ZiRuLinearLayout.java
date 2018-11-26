package com.lucky.customviewlearn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.lucky.customviewlearn.view.style.BorderStyle;

// RoundRectDrawable
// 满足两种情况
// 1 圆角矩形
// 2 圆角实心矩形
// 3 矩形
public class ZiRuLinearLayout extends LinearLayout {

    private static final String TAG = "ZiRuLinearLayoutNew";
    private Rect mRectF;
    private int mWidth, mHeight;
    private BorderStyle mBorderStyle;

    public ZiRuLinearLayout(Context context) {
        this(context, null);
    }

    public ZiRuLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZiRuLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    private void initPaint() {
        mRectF = new Rect();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRectF.left = 0;
        mRectF.top = 0;
        mRectF.right = mWidth;
        mRectF.bottom = mHeight;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (mBorderStyle!=null && mBorderStyle.mDrawBorder) {
            mBorderStyle.drawBorder(canvas, mRectF);
            super.dispatchDraw(canvas);
            return;
        }
        if (mBorderStyle!=null && mBorderStyle.mDrawRoundShape) {
            mBorderStyle.drawRoundCorner(canvas, mRectF);
        }
        super.dispatchDraw(canvas);
    }

    public void setBorderStyle(BorderStyle borderStyle) {
        this.mBorderStyle = borderStyle;
        if (mBorderStyle.mDrawBorder) {
            invalidate();
        }
        if (mBorderStyle.mDrawRoundShape) {
            invalidate();
        }
    }

}
