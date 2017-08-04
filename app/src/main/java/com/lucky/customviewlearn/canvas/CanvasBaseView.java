package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zfz on 2017/8/3.
 */

public class CanvasBaseView extends View{

    public CanvasBaseView(Context context) {
        super(context);
    }

    public CanvasBaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasBaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
