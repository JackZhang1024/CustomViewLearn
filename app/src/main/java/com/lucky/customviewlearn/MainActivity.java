package com.lucky.customviewlearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lucky.customviewlearn.canvas.ui.CanvasActivity;
import com.lucky.customviewlearn.eventdispatch.ui.EventDispatchActivity;
import com.lucky.customviewlearn.materialdesign.ui.MaterialDesignActivity;
import com.lucky.customviewlearn.path.ui.HeartBezierActivity;
import com.lucky.customviewlearn.path.ui.PathViewActivity;
import com.lucky.customviewlearn.resource.ResourcesActivity;
import com.lucky.customviewlearn.scroller.ui.ScrollerActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        findViewById(R.id.btn_canvas).setOnClickListener(this);
        findViewById(R.id.btn_scroller).setOnClickListener(this);

        findViewById(R.id.btn_pathview).setOnClickListener(this);
        findViewById(R.id.btn_heart_bezier).setOnClickListener(this);
        findViewById(R.id.btn_event_dispatch).setOnClickListener(this);

        findViewById(R.id.btn_resources).setOnClickListener(this);
        findViewById(R.id.btn_material_design).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_canvas:
                intent.setClass(this, CanvasActivity.class);
                break;
            case R.id.btn_scroller:
                intent.setClass(this, ScrollerActivity.class);
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
        }
        startActivity(intent);
    }
}
