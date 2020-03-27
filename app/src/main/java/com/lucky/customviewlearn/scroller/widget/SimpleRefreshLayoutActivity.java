package com.lucky.customviewlearn.scroller.widget;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.lucky.customviewlearn.R;

public class SimpleRefreshLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplerefresh);
        final SimpleRefreshLayout simpleRefreshLayout = findViewById(R.id.simple_refresh_layout);
        simpleRefreshLayout.setOnRefreshListener(new SimpleRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        simpleRefreshLayout.stopRefresh();
                    }
                }, 2000);

            }

            @Override
            public void onBottomRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        simpleRefreshLayout.stopRefresh();
                    }
                }, 2000);
            }
        });
    }


}
