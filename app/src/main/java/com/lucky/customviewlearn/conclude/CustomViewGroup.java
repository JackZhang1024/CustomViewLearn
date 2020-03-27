package com.lucky.customviewlearn.conclude;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class CustomViewGroup extends ViewGroup {

    private static final String TAG = "CustomViewGroup";

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 思考流程
        // 1. 想要什么样式的布局
        // 2. 整个流程是什么样的 onMeasure 测量大小 onLayout 进行布局
        // 3. onMeasure 先测量子View的宽高 然后才能确定我们需呀给多大的控件才能容纳这些子控件
        // 4. 我们获取到所有的子控件的宽高 然后根据产品需求确定计算出我们的自定义ViewGroup的大小
        // 5. 在我们获取到所有控件的大小之后 需要将这些控件进行摆放
        // 6. 我们需要先确定这些控件的位置 然后进行拜访

        // 我们的需求是将所有的子控件按照顺序垂直摆放

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (getChildCount() == 0) {
            // 如果没有子View 当前ViewGroup就没有意思 不占用空间
            setMeasuredDimension(0, 0);
            return;
        }
        // 1. 如果宽度是包裹内容的 那么就算出最大子控件的宽度 就是最大宽度 高度就是获取到的自己测量到的值
        // 2. 如果高度是包裹内容的 那么就算出所有子控件的高度 宽度就是自己测量出来的值
        // 3. 如果宽度和高度都是包裹内容的 那么就按照最大空间的宽度和所有子控件的高度和来设置数值
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            int width = getMaxChildViewWidth();
            int height = getTotalChildViewHeight();
            setMeasuredDimension(width, height);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            int width = getMaxChildViewWidth();
            setMeasuredDimension(width, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            int height = getTotalChildViewHeight();
            setMeasuredDimension(widthSize, height);
        }
    }

    private int getMaxChildViewWidth() {
        int maxWidth = 0;
        for (int index = 0; index < getChildCount(); index++) {
            View childView = getChildAt(index);
            if (childView.getMeasuredWidth() > maxWidth) {
                maxWidth = childView.getMeasuredWidth();
            }
        }
        return maxWidth;
    }

    private int getTotalChildViewHeight() {
        int totalHeight = 0;
        for (int index = 0; index < getChildCount(); index++) {
            View childView = getChildAt(index);
            totalHeight += childView.getMeasuredHeight();
        }
        return totalHeight;
    }


    // getMeasuredWidth/getMeasuredHeight 是测量阶段的数值
    // getWidth/getHeight 是测量和布局结束之后的数值
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int currentTop = t;
        for (int index = 0; index < getChildCount(); index++) {
            View childView = getChildAt(index);
            int width = childView.getMeasuredWidth();
            int height = childView.getMeasuredHeight();
            //  measuredWidth  788 measuredHeight 105 width 0 height 0
            Log.e(TAG, "onLayout: measuredWidth  " + width + " measuredHeight " + height + " width " + childView.getWidth() + " height " + childView.getHeight());
            childView.layout(l, currentTop, l + width, currentTop + height);
            currentTop += childView.getHeight();
        }
    }



}
