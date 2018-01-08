package com.lucky.customviewlearn.canvas.imageprocess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lucky.customviewlearn.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zfz on 2018/1/13.
 */

public class ImageMatrixSimpleActivity extends AppCompatActivity {

    @BindView(R.id.imageview)
    ImageMatrixView imageView;
    Bitmap mBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_matrix_simple);
        ButterKnife.bind(this);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    }

    @OnClick(R.id.btn_translate)
    public void onTranslateClick(View view) {
        Matrix matrix = new Matrix();
        matrix.setTranslate(100, 0);
        imageView.setImageAndMatrix(mBitmap, matrix);
        imageView.invalidate();
    }

    @OnClick(R.id.btn_Rotate)
    public void onRotateClick(View view) {
        Matrix matrix = new Matrix();
        matrix.setRotate(45);
        imageView.setImageAndMatrix(mBitmap, matrix);
        imageView.invalidate();
    }

    @OnClick(R.id.btn_scale)
    public void onScaleClick(View view) {
        Matrix matrix = new Matrix();
        matrix.setScale(2, 2);
        imageView.setImageAndMatrix(mBitmap, matrix);
        imageView.invalidate();
    }

    @OnClick(R.id.btn_skew)
    public void onSkeWClick(View view) {
        Matrix matrix = new Matrix();
        matrix.setSkew(2, 2);
        imageView.setImageAndMatrix(mBitmap, matrix);
        imageView.invalidate();
    }

    @OnClick(R.id.btn_pre)
    public void onPreClick(View view) {
        Matrix matrix = new Matrix();
        matrix.setTranslate(200, 200);
        matrix.preRotate(45);
        imageView.setImageAndMatrix(mBitmap, matrix);
        imageView.invalidate();
    }

    @OnClick(R.id.btn_post)
    public void onPostClick(View view) {
        Matrix matrix = new Matrix();
        matrix.setScale(1, -1);
        matrix.postRotate(45);
        matrix.postTranslate(0, 200);
        imageView.setImageAndMatrix(mBitmap, matrix);
        imageView.invalidate();
    }

    @OnClick(R.id.btn_reset)
    public void onResetClick(View view) {
        Matrix matrix = new Matrix();
        imageView.setImageAndMatrix(mBitmap, matrix);
        imageView.invalidate();
    }
}
