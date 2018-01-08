package com.lucky.customviewlearn.canvas.specialeffects;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.core.DisplayUtil;

/**
 * Created by zfz on 2018/1/13.
 */

public class RoundRectXferModeView extends View {
    private static final String TAG = "RoundRectXferModeView";
    private Bitmap mBitmap;
    private Bitmap mOutBitmap;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int mMaxWidth;
    private int mMaxHeight;

    public RoundRectXferModeView(Context context) {
        super(context);
        initView();
    }

    public RoundRectXferModeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RoundRectXferModeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mMaxWidth, mMaxHeight);
        }
    }

    @TargetApi(21)
    private void initView() {
        mMaxWidth = DisplayUtil.dp2px(getContext(), 340);
        mMaxHeight = DisplayUtil.dp2px(getContext(), 340);
        mWidth = DisplayUtil.dp2px(getContext(), 300);
        mHeight = DisplayUtil.dp2px(getContext(), 300);
        mBitmap = loadBitmap(R.drawable.bg_scenary, mWidth, mHeight);
        Log.e(TAG, "initView: mWidth " + mWidth + " mHeight " + mHeight);
        Log.e(TAG, "initView: mBitmap.getWidth() " + mBitmap.getWidth() + " mBitmap.getHeight() " + mBitmap.getHeight());
        mOutBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mOutBitmap);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        canvas.drawRoundRect(0, 0, mBitmap.getWidth(), mBitmap.getHeight(), 10, 10, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mOutBitmap, 0, 0, null);
    }

    public Bitmap loadBitmap(int resID, int width, int height) {
        // 创建Options
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        // 加载边界信息
        BitmapFactory.decodeResource(getResources(), resID, opts);
        // 计算收缩比例
        int xScale = opts.outWidth / width;
        int yScale = opts.outHeight / height;
        // 设置收缩比例
        opts.inSampleSize = xScale > yScale ? xScale : yScale;
        opts.inJustDecodeBounds = false;
        // 返回 加载位图
        return BitmapFactory.decodeResource(getResources(), resID, opts);
    }
}
