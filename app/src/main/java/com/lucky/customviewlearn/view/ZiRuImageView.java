package com.lucky.customviewlearn.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

import com.lucky.customviewlearn.utils.PicassoUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ZiRuImageView extends AppCompatImageView implements Target {
    private static final String TAG = "ZiRuImageView";
    private Paint mPaint;
    private int mWidth, mHeight;
    private int mLeft, mBottom;
    private float mRadius = 0;
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
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public void setImageResource(String url) {
        //ImageLoader.getInstance(getContext()).loadImage(url, this);
        PicassoUtils.getinstance().loadImageBitmap(getContext(), url, this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged: w " + w + " h " + h);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw: " + getWidth() + " " + getHeight() + " " + getId());
        if (mSetRadius) {
            int width = getWidth();
            int height = getHeight();
            Path path = new Path();
            RectF rectF = new RectF(0, 0, width, height);
            //mRadius = Math.min(width, height) / 2;
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

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) { // 100021
        //onBitmapLoaded: 17 22 2131296478 48 0
        int bmpWidth = bitmap.getWidth();
        int bmpHeight = bitmap.getHeight();
        setImageBitmap(bitmap);
//        getLayoutParams().width = bmpWidth;
//        getLayoutParams().height = bmpHeight;
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }

    public static Bitmap getScaledBitmap(Bitmap bitmap, float scale) {
        float scaledWidth = 1;
        float scaledHeight = 1;
        int bmpWidth = bitmap.getWidth();
        int bmpHeight = bitmap.getHeight();
        //计算出此次要缩小的比例
        scaledWidth = (float) (scaledWidth * scale);
        scaledHeight = (float) (scaledHeight * scale);
        //生成缩放之后的Bitmap对象
        Matrix matrix = new Matrix();
        matrix.postScale(scaledWidth, scaledHeight);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bmpWidth, bmpHeight, matrix, true);
        return bitmap;
    }
}