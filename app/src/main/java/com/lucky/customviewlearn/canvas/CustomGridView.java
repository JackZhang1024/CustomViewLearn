package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.lucky.customviewlearn.R;

/**
 * Created by zfz on 2018/1/9.
 */

public class CustomGridView extends ViewGroup {
    private int childHorizontalSpace;
    private int childVerticalSpace;
    private int columnNum;
    private int childWidth;
    private int childHeight;

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomGridView);
        childHorizontalSpace = attributes.getDimensionPixelSize(R.styleable.CustomGridView_childHorizontalSpace, 0);
        childVerticalSpace = attributes.getDimensionPixelSize(R.styleable.CustomGridView_childVerticalSpace, 0);
        columnNum = attributes.getInt(R.styleable.CustomGridView_columnNum, 0);
        attributes.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = getChildCount();
        if (childCount > 0) {
            childWidth = (width - (columnNum - 1) * childHorizontalSpace) / columnNum;
            childHeight = childWidth;
            int innerWidth = width;
            if (childCount < columnNum) {
                innerWidth = childCount * (childHeight + childVerticalSpace);
            }
            int innerHeight = 0;
            int rowCount = childCount / columnNum + (childCount % columnNum == 0 ? 0 : 1);
            innerHeight = rowCount * childHeight + (rowCount > 0 ? rowCount - 1 : 0) * childVerticalSpace;
            setMeasuredDimension(innerWidth, innerHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int index = 0; index < childCount; index++) {
                View child = getChildAt(index);
                int left = (index % columnNum) * (childWidth + childHorizontalSpace);
                int top = (index / columnNum) * (childHeight + childVerticalSpace);
                child.layout(left, top, left + childWidth, top + childHeight);
            }
        }
    }
}
