package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lucky.customviewlearn.R;

/**
 * Created by zfz on 2017/7/5.
 */

public class CheckView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;
    private int mMaxPage = 13;
    private int mDuration;
    private int mCheckState;
    private boolean isCheck;
    private int mCurrentPage;
    private static final int ANIM_NULL = 0;  // 停止状态
    private static final int ANIM_START = 1; // 开始状态
    private static final int ANIM_END = 2;   // 停止状态
    private Bitmap mCheckMarkBitmap;
    private Handler mHandler;
    private int mBackgroundColor;
    private boolean isStartDrawMark;
    private Rect src, dst;

    public CheckView(Context context) {
        super(context);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context){
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mCheckMarkBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.checkmark);

        mHandler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (mCurrentPage>= 0 && mCurrentPage < mMaxPage){
                    invalidate();
                    if (mCheckState == ANIM_NULL){
                        return;
                    }
                    if (mCheckState == ANIM_START){
                        mCurrentPage++;
                    } else if (mCheckState == ANIM_END){
                        mCurrentPage--;
                    }
                    this.sendEmptyMessageDelayed(0, mDuration/mMaxPage);
                }else{
                    if (isCheck){
                        mCurrentPage = mMaxPage -1;
                    }else{
                        mCurrentPage = -1;
                    }
                    invalidate();
                    mCheckState = ANIM_NULL;
                }
            }
        };
        src = new Rect();
        dst = new Rect();
    }


    //确定自定义View大小
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBackgroundColor!=-1) mPaint.setColor(mBackgroundColor);
        canvas.translate(mWidth/2, mHeight/2);
        canvas.drawCircle(0, 0, 240, mPaint);
        int mCheckMarkHeight = mCheckMarkBitmap.getHeight();
        src.set(mCheckMarkHeight*mCurrentPage, 0 ,mCheckMarkHeight*(mCurrentPage+1), mCheckMarkHeight);//设置绘图区域
        dst.set(-200, -200, 200, 200);//设置显示区域
        if (!isStartDrawMark) return;
        canvas.drawBitmap(mCheckMarkBitmap, src, dst, null);
    }

    public void check(){
        if (mCheckState!=ANIM_NULL || isCheck) {
            return;
        }
        mCheckState = ANIM_START;
        isStartDrawMark = true;
        mCurrentPage = 0;
        mHandler.sendEmptyMessageDelayed(0, mDuration/mMaxPage);
        isCheck = true;
    }

    public void unCheck(){
        if (mCheckState!=ANIM_NULL || !isCheck) {
            return;
        }
        mCheckState = ANIM_END;
        mCurrentPage = mMaxPage -1 ;
        mHandler.sendEmptyMessageDelayed(0, mDuration/mMaxPage);
        isCheck = false;
    }

    public void setDuration(int duration){
        this.mDuration = duration;
    }

    public void setBackgroundColor(int color){
        this.mBackgroundColor = color;
    }


}
