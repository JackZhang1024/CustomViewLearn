package com.lucky.customviewlearn.material.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class FooterBehaviorAppBar extends CoordinatorLayout.Behavior<View> {


    public FooterBehaviorAppBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.abs(dependency.getY());
        Log.e("transitionY ", " "+translationY);
        child.setTranslationY(translationY);
        return true;
    }


}
