package com.lucky.customviewlearn.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


// 带有文字的图片控件 组合控件
public class ZiRuImageTextView extends RelativeLayout {
    private ImageView mImageView;
    private TextView mTextView;
    private int mTextSize = 14;
    private int mImageResID = 1000;

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
        mTextView.setTextSize(mTextSize);
        int width = LayoutParams.WRAP_CONTENT;
        int height = LayoutParams.WRAP_CONTENT;
        LayoutParams imageLayoutParams = new LayoutParams(width, height);
        imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        imageLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mImageView.setId(mImageResID);
        addView(mImageView, imageLayoutParams);

        LayoutParams textLayoutParams = new LayoutParams(width, height);
        //textLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        //textLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        //textLayoutParams.addRule(RelativeLayout.RIGHT_OF, mImageResID);
        //textLayoutParams.leftMargin = 20;
        //mTextView.setGravity(Gravity.CENTER);
        //addView(mTextView, textLayoutParams);

    }

    public void setImageResource(int resID){
        mImageView.setImageResource(resID);
    }

    public void setImageResource(String uri){
        //ImageLoader.getInstance(getContext()).loadImage(uri, mImageView);
    }


    public void setTextViewText(String viewText){
        //mTextView.setText(viewText);
    }

    public void setTextViewTextColor(String viewTextColor){
        //mTextView.setTextColor(Color.parseColor(viewTextColor));
    }

    public void setTextViewTextSize(int size){
        //mTextView.setTextSize(size);
    }


}
