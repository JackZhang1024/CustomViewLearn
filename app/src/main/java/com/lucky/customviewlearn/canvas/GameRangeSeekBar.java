package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.lucky.customviewlearn.R;

import timber.log.Timber;


/**
 * 德扑圈 RangeSeekBar
 * 一点一点处理 不要着急！！！
 * Created by zfz on 2017/9/1.
 */

public class GameRangeSeekBar extends View {

    private int mSeekBarHeight;
    private CharSequence[] mMarkTextArray;
    private float mMarkTextSize;
    private int mSeekBarBackgroundColor;
    private int mSeekBarTextNormalColor, mSeekBarTextSelectedColor;
    private Paint mPaint;
    private Rect mPaddingRect;
    private RectF mSeekBarRect, mSeekBarSelectRect;
    private Scroller mLeftScroller, mRightScroller;
    private int mTextHeight;
    private float[] mTextWidthArray;
    private int mLeftCursorIndex, mRightCursorIndex;
    private int mIntervalLength;
    private float mTextDrawBottom;
    private Rect[] mClickRctArray;
    private Drawable mLeftCursorBG, mRightCursorBG;
    /**
     * mGap Mark文字与SeekBar的Thumb之间的距离
     */
    private int mGap;

    public GameRangeSeekBar(Context context) {
        this(context, null);
    }

    public GameRangeSeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GameRangeSeekBar);
        mSeekBarHeight = typedArray.getDimensionPixelOffset(R.styleable.GameRangeSeekBar_seek_bar_height, 0);
        mMarkTextArray = typedArray.getTextArray(R.styleable.GameRangeSeekBar_mark_text_array);
        mSeekBarBackgroundColor = typedArray.getColor(R.styleable.GameRangeSeekBar_seek_bar_bg, 0);
        mMarkTextSize = typedArray.getDimensionPixelOffset(R.styleable.GameRangeSeekBar_mark_text_size, 0);
        mSeekBarTextNormalColor = typedArray.getColor(R.styleable.GameRangeSeekBar_seek_bar_text_normal_color, 0);
        mSeekBarTextSelectedColor = typedArray.getColor(R.styleable.GameRangeSeekBar_seek_bar_text_selected_color, 0);
        mGap = typedArray.getDimensionPixelOffset(R.styleable.GameRangeSeekBar_seek_bar_gap, 0);
        typedArray.recycle();
        initRect();
        initTextBoundsArray();
    }

    private void initRect() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mSeekBarBackgroundColor);
        mPaint.setTextSize(mMarkTextSize);
        mSeekBarSelectRect = new RectF();
        mSeekBarRect = new RectF();
        mTextWidthArray = new float[mMarkTextArray.length];
        mClickRctArray = new Rect[mMarkTextArray.length];
    }


    /**
     * 设置最外层的Rect大小
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaddingRect = new Rect();
        mPaddingRect.left = 0;
        mPaddingRect.top = 0;
        mPaddingRect.bottom = mPaddingRect.top + h;
        mPaddingRect.right = mPaddingRect.left + w;

        mSeekBarRect.left = mPaddingRect.left + 10;
        mSeekBarRect.bottom = mPaddingRect.bottom - 10;
        mSeekBarRect.top = mSeekBarRect.bottom - mSeekBarHeight;
        mSeekBarRect.right = mPaddingRect.right - 10;

        mIntervalLength = (int) ((mSeekBarRect.right - mSeekBarRect.left) / (mMarkTextArray.length - 1) + 0.5f);
        mTextDrawBottom = mPaddingRect.top + mTextHeight;

        mSeekBarSelectRect.top = mSeekBarRect.top;
        mSeekBarSelectRect.bottom = mSeekBarRect.bottom;

    }


    private void initTextBoundsArray() {
        if (mMarkTextArray != null && mMarkTextArray.length > 0) {
            for (int index = 0; index < mMarkTextArray.length; index++) {
                CharSequence markText = mMarkTextArray[index];
                mTextWidthArray[index] = mPaint.measureText(markText.toString());
            }
        }
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        mTextHeight = (int) (Math.ceil(fontMetrics.leading - fontMetrics.ascent) - 2);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSeekBar(canvas);
        drawMarkText(canvas);
        drawCursor(canvas);
    }

    /**
     * 画SeekBar
     */
    private void drawSeekBar(Canvas canvas) {
        mSeekBarSelectRect.left = mSeekBarRect.left + mIntervalLength * mLeftCursorIndex;
        mSeekBarSelectRect.right = mSeekBarRect.left + mIntervalLength * mRightCursorIndex;
        if (mLeftCursorIndex == 0 && mRightCursorIndex == mMarkTextArray.length - 1) {
            mPaint.setColor(mSeekBarTextSelectedColor);
            canvas.drawRect(mSeekBarRect, mPaint);
        } else {
            mPaint.setColor(mSeekBarTextSelectedColor);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(mSeekBarRect, mPaint);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(mSeekBarTextSelectedColor);
            canvas.drawRect(mSeekBarSelectRect, mPaint);
        }

    }

    /**
     * 画MarkText
     */
    private void drawMarkText(Canvas canvas) {
        for (int index = 0; index < mMarkTextArray.length; index++) {
            if ((index > mLeftCursorIndex && index < mRightCursorIndex) || index == mLeftCursorIndex || index == mRightCursorIndex) {
                mPaint.setColor(mSeekBarTextNormalColor);
            } else {
                mPaint.setColor(mSeekBarTextSelectedColor);
            }
            final String text2draw = mMarkTextArray[index].toString();
            final float textWidth = mTextWidthArray[index];

            float markX = mSeekBarRect.left + index * mIntervalLength;
            if (index == 0) {
                markX += mPaint.getStrokeWidth();
            } else if (index == mMarkTextArray.length - 1) {
                markX -= mPaint.getStrokeWidth();
            }
            float textDrawLeft = markX - textWidth / 2f;
            mPaint.setColor(Color.BLACK);
            canvas.drawText(text2draw, textDrawLeft, mTextDrawBottom, mPaint);
            Rect rect = mClickRctArray[index];
            if (rect == null) {
                rect = new Rect();
                rect.top = mPaddingRect.top;
                rect.bottom = rect.top + mTextHeight + mGap + mSeekBarHeight;
                rect.left = (int) textDrawLeft;
                rect.right = (int) (rect.left + textWidth);
                mClickRctArray[index] = rect;
            }
        }
    }

    private void drawCursor(Canvas canvas) {


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
    }

}
