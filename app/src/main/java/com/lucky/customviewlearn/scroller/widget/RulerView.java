package com.lucky.customviewlearn.scroller.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.lucky.customviewlearn.R;

import timber.log.Timber;


// 尺子
public class RulerView extends View {

    private int mMinVelocity;
    // Scroller 是一个专门处理滚动效果的类，用mScroller 记录和计算View滚动的位置 再重写View的computeScroll() 完成实际的滚动
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker; // 主要用跟踪触摸屏事件（flinging事件和其他Gesture手势事件）的速率
    private int mWidth;
    private int mHeight;

    // 未选择时 默认的值 滑动后表示当前中间指针正在指着的值
    private float mSelectorValue = 50.0f;
    // 最大值
    private float mMaxValue = 200;
    // 最小值
    private float mMinValue = 100.0f;

    // 最小单位 如 1:表示 每2条刻度差为1
    private float mPerValue = 1;

    // 尺子刻度2条线之间的距离
    private float mLineSpaceWidth = 5;
    // 尺子刻度的宽度
    private float mLineWidth = 4;
    // 尺子刻度中最常的高度对应着 10 20 30 就是10的倍数
    private float mLineMaxHeight = 420;
    // 尺子刻度中中间高度 对应着 5 15 25
    private float mLineMidHeight = 30;
    // 尺子刻度中最小的高度 1 2 3 4 这些值
    private float mLineMinHeight = 17;


    private float mTextMarginTop = 10;
    // 尺子刻度下方的数字 textSize
    private float mTextSize = 30;

    // 尺子 最左边 最后边 是否需要透明（透明效果更好点）
    private boolean mAlphaEnable = false;

    // 尺子刻度下方数字的高度
    private float mTextHeight;

    // 刻度下方数字 Paint
    private Paint mTextPaint;
    // 尺子刻度 Paint
    private Paint mLinePaint;

    // 共有多少条 刻度
    private int mTotalLine;
    // 所有刻度  共有多长
    private int mMaxOffset;
    // 默认状态下 mSelectorValue所在的位置 位于尺子总刻度的位置
    private float mOffset;
    private int mLastX, mMove;
    // 滑动后数值回调
    private OnValueChangeListener mListener;

    // 刻度的颜色
    private int mLineColor = Color.GRAY;
    // 文字的颜色
    private int mTextColor = Color.BLACK;

    // 滑动后的回调
    public interface OnValueChangeListener {

        void onValueChange(float value);

    }

    public RulerView(Context context) {
        this(context, null);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Timber.d("init RulerView ");
        mScroller = new Scroller(context);

        this.mLineSpaceWidth = myFloat(25.0f);
        this.mLineWidth = myFloat(2.0f);
        this.mLineMaxHeight = myFloat(100.0f);
        this.mLineMidHeight = myFloat(60.0f);
        this.mLineMinHeight = myFloat(40.0f);
        this.mTextHeight = myFloat(40.0f);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NRulerView);

        mAlphaEnable = typedArray.getBoolean(R.styleable.NRulerView_alphaEnable, mAlphaEnable);
        mLineSpaceWidth = typedArray.getDimension(R.styleable.NRulerView_lineSpaceWidth, mLineSpaceWidth);
        mLineWidth = typedArray.getDimension(R.styleable.NRulerView_lineWidth, mLineWidth);
        mLineMaxHeight = typedArray.getDimension(R.styleable.NRulerView_lineMaxHeight, mLineMaxHeight);
        mLineMidHeight = typedArray.getDimension(R.styleable.NRulerView_lineMidHeight, mLineMidHeight);
        mLineMinHeight = typedArray.getDimension(R.styleable.NRulerView_lineMinHeight, mLineMinHeight);
        mLineColor = typedArray.getColor(R.styleable.NRulerView_lineColor, mLineColor);

        mTextSize = typedArray.getDimension(R.styleable.NRulerView_textSize, mTextSize);
        mTextColor = typedArray.getColor(R.styleable.NRulerView_textColor, mTextColor);
        mTextMarginTop = typedArray.getDimension(R.styleable.NRulerView_textMarginTop, mTextMarginTop);

        mSelectorValue = typedArray.getFloat(R.styleable.NRulerView_selectorValue, 0.0f);
        mMinValue = typedArray.getFloat(R.styleable.NRulerView_minValue, 0.0f);
        mMaxValue = typedArray.getFloat(R.styleable.NRulerView_maxValue, 100.0f);
        mPerValue = typedArray.getFloat(R.styleable.NRulerView_perValue, 0.1f);


        mMinVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        mTextHeight = getFontHeight(mTextPaint);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStrokeWidth(mLineWidth);
        mLinePaint.setColor(mLineColor);
    }

    public static int myFloat(float paramFloat) {
        return (int) (0.5f + paramFloat * 1.0f);
    }

    private float getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }

    // per 最小单位 如 1： 表示 每2条刻度差为1  0.1: 表示 每2条刻度差为 0.1 身高 mPerValue为1 体重mPerValue 为 0.1
    public void setValue(float selectorValue, float minValue,
                         float maxValue, float per) {
        this.mSelectorValue = selectorValue;
        this.mMaxValue = maxValue;
        this.mMinValue = minValue;
        this.mPerValue = (int) (per * 10.0f);
        this.mTotalLine = ((int) ((mMaxValue * 10 - mMinValue * 10) / mPerValue)) + 1;

        // 为什么是负数 做什么用？？
        mMaxOffset = (int) (-(mTotalLine - 1) * mLineSpaceWidth);
        // 默认偏移位置 就是 当前位置距离开始的位置
        mOffset = (mMinValue - mSelectorValue) / mPerValue * mLineSpaceWidth * 10;
        Timber.d("mOffset " + mOffset + " mMaxOffset " + mMaxOffset + " mTotalLine " + mTotalLine);
        invalidate();
        setVisibility(View.VISIBLE);
    }

    public void setOnValueChangeListener(OnValueChangeListener listener) {
        this.mListener = listener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            mWidth = w;
            mHeight = h;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float left, height;
        String value;
        int alpha = 0;
        float scale;
        int srcPointX = mWidth / 2;
        for (int i = 0; i < mTotalLine; i++) {
            left = srcPointX + mOffset + i * mLineSpaceWidth;
            // 下面的操作就是只画出可视区域内的刻度线和刻度文字
            // 可见区域以外的暂时不会画的
            if (left < 0 || left > mWidth) {
                // 先画默认值在中间 左右各一半的view 多余部分暂时不画
                continue;
            }
            if (i % 10 == 0) {
                height = mLineMaxHeight;
            } else if (i % 5 == 0) {
                height = mLineMidHeight;
            } else {
                height = mLineMinHeight;
            }
            if (mAlphaEnable) {
                scale = 1 - Math.abs(left - srcPointX) / srcPointX;
                alpha = (int) (255 * scale * scale);
                mLinePaint.setAlpha(alpha);
            }
            canvas.drawLine(left, 0, left, height, mLinePaint);
            if (i % 10 == 0) {
                value = String.valueOf((int) (mMinValue + i * mPerValue / 10));
                if (mAlphaEnable) {
                    mTextPaint.setAlpha(alpha);
                }
                canvas.drawText(value, left - mTextPaint.measureText(value) / 2,
                        height + mTextMarginTop + mTextHeight, mTextPaint
                );
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Timber.d("onTouchEvent ");
        int action = event.getAction();
        int xPosition  = (int)event.getX();
        if (mVelocityTracker == null){
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mScroller.forceFinished(true);
                mLastX = xPosition;
                mMove = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                mMove = mLastX-xPosition;
                // 修改位置和数值
                changeMoveAndValue();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // 触摸滑动结束
                countMoveEnd();
                countVelocityTracker();
                return false;
            default:
                break;
        }
        mLastX = xPosition;
        //return super.onTouchEvent(event);
        return true;
    }

    // 滑动后的操作
    private void changeMoveAndValue(){

       mOffset = mOffset - mMove;
       if (mOffset <=mMaxOffset){
           mOffset = mMaxOffset;
           mMove = 0;
           mScroller.forceFinished(true);
       } else if (mOffset >=0){
           mOffset = 0;
           mMove = 0;
           mScroller.forceFinished(true);
       }
       // 计算出要选中的数据
       mSelectorValue = mMinValue + Math.round(Math.abs(mOffset)*1.0f/mLineSpaceWidth)*mPerValue/10.0f;
       notifyValueChanged();
       postInvalidate();
    }

    private void notifyValueChanged(){
        if (mListener!=null){
            mListener.onValueChange(mSelectorValue);
        }
    }

    // 滑动结束后 如果指针在2条刻度之间时，改变mOffset 让指针正好指在刻度上
    private void countMoveEnd(){
        mOffset -=mMove;
        if (mOffset <=mMaxOffset){
            mOffset = mMaxOffset;
        }else if (mOffset >=0){
            mOffset = 0;
        }
        mLastX = 0;
        mMove = 0;
        mSelectorValue = mMinValue + Math.round(Math.abs(mOffset)*1.0f/mLineSpaceWidth)*mPerValue/10.0f;

        mOffset = (mMinValue - mSelectorValue)*10.0f/mPerValue*mLineSpaceWidth;
        notifyValueChanged();
        postInvalidate();
    }

    //
    private void countVelocityTracker(){
         Timber.d("conuntVelocityTracker");
         // 初始化速率单位
         mVelocityTracker.computeCurrentVelocity(1000);
         float xVelocity = mVelocityTracker.getXVelocity();
         if (Math.abs(xVelocity)>mMinVelocity){
             mScroller.fling(0, 0, (int)xVelocity, 0,
                     Integer.MIN_VALUE, Integer.MAX_VALUE,
                     0, 0);
         }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        // mScroller.computeScrollOffset() 返回true表示还没有结束
        if (mScroller.computeScrollOffset()){
            if (mScroller.getCurrX() == mScroller.getFinalX()){
                countMoveEnd();
            } else {
                int xPosition = mScroller.getCurrX();
                mMove = (mLastX - xPosition);
                changeMoveAndValue();
                mLastX = xPosition;
            }
        }
    }
}
