package com.cd.ruileda.cc.view.widget;

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

import com.cd.ruileda.cc.view.R;

public class ProgressBarView extends View {

    int mInnerBackGround= Color.RED;
    int mOutBackGround= Color.RED;
    int mRoundWidth= 10;
    float mProgressTextSize=15;
    int mProgreessTextColor=Color.RED;

    Paint minnerPaint,moutPaint,mTextPaint;
    int mMax=100;
    int mProgress=30;


    public ProgressBarView(Context context) {
        this(context,null);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs) {
        this (context, attrs,0);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray   arrary= context.obtainStyledAttributes(attrs, R.styleable.ProgressBarView);

         mInnerBackGround= arrary.getColor(R.styleable.ProgressBarView_innerBackground,mInnerBackGround);
        mOutBackGround= arrary.getColor(R.styleable.ProgressBarView_outBackground,mOutBackGround);
        mRoundWidth= (int) arrary.getDimension(R.styleable.ProgressBarView_roundWidth,mRoundWidth);
        mProgressTextSize=arrary.getDimension(R.styleable.ProgressBarView_progressTextSize,mProgressTextSize);
        mProgreessTextColor= arrary.getColor(R.styleable.ProgressBarView_progressTextColor,mProgreessTextColor);
        arrary.recycle();


        minnerPaint=new Paint();
        minnerPaint.setAntiAlias(true);
        minnerPaint.setColor(mInnerBackGround);
        minnerPaint.setStrokeWidth(mRoundWidth);
        minnerPaint.setStyle(Paint.Style.STROKE);


        moutPaint=new Paint();
        moutPaint.setAntiAlias(true);
        moutPaint.setColor(mOutBackGround);
        moutPaint.setStrokeWidth(mRoundWidth);
        moutPaint.setStyle(Paint.Style.STROKE);


        mTextPaint=new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mProgreessTextColor);
        mTextPaint.setStrokeWidth(mRoundWidth);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(Math.min(width,height),Math.min(width,height));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       int center= getWidth()/2;
        canvas.drawCircle(center,center,center-mRoundWidth/2,minnerPaint);
        RectF rect=new RectF(0+mRoundWidth/2,0+mRoundWidth/2,getWidth()-mRoundWidth/2,getHeight()-mRoundWidth/2);
        if (mProgress==0){
            return;
        }
       float Current= mProgress/mMax;
        canvas.drawArc(rect,0,Current*360,false,moutPaint);


        String  text=Current*100+"%";
        Rect  textBound=new Rect();
        mTextPaint.getTextBounds(text,0,text.length(),textBound);

        Paint.FontMetricsInt  fontMetricsInt= mTextPaint.getFontMetricsInt();
      int dy=  (fontMetricsInt.bottom-fontMetricsInt.top)/2-fontMetricsInt.bottom;
      int x=  getWidth()/2-textBound.width()/2;
      int baselIne=getHeight()/2-dy;
        canvas.drawText(text,x,baselIne,mTextPaint);


    }
}
