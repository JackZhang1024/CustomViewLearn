package com.lucky.customviewlearn.canvas.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lucky.customviewlearn.R;

/**
 * 简单2D绘图基础
 * Created by zfz on 2018/1/6.
 */

public class SimpleCanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_canvas);
    }
}
