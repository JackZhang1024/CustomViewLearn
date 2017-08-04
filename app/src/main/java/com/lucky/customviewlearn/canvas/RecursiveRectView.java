package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zfz on 2017/8/3.
 */

public class RecursiveRectView extends View {

    private int mWidth, mHeight, mSideLength, mHalfWidth, mHalfHeight;
    private float mHalfSideLength;
    private Paint mPaint;
    private static final int RECURSIVE_COUNT= 10;
    private Rect mRect;

    public RecursiveRectView(Context context) {
        this(context, null);
    }

    public RecursiveRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public RecursiveRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w ;
        mHeight = h;
        mSideLength = Math.min(mWidth, mHeight);
        mHalfWidth  = mSideLength/2;
        mHalfHeight = mSideLength/2;
        mRect= new Rect(-mSideLength/2, -mSideLength/2, mSideLength/2, mSideLength/2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 将原点移动到正方形的中心点位置
        canvas.translate(mHalfWidth, mHalfHeight);
        for (int i=0; i< RECURSIVE_COUNT; i++){
            canvas.save();
            float fraction = (float) i/RECURSIVE_COUNT;
            // 按照原点的为中心点进行缩放操作
            canvas.scale(fraction, fraction, 0, 0);
            // 这块的正方形还是原来的正方形 没有发生变化
            // 但是canvas的操作会影响到这个
            canvas.drawRect(mRect, mPaint);
            canvas.restore();
        }


    }
}
