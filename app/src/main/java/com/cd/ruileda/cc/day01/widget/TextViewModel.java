package com.cd.ruileda.cc.day01.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.cd.ruileda.cc.day01.R;


/**
 * 视频
 */
//public class TextViewModel extends View {
public class TextViewModel extends LinearLayout {

    String mtext="TextViewModelmtext";
    int mtextSize=15;
    int mtextColor= Color.RED;

    Paint mpaint;

    public TextViewModel(Context context) {
//        super(context);
        this(context,null);
    }

    public TextViewModel(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
        this(context,attrs,0);
    }

    public TextViewModel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
     TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.TextViewModel);

        mtext= array.getString(R.styleable.TextViewModel_text);
        mtextColor=array.getColor(R.styleable.TextViewModel_textColor,mtextColor);
        mtextSize=  array.getDimensionPixelSize(R.styleable.TextViewModel_textSize,mtextSize);

        array.recycle();
        mpaint=new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setTextSize(mtextSize);
        mpaint.setColor(mtextColor);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

      switch (event.getAction()){
          case MotionEvent.ACTION_DOWN:

              Log.d("-------------","-------按下------");
              break;
          case MotionEvent.ACTION_MOVE:
              Log.d("-------------","-------移动------");
              break;
          case MotionEvent.ACTION_UP:
              Log.d("-------------","-------抬起------");
              break;
      }



        return super.onTouchEvent(event);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

         int   widthMode= MeasureSpec.getMode(widthMeasureSpec);
        int   heightMode= MeasureSpec.getMode(heightMeasureSpec);

        int  heightSize=  MeasureSpec.getSize(heightMeasureSpec);
        int  widthSize=  MeasureSpec.getSize(widthMeasureSpec);



        Log.d("---------------","------mtext---------"+mtext);

        if (widthMode==MeasureSpec.AT_MOST){
            Rect  bounds=new Rect();
            mpaint.getTextBounds(mtext,0,mtext.length(),bounds);
            widthSize=bounds.width()+getPaddingLeft()+getPaddingRight();

        }

        if (heightMode==MeasureSpec.AT_MOST){
            Rect  bounds=new Rect();
            mpaint.getTextBounds(mtext,0,mtext.length(),bounds);
            heightSize=bounds.height()+getPaddingTop()+getPaddingBottom();
        }

        setMeasuredDimension(widthSize,heightSize);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawText();
//        canvas.drawArc();//圆弧
//        canvas.drawCircle();//画园


        Paint.FontMetricsInt fontMetrics=mpaint.getFontMetricsInt();

         int dy= (fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom;
          int BaseLine=getHeight()/2  +dy;

          Log.d("-----------------","-------getHeight()---------"+getHeight());
        Log.d("-----------------","--------BaseLine---------"+BaseLine);
        Log.d("-----------------","----------dy-------"+dy);

        Log.d("-----------------","----------getPaddingLeft()-------"+getPaddingLeft());
        canvas.drawText(mtext,getPaddingLeft(),BaseLine,mpaint);



    }
}
