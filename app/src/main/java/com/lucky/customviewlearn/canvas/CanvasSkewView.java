package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Map;

/**
 * Created by zfz on 2017/8/4.
 */

public class CanvasSkewView extends View {

    private Paint mPaint;

    public CanvasSkewView(Context context) {
        this(context, null);
    }

    public CanvasSkewView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasSkewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        canvas.drawRect(0, 0, 400, 400, mPaint);
        //这个方法只要理解了两个参数即可：
        //float sx:将画布在x方向上倾斜相应的角度，sx为倾斜角度的tan值；
        //float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值；
        //注意，这里全是倾斜角度的tan值，比如我们打算在X轴方向上倾斜45度，tan45=1；

        // 向X轴方向上错切
        canvas.save();
        canvas.skew(1, 0);
        mPaint.setColor(0x8800ff00);
        canvas.drawRect(0, 0, 400, 400, mPaint);
        canvas.restore();

        // 向Y轴方向上错切
        canvas.save();
        canvas.skew(0, 1);
        mPaint.setColor(0x8800ff00);
        canvas.drawRect(0, 0, 400, 400, mPaint);
        canvas.restore();
    }
}
