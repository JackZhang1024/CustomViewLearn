package com.lucky.customviewlearn.scroller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lucky.customviewlearn.BaseActivity;
import com.lucky.customviewlearn.R;

/**
 * Created by zfz on 2017/8/3.
 */

public class ScrollerActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);
        findViewById(R.id.btn_scroller).setOnClickListener(this);
        findViewById(R.id.btn_scroller_extend).setOnClickListener(this);
        findViewById(R.id.btn_scroller_advanced).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_scroller:
                intent.setClass(this, ScrollerLearnActivity.class);
                break;
            case R.id.btn_scroller_extend:
                intent.setClass(this, ExtScrollerLearnActivity.class);
                break;
            case R.id.btn_scroller_advanced:
                intent.setClass(this, AdvancedScrollerActivity.class);
                break;
        }
        startActivity(intent);
    }
}
