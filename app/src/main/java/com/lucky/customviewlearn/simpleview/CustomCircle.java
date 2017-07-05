package com.lucky.customviewlearn.simpleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lucky.customviewlearn.R;

/**
 * Created by zfz on 2017/7/3.
 */

public class CustomCircle extends View {

    private int mColor = Color.RED;
    private Paint paint = new Paint();
    public CustomCircle(Context context) {
        super(context);
    }

    public CustomCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = (TypedArray) context.obtainStyledAttributes(attrs, R.styleable.CustomCircle);
        mColor = a.getColor(R.styleable.CustomCircle_custom_color, Color.BLUE);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(mColor);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        canvas.drawCircle(100+paddingLeft, 100+paddingTop, 50, paint);
    }



    /**
     * widthMeasureSpec/heightMeasureSpec包括了两部分 mode和size
     * 我们可以利用MeasureSpec.getSize()方法获取到宽高(从指定的MeasureSepecification) the size in pixels defined in the supplied measure specification
     * (specification指的就是widthMeasureSpec/heightMeasureSpec)
     * MeasureSpec.getMode()方法获取到的是自定义View的宽高模式 分为三种:
     * 1. UNSPECIFIED 父容器对View无任何限制，一般用于系统内部
     * 2. EXACTLY View的最终大小就是SpecSize指定的值，适用于指定具体大小和match_parent的形
     * 3. AT_MOST  父容器指定了一个SpecSize，View不能超过它，适用于wrap_content
     *
     * 如果想在wrap_content的情况下达到match_parent的效果，那么我们就可以重写onMeasure方法
     * if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
     *      setMeasureDimension(width,height); // 设置指定的宽高
     * }
     * */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            // layout_width = "wrap_content" layout_height = "wrap_content"
            System.out.println("AT Most width "+400 + " height "+400);
            setMeasuredDimension(400, 400);
        }else if (widthSpecMode == MeasureSpec.AT_MOST){// layout_width = "wrap_content" layout_height = "match_parent"
            System.out.println("AT Most width "+400 + " height "+heightSpecSize);
            setMeasuredDimension(400, heightSpecSize);
        }else if (heightSpecMode == MeasureSpec.AT_MOST){//layout_width = "match_parent" layout_height = "wrap_content"
            System.out.println("AT Most width "+widthSpecSize + " height "+400);
            setMeasuredDimension(widthSpecSize, 400);
        }else if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.EXACTLY){
            // layout_width = "match_parent" layout_height = "wrap_content" 或者指定width height 的大小
            System.out.println("Exactly width "+widthSpecSize + " height "+heightSpecSize);
            setMeasuredDimension(widthSpecSize, heightSpecSize);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
