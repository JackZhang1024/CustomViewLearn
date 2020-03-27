package com.lucky.customviewlearn.scroller.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

public class SmartScrollView extends ScrollView {

    private static final String TAG = "SmartScrollView";
    private boolean isScrollToTop = true;
    private boolean isScrollToBottom = false;

    public SmartScrollView(Context context) {
        super(context);
    }

    public SmartScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // onOverScrolled API 9 以上才支持
    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (scrollY == 0) {
            isScrollToTop = clampedY;
            isScrollToBottom = false;
            if (isScrollToTop) {
                Log.e(TAG, "onOverScrolled: 我到最顶部了");
            }
        } else {
            isScrollToTop = false;
            isScrollToBottom = clampedY;
            if (isScrollToBottom) {
                Log.e(TAG, "onOverScrolled: 我到最底部了");
            }
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (Build.VERSION.SDK_INT < 9) {
            if (getScrollY() == 0) {
                isScrollToTop = true;
                isScrollToBottom = false;
            } else if (getScrollY() + getHeight() == getChildAt(0).getHeight()) {
                isScrollToBottom = true;
                isScrollToTop = false;
            } else {
                isScrollToTop = false;
                isScrollToBottom = false;
            }
        }
    }


}
