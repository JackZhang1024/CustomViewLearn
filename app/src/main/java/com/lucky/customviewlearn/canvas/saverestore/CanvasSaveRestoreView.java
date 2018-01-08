package com.lucky.customviewlearn.canvas.saverestore;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zfz on 2017/8/2.
 *
 * 相关知识的解答
 * save() : 用来保存Canvas的状态,save()方法之后的代码，可以调用Canvas的平移、放缩、旋转、裁剪等操作！
 * restore()：用来恢复Canvas之前保存的状态(可以想成是保存坐标轴的状态),防止save()方法代码之后对Canvas执行的操作，
 * 继续对后续的绘制会产生影响，通过该方法可以避免连带的影响
 */

public class CanvasSaveRestoreView extends View{

    private Paint mBackgroundPaint;
    private Paint mLinePaint;
    private int mWidth, mHeight;

    public CanvasSaveRestoreView(Context context) {
        this(context, null);
    }

    public CanvasSaveRestoreView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public CanvasSaveRestoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
    }

    private void init(){
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.BLUE);
        mBackgroundPaint.setStyle(Paint.Style.FILL);

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.RED);
        mLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mLinePaint.setStrokeWidth(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int px = mWidth;
        int py = mWidth;
        canvas.drawRect(0, 0, px, py, mBackgroundPaint);
        // canvas.save()方法保存canvas的状态
        // 后面可以调用canvas的translate, scale, rotate, skew 这几种操作
        canvas.save();
        int centerX = mWidth/2;
        int centerY = mWidth/2;
        // 将画布旋转90度
        canvas.rotate(90, centerX, centerY);
        // /|\
        // 画左边箭头
        canvas.drawLine(px/2, 0, 0, py/2, mLinePaint );
        // 画右边箭头
        canvas.drawLine(px/2, 0, px, py/2, mLinePaint );
        // 画垂直箭头
        canvas.drawLine(px/2, 0, px/2, py/2, mLinePaint );
        // canvas.restore()方法的作用是将画布恢复到rotate之前的状态
        // 然后后面的drawCircle使用的还是原来的坐标系进行绘制
        // 如果不使用canvas.restore()方法 则绘制的图形就是以旋转之后的坐标系进行
        // 绘制
        canvas.restore();
        canvas.drawCircle(px-100, py-100, 20, mLinePaint);
    }
}
