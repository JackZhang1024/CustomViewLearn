package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.R.attr.x;

/**
 * Created by zfz on 2018/1/12.
 */

public class SerialCureLineSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    //SurfaceHolder
    private SurfaceHolder mSurfaceHolder;
    //用于绘图的Canvas
    private Canvas mCanvas;
    //子线程标记为
    private boolean mIsDrawing;
    private Path mPath;
    private float x, y;
    private Paint mPaint;

    public SerialCureLineSurfaceView(Context context) {
        super(context);
        initView();
    }

    public SerialCureLineSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SerialCureLineSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        //mSurfaceHolder.setFormat(PixelFormat.OPAQUE);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            draw();
            x += 1;
            y = (int) (100 * Math.sin(x * 2 * Math.PI / 180) + 400);
            mPath.lineTo(x, y);
        }
    }

    private void draw() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            // doSomething();
            // SurfaceView背景色
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
        } catch (Exception e) {

        } finally {
            if (mCanvas != null) {
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
