package com.lucky.customviewlearn.material;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lucky.customviewlearn.BaseActivity;
import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.material.behavior.BackTopActivity;
import com.lucky.customviewlearn.material.behavior.BottomSheetBehaviorActivity;
import com.lucky.customviewlearn.material.behavior.SwipeDismissBehaviorActivity;
import com.lucky.customviewlearn.material.behavior.ZhihuActivity;

/**
 * Created by zfz on 2017/7/21.
 */

public class BehaviorsActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behaviors);
        findViewById(R.id.btn_back_top).setOnClickListener(this);
        findViewById(R.id.btn_bottom_sheet).setOnClickListener(this);
        findViewById(R.id.btn_swipe_dismiss).setOnClickListener(this);
        findViewById(R.id.btn_zhi_hu).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_back_top:
                intent.setClass(this, BackTopActivity.class);
                break;
            case R.id.btn_bottom_sheet:
                intent.setClass(this, BottomSheetBehaviorActivity.class);
                break;
            case R.id.btn_swipe_dismiss:
                intent.setClass(this, SwipeDismissBehaviorActivity.class);
                break;
            case R.id.btn_zhi_hu:
                intent.setClass(this, ZhihuActivity.class);
                break;
        }
        startActivity(intent);
    }
}
