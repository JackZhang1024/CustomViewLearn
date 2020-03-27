package com.lucky.customviewlearn.conclude;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lucky.customviewlearn.R;

public class CustomCircleView extends View {

    private int mColor;
    private Paint mPaint;

    public CustomCircleView(Context context) {
        this(context, null);
    }

    public CustomCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCircleView);
        mColor = typedArray.getColor(R.styleable.CustomCircleView_fillColor, getResources().getColor(R.color.action_blue));
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDesiredSize(100, widthMeasureSpec);
        int height = getDesiredSize(120, heightMeasureSpec);
        if (width > height) {
            width = height;
        } else {
            height = width;
        }
        setMeasuredDimension(width, height);
    }

    private int getDesiredSize(int defaultSize, int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int desiredSize = 0;
        if (MeasureSpec.UNSPECIFIED == mode) {
            // 父容器不管子控件的大小 你爱多大就多大 这样 我们可以选定默认值为该自定义控件的大小
            desiredSize = defaultSize;
        } else if (MeasureSpec.EXACTLY == mode) {
            // EXACTLY 表示两种情况 一：尽可能的占据的是父容器的剩余的大小 二：子控件占据的真实大小 在布局文件XML中已经定好了
            // 情况一 理解尽可能占据父容器的剩余大小 就是只要占据了就容不下其他控件了
            // 如果在我们自定义的控件之前也有一个控件 那我们自定义控件占据的大小就是除了之前这个控件之外的大小
            // 所以大小是确定 size 就是父容器给的参考值或者我们在xml中指定的大小
            desiredSize = size;
        } else if (MeasureSpec.AT_MOST == mode) {
            // AT_MOST 表示的是子控件最大不能超过父容器的大小
            // size 表示的是不能超过父容器大小的最大值 我们也可以选取其他值
            // 我们这里一般才去的是默认值 自定义View 的通用做法
            desiredSize = size;
        }
        return desiredSize;
    }

    private static final String TAG = "CustomCircleView";


    // 注意： 这里不要用getLeft+getMeasuredWidth()/2 做为圆心横坐标
    //这里不要用getTop+getMeasuredWidth()/2 做为圆心纵坐标

    // 通常情况下 我们一般把自定义View的左上角定位0点坐标  其他的都是依照这个坐标点来进行定位绘制的
    // 不要用其他的乱七八糟的写法来做
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r = getMeasuredWidth() / 2;
        float cx = getMeasuredWidth() / 2;
        float cy = getMeasuredHeight() / 2;
        canvas.drawCircle(cx, cy, r, mPaint);
        //canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
    }


}
