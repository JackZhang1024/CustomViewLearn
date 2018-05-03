package com.lucky.customviewlearn.canvas.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.canvas.NBScrollRuler;


public class NBScrollRulerActivity extends AppCompatActivity {

    private NBScrollRuler mNBScrollRuler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nb_scrollruler);
        mNBScrollRuler = (NBScrollRuler) findViewById(R.id.rulerView_height);
        final TextView mTvWeight = (TextView) findViewById(R.id.tv_weight);
        //设置选中值、最小值、最大值、单位值
        mNBScrollRuler.setOnChooseResulterListener(new NBScrollRuler.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                //滑动结束时给的回调
                mTvWeight.setText(result);
            }

            @Override
            public void onScrollResult(String result) {
                mTvWeight.setText(result);
            }
        });
    }


}
