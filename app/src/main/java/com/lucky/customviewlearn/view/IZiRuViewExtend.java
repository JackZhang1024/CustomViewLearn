package com.lucky.customviewlearn.view;

import android.graphics.drawable.ShapeDrawable;

public interface IZiRuViewExtend {

    float[] getOutRadius();

    void setLeftTopCorner(boolean leftTopCorner);

    void setRightTopCorner(boolean rightTopCorner);

    void setLeftBottomCorner(boolean leftBottomCorner);

    void setRightBottomCorner(boolean rightBottomCorner);

    // 矩形边框
    void setDrawRect(boolean drawRect);

    // 实线边框
    void setDrawRoundCornerRect(boolean drawRoundCornerRect);

    // 实线填充布局
    void setDrawSolidRoundCornerRect(boolean drawSolidRoundRect);

    ShapeDrawable getShapeDrawable();

    void setColor(String color);

    void setBorderWidth(int borderWidth);

    void setBorder(int borderWidth, String color);

    void setDrawTopSide(boolean mDrawTopSide);

    void setDrawLeftOutSide(boolean mDrawLeftOutSide);

    void setDrawRightSide(boolean mDrawRightSide);

    void setDrawBottomSide(boolean mDrawBottomSide);

}
