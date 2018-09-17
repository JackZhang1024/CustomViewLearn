package com.lucky.customviewlearn.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lucky.customviewlearn.R;

public class ZiRuLinearLayoutActivity extends AppCompatActivity {

    private ZiRuLinearLayout mZiRuLinearlayout;
    private ZiRuLinearLayoutNew mZiRuLinearLayoutNewOutter;
    private ZiRuLinearLayoutNew mZiRuLinearLayoutInner;
    private ZiRuImageView mZiruImageView;
    private ZiRuImageTextView mZiruImageTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zirulinearlayout);
        createZiRuLinearLayoutNew();
        createZiRuImageView();
        createImageTextView();
        createZiRuEditTextView();
    }

    private void createZiRuEditTextView() {

    }

    private void createZiRuLinearLayout(){
        mZiRuLinearlayout = findViewById(R.id.ll_ziru);
        TextView textView = new TextView(this);
        textView.setText("Hello World!");
        textView.setTextSize(20);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mZiRuLinearlayout.addView(textView, params);
    }


    private void createZiRuLinearLayoutNew(){
        mZiRuLinearLayoutNewOutter = (ZiRuLinearLayoutNew) findViewById(R.id.ll_ziru_new_outter);
        mZiRuLinearLayoutNewOutter.setDrawSolidRoundCornerRect(true);


        mZiRuLinearLayoutInner = (ZiRuLinearLayoutNew) findViewById(R.id.ll_ziru_new_inner);
        mZiRuLinearLayoutInner.setDrawSolidRoundCornerRect(true);
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(Color.RED);
        mZiRuLinearLayoutInner.setBackground(colorDrawable);
    }

    private void createZiRuImageView(){
        mZiruImageView = findViewById(R.id.ziru_imageview);
        mZiruImageView.setBackgroundColor(Color.GREEN);
    }


    private void createImageTextView(){
        mZiruImageTextView = findViewById(R.id.imagetextView);
        mZiruImageTextView.setImageResource(R.drawable.left_arrow);
        mZiruImageTextView.setTextViewText("返回");
    }

}
