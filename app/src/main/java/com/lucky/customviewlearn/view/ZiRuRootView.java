package com.lucky.customviewlearn.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import java.util.HashMap;
import java.util.Map;

/**
 * 根布局
 */
public final class ZiRuRootView extends ScrollView {
    private Map<String, View> mViewWithID = new HashMap<>();
    private FrameLayout mContentView;

    public ZiRuRootView(@NonNull Context context){
        super(context);
        mContentView = new FrameLayout(context);
      //  super.addView(mContentView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }

    public void addContent(View view, ViewGroup.LayoutParams layoutParams){
        mContentView.addView(view, layoutParams);
    }

    public void putViewWithId(View view, String viewId){
        mViewWithID.put(viewId, view);
    }

    public View getViewById(String viewId){
        return mViewWithID.get(viewId);
    }

    public boolean containsView(String viewId){
        return mViewWithID.containsKey(viewId);
    }

}
