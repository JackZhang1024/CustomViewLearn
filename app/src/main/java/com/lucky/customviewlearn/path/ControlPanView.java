package com.lucky.customviewlearn.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zfz on 2017/7/7.
 */

public class ControlPanView extends View {

    private Paint mPaint;
    private Path centerPath, rightPath, downPath, leftPath, upPath;
    private Region centerRegion, rightRegion, downRegion, leftRegion, upRegion;
    private int mWidth, mHeight;
    private int default_color = 0xFF4E5268;
    private int touch_color = 0xFFDF9C81;
    private Matrix mMapMatrix;
    private int currentFlag = -1;
    private int touchFlag = -1;
    private static final int CENTER = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int UP = 4;


    public ControlPanView(Context context) {
        super(context);
    }

    public ControlPanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(default_color);
        mMapMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMapMatrix.reset();
        mWidth = w;
        mHeight = h;
        // 这块就是包括Path的区域 只要能够包住Path就可以 不一定是-w, -h, w, h
        Region globalRegion = new Region(-w, -h, w, h);
        int miniWidth = mWidth > mHeight ? mHeight : mWidth;
        float centerRadius = miniWidth * 0.2f;

        centerPath = new Path();
        centerPath.addCircle(0, 0, centerRadius, Path.Direction.CW);
        centerRegion = new Region();
        centerRegion.setPath(centerPath, globalRegion);


        float bigRadius = miniWidth / 2;
        RectF bigCircle = new RectF(-bigRadius, -bigRadius, bigRadius, bigRadius);
        float smallRadius = miniWidth / 4;
        RectF smallCircle = new RectF(-smallRadius, -smallRadius, smallRadius, smallRadius);

        float bigSweepAngle = 82;
        float smallSweepAngle = -80;

        rightPath = new Path();
        rightPath.addArc(bigCircle, -40, bigSweepAngle);
        rightPath.arcTo(smallCircle, 40, smallSweepAngle);
        rightPath.close();
        rightRegion = new Region();
        rightRegion.setPath(rightPath, globalRegion);


        downPath = new Path();
        downPath.addArc(bigCircle, 50, bigSweepAngle);
        downPath.arcTo(smallCircle, 130, smallSweepAngle);
        downPath.close();
        downRegion = new Region();
        downRegion.setPath(downPath, globalRegion);

        leftPath = new Path();
        leftPath.addArc(bigCircle, 140, bigSweepAngle);
        leftPath.arcTo(smallCircle, 220, smallSweepAngle);
        leftPath.close();
        leftRegion = new Region();
        leftRegion.setPath(leftPath, globalRegion);

        upPath = new Path();
        upPath.addArc(bigCircle, 230, bigSweepAngle);
        upPath.arcTo(smallCircle, 310, smallSweepAngle);
        upPath.close();
        upRegion = new Region();
        upRegion.setPath(upPath, globalRegion);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        // 获取测量矩阵
        if (mMapMatrix.isIdentity()) {
            canvas.getMatrix().invert(mMapMatrix);
        }

        //画出中心圆
        canvas.drawPath(centerPath, mPaint);

        //画出右侧控制按钮
        //mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(rightPath, mPaint);
        canvas.drawPath(downPath, mPaint);
        canvas.drawPath(leftPath, mPaint);
        canvas.drawPath(upPath, mPaint);

        //绘制触摸区域的颜色
        mPaint.setColor(touch_color);
        if (currentFlag == CENTER) {
            canvas.drawPath(centerPath, mPaint);
        } else if (currentFlag == RIGHT) {
            canvas.drawPath(rightPath, mPaint);
        } else if (currentFlag == DOWN) {
            canvas.drawPath(downPath, mPaint);
        } else if (currentFlag == LEFT) {
            canvas.drawPath(leftPath, mPaint);
        } else if (currentFlag == UP) {
            canvas.drawPath(upPath, mPaint);
        }
        mPaint.setColor(default_color);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float[] points = new float[2];
        // points[0] = event.getRawX();
        // points[1] = event.getRawY();
        points[0] = event.getX();
        points[1] = event.getY();
        mMapMatrix.mapPoints(points);

        int x = (int) points[0];
        int y = (int) points[1];

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                touchFlag = getTouchedRegion(x, y);
                currentFlag = touchFlag;
                break;
            case MotionEvent.ACTION_MOVE:
                currentFlag = getTouchedRegion(x, y);
                break;
            case MotionEvent.ACTION_UP:
                // 如果currentFlag和touchFlag一样，且不为空 则判断为点击事件
                if (touchFlag == currentFlag && currentFlag != -1 && menuClickListener != null) {
                    if (currentFlag == CENTER) {
                        menuClickListener.onCenterMenuClick();
                    } else if (currentFlag == RIGHT) {
                        menuClickListener.onRightMenuClick();
                    } else if (currentFlag == DOWN) {
                        menuClickListener.onDownMenuClick();
                    } else if (currentFlag == LEFT) {
                        menuClickListener.onLeftMenuClick();
                    } else if (currentFlag == UP) {
                        menuClickListener.onUpMenuClick();
                    }
                }
                touchFlag = currentFlag = -1; //赋为空值
                break;
            case MotionEvent.ACTION_CANCEL:
                touchFlag = currentFlag = -1; //赋为空值
                break;
        }
        invalidate();
        return true;
    }


    // 获取当前触摸点的在哪个区域
    private int getTouchedRegion(int x, int y) {
        if (centerRegion.contains(x, y)) {
            return CENTER;
        } else if (rightRegion.contains(x, y)) {
            return RIGHT;
        } else if (downRegion.contains(x, y)) {
            return DOWN;
        } else if (leftRegion.contains(x, y)) {
            return LEFT;
        } else if (upRegion.contains(x, y)) {
            return UP;
        }
        return -1;
    }

    public interface MenuClickListener {
        void onCenterMenuClick();

        void onRightMenuClick();

        void onDownMenuClick();

        void onLeftMenuClick();

        void onUpMenuClick();
    }

    private MenuClickListener menuClickListener;

    public void setMenuClickListener(MenuClickListener menuClickListener) {
        this.menuClickListener = menuClickListener;
    }

}
