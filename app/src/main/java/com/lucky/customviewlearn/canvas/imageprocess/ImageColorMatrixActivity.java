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
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.lucky.customviewlearn.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 颜色矩阵的学习
 * Created by zfz on 2018/1/13.
 */

public class ImageColorMatrixActivity extends AppCompatActivity {
    @BindView(R.id.imageview)
    ImageView mImageView;
    @BindView(R.id.gridlayout)
    GridLayout mGridLayout;
    @BindView(R.id.btn_change)
    Button mBtnChange;
    @BindView(R.id.btn_reset)
    Button mBtnReset;
    private Bitmap mBitmap;
    private int mEtWidth;
    private int mEtHeight;
    private EditText[] mEts = new EditText[20];
    private float[] mColorMatrix = new float[20];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_colormatrix);
        ButterKnife.bind(this);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_scenary);
        mImageView.setImageBitmap(mBitmap);
        mGridLayout.post(new Runnable() {
            @Override
            public void run() {
                mEtWidth = mGridLayout.getWidth() / 5;
                mEtHeight = mGridLayout.getHeight() / 4;
                addEts();
                initMatrix();
            }
        });
    }

    private void addEts() {
        for (int index = 0; index < 20; index++) {
            EditText editText = new EditText(this);
            editText.setGravity(Gravity.CENTER);
            mEts[index] = editText;
            mGridLayout.addView(editText, mEtWidth, mEtHeight);
        }
    }

    private void initMatrix() {
        for (int index = 0; index < 20; index++) {
            if (index % 6 == 0) {
                mEts[index].setText(String.valueOf(1));
            } else {
                mEts[index].setText(String.valueOf(0));
            }
        }
    }

    private void getMatrix() {
        for (int index = 0; index < 20; index++) {
            mColorMatrix[index] = Float.valueOf(mEts[index].getText().toString());
        }
    }

    private void setImageMatrix() {
        Bitmap bmp = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mColorMatrix);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        mImageView.setImageBitmap(bmp);
    }

    @OnClick(R.id.btn_change)
    public void onChangeClick(View view) {
        getMatrix();
        setImageMatrix();
    }

    @OnClick(R.id.btn_reset)
    public void onResetClick(View view) {
        initMatrix();
        getMatrix();
        setImageMatrix();
    }
}
