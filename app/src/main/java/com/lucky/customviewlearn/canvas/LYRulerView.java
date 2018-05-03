package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * https://blog.csdn.net/su1216/article/details/46900617
 * 对Paint绘制文字进行解释
 */
public class LYRulerView extends View {

    public LYRulerView(Context context) {
        super(context);
    }

    public LYRulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LYRulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);



    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


}
