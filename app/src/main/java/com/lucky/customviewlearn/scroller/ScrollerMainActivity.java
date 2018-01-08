package com.lucky.customviewlearn.scroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lucky.customviewlearn.BaseActivity;
import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.scroller.widget.DragView;

/**
 * Created by zfz on 2017/8/3.
 */

public class ScrollerMainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller_main);
        findViewById(R.id.btn_scroller).setOnClickListener(this);
        findViewById(R.id.btn_scroller_extend).setOnClickListener(this);
        findViewById(R.id.btn_scroller_advanced).setOnClickListener(this);
        findViewById(R.id.btn_drag_view).setOnClickListener(this);
        findViewById(R.id.btn_advanced_drag_view).setOnClickListener(this);
        findViewById(R.id.btn_drag_view_helper).setOnClickListener(this);
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
            case R.id.btn_drag_view:
                intent.setClass(this, DragViewActivity.class);
                break;
            case R.id.btn_advanced_drag_view:
                intent.setClass(this, AdvancedDragViewActivity.class);
                break;
            case R.id.btn_drag_view_helper:
                intent.setClass(this, ViewDragHelperActivity.class);
                break;
        }
        startActivity(intent);
    }
}
