package com.lucky.customviewlearn.canvas.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.lucky.customviewlearn.core.DisplayUtil;

/**
 * Created by zfz on 2018/1/6.
 */

public class SimpleCanvasView extends View {

    private Paint mPaint;

    public SimpleCanvasView(Context context) {
        super(context);
        initView();
    }

    public SimpleCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SimpleCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPoint(canvas);
        //drawRectangle(canvas);
        //drawLine(canvas);
        //drawLines(canvas);
        //drawRoundRect(canvas);
        //drawCircle(canvas);
        //drawArc(canvas);
        //drawArcFull(canvas);
        //drawOval(canvas);
        //drawPosText(canvas);
        //drawPath(canvas);
    }

    private void drawPoint(Canvas canvas) {
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPoint(100, 100, mPaint);
    }

    private void drawRect(Canvas canvas) {
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        Rect rect = new Rect(10, 10, 500, 500);
        canvas.drawRect(rect, mPaint);
    }

    private void drawLine(Canvas canvas) {
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(0, 0, 500, 500, mPaint);
    }

    private void drawLines(Canvas canvas) {
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        float[] pts = {0, 0, 200, 200, 210, 210, 300, 100};
        canvas.drawLines(pts, mPaint);
    }

    private void drawRoundRect(Canvas canvas) {
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(10, 10, 500, 500);
        canvas.drawRoundRect(rectF, 10.0f, 10.0f, mPaint);
    }

    private void drawCircle(Canvas canvas) {
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(500, 500, 100.0f, mPaint);
    }

    // 不填充颜色的弧线或者扇形
    private void drawArc(Canvas canvas) {
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(10, 10, 500, 500);
        //canvas.drawArc(rectF, 0, 45, false, mPaint); // useCenter false 表示绘制的是弧线
        canvas.drawArc(rectF, 0, 45, true, mPaint);  // useCenter true 表示绘制的是扇形
    }

    // 填充颜色的弧线或者扇形
    private void drawArcFull(Canvas canvas) {
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        RectF rectF = new RectF(10, 10, 500, 500);
        //canvas.drawArc(rectF, 0, 45, false, mPaint); // useCenter false 表示绘制的是弧线
        canvas.drawArc(rectF, 0, 45, true, mPaint);  // useCenter true 表示绘制的是扇形
    }

    // 绘制椭圆
    private void drawOval(Canvas canvas) {
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(10, 10, 500, 300);
        canvas.drawOval(rectF, mPaint);
    }

    // new float[]{X1,Y1, X2,Y2, X3,Y3, X4,Y4,...,Xn,yn}
    private void drawPosText(Canvas canvas) {
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(DisplayUtil.sp2px(getContext(), 20));
        canvas.drawPosText("Hello", new float[]{100, 100, 200, 200, 300, 300, 400, 400, 500, 500}, mPaint);
    }

    private void drawPath(Canvas canvas) {
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(150, 150);
        path.lineTo(200, 100);
        path.lineTo(400, 100);
        canvas.drawPath(path, mPaint);
    }


}
