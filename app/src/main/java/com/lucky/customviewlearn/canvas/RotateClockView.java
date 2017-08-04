package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by zfz on 2017/8/3.
 */

public class RotateClockView extends View {

    private Paint mPaint, mTimePaint, mPanePaint, mHourPaint, mMinutePaint, mSecondPaint;
    private int mWidth, mHeight, mRadius;
    private float lineLeft, lineRight, lineTop, lineBottom;
    private int longLineHeight, middleLineHeight;
    private float currentSecondDegree, currentMinuteDegree, currentHourDegree;
    private float lastSecondX, lastSecondY, lastMinuteX, lastMinuteY, lastHourX, lastHourY;
    private boolean isRestoreLastState;
    private Handler mHandler;

    public RotateClockView(Context context) {
        this(context, null);
    }

    public RotateClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public RotateClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.BLACK);

        mTimePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTimePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTimePaint.setColor(Color.BLACK);
        float numberTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, context.getResources().getDisplayMetrics());
        mTimePaint.setTextSize(numberTextSize);
        mTimePaint.setStrokeWidth(2);

        mPanePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPanePaint.setStyle(Paint.Style.FILL);
        mPanePaint.setColor(Color.BLACK);
        mPanePaint.setStrokeWidth(4);

        mHourPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHourPaint.setStyle(Paint.Style.FILL);
        mHourPaint.setColor(Color.BLACK);
        mHourPaint.setStrokeWidth(20);

        mMinutePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMinutePaint.setStyle(Paint.Style.FILL);
        mMinutePaint.setColor(Color.BLACK);
        mMinutePaint.setStrokeWidth(10);

        mSecondPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondPaint.setStyle(Paint.Style.FILL);
        mSecondPaint.setColor(Color.BLACK);
        mSecondPaint.setStrokeWidth(8);

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                try {
                    invalidate();
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadius = (Math.min(w, h) - 2 * getPaddingLeft()) / 2;
        longLineHeight = mRadius / 5;
        middleLineHeight = mRadius / 8;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, mRadius, mPaint);
        drawLines(canvas);
        drawNumbers(canvas);
        drawPaneCenter(canvas);
        startTick(canvas);
    }

    private void drawLines(Canvas canvas) {
        float timeTextBottom;
        for (int i = 0; i <= 360; i++) {
            canvas.save();
            if (i % 6 == 0) {
                canvas.rotate(i, 0, 0);
            }
            if (i % 30 == 0) {
                mPaint.setStrokeWidth(10);
                canvas.drawLine(0, -mRadius, 0, -mRadius + longLineHeight, mPaint);
            } else if (i % 6 == 0) {
                mPaint.setStrokeWidth(4);
                canvas.drawLine(0, -mRadius, 0, -mRadius + middleLineHeight, mPaint);
            }
            canvas.restore();
        }
    }

    private void drawNumbers(Canvas canvas){
        for (int i = 0; i<=360; i+=30){
            if (i%30 == 0){
                float distance = mRadius - (longLineHeight+20);
                double radians = Math.toRadians(i);
                float x = (float)(distance*Math.sin(radians));
                float y = (float)(distance*Math.cos(radians));
                String number = String.valueOf(i/30);
                if (i/30 ==12 || i/30 ==0) number = "0";
                canvas.drawText(number, x, -y, mTimePaint);
            }
        }
    }

    private void drawPaneCenter(Canvas canvas){
        canvas.drawCircle(0, 0, -mRadius/30, mPanePaint);
    }

    private void startTick(Canvas canvas){
        try {
            //判断 秒 分 时 所划过的度数
            if (currentSecondDegree < 360){ // 秒针每60秒转动一圈
                float secondX = (float) Math.sin(Math.toRadians(currentSecondDegree))*Math.abs(mRadius*9/10);
                float secondY = (float) Math.cos(Math.toRadians(currentSecondDegree))*Math.abs(mRadius*9/10);
                canvas.drawLine(0, 0, secondX, -secondY, mSecondPaint);
                currentSecondDegree += 360.0f/60;
                lastSecondX = secondX;
                lastSecondY = secondY;
                if (currentMinuteDegree < 360){  // 分钟指针每一小时转动一圈
                    float minuteX = (float) Math.sin(Math.toRadians(currentMinuteDegree))*Math.abs(mRadius/2);
                    float minuteY = (float) Math.cos(Math.toRadians(currentMinuteDegree))*Math.abs(mRadius/2);
                    canvas.drawLine(0, 0, minuteX, -minuteY, mMinutePaint);
                    currentMinuteDegree += 360.0f/(60*60);
                    lastMinuteX = minuteX;
                    lastMinuteY = minuteY;
                    if (currentHourDegree < 360){ // 时针每12小时转动一圈
                        float hourX = (float) Math.sin(Math.toRadians(currentHourDegree))*Math.abs(mRadius/3);
                        float hourY = (float) Math.cos(Math.toRadians(currentHourDegree))*Math.abs(mRadius/3);
                        canvas.drawLine(0, 0, hourX, -hourY, mHourPaint);
                        currentHourDegree+= 360.0f/(12*60*60);
                        lastHourX = hourX;
                        lastHourY = hourY;
                        invalidateCanvas();
                    }else{
                        currentHourDegree = 0;
                        invalidateCanvas();
                    }
                }else{
                    currentMinuteDegree =0;
                    invalidateCanvas();
                }
            }else{
                currentSecondDegree = 0;
                invalidate();
                canvas.drawLine(0, 0, lastSecondX, -lastSecondY, mSecondPaint);
                canvas.drawLine(0, 0, lastMinuteX, -lastMinuteY, mMinutePaint);
                canvas.drawLine(0, 0, lastHourX, -lastHourY, mHourPaint);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void invalidateCanvas() throws Exception{
        mHandler.sendEmptyMessage(0);
    }

}
