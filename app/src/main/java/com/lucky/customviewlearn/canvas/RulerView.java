package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by zfz on 2017/8/2.
 * 自定义尺子View
 */

public class RulerView extends View {

    private Paint mPaint;
    private Paint mNumberPaint;
    // 尺子左右外边距
    private static final int RULE_HEIGHT = 70;
    private static final int RULE_LEFT_RIGHT_MARGIN = 10;
    private static final int RULE_LEFT_RIGHT_PADDING = 5;
    private static final int RULE_TOP_BOTTOM_MARGIN = 5;
    private static final int RULE_MAX_LENGTH = 10;
    private static final int DIVIDER_TEXT_MARGIN = 4;

    private int divideRuleHeight, halfDivideRuleHeight;
    private int mTotalWidth, mTotalHeight;
    private int mHalfWidth, mHalfHeight;
    private Rect outRect;
    private int ruleBottom;
    private int leftRightMargin;
    private int firstLastLineMargin;
    private int topBottomPadding;
    private int intervalWidth;
    private int startLineX;
    private int maxLineTop;
    private int middleLineTop;
    private int normalLineTop;
    private int numberTextTop;


    public RulerView(Context context) {
        this(context, null);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.BLACK);

        mNumberPaint = new Paint();
        mNumberPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mNumberPaint.setTextSize(10);
        mNumberPaint.setColor(Color.BLACK);
        mNumberPaint.setAntiAlias(true);
        mNumberPaint.setStrokeWidth(1);

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        divideRuleHeight = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, RULE_HEIGHT, displayMetrics);
        halfDivideRuleHeight = divideRuleHeight/2;
        leftRightMargin  = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, RULE_LEFT_RIGHT_MARGIN, displayMetrics);
        firstLastLineMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, RULE_LEFT_RIGHT_PADDING, displayMetrics);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        mHalfWidth = w/2;
        mHalfHeight = h/2;

        int top = mHalfHeight - halfDivideRuleHeight;
        ruleBottom = top + divideRuleHeight;
        outRect = new Rect(leftRightMargin, top, mTotalWidth -leftRightMargin, ruleBottom);
        intervalWidth = (mTotalWidth - 2*leftRightMargin -2*firstLastLineMargin)/99;
        startLineX = leftRightMargin + firstLastLineMargin;
        maxLineTop = ruleBottom - divideRuleHeight/2;
        middleLineTop = ruleBottom - divideRuleHeight/3;
        normalLineTop = ruleBottom - divideRuleHeight/4;
        numberTextTop = maxLineTop - divideRuleHeight/10;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画外边框
        drawOuter(canvas);
        // 画刻度
        drawLines(canvas);
    }

    private void drawOuter(Canvas canvas){
        canvas.drawRect(outRect, mPaint);
    }

    private void drawLines(Canvas canvas){
        canvas.save();
        canvas.translate(startLineX, 0);
        int top = maxLineTop;
        for (int i =0 ; i <= 100; i++){
             if (i%10 ==0){
                 top = maxLineTop;
                 canvas.drawText(String.valueOf(i), 0, numberTextTop, mNumberPaint);
             }else if(i%5==0){
                 top = middleLineTop;
             }else{
                 top = normalLineTop;
             }
            canvas.drawLine(0, ruleBottom, 0, top, mPaint);
            canvas.translate(intervalWidth,0);
        }
        canvas.restore();
    }
}
