package com.lucky.customviewlearn.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

// 带有文字的图片控件 组合控件
public class ZiRuImageTextView extends RelativeLayout {
    private ImageView mImageView;
    private TextView mTextView;

    public ZiRuImageTextView(Context context) {
        this(context, null);
    }

    public ZiRuImageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        mImageView = new ImageView(context);
        mTextView = new TextView(context);
        int width = LayoutParams.WRAP_CONTENT;
        int height = LayoutParams.WRAP_CONTENT;
        RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(width, height);
        imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        imageLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(mImageView, imageLayoutParams);

        RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(width, height);
        textLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(mTextView, textLayoutParams);
    }

    public void setImageResource(int resID){
        mImageView.setImageResource(resID);
    }

    public void setImageResource(String uri){

    }


    public void setTextViewText(String viewText){
        mTextView.setText(viewText);
    }

    public void setTextViewTextColor(String viewTextColor){
        mTextView.setTextColor(Color.parseColor(viewTextColor));
    }


}
