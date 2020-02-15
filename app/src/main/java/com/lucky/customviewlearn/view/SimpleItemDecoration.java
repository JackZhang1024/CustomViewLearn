package com.lucky.customviewlearn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.core.DisplayUtil;

public class SimpleItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "SimpleItemDecoration";
    private int dividerHeight;
    private Paint dividerPaint;
    private int leftRightMargin;

    public SimpleItemDecoration(Context context) {
        dividerPaint = new Paint();
        dividerPaint.setColor(context.getResources().getColor(R.color.divider_line));
        dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.simple_divier_height);
        leftRightMargin = DisplayUtil.dp2px(context, 10);
    }


    public void setLeftMargin(int leftMargin){
        leftRightMargin = leftMargin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft() + leftRightMargin;
        int right = parent.getWidth() - parent.getPaddingRight() - leftRightMargin;

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }


}
