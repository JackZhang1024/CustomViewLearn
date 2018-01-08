package com.lucky.customviewlearn.scroller.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by zfz on 2018/1/3.
 */

public class AdvancedDragView extends View {
    private static final String TAG = "DragView";
    private int lastX, lastY;
    private Scroller mScroller;

    public AdvancedDragView(Context context) {
        super(context);
        initView(context);
    }

    public AdvancedDragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AdvancedDragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);

    }

    private void initView(Context context){
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                ViewGroup parent = (ViewGroup) getParent();
                Log.e(TAG, "onTouchEvent: Down getScrollX() "+parent.getScrollX()+" scrollY() "+parent.getScrollY());
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                // scrollBy移动的是父布局的内容(也就是所有的childView)
                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                break;
            case MotionEvent.ACTION_UP:
                // 利用Scroller scroller和scrollTo scrollBy的区别在于 一个是平滑移动 一个是瞬间移动
                ViewGroup viewgroup = (ViewGroup) getParent();
                Log.e(TAG, "moveToStart: viewGroup.getScrollX() " + viewgroup.getScrollX() + " viewGroup.getScrollY() " + viewgroup.getScrollY());
                // dx dy和坐标轴正方向相同(即 dx>0 dy>0) 则向坐标轴反方向移动(即沿着x轴向左移动 沿着y轴向上移动)
                // dx dy和坐标轴正方向相反(即 dx<0 dy<0) 则向坐标轴正方向移动(即沿着x轴向右移动 沿着y轴向下移动)
                mScroller.startScroll(viewgroup.getScrollX(), viewgroup.getScrollY(), -viewgroup.getScrollX(), -viewgroup.getScrollY(), 200);
                invalidate();
                break;
        }
        return true;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            //mScroller.getCurrX() mScroller.getCurrY() 获取到的是滑动坐标
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 通过重绘来不断调用computeScroll()方法
            invalidate();
        }
    }
}
