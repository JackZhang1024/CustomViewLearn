package com.lucky.customviewlearn.canvas.imageprocess;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.lucky.customviewlearn.R;

/**
 * Created by zfz on 2018/1/15.
 */

public class ReflectView extends View {

    private Bitmap mSrcBitmap;
    private Bitmap mRefBitmap;
    private Paint mPaint;
    private PorterDuffXfermode mPorterDuffXfermode;

    public ReflectView(Context context) {
        super(context);
        init();
    }

    public ReflectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ReflectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_gakiki2);
        Matrix matrix = new Matrix();
        matrix.setScale(1, -1);
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0, mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix, true);
        mPaint = new Paint();
        mPaint.setShader(new LinearGradient(0, mSrcBitmap.getHeight(), 0, mSrcBitmap.getHeight() + mSrcBitmap.getHeight() / 4,
                0XDD000000, 0X10000000, Shader.TileMode.CLAMP
        ));
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(mSrcBitmap, 0, 0, null);
        canvas.drawBitmap(mRefBitmap, 0, mSrcBitmap.getHeight(), null);
        mPaint.setXfermode(mPorterDuffXfermode);
        //绘制渐变效果矩形
        canvas.drawRect(0, mSrcBitmap.getHeight(), mRefBitmap.getWidth(), mSrcBitmap.getHeight() * 2, mPaint);
        mPaint.setXfermode(null);
    }


}
