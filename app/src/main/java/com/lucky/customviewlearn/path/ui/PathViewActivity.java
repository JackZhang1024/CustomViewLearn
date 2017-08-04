package com.lucky.customviewlearn.path.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.path.BezierCubicVIew;
import com.lucky.customviewlearn.path.BezierPathView;

/**
 * Created by zfz on 2017/7/5.
 */

public class PathViewActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout llPathView, llBezierView, llCubicBezierView, llControlPane;
    private Button btnPathView, btnBezierView, btnCubicBezierView, btnControlPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathview);
        llPathView = (LinearLayout) findViewById(R.id.ll_pathview);
        llBezierView = (LinearLayout) findViewById(R.id.ll_bezier);
        llCubicBezierView = (LinearLayout) findViewById(R.id.ll_cubic_bezier);
        llControlPane = (LinearLayout) findViewById(R.id.ll_control_pan);

        btnPathView = (Button) findViewById(R.id.btn_pathview);
        btnBezierView = (Button) findViewById(R.id.btn_bezier);
        btnCubicBezierView = (Button) findViewById(R.id.btn_cubic_bezier);
        btnControlPane = (Button) findViewById(R.id.btn_control_pan);

        btnPathView.setOnClickListener(this);
        btnBezierView.setOnClickListener(this);
        btnCubicBezierView.setOnClickListener(this);
        btnControlPane.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pathview:
                llPathView.setVisibility(View.VISIBLE);
                llBezierView.setVisibility(View.GONE);
                llCubicBezierView.setVisibility(View.GONE);
                llControlPane.setVisibility(View.GONE);
                break;
            case R.id.btn_bezier:
                llPathView.setVisibility(View.GONE);
                llCubicBezierView.setVisibility(View.GONE);
                llBezierView.setVisibility(View.VISIBLE);
                llControlPane.setVisibility(View.GONE);
                break;
            case R.id.btn_cubic_bezier:
                llPathView.setVisibility(View.GONE);
                llCubicBezierView.setVisibility(View.VISIBLE);
                llBezierView.setVisibility(View.GONE);
                llControlPane.setVisibility(View.GONE);
                final BezierCubicVIew bezierCubicView = (BezierCubicVIew)findViewById(R.id.cubic_bezier);
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        if (checkedId == R.id.rbtn_control1){
                            bezierCubicView.setMode(true);
                        }else{
                            bezierCubicView.setMode(false);
                        }
                    }
                });
                break;
            case R.id.btn_control_pan:
                llPathView.setVisibility(View.GONE);
                llBezierView.setVisibility(View.GONE);
                llCubicBezierView.setVisibility(View.GONE);
                llControlPane.setVisibility(View.VISIBLE);
                break;
        }
    }
}
