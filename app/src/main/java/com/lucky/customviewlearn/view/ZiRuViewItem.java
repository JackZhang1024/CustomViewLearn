package com.lucky.customviewlearn.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ZiRuViewItem extends ZiRuLinearLayout {
    private static final String TAG = "ZiRuViewItem";
    private Context mContext;
    private int mResID = 1001;
    private LinearLayout mContentView;
    public ZiRuViewItem(Context context) {
        this(context, null);
    }

    public ZiRuViewItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mContentView = new LinearLayout(context);
        addView(mContentView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200));
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width  = 0;
        int height = 0;
        int length = getChildCount();
        setMeasuredDimension(widthSize, 200);
    }

    public void addItemView(View view, int viewId) {
        TextView textView = new TextView(mContext);
        textView.setTextSize(20);
        textView.setId(mResID);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100);
        mContentView.addView(textView, lp);
    }


    public void setItemData(String sourceData) {
        Log.e(TAG, "setItemData: "+sourceData);
        TextView textView = (TextView) findViewById(mResID);
        textView.setText(sourceData);
    }

    // 创建模板布局
    public void createItemTemplate(String templateData){


    }


}
