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
 * 注意精度问题 该用float的时候千万不要用int
 * 否则会发生精度误差 出现一些奇葩的问题
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

    private float divideRuleHeight, halfDivideRuleHeight;
    private float mTotalWidth, mTotalHeight;
    private float mHalfWidth, mHalfHeight;
    private Rect outRect;
    private float ruleBottom;
    private float leftRightMargin;
    private float firstLastLineMargin;
    private int topBottomPadding;
    private float intervalWidth;
    private float startLineX;
    private float maxLineTop;
    private float middleLineTop;
    private float normalLineTop;
    private float numberTextTop;
    // 打算绘制的厘米数
    private static final int DEFAULT_COUNT = 10;
    // 每条竖线所占的像素
    private static final int LINE_SP = 3;

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
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(LINE_SP);
        mPaint.setColor(Color.BLACK);

        mNumberPaint = new Paint();
        mNumberPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mNumberPaint.setTextSize(20);
        mNumberPaint.setColor(Color.BLACK);
        mNumberPaint.setAntiAlias(true);
        mNumberPaint.setStrokeWidth(3);

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        divideRuleHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, RULE_HEIGHT, displayMetrics);
        halfDivideRuleHeight = divideRuleHeight/2;
        leftRightMargin  = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, RULE_LEFT_RIGHT_MARGIN, displayMetrics);
        firstLastLineMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, RULE_LEFT_RIGHT_PADDING, displayMetrics);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight= h;
        mHalfWidth  = w/2;
        mHalfHeight = h/2;

        float top = mHalfHeight - halfDivideRuleHeight;
        ruleBottom = top + divideRuleHeight;
        outRect = new Rect((int)leftRightMargin, (int)top, (int)(mTotalWidth -leftRightMargin), (int)ruleBottom);
        intervalWidth = (mTotalWidth - 2*leftRightMargin -2*firstLastLineMargin)/(DEFAULT_COUNT*10);
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
        // 将divider移动到第一个
        canvas.translate(startLineX, 0);
        float top = maxLineTop;
        for (int i =0 ; i <= DEFAULT_COUNT*10; i++){
             if (i%10 ==0){
                 top = maxLineTop;
                 canvas.drawText(String.valueOf(i), 0, numberTextTop, mNumberPaint);
             }else if(i%5==0){
                 top = middleLineTop;
             }else{
                 top = normalLineTop;
             }
            //在上一个直线所存在的位置绘制一条直线 然后向右移动一个intervalWidth
            //记住：所有类似的操作都是 先做scale rotate translate skew 然后才去绘制
            canvas.drawLine(0, ruleBottom, 0, top, mPaint);
            canvas.translate(intervalWidth, 0);
        }
        canvas.restore();
    }
}
