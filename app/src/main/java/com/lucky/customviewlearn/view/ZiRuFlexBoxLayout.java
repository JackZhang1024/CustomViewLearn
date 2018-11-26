package com.lucky.customviewlearn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;

import com.google.android.flexbox.FlexboxLayout;

public class ZiRuFlexBoxLayout extends FlexboxLayout {
    private static final String TAG = "ZiRuFlexBoxLayout";
    private Rect mRectF;
    private int mWidth, mHeight;
    private float mRadius = 4;
    private Paint mPaint;
    private float[] radiis = new float[8];
    private boolean mSetRadius, mSetBorder;
    private int mStrokeWidth;

    public ZiRuFlexBoxLayout(Context context) {
        this(context, null);
    }

    public ZiRuFlexBoxLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mRectF = new Rect();
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRectF.left = 0;
        mRectF.top = 0;
        mRectF.right = mWidth;
        mRectF.bottom = mHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Path path = new Path();
        int width = getWidth();
        int height = getHeight();
        int halfStrokeWidth = (int) (mStrokeWidth / 2 + 0.5f);
        RectF rectFF = new RectF(halfStrokeWidth, halfStrokeWidth, width - halfStrokeWidth, height - halfStrokeWidth);
        canvas.drawRoundRect(rectFF, mRadius, mRadius, mPaint);
        RectF rectF = new RectF(halfStrokeWidth, halfStrokeWidth, width - halfStrokeWidth, height - halfStrokeWidth);
        if (mSetRadius) {
            setAllRadius();
            path.addRoundRect(rectF, radiis, Path.Direction.CW);
            canvas.clipPath(path);
        }
        super.dispatchDraw(canvas);
    }

    private void setBorderColor(int borderColor) {
        mPaint.setColor(borderColor);
    }

    public void setRadius(boolean setRadius, float radius) {
        this.mSetRadius = setRadius;
        this.mRadius = radius;
    }

    private void setAllRadius() {
        for (int index = 0; index < radiis.length; index++) {
            radiis[index] = mRadius;
        }
    }

    public void setBorderWidth(int borderWidth) {
        mStrokeWidth = borderWidth;
        mSetBorder = true;
        mPaint.setStrokeWidth(borderWidth);
    }
}
