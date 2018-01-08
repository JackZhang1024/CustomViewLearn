package com.lucky.customviewlearn.scroller.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zfz on 2018/1/3.
 */

public class DragView extends View {
    private static final String TAG = "DragView";
    private int lastX, lastY;
    private int rawLeft, rawTop, rawRight, rawBottom;
    private boolean isUpDateLocation;

    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (!isUpDateLocation) {
            rawLeft = left;
            rawTop = top;
            rawRight = right;
            rawBottom = bottom;
            isUpDateLocation = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                dragViewMethod4(offsetX, offsetY);
                break;
            case MotionEvent.ACTION_UP:
                layout(rawLeft, rawTop, rawRight, rawBottom);
                break;
        }
        return true;
    }

    private void dragViewMethod1(int offsetX, int offsetY) {
        layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
    }

    private void dragViewMethod2(int offsetX, int offsetY) {
        offsetLeftAndRight(offsetX);
        offsetTopAndBottom(offsetY);
    }

    private void dragViewMethod3(int offsetX, int offsetY) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        layoutParams.leftMargin = getLeft() + offsetX;
        layoutParams.topMargin = getTop() + offsetY;
        setLayoutParams(layoutParams);
    }


    private void dragViewMethod4(int offsetX, int offsetY) {
        ((View) getParent()).scrollBy(-offsetX, -offsetY);
    }

    private void dragViewMethod5(int offsetX, int offsetY) {
        ((View) getParent()).scrollTo(-offsetX+getLeft(), -offsetY+getTop());
    }

}
