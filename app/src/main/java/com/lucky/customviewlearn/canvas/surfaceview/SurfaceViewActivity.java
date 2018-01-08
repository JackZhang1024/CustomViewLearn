package com.lucky.customviewlearn.canvas.surfaceview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.lucky.customviewlearn.R;
import butterknife.ButterKnife;

/**
 * Created by zfz on 2018/1/12.
 */

public class SurfaceViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surfaceview);
        ButterKnife.bind(this);


    }
}
