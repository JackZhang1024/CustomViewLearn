package com.lucky.customviewlearn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

// 带有文字的ImageView
public class ZiRuImageView extends ImageView {
    private static final String TAG = "ZiRuImageView";
    private Paint mPaint;
    private int mWidth, mHeight;
    private int mLeft , mBottom;
    public ZiRuImageView(Context context) {
        this(context, null);
    }

    public ZiRuImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(60);
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mLeft = 10;
        mBottom = mHeight-10;
        Log.e(TAG, "onSizeChanged: w "+w+"  h "+h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = getDrawable();
        int intrinsicWidth  = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Log.e(TAG, "onDraw: intrinsicWidth "+intrinsicWidth+" "+intrinsicHeight);
        canvas.drawText("返回", mLeft, mBottom, mPaint);
    }
}
