package com.lucky.customviewlearn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.lucky.customviewlearn.core.DisplayUtil;


// RoundRectDrawable
// 满足两种情况
// 1 圆角矩形
// 2 圆角实心矩形
// 3 矩形
public class ZiRuLinearLayoutNew extends LinearLayout {
    private static final String TAG = "ZiRuLinearLayoutNew";
    private Paint mPaint;
    private Rect mRectF;
    private int mWidth, mHeight;
    private float mRadius = 10;
    // 左上角圆角
    private boolean mLeftTopCorner;
    // 右上角圆角
    private boolean mRightTopCorner;
    // 左下角圆角
    private boolean mLeftBottomCorner;
    // 右下上角圆角
    private boolean mRightBottomCorner;
    private boolean isHasBorder = true;
    private float mBorderWidth = 1;
    // 绘制矩形
    private boolean mDrawRectangle;
    // 绘制圆角矩形
    private boolean mDrawRoundCornerRectangle;
    // 绘制实心圆角矩形
    private boolean mDrawSolidRoundCornerRectangle;
    private int mStrokeWidth = 2;

    public ZiRuLinearLayoutNew(Context context) {
        this(context, null);
    }

    public ZiRuLinearLayoutNew(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZiRuLinearLayoutNew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        mRectF = new Rect();
        mBorderWidth = DisplayUtil.dp2px(getContext(), (int) mBorderWidth);
        mStrokeWidth = DisplayUtil.dip22px(getContext(), (int)mStrokeWidth);
        setRoundCornerRadius(40);
        setBackground(null);
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


    // 继承自LinearLayout或者FrameLayout
    // 一般不会调用onDraw()方法 我们可以使用dispatchDraw()方法来
    // 进行我们想要的操作
    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.e(TAG, "dispatchDraw: ");
        //绘制一般的矩形
        //canvas.drawRect(mRectF, mPaint);
        ShapeDrawable shapeDrawable = getShapeDrawable();
        if (shapeDrawable != null) {
            shapeDrawable.setBounds(mRectF);
            shapeDrawable.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw: ");
    }


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

    private void setRoundCornerRadius(int radius) {
        this.mRadius = radius;
        setLeftTopCorner(true);
        setLeftBottomCorner(true);
        setRightTopCorner(true);
        setRightBottomCorner(true);
        //invalidate();
    }

    public void setLeftTopCorner(boolean leftTopCorner) {
        this.mLeftTopCorner = leftTopCorner;
    }

    public void setRightTopCorner(boolean rightTopCorner) {
        this.mRightTopCorner = rightTopCorner;
    }

    public void setLeftBottomCorner(boolean leftBottomCorner) {
        this.mLeftBottomCorner = leftBottomCorner;
    }

    public void setRightBottomCorner(boolean rightBottomCorner) {
        this.mRightBottomCorner = rightBottomCorner;
    }

    public void setDrawRect(boolean drawRect) {
        this.mDrawRectangle = drawRect;
        invalidate();
    }

    public void setDrawRoundCornerRect(boolean drawRoundCornerRect){
        this.mDrawRoundCornerRectangle = drawRoundCornerRect;
        invalidate();
    }

    public void setDrawSolidRoundCornerRect(boolean drawSolidRoundRect){
        this.mDrawSolidRoundCornerRectangle = drawSolidRoundRect;
        invalidate();
    }

    public ShapeDrawable getShapeDrawable() {
        ShapeDrawable shapeDrawable = null;
        Paint paint = null;
        if (mDrawRectangle) {
            shapeDrawable = new ShapeDrawable(new RectShape());
            paint = shapeDrawable.getPaint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(mStrokeWidth);
            paint.setStyle(Paint.Style.STROKE);
        } else if (mDrawRoundCornerRectangle) {
            float[] ffVar = getOutRadius();
            RectF rectF = new RectF(mBorderWidth, mBorderWidth, mBorderWidth, mBorderWidth);
            shapeDrawable = new ShapeDrawable(new RoundRectShape(ffVar, rectF, ffVar));
            paint = shapeDrawable.getPaint();
            paint.setColor(Color.RED);
        } else if (mDrawSolidRoundCornerRectangle) {
            float[] ffVar = getOutRadius();
            shapeDrawable = new ShapeDrawable(new RoundRectShape(ffVar, null, null));
            paint = shapeDrawable.getPaint();
            paint.setColor(Color.BLACK);
        }
        return shapeDrawable;
    }

}
