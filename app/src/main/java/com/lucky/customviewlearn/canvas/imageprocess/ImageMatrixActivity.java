package com.lucky.customviewlearn.canvas.imageprocess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
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
 * Created by zfz on 2018/1/13.
 */

public class ImageMatrixActivity extends AppCompatActivity {

    @BindView(R.id.imageview)
    ImageMatrixView mImageView;
    @BindView(R.id.gridlayout)
    GridLayout mGridLayout;
    @BindView(R.id.btn_change)
    Button mBtnChange;
    @BindView(R.id.btn_reset)
    Button mBtnReset;
    private Bitmap mBitmap;
    private int mEtWidth;
    private int mEtHeight;
    private EditText[] mEts = new EditText[9];
    private float[] mMatrixValues = new float[9];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_matrix);
        ButterKnife.bind(this);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        mGridLayout.post(new Runnable() {
            @Override
            public void run() {
                mEtWidth = mGridLayout.getWidth() / 3;
                mEtHeight = mGridLayout.getHeight() / 3;
                addEts();
                initMatrix();
            }
        });
    }

    private void addEts() {
        for (int index = 0; index < 9; index++) {
            EditText editText = new EditText(this);
            editText.setGravity(Gravity.CENTER);
            mEts[index] = editText;
            mGridLayout.addView(editText, mEtWidth, mEtHeight);
        }
    }

    private void initMatrix() {
        for (int index = 0; index < 9; index++) {
            if (index % 4 == 0) {
                mEts[index].setText(String.valueOf(1));
            } else {
                mEts[index].setText(String.valueOf(0));
            }
        }
    }

    private void getMatrix() {
        for (int index = 0; index < 9; index++) {
            mMatrixValues[index] = Float.valueOf(mEts[index].getText().toString());
        }
    }

    private void setImageMatrix() {
        Matrix matrix = new Matrix();
        matrix.setValues(mMatrixValues);
        mImageView.setImageAndMatrix(mBitmap, matrix);
        mImageView.invalidate();
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
