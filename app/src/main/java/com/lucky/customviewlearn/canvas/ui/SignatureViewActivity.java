package com.lucky.customviewlearn.canvas.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.canvas.SignatureView;

public class SignatureViewActivity extends AppCompatActivity {

    private SignatureView mSignatureView;
    private Button mBtnSave, mBtnReset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signatureview);
        mSignatureView = findViewById(R.id.signature_view);
        mBtnSave = findViewById(R.id.btn_save);
        mBtnReset = findViewById(R.id.btn_reset);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignatureView.saveSignaturePicture();
            }
        });
        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignatureView.clear();
            }
        });
    }




}
