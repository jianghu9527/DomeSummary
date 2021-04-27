package com.cd.ruileda.cc.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.cd.ruileda.cc.view.R;

/**
 */
public class RatingBar extends View {
    private static final String TAG = "RatingBar";

    private Bitmap mStarNormalBitmap, mStarFocusBitmap;
    private int mGradeNumber = 5;

    private int mCurrentGrade = 0;

    public RatingBar(Context context) {
        this(context, null);
    }

    public RatingBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RatingBar);
        int starNormalId = array.getResourceId(R.styleable.RatingBar_starNormal, 0);
        if (starNormalId == 0) {
            throw new RuntimeException("请设置属性 starNormal ");
        }

        mStarNormalBitmap = BitmapFactory.decodeResource(getResources(), starNormalId);

        int starFocusId = array.getResourceId(R.styleable.RatingBar_starFocus, 0);
        if (starFocusId == 0) {
            throw new RuntimeException("请设置属性 starFocus ");
        }

        mStarFocusBitmap = BitmapFactory.decodeResource(getResources(), starFocusId);

        mGradeNumber = array.getInt(R.styleable.RatingBar_gradeNumber, mGradeNumber);

        array.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 高度  一张图片的高度， 自己去实现 padding  + 加上间隔
        int height = mStarFocusBitmap.getHeight();
        int width = mStarFocusBitmap.getWidth() * mGradeNumber;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < mGradeNumber; i++) {
            // i*星星的宽度
            int x = i * mStarFocusBitmap.getWidth();

            // 结合第二个步骤 触摸的时候mCurrentGrade值是不断变化
            if(mCurrentGrade>i){// 1  01
                // 当前分数之前
                canvas.drawBitmap(mStarFocusBitmap, x, 0, null);
            }else{
                canvas.drawBitmap(mStarNormalBitmap, x, 0, null);
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 移动 按下 抬起 处理逻辑都是一样，判断手指的位置，根据当前位置计算出分数，再去刷新显示
        Log.e(TAG, "moveXE -> " + event.getAction() +"");
        switch (event.getAction()) {
            // case MotionEvent.ACTION_DOWN: // 按下 尽量减少onDraw()的调用
            case MotionEvent.ACTION_MOVE: // 移动
            // case MotionEvent.ACTION_UP: // 抬起 尽量减少onDraw()的调用
                float moveX = event.getX();//event.getX()相对于当前控件的位置   event.getRawX()获取幕的x位置
                // Log.e(TAG, "moveX -> " + moveX +"");
                // 计算分数
                int currentGrade = (int) (moveX/mStarFocusBitmap.getWidth()+1);

                // 范围问题
                if(currentGrade<0){
                    currentGrade = 0;
                }
                if(currentGrade>mGradeNumber){
                    currentGrade = mGradeNumber;
                }
                // 分数相同的情况下不要绘制了 , 尽量减少onDraw()的调用
                if(currentGrade == mCurrentGrade){
                    return true;
                }

                // 再去刷新显示
                mCurrentGrade = currentGrade;
                invalidate();// onDraw()  尽量减少onDraw()的调用，目前是不断调用，怎么减少？
                break;
        }
        return true;// onTouch 后面看源码（2天,3个小时） false 不消费 第一次 DOWN false DOWN以后的事件是进不来的
    }
}
