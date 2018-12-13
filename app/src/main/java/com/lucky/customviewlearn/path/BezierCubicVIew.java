package com.lucky.customviewlearn.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 贝塞尔曲线绘制
 * https://www.jianshu.com/p/b1579e8c09ec
 * Created by zfz on 2017/7/6.
 * 三次贝塞尔曲线
 */

public class BezierCubicVIew extends View {

    private int mWidth, mHeight;
    private PointF startPoint, endPoint, controlPoint1, controlPoint2;
    private Paint mPaint;
    private int centerX, centerY;
    private boolean mode;

    public BezierCubicVIew(Context context) {
        super(context);
    }

    public BezierCubicVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);

        startPoint = new PointF(0, 0);
        endPoint = new PointF(0, 0);
        controlPoint1 = new PointF(0, 0);
        controlPoint2 = new PointF(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        centerX = mWidth / 2;
        centerY = mHeight / 2;

        // 设置开始点 结束点 控制点坐标
        startPoint.x = centerX - 200;
        startPoint.y = centerY;
        endPoint.x = centerX + 200;
        endPoint.y = centerY;
        controlPoint1.x = centerX;
        controlPoint1.y = centerY - 100;
        controlPoint2.x = centerX;
        controlPoint2.y = centerY - 100;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mode) {
            controlPoint1.x = event.getX();
            controlPoint1.y = event.getY();
        } else {
            controlPoint2.x = event.getX();
            controlPoint2.y = event.getY();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);

        // 绘制开始点 结束点 两个控制点
        canvas.drawPoint(startPoint.x, startPoint.y, mPaint);
        canvas.drawPoint(endPoint.x, endPoint.y, mPaint);
        canvas.drawPoint(controlPoint1.x, controlPoint1.y, mPaint);
        canvas.drawPoint(controlPoint2.x, controlPoint2.y, mPaint);

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(startPoint.x, startPoint.y, controlPoint1.x, controlPoint1.y, mPaint);
        canvas.drawLine(controlPoint1.x, controlPoint1.y, controlPoint2.x, controlPoint2.y, mPaint);
        canvas.drawLine(controlPoint2.x, controlPoint2.y, endPoint.x, endPoint.y, mPaint);

        // 绘制三次贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        // 将path移动到下次操作的起点位置
        path.moveTo(startPoint.x, startPoint.y);
        // cubicTo(float x1, float y1, float x2, float y2,float x3, float y3)
        // x1, y1 是控制点1坐标 x2, y2 是控制点2坐标 x3, y3是贝塞尔曲线终点坐标
        path.cubicTo(controlPoint1.x, controlPoint1.y, controlPoint2.x, controlPoint2.y, endPoint.x, endPoint.y);
        canvas.drawPath(path, mPaint);
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }
}
