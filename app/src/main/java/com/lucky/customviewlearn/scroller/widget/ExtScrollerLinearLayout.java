package com.lucky.customviewlearn.scroller.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * 向左滑动 是指的从右向左滑动 箭头朝左
 * 向右滑动 是指的从左向右滑动 箭头向右
 * */
public class ExtScrollerLinearLayout extends LinearLayout {

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mMaxFlingVelocity;
    private static int SNAP_VELOCITY = 600;
    private float lastX, lastY, downX, downY;

    public ExtScrollerLinearLayout(Context context) {
        super(context);
    }

    public ExtScrollerLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        mMaxFlingVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public ExtScrollerLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        System.out.println("width "+w+" height "+h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerId = event.getPointerId(0);
        float currentX = event.getX();
        float currentY = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = getScrollX();
                downY = getScrollY();

                lastX = event.getX();
                lastY = event.getY();

                System.out.println("downX "+downX+" downY "+downY+" lastX "+lastX+ "lastY "+lastY);

                break;
            case MotionEvent.ACTION_MOVE:
                // 计算当前位置于上次手指触摸位置的距离
                float dx = currentX - lastX;
                float dy = currentY - lastY;

                // 对View中的Content进行位移
                scrollBy(-(int)dx, -(int)dy);
                // 重新设置上次手指触摸的位置坐标
                lastX = currentX;
                lastY = currentY;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                // XY从滑动的距离返回到初始位置
                // getScrollX()返回的是当前content相对于初始位置的在X轴上的位移
                // getScrollY()返回的是当前content相对于初始位置的在Y轴上的位移
                // -getScrollX(),-getScrollY() 是当前content返回到初始位置的所要进行的位移
                // 1000是整个内容滚动动画所经历的时间 默认250ms
                mScroller.startScroll(getScrollX(), getScrollY(), -getScrollX(), -getScrollY(), 1000);
                // invalidate()方法调用computeScroll()方法
                invalidate();
                getVelocityTracker();
                mVelocityTracker.computeCurrentVelocity(1000, mMaxFlingVelocity);
                float vX = mVelocityTracker.getXVelocity(pointerId);
                if (vX > SNAP_VELOCITY){

                }else if (vX < -SNAP_VELOCITY){

                }else{

                }
                break;
        }
        return true;
    }

    private void getVelocityTracker(){
        if (mVelocityTracker == null){
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void startScroll(){
        int destX = 0;
        if (mScroller.getStartX() - mScroller.getFinalX() > 0){//向右滑动
            destX = mScroller.getFinalX() - 100;
        }else{//向左滑动
            destX = mScroller.getFinalX() + 100;
        }
        smoothScrollTo(destX, 0);

    }

    private void scrollTo(int x){
        if (x < 0){
            scrollTo(0, 0);
        }else {
            scrollTo(x , 0);
        }
    }

    private void smoothScrollTo(int destx, int desty) {
        int dx = destx - mScroller.getFinalX();
        int dy = desty - mScroller.getFinalY();
        smoothScrollBy(dx, dy);
    }


    private void smoothScrollBy(int dx, int dy) {
        System.out.println("dx "+dx);
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy, 300);
        invalidate();
    }

    @Override
    public void computeScroll() {
        // scroller.computeScrollOffset() 判断滚动是否已经完成
        // true表示动画仍在继续 false表示动画结束
        if (mScroller.computeScrollOffset()) {
            // scroller.getCurrX(), scroller.getCurrY() 分别获取的是当前content滚动到的位置
            // scrollTo()方法将content滚动到指定的位置
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // invalidate() 方法调用computeScroll()方法 呈现出动画效果
            invalidate();
        }
        super.computeScroll();
    }
}
