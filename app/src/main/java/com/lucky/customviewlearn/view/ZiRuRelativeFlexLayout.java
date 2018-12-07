package com.lucky.customviewlearn.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.JustifyContent;

import org.json.JSONObject;

/**
 * * FlexBoxLayout
 * 默认属性
 * flex-direction:row
 * flex-wrap：nowrap
 * justify-content：flex-start
 * align-items：stretch
 * align-content：stretch
 * order：0
 * flex-grow：0
 * flex-shrink：1
 * flex-basis：auto
 * align-self:auto
 */
public class ZiRuRelativeFlexLayout extends ZiRuRelativeLayout {
    private FlexboxLayout.LayoutParams mFlexLayoutParams = null;
    private RelativeLayout.LayoutParams mRelativeLayoutParams = null;
    private FlexboxLayout mFlexBoxLayout;

    public ZiRuRelativeFlexLayout(Context context) {
        this(context, null);
    }

    public ZiRuRelativeFlexLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRelativeLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mFlexBoxLayout = new FlexboxLayout(context);
        mFlexLayoutParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mFlexBoxLayout.setLayoutParams(mFlexLayoutParams);
        addView(mFlexBoxLayout, mRelativeLayoutParams);
    }

    // 根据position来决定创建什么参数 用于addView
    // outerLayoutParams来决定用什么尺寸展示ZiRuRelativeFlexLayout
    public void setLayoutParams(int width, int height, ViewGroup.LayoutParams outerLayoutParams) {
        // 这块是控制了相对布局中的Flex布局的宽高
        mRelativeLayoutParams.width = width;
        mRelativeLayoutParams.height = height;
        outerLayoutParams.width = width;
        outerLayoutParams.height = height;
    }

    public void setFlexLayoutParams(int direction, String justifyContent){
        mFlexBoxLayout.setFlexDirection(FlexDirection.ROW);
        mFlexBoxLayout.setJustifyContent(JustifyContent.SPACE_AROUND);
    }

    public void addView(String position, View view, ViewGroup.LayoutParams layoutParams) {
        if (!TextUtils.isEmpty(position)) {
            //添加到相对布局
            addView(view, -1, layoutParams);
        } else {
            //添加到FlexBoxLayout
            mFlexBoxLayout.addView(view, layoutParams);
        }
    }
}
