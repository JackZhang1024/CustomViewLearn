package com.lucky.customviewlearn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

// TODO: 2018/11/3 实现外padding
public class ZiRuTextView extends android.support.v7.widget.AppCompatTextView {
    private int mllWidth, mllHeight;
    private Paint mPaint;
    private RectF mRectF;
    private String color;

    public ZiRuTextView(Context context) {
        this(context, null);
    }

    public ZiRuTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        mRectF = new RectF();
    }

    public void setBorderColor(String color) {
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }


    public void setBorderWidth(int borderWidth) {
        mPaint.setStrokeWidth(borderWidth);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mRectF, mPaint);
    }


}
