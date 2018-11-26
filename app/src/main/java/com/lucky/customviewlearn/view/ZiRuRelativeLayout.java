package com.lucky.customviewlearn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.AttributeSet;

import com.lucky.customviewlearn.view.style.BorderStyle;

public class ZiRuRelativeLayout extends PercentRelativeLayout {
    private static final String TAG = "ZiRuRelativeLayout";
    private Rect mRectF;
    private int mWidth, mHeight;
    private BorderStyle mBorderStyle;

    public ZiRuRelativeLayout(Context context) {
        this(context, null);
    }

    public ZiRuRelativeLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZiRuRelativeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
