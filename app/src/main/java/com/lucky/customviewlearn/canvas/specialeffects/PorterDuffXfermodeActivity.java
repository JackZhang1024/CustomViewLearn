package com.lucky.customviewlearn.canvas.specialeffects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lucky.customviewlearn.R;

import butterknife.ButterKnife;

/**
 * 画笔特效处理
 * Created by zfz on 2018/1/6.
 */

public class PorterDuffXferModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porterduff);
        ButterKnife.bind(this);
    }


}
