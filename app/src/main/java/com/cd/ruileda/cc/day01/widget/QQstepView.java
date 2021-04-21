package com.cd.ruileda.cc.day01.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.cd.ruileda.cc.day01.R;

/**
 * https://www.bilibili.com/video/BV15K411L7gU?p=10
 */
public class QQstepView extends View {


    int moutcolor= Color.RED;
    int mincolor= Color.BLUE;
    int mstepTextcolor= Color.YELLOW;

    int mborderWidth= 20;
    int mstepTextSize= 20;


    int mStepMax=4000;
    int mCurrentStep=3000;
    Paint  moutpian;
    Paint  minnerPiant;
    Paint  mTextPiant;

    public QQstepView(Context context) {
       this (context, null);
    }

    public QQstepView(Context context, @Nullable AttributeSet attrs) {
        this (context, attrs,0);
    }

    public QQstepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray   array= context.obtainStyledAttributes(attrs, R.styleable.QQstepView);
        mborderWidth=(int) array.getDimension(R.styleable.QQstepView_borderWidth,mborderWidth);
        mstepTextSize=array.getDimensionPixelSize(R.styleable.QQstepView_stepTextSize,mstepTextSize);

        mstepTextcolor=array.getColor(R.styleable.QQstepView_stepTextcolor,mstepTextcolor);
        mincolor= array.getColor(R.styleable.QQstepView_incolor,mincolor);
        moutcolor= array.getColor(R.styleable.QQstepView_outcolor,moutcolor);

        array.recycle();

        moutpian=new Paint();
        moutpian.setAntiAlias(true);
        moutpian.setStrokeWidth(mborderWidth);
        moutpian.setColor(moutcolor);
        moutpian.setStrokeCap(Paint.Cap.ROUND);
        moutpian.setStyle(Paint.Style.STROKE);

        minnerPiant=new Paint();
        minnerPiant.setAntiAlias(true);
        minnerPiant.setStrokeWidth(mborderWidth);
        minnerPiant.setColor(mincolor);
        minnerPiant.setStrokeCap(Paint.Cap.ROUND);
        minnerPiant.setStyle(Paint.Style.STROKE);

        mTextPiant=new Paint();
        mTextPiant.setAntiAlias(true);
        mTextPiant.setColor(mstepTextcolor);
        mTextPiant.setTextSize(mstepTextSize);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width= MeasureSpec.getSize(widthMeasureSpec);
        int height= MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width>height?height:width,width>height?height:width);

    }


    // 画外圆弧   内圆弧  文字
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

          int Center=  getWidth()/2;
          int radius=getWidth()/2-mborderWidth;
          RectF  rectF=new RectF(mborderWidth/2,mborderWidth/2,
                getWidth()-mborderWidth/2,getHeight()-mborderWidth/2) ;
         canvas.drawArc(rectF,135, 270,false,moutpian);



         if (mStepMax==0)return;
         float sweepagle=(float)mCurrentStep/mStepMax;
         canvas.drawArc(rectF,135,sweepagle*270,false,minnerPiant);



        //文字
        String setptext=mCurrentStep+"";
        Rect  textBounds=new Rect();
        mTextPiant.getTextBounds(setptext,0,setptext.length(),textBounds);
        Paint.FontMetricsInt fontMetrics=  mTextPiant.getFontMetricsInt();
//        int dy=  getWidth()/2-textBounds.width()/2;
        int dy=(fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom;

        int baseline=getHeight()/2+dy;

       int dx=getWidth()/2-textBounds.width()/2;
       canvas.drawText(setptext,dx,baseline,mTextPiant);



    }

    public synchronized void seStepMax(int  StepMax){
        this.mStepMax=StepMax;

    }
    public  synchronized void seCurrentStep(int  CurrentStep){
        this.mCurrentStep=CurrentStep;
        invalidate();

    }

}
