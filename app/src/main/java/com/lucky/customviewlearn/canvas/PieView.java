package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by zfz on 2017/7/4.
 * 饼状图
 */

public class PieView extends View {

    private Paint mPaint;
    private int[] colors = new int[]{Color.BLACK, Color.RED, Color.GREEN, Color.GRAY};
    private int mWidth, mHeight;
    private List<PieData> pieDataList;
    private float mStartAngle;

    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint(){
        //初始化画笔
        //设置画笔的样式
        //Paint.Style.Fill 填充颜色
        //Paint.Style.Stroke 描边
        //Paint.Style.FILL_AND_STROKE 填充颜色和描边
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //确定最终的自定义View的宽高
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (pieDataList == null) return;
        float currentAngle = mStartAngle;
        // 将坐标原点移动到画布的中心点位置
        canvas.translate(mWidth/2, mHeight/2);
        // 计算出合适的半径
        float r = (float) (Math.min(mWidth, mHeight)/2*0.8) ;
        for (int index =0 ; index< pieDataList.size(); index++){
             RectF rectF = new RectF(-r,-r, r, r);
             PieData pieData = pieDataList.get(index);
             mPaint.setColor(colors[index%4]);
            // currentAngle是开始的角度 pieData.getAngle获取的是扫过的角度
             canvas.drawArc(rectF, currentAngle, pieData.getAngle(), true, mPaint);
             currentAngle += pieData.getAngle();
        }

    }

    public void setStartAngle(float startAngle){
        this.mStartAngle = startAngle;
        invalidate();
    }

    public void setPieDataList(List<PieData> pieDatas){
        this.pieDataList = pieDatas;
        initPieDataList();
    }

    public void initPieDataList(){
        float sumValue = 0;
        for (PieData data: pieDataList){
            // 计算所有数据的和
             sumValue += data.getValue();
        }
        for (PieData data: pieDataList){
            // 计算每个PieData的百分比
             float percentage = data.getValue()/sumValue;
             data.setPercentage(percentage);
            // 计算每个PieData的角度
             float angle = 360*percentage;
             data.setAngle(angle);
        }
        invalidate();
    }
}
