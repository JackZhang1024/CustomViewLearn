package com.lucky.customviewlearn.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.google.android.flexbox.FlexboxLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class ZiRuFlexBoxLayout extends FlexboxLayout implements Target {
    private static final String TAG = "ZiRuFlexBoxLayout";
    private Rect mRectF;
    private Paint mPaint;
    private float[] radiis = new float[8];
    private boolean mSetRadius, mSetBorder;
    private int mStrokeWidth = 1;
    private int mBorderColor;
    private PaintFlagsDrawFilter paintFlagsDrawFilter;
    private Bitmap mBackgroundBitmap;
    private int mBackgroundColor;
    private float mTopRightRadius, mBottomRightRadius, mBottomLeftRadius, mTopLeftRadius;
    private int mTopBorderWidth, mRightBorderWidth, mBottomBorderWidth, mLeftBorderWidth;
    private int mTopBorderColor, mRightBorderColor, mBottomBorderColor, mLeftBorderColor;

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
        mBorderColor = Color.GRAY;
        //抗锯齿
        paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        //硬件加速
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF.left = 0;
        mRectF.top = 0;
        mRectF.right = w;
        mRectF.bottom = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int halfStrokeWidth = (int) (mStrokeWidth / 2 + 0.5f);
        if (mSetRadius) {
            //绘制外边框
            Path path = new Path();
            //裁切绘制图片的区域
            RectF rectF = new RectF(halfStrokeWidth, halfStrokeWidth, width - halfStrokeWidth, height - halfStrokeWidth);
            setAllRadius();
            path.addRoundRect(rectF, radiis, Path.Direction.CW);
            canvas.setDrawFilter(paintFlagsDrawFilter);
            canvas.clipPath(path);
        }
        if (mBackgroundColor != -1) {
            // 绘制纯背景色
            int a = Color.alpha(mBackgroundColor);
            int r = Color.red(mBackgroundColor);
            int g = Color.green(mBackgroundColor);
            int b = Color.blue(mBackgroundColor);
            canvas.drawARGB(a, r, g, b);
        }
        if (mBackgroundBitmap != null) {
            // 绘制背景图片
            canvas.drawBitmap(mBackgroundBitmap, 0, 0, mPaint);
        }
        if (mSetBorder) {
            if (mTopBorderWidth > 0) {
                mPaint.setColor(mTopBorderColor);
                setBorderTop(width, height, halfStrokeWidth, canvas, mPaint);
            }
            if (mRightBorderWidth > 0) {
                mPaint.setColor(mRightBorderColor);
                setBorderRight(width, height, halfStrokeWidth, canvas, mPaint);
            }
            if (mBottomBorderWidth > 0) {
                mPaint.setColor(mBottomBorderColor);
                setBorderBottom(width, height, halfStrokeWidth, canvas, mPaint);
            }
            if (mLeftBorderWidth > 0) {
                mPaint.setColor(mLeftBorderColor);
                setBorderLeft(width, height, halfStrokeWidth, canvas, mPaint);
            }
        }
        super.dispatchDraw(canvas);
    }

    public void setCornerRadius(boolean setCornerRadius, float rightTop, float rightBottom, float leftBottom, float leftTop) {
        this.mSetRadius = setCornerRadius;
        this.mTopRightRadius = rightTop;
        this.mBottomRightRadius = rightBottom;
        this.mBottomLeftRadius = leftBottom;
        this.mTopLeftRadius = leftTop;
    }

    public void setBorder(int strokeWidth, int color) {
        mSetBorder = true;
        mStrokeWidth = strokeWidth;
        mBorderColor = color;
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setColor(color);
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
    }

    public void setBackgroundBitmap(@DrawableRes int resourceId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        mBackgroundBitmap = bitmap;
    }

    public void setBackgroundColor(@ColorRes int colorResourceId) {
        mBackgroundColor = colorResourceId;
    }

    // 绘制单圆角边框
    private void setSingleCornerBorder(Canvas canvas, int width, int height, int halfStrokeWidth) {
        Rect rect = new Rect(halfStrokeWidth, halfStrokeWidth, width - halfStrokeWidth, height - halfStrokeWidth);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(radiis, null, null));
        Paint paint = shapeDrawable.getPaint();
        paint.setColor(mBorderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mStrokeWidth);
        //RoundRectShape绘制的边界
        shapeDrawable.setBounds(rect);
        shapeDrawable.draw(canvas);
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

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        mBackgroundBitmap = bitmap;
        invalidate();
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }

    //border-top,border-bottom,border-left,border-right
    // 顶部边框
    public void setBorderTop(int width, int height, int halfStrokeWidth, Canvas canvas, Paint paint) {
        Path path = new Path();
        path.moveTo(0, mTopLeftRadius + halfStrokeWidth);
        // 圆内切
        if (mTopLeftRadius > 0) {
            path.arcTo(new RectF(halfStrokeWidth, halfStrokeWidth, mTopLeftRadius * 2 - halfStrokeWidth, mTopLeftRadius * 2 - halfStrokeWidth), -180, 90);
        }
        path.lineTo(width - mTopRightRadius, halfStrokeWidth);
        if (mTopRightRadius > 0) {
            path.arcTo(new RectF(width - 2 * mTopRightRadius + halfStrokeWidth, halfStrokeWidth, width - halfStrokeWidth, mTopRightRadius * 2 - halfStrokeWidth),
                    -90, 90);
        }
        canvas.drawPath(path, paint);
    }

    // 底部边框
    public void setBorderBottom(int width, int height, int halfStrokeWidth, Canvas canvas, Paint paint) {
        Path path = new Path();
        path.moveTo(0, height - mBottomLeftRadius - halfStrokeWidth);
        // 圆内切
        if (mBottomLeftRadius > 0) {
            path.arcTo(new RectF(halfStrokeWidth, height - mBottomLeftRadius * 2 + halfStrokeWidth, mBottomLeftRadius * 2 - halfStrokeWidth, height - halfStrokeWidth),
                    -180, -90);
        }
        path.lineTo(width - mBottomRightRadius, height - halfStrokeWidth);
        if (mBottomRightRadius > 0) {
            path.arcTo(new RectF(width - 2 * mBottomRightRadius + halfStrokeWidth, height - mBottomRightRadius * 2 + halfStrokeWidth,
                    width - halfStrokeWidth, height - halfStrokeWidth), 90, -90);
        }
        canvas.drawPath(path, paint);
    }

    // 左边边框
    public void setBorderLeft(int width, int height, int halfStrokeWidth, Canvas canvas, Paint paint) {
        Path path = new Path();
        path.moveTo(mTopLeftRadius + halfStrokeWidth, 0);
        // 圆内切
        if (mTopLeftRadius > 0) {
            path.arcTo(new RectF(halfStrokeWidth, halfStrokeWidth, mTopLeftRadius * 2 - halfStrokeWidth, mTopLeftRadius * 2 - halfStrokeWidth), -90, -90);
        }
        path.lineTo(halfStrokeWidth, height - mBottomLeftRadius);
        if (mBottomLeftRadius > 0) {
            path.arcTo(new RectF(halfStrokeWidth, height - mBottomLeftRadius * 2 + halfStrokeWidth, mBottomLeftRadius * 2 - halfStrokeWidth, height - halfStrokeWidth),
                    -180, -90);
        }
        canvas.drawPath(path, paint);
    }

    // 右边边框
    public void setBorderRight(int width, int height, int halfStrokeWidth, Canvas canvas, Paint paint) {
        Path path = new Path();
        path.moveTo(width - mTopRightRadius - halfStrokeWidth, 0);
        // 圆内切
        if (mTopRightRadius > 0) {
            path.arcTo(new RectF(width - 2 * mTopRightRadius + halfStrokeWidth, halfStrokeWidth, width - halfStrokeWidth, mTopRightRadius * 2 - halfStrokeWidth),
                    -90, 90);
        }
        path.lineTo(width - halfStrokeWidth, height - mBottomRightRadius);
        if (mBottomRightRadius > 0) {
            path.arcTo(new RectF(width - 2 * mBottomRightRadius + halfStrokeWidth, height - mBottomRightRadius * 2 + halfStrokeWidth, width - halfStrokeWidth, height - halfStrokeWidth),
                    0, 90);
        }
        canvas.drawPath(path, paint);
    }

}
