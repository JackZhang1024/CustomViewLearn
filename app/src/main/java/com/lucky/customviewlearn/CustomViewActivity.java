package com.lucky.customviewlearn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by zfz on 2018/1/2.
 */

public class CustomViewActivity extends AppCompatActivity {


    TextView tvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_another);
        tvContent = (TextView) findViewById(R.id.tv_content);
        if (getIntent() != null) {
            Intent intent = getIntent();
            Uri uri = intent.getData();
            String clientId = uri.getQueryParameter("clientId");
            String userId = uri.getQueryParameter("userId");
            tvContent.setText(clientId + "  " + userId);
        }
    }
}
