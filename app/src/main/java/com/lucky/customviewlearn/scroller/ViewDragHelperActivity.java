package com.lucky.customviewlearn.scroller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lucky.customviewlearn.R;

import butterknife.ButterKnife;

/**
 * Created by zfz on 2018/1/4.
 */

public class ViewDragHelperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdraghelper);
        ButterKnife.bind(this);
    }
}
