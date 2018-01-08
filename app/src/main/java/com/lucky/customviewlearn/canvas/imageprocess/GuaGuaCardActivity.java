package com.lucky.customviewlearn.canvas.imageprocess;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lucky.customviewlearn.R;

import butterknife.ButterKnife;

/**
 * 刮奖卡
 * Created by zfz on 2018/1/13.
 */

public class GuaGuaCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guagua_card);
        ButterKnife.bind(this);
    }
}
