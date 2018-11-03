package com.lucky.customviewlearn.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lucky.customviewlearn.R;

public class ZiRuLinearLayoutActivity extends AppCompatActivity {

    private ZiRuLinearLayout mZiRuLinearlayout;
    private ZiRuLinearLayoutNew mZiRuLinearLayoutNewOutter;
    private ZiRuLinearLayoutNew mZiRuLinearLayoutInner;
    private ZiRuImageView mZiruImageView;
    private ZiRuImageTextView mZiruImageTextView;
    private ZiRuRelativeLayout mZiRuRelativeLayout;
    private ZiRuOuterRelativeLayout mZiRuRelativeParent;
    private TextView mTvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zirulinearlayout);
        createZiRuLinearLayoutNew();
        createZiRuImageView();
        createImageTextView();
        createZiRuEditTextView();
        createZiRuRelativeLayout();
        expandInnerView();
    }

    private void expandInnerView() {
        findViewById(R.id.btn_expand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout.LayoutParams rlParams = (RelativeLayout.LayoutParams) ziRuRelativeLayout.getLayoutParams();
                rlParams.width = rlParams.width*2;
                rlParams.height = rlParams.height*2;
                ziRuRelativeLayout.setLayoutParams(rlParams);
                //mZiRuRelativeParent.addView(ziRuRelativeLayout, rlParams);
            }
        });
    }

    private void createZiRuEditTextView() {

    }

    private void createZiRuLinearLayout() {
        mZiRuLinearlayout = findViewById(R.id.ll_ziru);
        TextView textView = new TextView(this);
        textView.setText("Hello World!");
        textView.setTextSize(20);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mZiRuLinearlayout.addView(textView, params);
    }


    private void createZiRuLinearLayoutNew() {
        mZiRuLinearLayoutNewOutter = (ZiRuLinearLayoutNew) findViewById(R.id.ll_ziru_new_outter);
        mZiRuLinearLayoutNewOutter.setDrawSolidRoundCornerRect(true);


        mZiRuLinearLayoutInner = (ZiRuLinearLayoutNew) findViewById(R.id.ll_ziru_new_inner);
        mZiRuLinearLayoutInner.setDrawSolidRoundCornerRect(true);
        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(Color.RED);
        mZiRuLinearLayoutInner.setBackground(colorDrawable);
    }

    private void createZiRuImageView() {
        mZiruImageView = findViewById(R.id.ziru_imageview);
        mZiruImageView.setBackgroundColor(Color.GREEN);
    }


    private void createImageTextView() {
        mZiruImageTextView = findViewById(R.id.imagetextView);
        mZiruImageTextView.setImageResource(R.drawable.left_arrow);
        mZiruImageTextView.setTextViewText("返回");
    }

    ZiRuRelativeLayout ziRuRelativeLayout;

    private void createZiRuRelativeLayout() {
        mZiRuRelativeParent = (ZiRuOuterRelativeLayout) findViewById(R.id.ziru_relative_parent);
        ziRuRelativeLayout = new ZiRuRelativeLayout(this);
        ziRuRelativeLayout.setDrawSolidRoundCornerRect(true);
        //ziRuRelativeLayout.setDrawRoundCornerRect(true);
        //ziRuRelativeLayout.setDrawRect(true);
        ziRuRelativeLayout.setDrawTopSide(true);
        ziRuRelativeLayout.setDrawBottomSide(true);
        ziRuRelativeLayout.setDrawLeftOutSide(true);
        ziRuRelativeLayout.setDrawRightSide(true);
        ziRuRelativeLayout.setBackgroundResource(0);
        //ziRuRelativeLayout.setInnerPadding(40, 120, 20, 20);
        ziRuRelativeLayout.setOuterPadding(40, 20, 40, 20);
        ziRuRelativeLayout.setBorder(10, "#FFF68F");
        TextView textView = new TextView(this);
        textView.setText("Hello ZiRuRelativeLayout");
        textView.setTextSize(20);
        RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //ziRuRelativeLayout.addView(textView, rp);
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
        RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(50, 50);
        rlParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mZiRuRelativeParent.addView(ziRuRelativeLayout, rlParams);

//        ZiRuRelativeLayout ziRuRelativeLayout2 = new ZiRuRelativeLayout(this);
//        RelativeLayout.LayoutParams rlParams2 = new RelativeLayout.LayoutParams(200, 400);
//        ziRuRelativeLayout2.setDrawSolidRoundCornerRect(true);
//        rlParams2.addRule(RelativeLayout.ALIGN_TOP);
//        mZiRuRelativeParent.addView(ziRuRelativeLayout2, rlParams2);
    }


}
