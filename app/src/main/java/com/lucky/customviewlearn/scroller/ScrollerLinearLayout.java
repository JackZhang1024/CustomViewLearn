package com.lucky.customviewlearn.scroller;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.Scroller;

/**
 * <p>This class encapsulates scrolling. You can use scrollers ({@link Scroller}
 * or {@link OverScroller}) to collect the data you need to produce a scrolling
 * animation&mdash;for example, in response to a fling gesture. Scrollers track
 * scroll offsets for you over time, but they don't automatically apply those
 * positions to your view. It's your responsibility to get and apply new
 * coordinates at a rate that will make the scrolling animation look smooth.</p>
 * <p>
 * <p>Here is a simple example:</p>
 * <p>
 * <pre> private Scroller mScroller = new Scroller(context);
 * ...
 * public void zoomIn() {
 *     // Revert any animation currently in progress
 *     mScroller.forceFinished(true);
 *     // Start scrolling by providing a starting point and
 *     // the distance to travel
 *     mScroller.startScroll(0, 0, 100, 0);
 *     // Invalidate to request a redraw
 *     invalidate();
 * }</pre>
 * <p>
 * <p>To track the changing positions of the x/y coordinates, use
 * {#computeScrollOffset}. The method returns a boolean to indicate
 * whether the scroller is finished. If it isn't, it means that a fling or
 * programmatic pan operation is still in progress. You can use this method to
 * find the current offsets of the x and y coordinates, for example:</p>
 * <p>
 * if (mScroller.computeScrollOffset()) {
 * // Get current x and y positions
 * int currX = mScroller.getCurrX();
 * int currY = mScroller.getCurrY();
 * ...
 * }
 * <p>
 * *
 */


/**
 * 向左滑动 是指的从右向左滑动 箭头朝左
 * 向右滑动 是指的从左向右滑动 箭头向右
 * */
public class ScrollerLinearLayout extends LinearLayout {

    private Scroller mScroller;
    private int mLastX;
    private VelocityTracker mVelocityTracker;
    private int mMaxFlingVelocity;
    private static int SNAP_VELOCITY = 600;

    public ScrollerLinearLayout(Context context) {
        super(context);
    }

    public ScrollerLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        mMaxFlingVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    public ScrollerLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerId = event.getPointerId(0);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int nextScrollX =(int)(mLastX -event.getX()+ getScrollX());
                scrollTo(nextScrollX);
                mLastX = (int) event.getX();
                break;

            case MotionEvent.ACTION_UP:
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


    // dx < 0 向右滑动 dx > 0 向左滑动
    // dy < 0 向下滑动 dy > 0 向上滑动
    private void smoothScrollBy(int dx, int dy) {
        System.out.println("dx "+dx);
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy, 300);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
        super.computeScroll();
    }
}
