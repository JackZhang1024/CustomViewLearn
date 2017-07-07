package com.lucky.customviewlearn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lucky.customviewlearn.canvas.CheckView;

/**
 * Created by zfz on 2017/7/5.
 */

public class CheckMarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkmark);
        drawCheckView();
    }


    private void drawCheckView(){
        final CheckView checkView = (CheckView) findViewById(R.id.check_view);
        checkView.setDuration(3000);
        checkView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        Button btnCheck = (Button) findViewById(R.id.check);
        Button btnUnCheck = (Button) findViewById(R.id.uncheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkView.check();
            }
        });
        btnUnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkView.unCheck();
            }
        });
    }


}
