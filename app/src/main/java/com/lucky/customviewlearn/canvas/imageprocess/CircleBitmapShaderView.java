package com.lucky.customviewlearn.canvas.imageprocess;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.lucky.customviewlearn.R;

/**
 * Created by zfz on 2018/1/13.
 */

public class CircleBitmapShaderView extends View {
    private Paint mPaint;

    public CircleBitmapShaderView(Context context) {
        super(context);
        init();
    }

    public CircleBitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleBitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_gakiki);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(bitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(500, 500, 100, mPaint);
    }
}
