package com.lucky.customviewlearn.canvas.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lucky.customviewlearn.BaseActivity;
import com.lucky.customviewlearn.R;

/**
 * Created by zfz on 2017/8/3.
 */

public class CanvasActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        findViewById(R.id.btn_checkmark).setOnClickListener(this);
        findViewById(R.id.btn_pieview).setOnClickListener(this);
        findViewById(R.id.btn_customcircle).setOnClickListener(this);
        findViewById(R.id.btn_taichi).setOnClickListener(this);
        findViewById(R.id.btn_canvas_save_restore).setOnClickListener(this);
        findViewById(R.id.btn_canvas_layer).setOnClickListener(this);
        findViewById(R.id.btn_rule_view).setOnClickListener(this);
        findViewById(R.id.btn_recursive_view).setOnClickListener(this);
        findViewById(R.id.btn_clock_view).setOnClickListener(this);
        findViewById(R.id.btn_canvas_skew).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_checkmark:
                intent.setClass(this, CheckMarkActivity.class);
                break;
            case R.id.btn_pieview:
                intent.setClass(this, PieViewLearnActivity.class);
                break;
            case R.id.btn_customcircle:
                intent.setClass(this, CustomCircleActivity.class);
                break;
            case R.id.btn_taichi:
                intent.setClass(this, TaiChiActivity.class);
                break;
            case R.id.btn_canvas_save_restore:
                intent.setClass(this, CanvasSaveRestoreActivity.class);
                break;
            case R.id.btn_canvas_layer:
                intent.setClass(this, CanvasLayerActivity.class);
                break;
            case R.id.btn_rule_view:
                intent.setClass(this, RuleViewActivity.class);
                break;
            case R.id.btn_recursive_view:
                intent.setClass(this, RecursiveActivity.class);
                break;
            case R.id.btn_clock_view:
                intent.setClass(this, RotateClockViewActivity.class);
                break;
            case R.id.btn_canvas_skew:
                intent.setClass(this, CanvasSkewActivity.class);
                break;
        }
        startActivity(intent);
    }
}
