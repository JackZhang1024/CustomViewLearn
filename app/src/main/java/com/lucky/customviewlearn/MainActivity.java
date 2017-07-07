package com.lucky.customviewlearn;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lucky.customviewlearn.canvas.CheckView;
import com.lucky.customviewlearn.simpleview.PieData;
import com.lucky.customviewlearn.simpleview.PieView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_checkmark).setOnClickListener(this);
        findViewById(R.id.btn_scroller).setOnClickListener(this);
        findViewById(R.id.btn_pieview).setOnClickListener(this);
        findViewById(R.id.btn_customcircle).setOnClickListener(this);
        findViewById(R.id.btn_pathview).setOnClickListener(this);
        findViewById(R.id.btn_heart_bezier).setOnClickListener(this);
        findViewById(R.id.btn_event_dispatch).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_checkmark:
                intent.setClass(this, CheckMarkActivity.class);
                break;
            case R.id.btn_scroller:
                intent.setClass(this, ScrollerLearnActivity.class);
                break;
            case R.id.btn_pieview:
                intent.setClass(this, PieViewLearnActivity.class);
                break;
            case R.id.btn_customcircle:
                intent.setClass(this, CustomCircleActivity.class);
                break;
            case R.id.btn_pathview:
                intent.setClass(this, PathViewActivity.class);
                break;
            case R.id.btn_heart_bezier:
                intent.setClass(this, HeartBezierActivity.class);
                break;
            case R.id.btn_event_dispatch:
                intent.setClass(this, EventDispatchActivity.class);
        }
        startActivity(intent);
    }
}
