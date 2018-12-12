package com.lucky.customviewlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lucky.androidlearn.aidl.BookManagerActivity;
import com.lucky.customviewlearn.canvas.ui.CanvasActivity;
import com.lucky.customviewlearn.eventdispatch.EventDispatchActivity;
import com.lucky.customviewlearn.material.ui.MaterialDesignActivity;
import com.lucky.customviewlearn.path.ui.HeartBezierActivity;
import com.lucky.customviewlearn.path.ui.PathViewActivity;
import com.lucky.customviewlearn.resource.ResourcesActivity;
import com.lucky.customviewlearn.scroller.ScrollerMainActivity;
import com.lucky.customviewlearn.securitycheck.SecurityCheckActivity;
import com.lucky.customviewlearn.view.DynamicViewInsertActivity;
import com.lucky.customviewlearn.view.FlexLayoutActivity;
import com.lucky.customviewlearn.view.ZiRuLinearLayoutActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        findViewById(R.id.btn_canvas).setOnClickListener(this);
        findViewById(R.id.btn_scroller).setOnClickListener(this);

        findViewById(R.id.btn_pathview).setOnClickListener(this);
        findViewById(R.id.btn_heart_bezier).setOnClickListener(this);
        findViewById(R.id.btn_event_dispatch).setOnClickListener(this);

        findViewById(R.id.btn_resources).setOnClickListener(this);
        findViewById(R.id.btn_material_design).setOnClickListener(this);
        findViewById(R.id.btn_aidl_bms).setOnClickListener(this);
        findViewById(R.id.btn_ziru_linearlayout).setOnClickListener(this);
        findViewById(R.id.btn_security_check).setOnClickListener(this);
        findViewById(R.id.btn_ziru_flexbox_layout).setOnClickListener(this);
        findViewById(R.id.btn_ziru_yoga_layout).setOnClickListener(this);
        findViewById(R.id.btn_relative_flex).setOnClickListener(this);
        findViewById(R.id.btn_dynamic_insert).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_canvas:
                intent.setClass(this, CanvasActivity.class);
                break;
            case R.id.btn_scroller:
                intent.setClass(this, ScrollerMainActivity.class);
                break;
            case R.id.btn_pathview:
                intent.setClass(this, PathViewActivity.class);
                break;
            case R.id.btn_heart_bezier:
                intent.setClass(this, HeartBezierActivity.class);
                break;
            case R.id.btn_event_dispatch:
                intent.setClass(this, EventDispatchActivity.class);
                break;
            case R.id.btn_resources:
                intent.setClass(this, ResourcesActivity.class);
                break;
            case R.id.btn_material_design:
                intent.setClass(this, MaterialDesignActivity.class);
                break;
            case R.id.btn_aidl_bms:
                intent.setClass(this, BookManagerActivity.class);
                break;
            case R.id.btn_ziru_linearlayout:
                intent.setClass(this, ZiRuLinearLayoutActivity.class);
                break;
            case R.id.btn_security_check:
                intent.setClass(this, SecurityCheckActivity.class);
                break;
            case R.id.btn_ziru_flexbox_layout:
                intent.setClass(this, FlexLayoutActivity.class);
                break;
            case R.id.btn_ziru_yoga_layout:
                intent.setClass(this, YogaLayoutActivity.class);
                break;
            case R.id.btn_relative_flex:
                intent.setClass(this, RelativeFlexBoxActivity.class);
                break;
            case R.id.btn_dynamic_insert:
                intent.setClass(this, DynamicViewInsertActivity.class);
                break;
        }
        startActivity(intent);
    }
}
