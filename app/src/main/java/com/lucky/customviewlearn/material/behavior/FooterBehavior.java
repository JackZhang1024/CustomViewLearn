package com.lucky.customviewlearn.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;

import timber.log.Timber;


// 底部面本随着列表上滑而显示 随着列表下滑而隐藏
public class FooterBehavior extends CoordinatorLayout.Behavior<View> {

    private int directionChange;

    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child,
                                       @NonNull View directTargetChild, @NonNull View target, int axes) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                  @NonNull View child, @NonNull View target,
                                  int dx, int dy, @NonNull int[] consumed) {
        if (dy > 0 && directionChange < 0 || dy < 0 && directionChange > 0) {
            child.animate().cancel();
            directionChange = 0;
        }
        directionChange += dy;
        Timber.d("directionChange " + directionChange + " dy " + dy);
        if (directionChange > child.getHeight()
                && child.getVisibility() == View.VISIBLE) {
            // 向下滑 隐藏
            hide(child);
        } else if (directionChange < 0
                && child.getVisibility() == View.INVISIBLE) {
            // 向上滑 显示
            show(child);
        }

    }

    // 注意：隐藏操作不能用 GONE, 因为一旦GONE之后，就跟踪不了滚动事件了 所以就不会回调onNestedScroll事件
    // 源码分析
    // if (view.getVisibility() == GONE) {
    //                // If the child is GONE, skip...
    //                continue;
    // }
    private void hide(final View view) {
        Timber.d("隐藏.......");
        ViewPropertyAnimator animator = view.animate().translationY(view.getHeight())
                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
//                view.setVisibility(View.GONE);
                view.setVisibility(View.INVISIBLE);
            }
        });
        animator.start();
    }


    private void show(final View view) {
        Timber.d("显示......");
        ViewPropertyAnimator animator = view.animate()
                .translationY(0)
                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.VISIBLE);
            }
        });
        animator.start();
    }

}


