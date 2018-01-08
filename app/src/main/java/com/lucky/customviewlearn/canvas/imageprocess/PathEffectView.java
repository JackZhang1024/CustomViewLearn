package com.lucky.customviewlearn.canvas.imageprocess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zfz on 2018/1/15.
 */

public class PathEffectView extends View {

    private Path mPath;
    private Paint mPaint;
    private PathEffect[] mEffects = new PathEffect[6];

    public PathEffectView(Context context) {
        super(context);
        init();
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
        mEffects[0] = null;
        mEffects[1] = new CornerPathEffect(30);
        mEffects[2] = new DiscretePathEffect(3.0f, 5.0f);
        mEffects[3] = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
        mPath = new Path();
        mPath.addRect(0, 0, 8, 8, Path.Direction.CCW);
        mEffects[4] = new PathDashPathEffect(mPath, 12, 0, PathDashPathEffect.Style.ROTATE);
        mEffects[5] = new ComposePathEffect(mEffects[3], mEffects[1]);
        mPath.moveTo(0, 0);
        for (int index = 0; index <= 30; index++) {
            mPath.lineTo(index * 35, (float) (Math.random() * 100));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int index = 0; index < 5; index++) {
            mPaint.setPathEffect(mEffects[index]);
            canvas.drawPath(mPath, mPaint);
            canvas.translate(0, 200);
        }
    }
}
