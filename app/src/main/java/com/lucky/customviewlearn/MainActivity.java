package com.lucky.customviewlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lucky.androidlearn.aidl.BookManagerActivity;
import com.lucky.androidlearn.socket.SocketIPClientActivity;
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
import com.lucky.customviewlearn.view.ZiRuImageViewActivity;
import com.lucky.customviewlearn.view.ZiRuLinearLayoutActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_canvas)
    public void onCanvasClick() {
        Intent intent = new Intent();
        intent.setClass(this, CanvasActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_scroller)
    public void onScrollerClick() {
        Intent intent = new Intent();
        intent.setClass(this, ScrollerMainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_pathview)
    public void onPathViewClick() {
        Intent intent = new Intent();
        intent.setClass(this, PathViewActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_heart_bezier)
    public void onBezierClick() {
        Intent intent = new Intent();
        intent.setClass(this, HeartBezierActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_event_dispatch)
    public void onEventDispatchClick() {
        Intent intent = new Intent();
        intent.setClass(this, EventDispatchActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_resources)
    public void onResourcesClick() {
        Intent intent = new Intent();
        intent.setClass(this, ResourcesActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_material_design)
    public void onMaterialDesignClick() {
        Intent intent = new Intent();
        intent.setClass(this, MaterialDesignActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_aidl_bms)
    public void onAIDLBMSClick() {
        Intent intent = new Intent();
        intent.setClass(this, BookManagerActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_ziru_linearlayout)
    public void onZiRULinearLayoutClick() {
        Intent intent = new Intent();
        intent.setClass(this, ZiRuLinearLayoutActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_security_check)
    public void onSecurityCheckClick() {
        Intent intent = new Intent();
        intent.setClass(this, SecurityCheckActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_ziru_flexbox_layout)
    public void onZiRuFlexBoxClick() {
        Intent intent = new Intent();
        intent.setClass(this, FlexLayoutActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_ziru_yoga_layout)
    public void onZiRuYogaLayoutClick() {
        Intent intent = new Intent();
        intent.setClass(this, YogaLayoutActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_relative_flex)
    public void onRelativeFlexBoxClick() {
        Intent intent = new Intent();
        intent.setClass(this, RelativeFlexBoxActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_dynamic_insert)
    public void onDynamicInsertClick() {
        Intent intent = new Intent();
        intent.setClass(this, DynamicViewInsertActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_dynamic_view_change)
    public void onDynamicViewChangeClick() {
        Intent intent = new Intent();
        intent.setClass(this, ZiRuImageViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_tcp_client)
    public void onTCPClientClick() {
        Intent intent = new Intent();
        intent.setClass(this, SocketIPClientActivity.class);
        startActivity(intent);
    }

}
