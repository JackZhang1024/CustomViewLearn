package com.lucky.customviewlearn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


// 可以控制边框颜色LinearLayout
// RoundRectDrawable
public class ZiRuLinearLayout extends FrameLayout {
    private static final String TAG = "ZiRuLinearLayout";
    private int mllWidth, mllHeight;
    private LinearLayout mInnerLinearLayout;

    public ZiRuLinearLayout(Context context) {
        this(context, null);
    }

    public ZiRuLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mInnerLinearLayout = new LinearLayout(context);
        RectangleView rectangleView = new RectangleView(context);
        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 200);
        //addView(rectangleView, layoutParams);
        mInnerLinearLayout.setGravity(Gravity.CENTER);
        addView(rectangleView);
        addView(mInnerLinearLayout);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //Log.e(TAG, "onMeasure: ");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //Log.e(TAG, "onSizeChanged: w "+w +" h "+h);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //Log.e(TAG, "onLayout: ");
        //int childCount = getChildCount();
        //Log.e(TAG, "onLayout: childCount "+childCount);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        mInnerLinearLayout.addView(child, params);
    }

    static class RectangleView extends View {
        private static final String TAG = "RectangleView+++++++++++++";
        private Paint mPaint;
        private RectF mRectF;
        private int mWidth, mHeight;

        public RectangleView(Context context) {
            super(context);
            init();
        }

        public RectangleView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init() {
            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.RED);
            mPaint.setStrokeWidth(20);
            mRectF = new RectF();
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            mWidth = w;
            mHeight = h;
            Log.e(TAG, "onSizeChanged: w " + w + " h " + h);
            mRectF.left = 0;
            mRectF.top = 0;
            mRectF.right = mWidth;
            mRectF.bottom = mHeight;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawRect(mRectF, mPaint);
        }
    }

}
