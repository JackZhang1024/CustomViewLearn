package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zfz on 2017/7/7.
 *
 * 绘制太极图
 *
 */

public class TaiChiView extends View {

    private Paint whitePaint, blackPaint;
    private int mWidth, mHeight;
    private Handler handler;
    private int degree;

    public TaiChiView(Context context) {
        super(context);
    }

    public TaiChiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        whitePaint.setStyle(Paint.Style.FILL);

        blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle(Paint.Style.FILL);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                invalidate();
            }
        };
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2, mHeight/2);
        canvas.drawColor(Color.GRAY);
        canvas.rotate(degree+=5);
        float radius = Math.min(mWidth,mHeight)/2.0f;
        RectF rectF = new RectF(-radius, -radius, radius, radius);
        canvas.drawArc(rectF, 90, 180, true, blackPaint);
        canvas.drawArc(rectF, 270, 180, true, whitePaint);

        // 绘制突出部位的圆
        float midRadius = radius/2.0f;
        canvas.drawCircle(0, midRadius, midRadius, whitePaint);
        canvas.drawCircle(0, -midRadius, midRadius, blackPaint);

        // 绘制鱼眼
        // 鱼眼的位置位于突出部位原的原点位置
        float miniRadius = midRadius/4.0f;
        canvas.drawCircle(0, midRadius, miniRadius, blackPaint);
        canvas.drawCircle(0, -midRadius, miniRadius, whitePaint);
        handler.sendEmptyMessageDelayed(0, 80);
    }

}
