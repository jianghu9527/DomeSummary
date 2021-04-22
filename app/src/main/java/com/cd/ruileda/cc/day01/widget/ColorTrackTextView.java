package com.cd.ruileda.cc.day01.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.icu.text.RelativeDateTimeFormatter;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.cd.ruileda.cc.day01.R;


/**
 * 字体颜色切换
 */
public class ColorTrackTextView  extends TextView {


    //实现一个文字两种颜色   不变色字体
    Paint   mOriginPaint;
    //实现一个文字两种颜色   变色字体
    Paint   mChangePaint;

//    两种颜色变化  进度
    float mCurrentProgress=0f;

    // 2.实现不同朝向
    private Direction mDirection = Direction.LEFT_TO_RIGHT;

    public enum Direction{
        LEFT_TO_RIGHT,RIGHT_TO_LEFT
    }



    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context, attrs);

    }

    private  void initPaint(Context context,AttributeSet attrs){


        TypedArray  array= context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);

        int   changeColor=   array.getColor(R.styleable.ColorTrackTextView_changeColor,getTextColors().getDefaultColor());
      int  originColor  =  array.getColor(R.styleable.ColorTrackTextView_originColor,getTextColors().getDefaultColor());

        mOriginPaint = getPaintByColor(originColor);
        mChangePaint = getPaintByColor(changeColor);

        // 回收
        array.recycle();

    }

    /**
     * 1.根据颜色获取画笔
     */
    private Paint getPaintByColor(int color) {
        Paint paint = new Paint();
        // 设置颜色
        paint.setColor(color);
        // 设置抗锯齿
        paint.setAntiAlias(true);
        // 防抖动
        paint.setDither(true);
        // 设置字体的大小  就是TextView的字体大小
        paint.setTextSize(getTextSize());
        return paint;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
           String text=  getText().toString();
        Rect   bounds=new Rect();
        mChangePaint.getTextBounds(text,0,text.length(),bounds);
        //字体宽度
            int x=   getWidth()/2-bounds.width()/2;
            Paint.FontMetricsInt fontMetrics=mChangePaint.getFontMetricsInt();

            int dy= (fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom;
               int baseLine=   getHeight()/2+dy;
        canvas.drawText(text,x,baseLine,mChangePaint);




    }
}
