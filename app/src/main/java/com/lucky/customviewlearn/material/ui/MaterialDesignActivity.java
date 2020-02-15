package com.lucky.customviewlearn.material.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lucky.customviewlearn.BaseActivity;
import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.material.BehaviorsActivity;
import com.lucky.customviewlearn.material.BottomPanelShowHideOneActivity;
import com.lucky.customviewlearn.material.CollapsingLayoutActivity;
import com.lucky.customviewlearn.material.CoordinatorViewActivity;
import com.lucky.customviewlearn.material.MaterialThemeActivity;
import com.lucky.customviewlearn.material.NavigationViewActivity;
import com.lucky.customviewlearn.material.SimpleMaterialDesignActivity;

/**
 * Created by zfz on 2017/7/12.
 */

public class MaterialDesignActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        findViewById(R.id.snack_bar).setOnClickListener(this);
        findViewById(R.id.app_bar_layout).setOnClickListener(this);
        findViewById(R.id.collapsing_toolbar_layout).setOnClickListener(this);
        findViewById(R.id.coordinator_layout).setOnClickListener(this);
        findViewById(R.id.float_action_button).setOnClickListener(this);
        findViewById(R.id.navigation_view).setOnClickListener(this);
        findViewById(R.id.tab_layout).setOnClickListener(this);
        findViewById(R.id.text_input_layout).setOnClickListener(this);
        findViewById(R.id.btn_behaviors).setOnClickListener(this);
        findViewById(R.id.btn_material_theme).setOnClickListener(this);
        findViewById(R.id.btn_material_learn).setOnClickListener(this);
        findViewById(R.id.btn_coordinator_one).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.snack_bar:
                intent.setClass(this, SimpleMaterialDesignActivity.class);
                break;
            case R.id.app_bar_layout:
                intent.setClass(this, CoordinatorViewActivity.class);
                break;
            case R.id.collapsing_toolbar_layout:
                intent.setClass(this, CollapsingLayoutActivity.class);
                break;
            case R.id.coordinator_layout:
                intent.setClass(this, CoordinatorViewActivity.class);
                break;
            case R.id.float_action_button:
                intent.setClass(this, SimpleMaterialDesignActivity.class);
                break;
            case R.id.navigation_view:
                intent.setClass(this, NavigationViewActivity.class);
                break;
            case R.id.tab_layout:
                intent.setClass(this, CoordinatorViewActivity.class);
                break;
            case R.id.text_input_layout:
                intent.setClass(this, SimpleMaterialDesignActivity.class);
                break;
            case R.id.btn_behaviors:
                intent.setClass(this, BehaviorsActivity.class);
                break;
            case R.id.btn_material_theme:
                intent.setClass(this, MaterialThemeActivity.class);
                break;
            case R.id.btn_material_learn:
                intent.setClass(this, MaterialMainActivity.class);
                break;
            case R.id.btn_coordinator_one:
                intent.setClass(this, BottomPanelShowHideOneActivity.class);
                break;
        }
        startActivity(intent);
    }
}
