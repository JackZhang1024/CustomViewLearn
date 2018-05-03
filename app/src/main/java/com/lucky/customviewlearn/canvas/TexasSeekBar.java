package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.lucky.customviewlearn.R;

/**
 * 时间线
 * Created by zfz on 2017/9/19.
 */

public class TexasSeekBar extends View {
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
    private Bitmap mNormalSrcBitmap;
    private Rect mSrcRect, mDestRect, mNormalSrcRect, mNormalDestRect;
    private int mIntervalWidth;
    private Paint mLinePaint;
    private int mLeftMargin;
    private int mTextCount;
    private Rect[] mClickRectArray;
    private int mTouchX, mTouchY;
    private int mChosenRectPosition = 0;
    private int mRectBottomPadding = 10;
    private DisplayMetrics mDisplayMetrics;
    private Context mContext;
    private CharSequence[] mTextArrays;
    private float mTextSize;

    public TexasSeekBar(Context context) {
        this(context, null);
    }

    public TexasSeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TexasSeekBar);
        mTextArrays = typedArray.getTextArray(R.styleable.TexasSeekBar_text_array);
        mTextSize = typedArray.getDimension(R.styleable.TexasSeekBar_text_size, 10f);
        typedArray.recycle();
        init();
    }

    private void init() {
        if (mTextArrays ==null || mTextArrays.length == 0){
            throw new IllegalStateException("TimeLine Arrays Length should not be zero");
        }
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.gambling_jetton);
        mDrawableBitmap = mDrawable.getBitmap();
        mNormalSrcBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.gambling_jetton_default)).getBitmap();
        mLeftMargin = 20;
        mTextCount = mTextArrays.length;
        mLinePaint = new Paint();
        mLinePaint.setColor(getResources().getColor(R.color.mttSeekBarLineColor));
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(6);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mClickRectArray = new Rect[mTextCount];
        WindowManager windowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        mDisplayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
        mSrcRect  = new Rect(0, 0, mDrawableBitmap.getWidth(), mDrawableBitmap.getHeight());
        mDestRect = new Rect();
        mDestRect.left  = mLeftMargin;
        mDestRect.bottom= mOutLineRect.bottom - mRectBottomPadding;
        mDestRect.right = mDestRect.left + mDrawableBitmap.getWidth();
        mDestRect.top   = mDestRect.bottom - mDrawableBitmap.getHeight();
        mIntervalWidth  = (mWidth - mLeftMargin - mDrawableBitmap.getWidth()*mTextCount)/(mTextCount-1);
        mNormalSrcRect  = new Rect(0, 0, mNormalSrcBitmap.getWidth(), mNormalSrcBitmap.getHeight());
        mNormalDestRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.TRANSPARENT);
        canvas.drawRect(mOutLineRect, mPaint);
        int startX = mLeftMargin + mDrawableBitmap.getWidth() / 2;
        int startY = mDestRect.bottom + (mDestRect.top - mDestRect.bottom) / 2;
        int endX = mLeftMargin + (mTextCount - 1) * (mIntervalWidth + mDrawableBitmap.getWidth()) + mDrawableBitmap.getWidth() / 2;
        int endY = startY;
        canvas.drawLine(startX, startY, endX, endY, mLinePaint);
        canvas.saveLayer(mOutLineRect.left, mOutLineRect.top, mOutLineRect.right, mOutLineRect.bottom, new Paint(), saveFlags);
        Rect mLastRect = new Rect();
        for (int index = 0; index < mTextCount; index++) {
            String value = mTextArrays[index].toString();
            int textColor = getResources().getColor(R.color.mttBlindTimeColor);
            mPaint.setColor(textColor);
            float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, mDisplayMetrics);
            mPaint.setTextSize(mTextSize);
            Paint.FontMetrics metrics = mPaint.getFontMetrics();
            float textWidth = mPaint.measureText(value);
            int textHeight = (int) (Math.ceil(metrics.descent - metrics.ascent) + 2);
            mDestRect.left = mLeftMargin + index * (mIntervalWidth + mDrawableBitmap.getWidth());
            mDestRect.right = mDestRect.left + mDrawableBitmap.getWidth();
            int destRectCenterX = mDestRect.left + mDrawableBitmap.getWidth()/2;
            int destRectCenterY = mDestRect.top + mDrawableBitmap.getHeight()/2;
            if (mChosenRectPosition == index) {
                canvas.drawBitmap(mDrawableBitmap, null, mDestRect, null);
            } else {
                int radius = Math.max(mNormalSrcBitmap.getWidth(),mNormalSrcBitmap.getHeight())/2;
                mNormalDestRect.left  = destRectCenterX - radius;
                mNormalDestRect.right = destRectCenterX + radius;
                mNormalDestRect.top   = destRectCenterY - radius;
                mNormalDestRect.bottom= destRectCenterY + radius;
                canvas.drawBitmap(mNormalSrcBitmap, null, mNormalDestRect, null);
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
            mTextViewTop = mDestRect.top - 10;
            // 最后一个文本如果文本宽度大于bitmap宽度 则从最后一个bitmap的右侧向左数起
            if (index == mTextCount-1 && (textWidth>mDrawableBitmap.getWidth())){
                mTextViewLeft = rectCenterX + mDrawableBitmap.getWidth()/2-textWidth;
            }
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
                mChosenRectPosition = index;
                if(mOnTexasSeekBarChangeListener!=null){
                   mOnTexasSeekBarChangeListener.onTexasSeekBarChange(mChosenRectPosition, this);
                }
                invalidate();
                break;
            }
        }
    }

    public void setCurrentPosition(int position){
        mChosenRectPosition = position;
        invalidate();
    }

    public CharSequence[] getSeekBarLabelArrays(){
        return mTextArrays;
    }

    public int getCurrentPosition(){
        return mChosenRectPosition;
    }

    public void setOnTexasSeekBarChangeListener(OnTexasSeekBarChangeListener onTexasSeekBarChangeListener){
        this.mOnTexasSeekBarChangeListener = onTexasSeekBarChangeListener;
    }

    public OnTexasSeekBarChangeListener mOnTexasSeekBarChangeListener;

    public interface OnTexasSeekBarChangeListener{

        void onTexasSeekBarChange(int position, TexasSeekBar texasSeekBar);

    }

    public void setTexasSeekBarArrays(String[] arrays){
        mTextArrays = arrays;
        init();
        resizeIntervalWidth();
    }

    private void resizeIntervalWidth(){
        mIntervalWidth = (mWidth - mLeftMargin - mDrawableBitmap.getWidth()*mTextCount)/(mTextCount-1);
        invalidate();
    }
}
