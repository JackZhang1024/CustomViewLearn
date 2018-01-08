package com.lucky.customviewlearn.canvas.imageprocess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.lucky.customviewlearn.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zfz on 2018/1/13.
 */

public class ImageProcessActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.img_scenery)
    ImageView mImgScenery;
    @BindView(R.id.sb_hue)
    SeekBar mSBHue; // 色调
    @BindView(R.id.sb_saturation)
    SeekBar mSBSaturation; // 饱和度
    @BindView(R.id.sb_lum)
    SeekBar mSBLum; //亮度
    private float mHue;
    private float mSaturation;
    private float mLum;
    private static int MAX_VALUE = 255;
    private static int MID_VALUE = 127;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_process);
        ButterKnife.bind(this);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_scenary);
        mImgScenery.setImageBitmap(mBitmap);
        mSBHue.setOnSeekBarChangeListener(this);
        mSBSaturation.setOnSeekBarChangeListener(this);
        mSBLum.setOnSeekBarChangeListener(this);
        mSBHue.setMax(MAX_VALUE);
        mSBSaturation.setMax(MAX_VALUE);
        mSBLum.setMax(MAX_VALUE);
        mSBHue.setProgress(MID_VALUE);
        mSBSaturation.setProgress(MID_VALUE);
        mSBLum.setProgress(MID_VALUE);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.sb_hue:
                mHue = (progress - MID_VALUE) * 1.0f / MID_VALUE * 180;
                break;
            case R.id.sb_saturation:
                mSaturation = progress * 1.0f / MID_VALUE;
                break;
            case R.id.sb_lum:
                mLum = progress * 1.0f / MID_VALUE;
                break;
        }
        mImgScenery.setImageBitmap(handleImageEffect(mBitmap, mHue, mSaturation, mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    // Android系统也不允许直接修改原图，类似PhotoShop中的锁定，必须通过原图创建一个大小一样的Bitmap
    // 并将原图绘制到该Bitmap中，以一个副本的形式来修改原图
    private Bitmap handleImageEffect(Bitmap bm, float hue, float saturation, float lum) {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bm, 0, 0, paint); // 将原画绘制到bmp中
        return bmp;
    }
}
