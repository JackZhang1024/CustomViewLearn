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
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;

import com.lucky.customviewlearn.core.DisplayUtil;

// 圆角显示输入框
public class ZiRuEditTextView extends EditText {
    private static final String TAG = "ZiRuEditTextView";
    // 绘制矩形
    private boolean mDrawRectangle;
    // 绘制圆角矩形
    private boolean mDrawRoundCornerRectangle;
    // 绘制实心圆角矩形
    private boolean mDrawSolidRoundCornerRectangle;
    private int mStrokeWidth = 2;
    private float mBorderWidth = 1;
    private float mRadius = 10;
    private Rect mRectF;
    private int mWidth, mHeight;

    public ZiRuEditTextView(Context context) {
        this(context, null);
    }

    public ZiRuEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mRectF = new Rect();
        mBorderWidth = DisplayUtil.dp2px(getContext(), (int) mBorderWidth);
        mStrokeWidth = DisplayUtil.dip22px(getContext(), (int) mStrokeWidth);
        setGravity(Gravity.CENTER_VERTICAL);
        setRoundCornerRadius(40);
        mDrawSolidRoundCornerRectangle = true;
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
        Log.e(TAG, "onSizeChanged: w "+w+" h "+h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        ShapeDrawable shapeDrawable = getShapeDrawable();
        if (shapeDrawable != null) {
            shapeDrawable.setBounds(mRectF);
            shapeDrawable.draw(canvas);
        }
        super.onDraw(canvas);
    }

    public void setDrawRect(boolean drawRect) {
        this.mDrawRectangle = drawRect;
        invalidate();
    }

    public void setDrawRoundCornerRect(boolean drawRoundCornerRect) {
        this.mDrawRoundCornerRectangle = drawRoundCornerRect;
        invalidate();
    }

    public void setDrawSolidRoundCornerRect(boolean drawSolidRoundRect) {
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

    public float[] getOutRadius() {
        return new float[]{mRadius, mRadius, mRadius, mRadius, mRadius, mRadius, mRadius, mRadius};
    }


    public void setRoundCornerRadius(int roundCornerRadius) {
        this.mRadius = roundCornerRadius;
    }
}
