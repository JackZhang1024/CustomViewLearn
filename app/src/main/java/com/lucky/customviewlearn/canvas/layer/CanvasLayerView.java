package com.lucky.customviewlearn.canvas.layer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zfz on 2017/8/2.
 * <p>
 * canvas的图层学习
 * <p>
 * Canvas 在一般的情况下可以看作是一张画布，所有的绘图操作如drawBitmap, drawCircle都发生在这张画布上，这张画板还定义了一些属性比如Matrix，颜色等等。
 * 但是如果需要实现一些相对复杂的绘图操作，比如多层动 画，地图（地图可以有多个地图层叠加而成，比如：政区层，道路层，兴趣点层）。Canvas提供了图层（Layer）支持，
 * 缺省情况可以看作是只有一个图 层Layer。如果需要按层次来绘图，Android的Canvas可以使用SaveLayerXXX, Restore 来创建一些中间层，对于这些Layer是按照“栈结构“来管理的：
 * 创建一个新的Layer到“栈”中，可以使用saveLayer, savaLayerAlpha, 从“栈”中推出一个Layer，可以使用restore,restoreToCount。
 * 但Layer入栈时，后续的DrawXXX操作都发生在这个 Layer上，而Layer退栈时，就会把本层绘制的图像“绘制”到上层或是Canvas上，
 * 在复制Layer到Canvas上时，可以指定Layer的 透明度(Layer），这是在创建Layer时指定的：
 * public int saveLayerAlpha(RectF bounds, int alpha, int saveFlags)
 * 本例Layers 介绍了图层的基本用法：Canvas可以看做是由两个图层（Layer）构成的，
 * 为了更好的说明问题，我们将代码稍微修改一下，缺省图层绘制一个红色的 圆，在新的图层画一个蓝色的圆，新图层的透明度为0×88。
 */

public class CanvasLayerView extends View {
    private Paint mRedCirclePaint;
    private Paint mBlueCirclePaint;
    private int saveFlags = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG |
            Canvas.CLIP_TO_LAYER_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
            Canvas.FULL_COLOR_LAYER_SAVE_FLAG;

    public CanvasLayerView(Context context) {
        this(context, null);
    }

    public CanvasLayerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public CanvasLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mRedCirclePaint = new Paint();
        mRedCirclePaint.setStyle(Paint.Style.FILL);
        mRedCirclePaint.setColor(Color.RED);
        mRedCirclePaint.setAntiAlias(true);

        mBlueCirclePaint = new Paint();
        mBlueCirclePaint.setStyle(Paint.Style.FILL);
        mBlueCirclePaint.setColor(Color.BLUE);
        mBlueCirclePaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.translate(100, 100);
        canvas.drawCircle(50, 50, 75, mRedCirclePaint);


        // canvas.saveLayerAlpha()方法创建了一个新的图层Layer放入栈中
        // 后续的画蓝色圆操作也是在这个Layer中进行的
        // 当我们执行restore方法的时候 蓝色的圆所在Layer就在红色Layer上面了
        //canvas.saveLayerAlpha(0, 0, 200, 200, 0x88, saveFlags); // 半透明
        //canvas.saveLayerAlpha(0, 0, 200, 200, 255, saveFlags); //不透明
        canvas.saveLayerAlpha(0, 0, 200, 200, 0, saveFlags); //完全透明
        canvas.drawCircle(125, 125, 75, mBlueCirclePaint);
        canvas.restore();
    }
}
