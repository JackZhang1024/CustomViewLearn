package com.lucky.customviewlearn.view.style;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.Log;

import com.lucky.customviewlearn.core.DisplayUtil;
import com.lucky.customviewlearn.view.IZiRuViewExtend;

public class BorderStyle implements IZiRuViewExtend {
    private static final String TAG = "BorderStyle";
    private Paint mPaint;
    private float mRadius = 40;
    // 左上角圆角
    private boolean mLeftTopCorner;
    // 右上角圆角
    private boolean mRightTopCorner;
    // 左下角圆角
    private boolean mLeftBottomCorner;
    // 右下上角圆角
    private boolean mRightBottomCorner;
    private boolean isHasBorder = true;
    private float mBorderWidth = 5;
    // 绘制矩形
    private boolean mDrawRectangle;
    // 绘制圆角矩形
    private boolean mDrawRoundCornerRectangle;
    // 绘制实心圆角矩形
    private boolean mDrawSolidRoundCornerRectangle;
    private int mStrokeWidth = 1;
    private int mColor;
    public boolean mDrawLeftSide, mDrawRightSide, mDrawTopSide, mDrawBottomSide;
    public boolean mDrawBorder, mDrawRoundShape;
    private Context mContext;

    public BorderStyle() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }

    public void setRoundCornerRadius(float radius) {
        this.mRadius = radius;
        setLeftTopCorner(true);
        setLeftBottomCorner(true);
        setRightTopCorner(true);
        setRightBottomCorner(true);
    }

    @Override
    public void setLeftTopCorner(boolean leftTopCorner) {
        this.mLeftTopCorner = leftTopCorner;
    }

    @Override
    public void setRightTopCorner(boolean rightTopCorner) {
        this.mRightTopCorner = rightTopCorner;
    }

    @Override
    public void setLeftBottomCorner(boolean leftBottomCorner) {
        this.mLeftBottomCorner = leftBottomCorner;
    }

    @Override
    public void setRightBottomCorner(boolean rightBottomCorner) {
        this.mRightBottomCorner = rightBottomCorner;
    }

    @Override
    public void setDrawRect(boolean drawRect) {
        this.mDrawRectangle = drawRect;
    }

    // 实线边框
    @Override
    public void setDrawRoundCornerRect(boolean drawRoundCornerRect) {
        this.mDrawRoundCornerRectangle = drawRoundCornerRect;
    }

    // 实线填充布局
    @Override
    public void setDrawSolidRoundCornerRect(boolean drawSolidRoundRect) {
        this.mDrawSolidRoundCornerRectangle = drawSolidRoundRect;
    }

    @Override
    public ShapeDrawable getShapeDrawable() {
        ShapeDrawable shapeDrawable = null;
        Paint paint = null;
        if (mDrawRectangle) {
            shapeDrawable = new ShapeDrawable(new RectShape());
            paint = shapeDrawable.getPaint();
            paint.setColor(mColor);
            paint.setStrokeWidth(mStrokeWidth);
            paint.setStyle(Paint.Style.STROKE);
        } else if (mDrawRoundCornerRectangle) {
            float[] ffVar = getOutRadius();
            RectF rectF = new RectF(mBorderWidth, mBorderWidth, mBorderWidth, mBorderWidth);
            shapeDrawable = new ShapeDrawable(new RoundRectShape(ffVar, rectF, ffVar));
            paint = shapeDrawable.getPaint();
            paint.setColor(mColor);
        } else if (mDrawSolidRoundCornerRectangle) {
            float[] ffVar = getOutRadius();
            shapeDrawable = new ShapeDrawable(new RoundRectShape(ffVar, null, null));
            paint = shapeDrawable.getPaint();
            paint.setColor(mColor);
        }
        return shapeDrawable;
    }

    @Override
    public void setColor(String color) {
        mColor = Color.parseColor(color);
        mPaint.setColor(mColor);
        //mDrawRectangle = true;
    }

    @Override
    public void setBorderWidth(int borderWidth) {
        mBorderWidth = DisplayUtil.dp2px(mContext, (int) borderWidth);
        mPaint.setStrokeWidth(mBorderWidth);
        //mDrawRectangle = true;
    }

    @Override
    public void setBorder(int borderWidth, String color) {
        mColor = Color.parseColor(color);
        mStrokeWidth = DisplayUtil.dp2px(mContext, (int) borderWidth);
        mPaint.setColor(mColor);
        //mDrawRectangle = true;
    }

    @Override
    public void setDrawTopSide(boolean mDrawTopSide) {
        this.mDrawTopSide = mDrawTopSide;
        this.mDrawBorder = true;
    }

    @Override
    public void setDrawLeftOutSide(boolean mDrawLeftOutSide) {
        this.mDrawLeftSide = mDrawLeftOutSide;
        this.mDrawBorder = true;
    }

    @Override
    public void setDrawRightSide(boolean mDrawRightSide) {
        this.mDrawRightSide = mDrawRightSide;
        this.mDrawBorder = true;
    }

    @Override
    public void setDrawBottomSide(boolean mDrawBottomSide) {
        this.mDrawBottomSide = mDrawBottomSide;
        this.mDrawBorder = true;
    }

    private float[] getTopLinePoints(Rect rect) {
        float[] points = new float[4];
        points[0] = rect.left;
        points[1] = rect.top;
        points[2] = rect.right;
        points[3] = rect.top;
        return points;
    }

    private float[] getBottomLinePoints(Rect rect) {
        float[] points = new float[4];
        points[0] = rect.left;
        points[1] = rect.bottom;
        points[2] = rect.right;
        points[3] = rect.bottom;
        return points;
    }

    private float[] getLeftLinePoints(Rect rect) {
        float[] points = new float[4];
        points[0] = rect.left;
        points[1] = rect.top;
        points[2] = rect.left;
        points[3] = rect.bottom;
        return points;
    }

    private float[] getRightLinePoints(Rect rect) {
        float[] points = new float[4];
        points[0] = rect.right;
        points[1] = rect.top;
        points[2] = rect.right;
        points[3] = rect.bottom;
        return points;
    }


    @Override
    public float[] getOutRadius() {
        float[] outRadius = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        if (mLeftTopCorner) {
            outRadius[0] = mRadius;
            outRadius[1] = mRadius;
        }
        if (mRightTopCorner) {
            outRadius[2] = mRadius;
            outRadius[3] = mRadius;
        }
        if (mLeftBottomCorner) {
            outRadius[4] = mRadius;
            outRadius[5] = mRadius;
        }
        if (mRightBottomCorner) {
            outRadius[6] = mRadius;
            outRadius[7] = mRadius;
        }
        return outRadius;
    }

    public void setDrawBorder(boolean drawBorder) {
        this.mDrawBorder = drawBorder;
    }

    public void setDrawRoundShape(boolean drawRoundShape) {
        this.mDrawRoundShape = drawRoundShape;
    }

    public void drawBorder(Canvas canvas, Rect rect) {
        if (mDrawLeftSide) {
            canvas.drawLines(getLeftLinePoints(rect), mPaint);
        }
        if (mDrawRightSide) {
            canvas.drawLines(getRightLinePoints(rect), mPaint);
        }
        if (mDrawTopSide) {
            canvas.drawLines(getTopLinePoints(rect), mPaint);
        }
        if (mDrawBottomSide) {
            canvas.drawLines(getBottomLinePoints(rect), mPaint);
        }
    }

    public void drawRoundCorner(Canvas canvas, Rect rect) {
        // 绘制圆角边框
        ShapeDrawable shapeDrawable = getShapeDrawable();
        if (shapeDrawable != null) {
            shapeDrawable.setBounds(rect);
            shapeDrawable.draw(canvas);
        }
    }

}
