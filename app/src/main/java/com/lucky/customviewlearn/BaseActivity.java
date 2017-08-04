package com.lucky.customviewlearn;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by zfz on 2017/7/13.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setupToolBar();
        super.onCreate(savedInstanceState);
    }

    private void setupToolBar() {
        ViewGroup activityRoot = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);

        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        contentLayout.setOrientation(LinearLayout.VERTICAL);

        getLayoutInflater().inflate(R.layout.toolbar, contentLayout);
        getLayoutInflater().inflate(R.layout.root_content, contentLayout);

        activityRoot.addView(contentLayout);

        setSupportActionBar((Toolbar) findViewById(R.id.tool_bar));
        Drawable indicatorDrawable = getResources().getDrawable(R.drawable.ic_ab_back);
        indicatorDrawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(indicatorDrawable);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        ViewGroup v = (ViewGroup) findViewById(R.id.root_content);
        v.removeAllViews();
        getLayoutInflater().inflate(layoutResID, v);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
