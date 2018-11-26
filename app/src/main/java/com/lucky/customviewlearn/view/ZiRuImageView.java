package com.lucky.customviewlearn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.utils.PicassoUtils;

public class ZiRuImageView extends AppCompatImageView {
    private static final String TAG = "ZiRuImageView";
    private Paint mPaint;
    private int mWidth, mHeight;
    private int mLeft, mBottom;
    private float mRadius = 137;
    private boolean mSetRadius;
    private float[] radiis = new float[8];

    public ZiRuImageView(Context context) {
        this(context, null);
    }

    public ZiRuImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public void setImageResource(String url) {
        PicassoUtils.getinstance().LoadImage(getContext(), url, this, R.drawable.img_loading, R.drawable.img_load_error,
                PicassoUtils.PICASSO_BITMAP_SHOW_NORMAL_TYPE, 20);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mSetRadius) {
            int width = getWidth();
            int height = getHeight();
            Path path = new Path();
            RectF rectF = new RectF(0, 0, width, height);
            mRadius = Math.min(width, height) / 2;
            setAllRadius();
            path.addRoundRect(rectF, radiis, Path.Direction.CW);
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
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

}
