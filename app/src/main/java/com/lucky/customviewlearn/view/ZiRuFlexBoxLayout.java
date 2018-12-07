package com.lucky.customviewlearn.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.google.android.flexbox.FlexboxLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ZiRuFlexBoxLayout extends FlexboxLayout implements Target {
    private static final String TAG = "ZiRuFlexBoxLayout";
    private Rect mRectF;
    private float mRadius = 4;
    private Paint mPaint;
    private float[] radiis = new float[8];
    private boolean mSetRadius, mSetBorder;
    private int mStrokeWidth = 1;

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
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int mWidth = w;
        int mHeight = h;
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
        int width = getWidth();
        int height = getHeight();
        if (mSetRadius) {
            Path path = new Path();
            int halfStrokeWidth = (int) (mStrokeWidth / 2 + 0.5f);
            RectF rectFF = new RectF(halfStrokeWidth, halfStrokeWidth, width - halfStrokeWidth, height - halfStrokeWidth);
            canvas.drawRoundRect(rectFF, mRadius, mRadius, mPaint);
            RectF rectF = new RectF(halfStrokeWidth, halfStrokeWidth, width - halfStrokeWidth, height - halfStrokeWidth);
            setAllRadius();
            path.addRoundRect(rectF, radiis, Path.Direction.CW);
            canvas.clipPath(path);
        }
        if (mSetBorder) {
            int halfStrokeWidth = (int) (mStrokeWidth / 2 + 0.5f);
            RectF rectFF = new RectF(halfStrokeWidth, halfStrokeWidth, width - halfStrokeWidth, height - halfStrokeWidth);
            canvas.drawRoundRect(rectFF, mRadius, mRadius, mPaint);
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

    public void setBorder(int strokeWidth, int color) {
        mStrokeWidth = strokeWidth;
        mSetBorder = true;
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setColor(color);
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        setBackgroundDrawable(bitmapDrawable);
        if (mSetRadius) {
            RoundImageDrawable roundImageDrawable = new RoundImageDrawable(bitmap);
            setBackgroundDrawable(roundImageDrawable);
        }
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
}
