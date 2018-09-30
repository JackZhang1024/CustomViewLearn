package com.lucky.customviewlearn.securitycheck;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lucky.customviewlearn.BaseActivity;
import com.lucky.customviewlearn.R;

public class SecurityCheckActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "SecurityCheckActivity";
    private Button mBtnCheckRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_securitycheck);
        mBtnCheckRoot = findViewById(R.id.btn_check_root);
        mBtnCheckRoot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_check_root:
                boolean isRooted = SecurityUtils.isRooted();
                Log.e(TAG, "onClick: "+isRooted);
                break;
        }
    }
}
