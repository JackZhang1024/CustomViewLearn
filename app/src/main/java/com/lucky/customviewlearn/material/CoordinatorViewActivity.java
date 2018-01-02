package com.lucky.customviewlearn.material;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.lucky.customviewlearn.R;

/**
 * Created by zfz on 2017/7/14.
 */

public class CoordinatorViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("tab1"));
        tabs.addTab(tabs.newTab().setText("tab2"));
        tabs.addTab(tabs.newTab().setText("tab3"));
    }
}
