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
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.AttributeSet;
import android.util.Log;

import com.lucky.customviewlearn.core.DisplayUtil;


public class ZiRuRelativeLayout extends PercentRelativeLayout implements IZiRuViewExtend {
    private static final String TAG = "ZiRuRelativeLayout";
    private Paint mPaint;
    private Rect mRectF;
    private int mWidth, mHeight;
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
    private float mBorderWidth = 1;
    // 绘制矩形
    private boolean mDrawRectangle;
    // 绘制圆角矩形
    private boolean mDrawRoundCornerRectangle;
    // 绘制实心圆角矩形
    private boolean mDrawSolidRoundCornerRectangle;
    private int mStrokeWidth = 1;
    private int mColor;
    private int mOuterLeftPadding, mOuterRightPadding, mOuterTopPadding, mOuterBottomPadding;
    private boolean mDrawLeftOutSide, mDrawRightSide, mDrawTopSide, mDrawBottomSide, mDrawBorder;


    public ZiRuRelativeLayout(Context context) {
        this(context, null);
    }

    public ZiRuRelativeLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZiRuRelativeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mStrokeWidth = DisplayUtil.dp2px(getContext(), (int) mStrokeWidth);
        mColor = Color.parseColor("#00000000");
        setRoundCornerRadius(mRadius);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize= MeasureSpec.getSize(heightMeasureSpec);
        int heightMode= MeasureSpec.getMode(heightMeasureSpec);
        int length = getChildCount();

        int width = widthSize + getOuterLeftPadding() + getOuterRightPadding();
        int height = heightSize + getOuterTopPadding() + getOuterBottomPadding();
        Log.e(TAG, "onMeasure: ZiRuItemView Width "+width+" Height "+height+" left "+getOuterLeftPadding()+" right "+getOuterRightPadding()+" top "+getOuterTopPadding()+" bottom "+getOuterBottomPadding());
        Log.e(TAG, "onMeasure: width "+widthSize+" height "+heightSize);
        int widthSpec = getMeasureSpec(width, widthMode);
        int heightSpec = getMeasureSpec(height, heightMode);
        super.onMeasure(widthSpec, heightSpec);

//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int width  = 0;
//        int height = 0;
//        int length = getChildCount();
//        switch (widthMode) {
//            case MeasureSpec.AT_MOST: // 最小宽度
//                int calculatedWidth = 0;
//                for (int i = 0; i < length; i++) {
//                    View child = getChildAt(i);
//                    measureChild(child, widthMeasureSpec, heightMeasureSpec);
//                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) child.getLayoutParams();
//                    calculatedWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
//                }
//                calculatedWidth = calculatedWidth + getOuterLeftPadding() + getOuterRightPadding();
//                width = Math.min(widthSize, calculatedWidth);
//                break;
//            case MeasureSpec.UNSPECIFIED:
//            case MeasureSpec.EXACTLY:
//                width = widthSize + getOuterLeftPadding() + getOuterRightPadding();
//                break;
//        }
//        switch (heightMode) {
//            case MeasureSpec.AT_MOST: // 最小高度
//                int calculatedHeight = 0;
//                for (int i = 0; i < length; i++) {
//                    View child = getChildAt(i);
//                    measureChild(child, widthMeasureSpec, heightMeasureSpec);
//                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) child.getLayoutParams();
//                    calculatedHeight += child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
//                }
//                calculatedHeight = calculatedHeight + getOuterTopPadding() + getOuterBottomPadding();
//                height = Math.min(heightSize, calculatedHeight);
//                break;
//            case MeasureSpec.UNSPECIFIED:
//                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//                break;
//            case MeasureSpec.EXACTLY:
//                height = heightSize + getOuterTopPadding() + getOuterBottomPadding();
//                break;
//        }
//        setMeasuredDimension(width, height);
    }


    private int getMeasureSpec(int size, int mode){
        return  MeasureSpec.makeMeasureSpec(size, mode);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

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
        Log.e(TAG, "onSizeChanged: width "+mWidth+" height "+mHeight+" id "+getId());
    }


    // 继承自LinearLayout或者FrameLayout
    // 一般不会调用onDraw()方法 我们可以使用dispatchDraw()方法来
    // 进行我们想要的操作
    @Override
    protected void dispatchDraw(Canvas canvas) {
        //绘制一般的矩形
        //canvas.drawRect(mRectF, mPaint);
        // 绘制Border
        if (mDrawBorder) {
            if (mDrawLeftOutSide){
                canvas.drawLines(getLeftLinePoints(mRectF), mPaint);
            }
            if (mDrawRightSide){
                canvas.drawLines(getRightLinePoints(mRectF), mPaint);
            }
            if (mDrawTopSide){
                canvas.drawLines(getTopLinePoints(mRectF), mPaint);
            }
            if (mDrawBottomSide){
                canvas.drawLines(getBottomLinePoints(mRectF), mPaint);
            }
            super.dispatchDraw(canvas);
            return;
        }
        // 绘制圆角边框
        ShapeDrawable shapeDrawable = getShapeDrawable();
        if (shapeDrawable != null) {
            shapeDrawable.setBounds(mRectF);
            shapeDrawable.draw(canvas);
        }
        super.dispatchDraw(canvas);
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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

    private void setRoundCornerRadius(float radius) {
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
        mDrawRectangle = true;
    }

    @Override
    public void setBorderWidth(int borderWidth) {
        mBorderWidth = DisplayUtil.dp2px(getContext(), (int) borderWidth);
        mPaint.setStrokeWidth(mBorderWidth);
        mDrawRectangle = true;
    }

    @Override
    public void setBorder(int borderWidth, String color){
        mColor = Color.parseColor(color);
        mStrokeWidth =  DisplayUtil.dp2px(getContext(), (int) borderWidth);
        mPaint.setColor(mColor);
        mDrawRectangle = true;
    }

    // 内padding
    @Override
    public void setInnerPadding(int leftPadding, int topPadding, int rightPadding, int bottomPadding) {
        setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
    }

    // 外padding 需要扩展宽高
    @Override
    public void setOuterPadding(int leftPadding, int topPadding, int rightPadding, int bottomPadding) {
        this.mOuterLeftPadding = leftPadding;
        this.mOuterTopPadding = topPadding;
        this.mOuterRightPadding = rightPadding;
        this.mOuterBottomPadding = bottomPadding;
    }

    @Override
    public int getOuterBottomPadding() {
        return mOuterBottomPadding;
    }

    @Override
    public int getOuterLeftPadding() {
        return mOuterLeftPadding;
    }

    @Override
    public int getOuterRightPadding() {
        return mOuterRightPadding;
    }

    @Override
    public int getOuterTopPadding() {
        return mOuterTopPadding;
    }


    @Override
    public void setDrawTopSide(boolean mDrawTopSide) {
        this.mDrawTopSide = mDrawTopSide;
        this.mDrawBorder = true;
    }

    @Override
    public void setDrawLeftOutSide(boolean mDrawLeftOutSide) {
        this.mDrawLeftOutSide = mDrawLeftOutSide;
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
}
