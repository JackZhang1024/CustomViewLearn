package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lucky.customviewlearn.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

//贝塞尔曲线实现毛笔签名效果 https://blog.csdn.net/ongyunhai/article/details/51354020

public class SignatureView extends View {

    private static final String TAG = "SignatureView";
    private Paint mPaint;
    private Path mPath;
    private Canvas mCanvas;
    private Bitmap mSignatureBgBitmap;
    private static final float STROKE_WIDTH = 30;
    private static final float HALF_STROKE_WITH = STROKE_WIDTH / 2;
    private RectF mDirtyRectF = new RectF();
    private float mLastTouchX, mLastTouchY;
    private int mWidth, mHeight;
    public SignatureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(STROKE_WIDTH);
        mSignatureBgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shouqian);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(eventX, eventY);
                mLastTouchX = eventX;
                mLastTouchY = eventY;
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                // 限制脏区范围
                restrictDirtyRegion(eventX, eventY);
                int historySize = event.getHistorySize();
                for (int i = 0; i < historySize; i++) {
                    float historyX = event.getHistoricalX(i);
                    float historyY = event.getHistoricalY(i);
                    // 扩展脏区
                    expandDirtyRegion(historyX, historyY);
                    mPath.lineTo(historyX, historyY);
                }
                break;
             default:
                 return false;

        }
        invalidate((int) (mDirtyRectF.left - HALF_STROKE_WITH), (int) (mDirtyRectF.top - HALF_STROKE_WITH), (int) (mDirtyRectF.right + HALF_STROKE_WITH), (int) (mDirtyRectF.bottom + HALF_STROKE_WITH));
        mLastTouchX = eventX;
        mLastTouchY = eventY;
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth =w;
        mHeight = h;
        mSignatureBgBitmap = loadBitmap(getBitmap2Byte(mSignatureBgBitmap), mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mSignatureBgBitmap, 0, 0, null);
        canvas.drawPath(mPath, mPaint);
    }


    private void restrictDirtyRegion(float eventX, float eventY) {
        mDirtyRectF.left = Math.min(mLastTouchX, eventX);
        mDirtyRectF.top = Math.min(mLastTouchY, eventY);
        mDirtyRectF.right = Math.max(mLastTouchX, eventX);
        mDirtyRectF.bottom = Math.max(mLastTouchY, eventY);
    }

    private void expandDirtyRegion(float historyX, float historyY) {
        if (historyX < mDirtyRectF.left) {
            mDirtyRectF.left = historyX;
        } else if (historyX > mDirtyRectF.right) {
            mDirtyRectF.right = historyX;
        }
        if (historyY < mDirtyRectF.top) {
            mDirtyRectF.top = historyY;
        } else if (historyY > mDirtyRectF.bottom) {
            mDirtyRectF.bottom = historyY;
        }
    }

    public void clear(){
        mPath.reset();
        invalidate();
    }


    public void saveSignaturePicture(){
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable drawable = getBackground();
//        if (drawable!=null){
//            drawable.draw(canvas);
//        }else{
//            canvas.drawColor(Color.WHITE);
//        }
        canvas.drawBitmap(mSignatureBgBitmap, 0 , 0, null);
        draw(canvas);
        try {
            File dir = Environment.getExternalStorageDirectory().getAbsoluteFile();
            File savedFile = new File(dir, "signature.png");
            saveBitMapToFile(bitmap, savedFile);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 将位图对象 保存到指定文件
     *
     * @param bm
     * @param file
     */
    public static void saveBitMapToFile(Bitmap bm, File file) throws IOException {
        // 如果文件所在目录不存在，则创建
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        // 如果文件不存在,则创建
        if (!file.exists()) {
            file.createNewFile();
        }
        // 创建文件输出流
        FileOutputStream out = new FileOutputStream(file);
        // 保存图片到指定输出流
        bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
        out.close();
    }

    public static Bitmap loadBitmap(byte[] data, int width, int height) {
        // 创建Options
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        // 加载边界信息
        BitmapFactory.decodeByteArray(data, 0, data.length, opts);
        // 计算收缩比例
        int xScale = opts.outWidth / width;
        int yScale = opts.outHeight / height;
        // 设置收缩比例
        opts.inSampleSize = xScale > yScale ? xScale : yScale;
        opts.inJustDecodeBounds = false;
        // 返回 加载位图
        return BitmapFactory.decodeByteArray(data, 0, data.length, opts);
    }

    public static byte[] getBitmap2Byte(Bitmap bitmap) {
        if (bitmap != null) {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            final byte[] data = baos.toByteArray();
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
        return null;
    }


}
