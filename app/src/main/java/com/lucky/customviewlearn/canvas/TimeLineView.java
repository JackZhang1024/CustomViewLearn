package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.lucky.customviewlearn.R;
import timber.log.Timber;

/**
 * 时间线
 * Created by zfz on 2017/9/19.
 */

public class TimeLineView extends View {
    private int saveFlags = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG |
            Canvas.CLIP_TO_LAYER_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
            Canvas.FULL_COLOR_LAYER_SAVE_FLAG;
    private int mWidth, mHeight;
    private Paint mPaint;
    private Rect mOutLineRect;
    private int mTextViewTop;
    private float mTextViewLeft;
    private BitmapDrawable mDrawable;
    private Bitmap mDrawableBitmap;
    private Bitmap mSelectedBitmap;
    private Rect mSrcRect, mDestRect;
    private int mIntervalWidth;
    private Paint mLinePaint;
    private int mLeftMargin;
    private int mTextCount;
    private Rect[] mClickRectArray;
    private int mTouchX, mTouchY;
    private int mChoosenRectPosition =-1;

    public TimeLineView(Context context) {
        this(context, null);
    }

    public TimeLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.game_level_thumb);
        mDrawableBitmap = mDrawable.getBitmap();
        mSelectedBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.selected_marker)).getBitmap();
        mLeftMargin = 20;
        mTextCount = 7;
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.RED);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(2);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mClickRectArray = new Rect[mTextCount];
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mOutLineRect = new Rect();
        mOutLineRect.left = 0;
        mOutLineRect.top = 0;
        mOutLineRect.right = mOutLineRect.left + w;
        mOutLineRect.bottom = mOutLineRect.top + h;
        mSrcRect = new Rect(0, 0, mDrawableBitmap.getWidth(), mDrawableBitmap.getHeight());
        mDestRect = new Rect();
        mDestRect.left = mLeftMargin;
        mDestRect.bottom = mOutLineRect.bottom;
        mDestRect.right = mDestRect.left + mDrawableBitmap.getWidth();
        mDestRect.top = mDestRect.bottom - mDrawableBitmap.getHeight();
        mIntervalWidth = (mWidth - mLeftMargin - mDrawableBitmap.getWidth()*mTextCount)/(mTextCount-1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mOutLineRect, mPaint);
        int startX = mLeftMargin + mDrawableBitmap.getWidth() / 2;
        int startY = mDestRect.bottom + (mDestRect.top - mDestRect.bottom) / 2;
        int endX = mLeftMargin + (mTextCount - 1) * (mIntervalWidth + mDrawableBitmap.getWidth()) + mDrawableBitmap.getWidth() / 2;
        int endY = startY;
        canvas.drawLine(startX, startY, endX, endY, mLinePaint);
        canvas.saveLayer(mOutLineRect.left, mOutLineRect.top, mOutLineRect.right, mOutLineRect.bottom, new Paint(), saveFlags);
        Rect mLastRect = new Rect();
        for (int index = 0; index < mTextCount; index++) {
            String value = "10000"+String.valueOf(index);
            mPaint.setColor(Color.BLACK);
            mPaint.setTextSize(30);
            Paint.FontMetrics metrics = mPaint.getFontMetrics();
            float textWidth = mPaint.measureText(value);
            int textHeight = (int) (Math.ceil(metrics.descent - metrics.ascent) + 2);
            mDestRect.left = mLeftMargin + index * (mIntervalWidth + mDrawableBitmap.getWidth());
            mDestRect.right = mDestRect.left + mDrawableBitmap.getWidth();
            if (mChoosenRectPosition == index) {
                canvas.drawBitmap(mSelectedBitmap, mSrcRect, mDestRect, null);
            } else {
                canvas.drawBitmap(mDrawableBitmap, mSrcRect, mDestRect, null);
            }
            // 设置点击矩形区域
            Rect clickRect = new Rect();
            if (index == 0) {
                clickRect.left = 0;
                clickRect.top = 0;
                clickRect.right = mDestRect.right + mIntervalWidth / 2;
                clickRect.bottom = mOutLineRect.bottom;
                mLastRect = clickRect;
            } else {
                clickRect.left   = mLastRect.right;
                clickRect.top    = mLastRect.top;
                clickRect.right  = clickRect.left+mDrawableBitmap.getWidth()+mIntervalWidth;
                clickRect.bottom = mLastRect.bottom;
                mLastRect = clickRect;
            }
            mClickRectArray[index] = clickRect;
            // 绘制文字
            int rectCenterX = 0;
            if (index == 0) {
                rectCenterX = mLeftMargin + mDrawableBitmap.getWidth() / 2;
            } else {
                rectCenterX = mLeftMargin + mDrawableBitmap.getWidth() / 2 + (mDrawableBitmap.getWidth() + mIntervalWidth) * index;
            }
            mTextViewLeft = rectCenterX - textWidth / 2;
            mTextViewTop = mOutLineRect.top + textHeight;
            canvas.drawText(value, mTextViewLeft, mTextViewTop, mPaint);
        }
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchX = (int) event.getX();
                mTouchY = (int) event.getY();
                checkInRect(mTouchX, mTouchY);
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(event);
    }

    private void checkInRect(int x, int y){
        for (int index =0; index<mClickRectArray.length; index++){
             Rect rect =mClickRectArray[index];
             if (rect.contains(x, y)){
                 mChoosenRectPosition = index;
                 invalidate();
                 break;
             }
        }
    }
}
