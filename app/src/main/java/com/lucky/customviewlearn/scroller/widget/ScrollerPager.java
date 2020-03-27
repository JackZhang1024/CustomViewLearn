package com.lucky.customviewlearn.scroller.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by zfz on 2017/7/27.
 * 实现类似于ViewPager的功能
 */

public class ScrollerPager extends ViewGroup {

    private static final String TAG = "ScrollerPager";

    private float lastX;
    private Scroller scroller;

    public ScrollerPager(Context context) {
        this(context, null);
    }

    public ScrollerPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.UNSPECIFIED) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                int width = childView.getMeasuredWidth();
                int height = childView.getMeasuredHeight();
                childView.layout(i * width, 0, i * width + width, height);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = currentX;
                return true;
            case MotionEvent.ACTION_MOVE:
                float dx = currentX - lastX;
                Log.e(TAG, "onTouchEvent: " + dx);
                scrollBy(-(int) dx, 0);
                //scrollBy((int)dx, 0 );
                lastX = currentX;
                return true;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: "+getScrollX()+" getScrollY "+getScrollY());
                // 松手时惯性滑动
                int sonIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int childCount = getChildCount();
                if (sonIndex >= childCount) {
                    sonIndex = childCount - 1;
                }
                int deltaX = sonIndex * getWidth() - getScrollX();
                scroller.startScroll(getScrollX(), 0, deltaX, 0, 500);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }
}
