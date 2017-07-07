package com.lucky.customviewlearn.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zfz on 2017/7/5.
 */

public class PathView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;

    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10f);
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
        // 将原点坐标移动到自定义View中心
        canvas.translate(mWidth/2, mHeight/2);
        // drawRectangle(canvas);
        // drawPerson(canvas);
        // drawOffSetPath(canvas);
        drawAddArc(canvas);
    }

    private void drawRectangle(Canvas canvas){
        Path path = new Path();
        //path.addRect(new RectF(-200,-200, 200, 200), Path.Direction.CW);  // 顺时针绘制
        path.addRect(-200,-200, 200, 200, Path.Direction.CW);  // 顺时针绘制
        //path.addRect(-200,-200, 200, 200, Path.Direction.CCW); // 逆时针绘制
        path.setLastPoint(-100, 100);
        canvas.drawPath(path, mPaint);
    }

    private void drawPerson(Canvas canvas){
        canvas.scale(1, -1); // 将Y轴反转 Y轴向上为正方向
        Path path = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CW); // 顺时针绘制
        Path src = new Path();
        src.addCircle(0, 0, 50, Path.Direction.CW);
        path.addPath(src, 0, 200);
        canvas.drawPath(path, mPaint);
    }

    private void drawAddArc(Canvas canvas){
        canvas.scale(1, -1);
        Path path = new Path();
        path.lineTo(100, 100);
        RectF  rectF = new RectF(0, 0, 300, 300);
        // path.addArc(rectF, 0, 270);
        // path.arcTo(rectF, 0, 270 , false);
        path.arcTo(rectF, 0, 270 , true);
        canvas.drawPath(path, mPaint);
    }

    private void drawOffSetPath(Canvas canvas){
        canvas.scale(1, -1);
        Path path = new Path();
        path.addCircle(0, 0, 100, Path.Direction.CW);

        Path dst = new Path();
        dst.addRect(-200, -200, 200, 200, Path.Direction.CW);

        // path.offset(300, 0, dst);  // 将Path向右偏移300 dst是用于存储平移后的path dst dst可以是null
        path.offset(300, 0, null);

        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawPath(dst, mPaint);
    }

}
