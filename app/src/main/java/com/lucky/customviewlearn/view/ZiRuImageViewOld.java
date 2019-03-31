package com.lucky.customviewlearn.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

import com.lucky.customviewlearn.utils.PicassoUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


/*
 * http://img.dafy.com/mobile/img3.0/dianhua.png
 * */
public class ZiRuImageViewOld extends AppCompatImageView implements Target {
    private static final String TAG = "ZiRuImageView";
    private Paint mPaint;
    private int mWidth, mHeight;
    private int mLeft, mBottom;
    private float mRadius = 0;
    private boolean mSetRadius;
    private float[] radiis = new float[8];
    private Path mPath;
    private RectF mRectF;
    private Bitmap mBackgroundBitmap;
    private boolean mAutoWidth, mAutoHeight;
    private Matrix matrix = null;

    public ZiRuImageViewOld(Context context) {
        this(context, null);
    }

    public ZiRuImageViewOld(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
    }

    public void setImageResource(String url) {
        PicassoUtils.getInstance().loadImageBitmap(getContext(), url, this);
    }

    public void setImageResource(String url, boolean autoWidth, boolean autoHeight) {
        mAutoWidth = autoWidth;
        mAutoHeight = autoHeight;
        PicassoUtils.getInstance().loadImageBitmap(getContext(), url, this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRectF = new RectF(0, 0, w, h);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        if (mSetRadius) {
            //mRadius = Math.min(width, height) / 2;
            setAllRadius();
            mPath.addRoundRect(mRectF, radiis, Path.Direction.CW);
            canvas.clipPath(mPath);
        }
        Log.e(TAG, "onDraw: width " + width + "  height " + height);
        if (mBackgroundBitmap != null) {
            // 绘制背景图片
            // 计算收缩比例
            float bmpWidth = mBackgroundBitmap.getWidth();
            float bmpHeight = mBackgroundBitmap.getHeight();
            float xScale = width / bmpWidth;
            float yScale = height / bmpHeight;
            if (yScale == 0 && xScale == 0) {
                matrix.postScale(1, 1);
            } else if (xScale == 0) {
                matrix.postScale(yScale, yScale);
            } else if (yScale == 0) {
                matrix.postScale(xScale, xScale);
            } else {
                matrix.postScale(xScale, yScale);
            }
            Log.e(TAG, "onDraw: x " + xScale + "  yScale " + yScale);
            Bitmap desiredBitmap = Bitmap.createBitmap(mBackgroundBitmap, 0, 0, mBackgroundBitmap.getWidth(), mBackgroundBitmap.getHeight(), matrix, true);
            canvas.drawBitmap(desiredBitmap, 0, 0, mPaint);
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

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        mBackgroundBitmap = bitmap;
        invalidate();

//        if (getWidth() == 0 || getHeight() == 0) {
//            int bmpWidth = bitmap.getWidth();
//            int bmpHeight = bitmap.getHeight();
//            float scale = getContext().getResources().getDisplayMetrics().density;
//            int layoutWidth = (int) (Math.ceil(bmpWidth * scale));
//            int layoutHeight = (int) (Math.ceil(bmpHeight * scale));
//            if (getParent() instanceof YogaLayout) {
//                YogaLayout yogaLayout = (YogaLayout) getParent();
//                final YogaNode imageNode = yogaLayout.getYogaNodeForView(this);
//                if (mAutoHeight) {
//                    imageNode.setHeight(layoutHeight);
//                }
//                if (mAutoWidth) {
//                    imageNode.setWidth(layoutWidth);
//                }
//            }
//            setImageBitmap(bitmap);
//            return;
//        }
//        setImageBitmap(bitmap);
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }
}
