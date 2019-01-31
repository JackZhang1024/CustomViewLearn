package com.lucky.customviewlearn.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.lucky.customviewlearn.utils.PicassoUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ZiRuImageView extends AppCompatImageView implements Target {
    private static final String TAG = "ZiRuImageView";
    private Paint mPaint;
    private int mWidth, mHeight;
    private int mLeft, mBottom;
    private boolean mSetRadius;
    private float[] radiis = new float[8];
    private Path mPath;
    private RectF mRectF;
    private boolean mAutoWidth, mAutoHeight;
    private float mTopLeftRadius, mTopRightRadius, mBottomRightRadius, mBottomLeftRadius;
    private int mBackgroundColor;
    private boolean mHasBackgroundColor;
    private int mTopBorderWidth, mRightBorderWidth, mBottomBorderWidth, mLeftBorderWidth;
    private int mTopBorderColor, mRightBorderColor, mBottomBorderColor, mLeftBorderColor;
    private boolean mSetBorder;
    private int mStrokeWidth = 1;

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
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRectF = new RectF(0, 0, w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        //mRadius = Math.min(width, height) / 2;
        adjustAllRadius(width, height);
        if (mSetRadius) {
            setAllRadius();
            mPath.addRoundRect(mRectF, radiis, Path.Direction.CW);
            canvas.clipPath(mPath);
        }
        if (mHasBackgroundColor) {
            // 绘制纯背景色
            int a = Color.alpha(mBackgroundColor);
            int r = Color.red(mBackgroundColor);
            int g = Color.green(mBackgroundColor);
            int b = Color.blue(mBackgroundColor);
            canvas.drawARGB(a, r, g, b);
        }
        if (mSetBorder) {
            int halfStrokeWidth = (int) (mStrokeWidth / 2 + 0.5f);
            if (mTopBorderWidth > 0) {
                mPaint.setColor(mTopBorderColor);
                mPaint.setStrokeWidth(mTopBorderWidth);
                //halfStrokeWidth = (int) (mTopBorderWidth / 2 + 0.5f);
                setBorderTop(width, height, halfStrokeWidth, canvas, mPaint);
            }
            if (mRightBorderWidth > 0) {
                mPaint.setColor(mRightBorderColor);
                mPaint.setStrokeWidth(mRightBorderWidth);
                setBorderRight(width, height, halfStrokeWidth, canvas, mPaint);
            }
            if (mBottomBorderWidth > 0) {
                mPaint.setColor(mBottomBorderColor);
                mPaint.setStrokeWidth(mBottomBorderWidth);
                setBorderBottom(width, height, halfStrokeWidth, canvas, mPaint);
            }
            if (mLeftBorderWidth > 0) {
                mPaint.setColor(mLeftBorderColor);
                mPaint.setStrokeWidth(mLeftBorderWidth);
                setBorderLeft(width, height, halfStrokeWidth, canvas, mPaint);
            }
        }
        super.onDraw(canvas);
    }

    public void setAllBorders(int topBorderWidth, int rightBorderWidth, int bottomBorderWidth, int leftBorderWidth) {
        mSetBorder = true;
        mTopBorderWidth = topBorderWidth;
        mRightBorderWidth = rightBorderWidth;
        mBottomBorderWidth = bottomBorderWidth;
        mLeftBorderWidth = leftBorderWidth;
    }

    public void setAllBorderColors(String topBorderColor, String rightBorderColor, String bottomBorderColor, String leftBorderColor) {
        mTopBorderColor = Color.parseColor(Colors.getHexColor(topBorderColor));
        mRightBorderColor = Color.parseColor(Colors.getHexColor(rightBorderColor));
        mBottomBorderColor = Color.parseColor(Colors.getHexColor(bottomBorderColor));
        mLeftBorderColor = Color.parseColor(Colors.getHexColor(leftBorderColor));
        invalidate();
    }

    public void setBackgroundColors(int backgroundColors) {
        this.mHasBackgroundColor = true;
        this.mBackgroundColor = backgroundColors;
    }

    public void setRadius(boolean setRadius, float leftTopRadius, float rightTopRadius, float rightBottomRadius, float leftBottomRadius) {
        this.mSetRadius = setRadius;
        this.mTopLeftRadius = leftTopRadius;
        this.mTopRightRadius = rightTopRadius;
        this.mBottomRightRadius = rightBottomRadius;
        this.mBottomLeftRadius = leftBottomRadius;
    }

    private void setAllRadius() {
        radiis[0] = mTopLeftRadius;
        radiis[1] = mTopLeftRadius;
        radiis[2] = mTopRightRadius;
        radiis[3] = mTopRightRadius;
        radiis[4] = mBottomRightRadius;
        radiis[5] = mBottomRightRadius;
        radiis[6] = mBottomLeftRadius;
        radiis[7] = mBottomLeftRadius;
    }

    // 调整圆弧大小
    private void adjustAllRadius(int width, int height) {
        int miniSize = Math.min(width, height);
        float halfMiniSize = miniSize / 2;
        if (mTopLeftRadius > halfMiniSize) {
            radiis[0] = halfMiniSize;
            radiis[1] = halfMiniSize;
            mTopLeftRadius = halfMiniSize;
        }
        if (mTopRightRadius > halfMiniSize) {
            radiis[2] = halfMiniSize;
            radiis[3] = halfMiniSize;
            mTopRightRadius = halfMiniSize;
        }
        if (mBottomRightRadius > halfMiniSize) {
            radiis[4] = halfMiniSize;
            radiis[5] = halfMiniSize;
            mBottomRightRadius = halfMiniSize;
        }
        if (mBottomLeftRadius > halfMiniSize) {
            radiis[6] = halfMiniSize;
            radiis[7] = halfMiniSize;
            mBottomLeftRadius = halfMiniSize;
        }
    }


    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        if (getWidth() == 0 || getHeight() == 0) {
            int bmpWidth = bitmap.getWidth();
            int bmpHeight = bitmap.getHeight();
            float scale = getContext().getResources().getDisplayMetrics().density;
            int layoutWidth = (int) (Math.ceil(bmpWidth * scale));
            int layoutHeight = (int) (Math.ceil(bmpHeight * scale));
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
            setImageBitmap(bitmap);
            return;
        }
        setImageBitmap(bitmap);
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }

    // 顶部边框
    private void setBorderTop(int width, int height, int halfStrokeWidth, Canvas canvas, Paint paint) {
        Path path = new Path();
        //顶部横线
        path.moveTo(0 + mTopLeftRadius + halfStrokeWidth, halfStrokeWidth);
        path.lineTo(width - mTopRightRadius - halfStrokeWidth, halfStrokeWidth);
        //左边弧线
        if (mTopLeftRadius > 0) {
            path.moveTo(0 + mTopLeftRadius + halfStrokeWidth, halfStrokeWidth);
            path.arcTo(new RectF(halfStrokeWidth, halfStrokeWidth, mTopLeftRadius * 2 - halfStrokeWidth, mTopLeftRadius * 2 - halfStrokeWidth), -90, -45);
        }
        //右边弧线
        if (mTopRightRadius > 0) {
            path.moveTo(width - mTopRightRadius - halfStrokeWidth, halfStrokeWidth);
            path.arcTo(new RectF(width - 2 * mTopRightRadius + halfStrokeWidth, halfStrokeWidth, width - halfStrokeWidth, mTopRightRadius * 2 - halfStrokeWidth),
                    -90, 45);
        }
        canvas.drawPath(path, paint);
    }

    // 底部边框
    private void setBorderBottom(int width, int height, int halfStrokeWidth, Canvas canvas, Paint paint) {
        //底部横线
        Path path = new Path();
        path.moveTo(0 + mBottomLeftRadius + halfStrokeWidth, height - halfStrokeWidth);
        path.lineTo(width - mBottomRightRadius - halfStrokeWidth, height - halfStrokeWidth);
        //左边弧线
        if (mBottomLeftRadius > 0) {
            path.moveTo(0 + mBottomLeftRadius + halfStrokeWidth, height - halfStrokeWidth);
            path.arcTo(new RectF(halfStrokeWidth, height - mBottomLeftRadius * 2 + halfStrokeWidth, mBottomLeftRadius * 2 - halfStrokeWidth, height - halfStrokeWidth),
                    90, 45);
        }
        //右边弧线
        if (mBottomRightRadius > 0) {
            path.moveTo(width - mBottomRightRadius - halfStrokeWidth, height - halfStrokeWidth);
            path.arcTo(new RectF(width - 2 * mBottomRightRadius + halfStrokeWidth, height - mBottomRightRadius * 2 + halfStrokeWidth,
                    width - halfStrokeWidth, height - halfStrokeWidth), 90, -45);
        }
        canvas.drawPath(path, paint);
    }

    // 左边边框
    private void setBorderLeft(int width, int height, int halfStrokeWidth, Canvas canvas, Paint paint) {
        Path path = new Path();
        path.moveTo(halfStrokeWidth, mTopLeftRadius + halfStrokeWidth);
        path.lineTo(halfStrokeWidth, height - mBottomLeftRadius - halfStrokeWidth);
        // 顶部弧线
        if (mTopLeftRadius > 0) {
            path.moveTo(halfStrokeWidth, mTopLeftRadius + halfStrokeWidth);
            path.arcTo(new RectF(halfStrokeWidth, halfStrokeWidth, mTopLeftRadius * 2 - halfStrokeWidth, mTopLeftRadius * 2 - halfStrokeWidth), -180, 45);
        }
        // 底部弧线
        if (mBottomLeftRadius > 0) {
            path.moveTo(halfStrokeWidth, height - mBottomLeftRadius - halfStrokeWidth);
            path.arcTo(new RectF(halfStrokeWidth, height - mBottomLeftRadius * 2 + halfStrokeWidth, mBottomLeftRadius * 2 - halfStrokeWidth, height - halfStrokeWidth),
                    -180, -45);
        }
        canvas.drawPath(path, paint);
    }

    // 右边边框
    private void setBorderRight(int width, int height, int halfStrokeWidth, Canvas canvas, Paint paint) {
        Path path = new Path();
        path.moveTo(width - halfStrokeWidth, mTopRightRadius + halfStrokeWidth);
        path.lineTo(width - halfStrokeWidth, height - mBottomRightRadius - halfStrokeWidth);
        // 顶部弧线
        if (mTopRightRadius > 0) {
            path.moveTo(width - halfStrokeWidth, mTopRightRadius + halfStrokeWidth);
            path.arcTo(new RectF(width - 2 * mTopRightRadius + halfStrokeWidth, halfStrokeWidth, width - halfStrokeWidth, mTopRightRadius * 2 - halfStrokeWidth),
                    0, -45);
        }
        // 底部弧线
        if (mBottomRightRadius > 0) {
            path.moveTo(width - halfStrokeWidth, height - mBottomRightRadius - halfStrokeWidth);
            path.arcTo(new RectF(width - 2 * mBottomRightRadius + halfStrokeWidth, height - mBottomRightRadius * 2 + halfStrokeWidth, width - halfStrokeWidth, height - halfStrokeWidth),
                    0, 45);
        }
        canvas.drawPath(path, paint);
    }

    public void setImageResource(String url) {
        PicassoUtils.getInstance().loadImageBitmap(getContext(), url, this);
    }

    public void setImageResource(String url, boolean autoWidth, boolean autoHeight) {
        mAutoWidth = autoWidth;
        mAutoHeight = autoHeight;
        PicassoUtils.getInstance().loadImageBitmap(getContext(), url, this);
    }
}
