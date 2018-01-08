package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.lucky.customviewlearn.R;


/**
 * Created by zfz on 2018/1/8.
 */

public class LineEditText extends AppCompatEditText {

    private Paint mPaint;

    public LineEditText(Context context) {
        super(context);
        initView();
    }

    public LineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LineEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(getResources().getColor(R.color.blueviolet));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, getHeight() - 2, getWidth()*20, getHeight() - 2, mPaint);
    }
}
