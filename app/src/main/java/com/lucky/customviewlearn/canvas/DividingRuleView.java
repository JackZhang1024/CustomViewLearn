
package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class DividingRuleView extends View {

    // 刻度尺高度
    private static final int DIVIDING_RULE_HEIGHT = 70;
    // 距离左右间
    private static final int DIVIDING_RULE_MARGIN_LEFT_RIGHT = 10;

    // 第一条线距离边框距离
    private static final int FIRST_LINE_MARGIN = 5;
    // 打算绘制的厘米数
    private static final int DEFAULT_COUNT = 9;

    private Resources mResources;
    private Paint mOuterPaint, mLinePaint;
    private int mTotalWidth, mTotalHeight;
    private int mHalfWidth, mHalfHeight;

    // 刻度尺高度
    private int mDividRuleHeight, mHalfRuleHeight;
    // 刻度尺距离左边距离
    private int mDividRuleLeftMargin;
    // 第一条线距离边框距离
    private int mFirstLineMargin;
    // 最高刻度线上端位置
    private int mMaxLineTop;
    // 中等刻度线上端位置
    private int mMiddleLineTop;
    // 最低刻度线上端位置
    private int mMinLineTop;

    // 刻度线之间的距离
    private int mLineInterval;
    // 刻度尺底端位置
    private int mRuleBottom;
    // 刻度线起始点
    private int mLineStartX;

    // 外框区域
    private Rect mOutRect;


    public DividingRuleView(Context context) {
        this(context, null);
    }

    public DividingRuleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mResources = getResources();
        initData();
        initPaint();
    }

    /**
     * 对数据进行转化
     */
    private void initData() {
        mDividRuleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                DIVIDING_RULE_HEIGHT, mResources.getDisplayMetrics());
        mHalfRuleHeight = mDividRuleHeight / 2;

        mDividRuleLeftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                DIVIDING_RULE_MARGIN_LEFT_RIGHT, mResources.getDisplayMetrics());
        mFirstLineMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                FIRST_LINE_MARGIN, mResources.getDisplayMetrics());

    }

    private void initPaint() {
        mOuterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOuterPaint.setColor(Color.BLACK);
        mOuterPaint.setStyle(Style.STROKE);
        mOuterPaint.setStrokeWidth(1);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStyle(Style.FILL_AND_STROKE);
        mLinePaint.setStrokeWidth(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制外框
        drawOuter(canvas);
        // 绘制刻度线
        drawLines(canvas);
        // 绘制数字
        drawNumbers(canvas);
    }

    /**
     * 绘制数字
     *
     * @param canvas
     */
    private void drawNumbers(Canvas canvas) {

    }

    /**
     * 绘制刻度线
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        canvas.save();
        canvas.translate(mLineStartX, 0);
        int top = mMaxLineTop;
        for (int i = 0; i <= DEFAULT_COUNT * 10; i++) {
            if (i % 10 == 0) {
                top = mMaxLineTop;
            } else if (i % 5 == 0) {
                top = mMiddleLineTop;
            } else {
                top = mMinLineTop;
            }

            canvas.drawLine(0, mRuleBottom, 0, top, mLinePaint);
            canvas.translate(mLineInterval, 0);

        }
        canvas.restore();

    }

    private void drawOuter(Canvas canvas) {
        canvas.drawRect(mOutRect, mOuterPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        mHalfWidth = w / 2;
        mHalfHeight = h / 2;

        int top = mHalfHeight - mHalfRuleHeight;
        mRuleBottom = top
                + mDividRuleHeight;
        mOutRect = new Rect(mDividRuleLeftMargin, top, mTotalWidth - mDividRuleLeftMargin,
                mRuleBottom);

        mLineInterval = (mTotalWidth - 2 * mDividRuleLeftMargin - 2 * mFirstLineMargin)
                / (DEFAULT_COUNT * 10 - 1);
        mLineStartX = mDividRuleLeftMargin + mFirstLineMargin;
        mMaxLineTop = mRuleBottom - mDividRuleHeight / 2;
        mMiddleLineTop = mRuleBottom - mDividRuleHeight / 3;
        mMinLineTop = mRuleBottom - mDividRuleHeight / 4;
    }
}
