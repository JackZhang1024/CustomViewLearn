package com.lucky.customviewlearn.scroller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.scroller.widget.RulerView;

public class RulerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_rulerview);
        RulerView ruler_weight = findViewById(R.id.ruler_weight);
        final TextView tvWeight = findViewById(R.id.tv_weight);
//        ruler_height.setValue(165, 80, 250, 1);
        ruler_weight.setValue(55, 20, 200, 0.1f);
        ruler_weight.setOnValueChangeListener(new RulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                         tvWeight.setText(String.format("体重%s", value));
            }
        });

    }
}
