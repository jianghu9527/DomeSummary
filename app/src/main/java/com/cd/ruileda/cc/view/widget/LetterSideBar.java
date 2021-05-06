package com.cd.ruileda.cc.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Email 240336124@qq.com
 * Created by Darren on 2017/6/3.
 * Version 1.0
 * Description:
 */
public class LetterSideBar extends View {
    private Paint mPaint;
    // 定义26个字母
    public static String[] mLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};

    // 当前触摸的位置字母
    private String mCurrentTouchLetter;

    public LetterSideBar(Context context) {
        this(context, null);
    }

    public LetterSideBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterSideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        // 自定义属性，颜色  字体大小
        mPaint.setTextSize(sp2px(12));// 设置的是像素
        // 默认颜色
        mPaint.setColor(Color.BLUE);
    }

    // sp 转 px
    private float sp2px(int sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 计算指定宽度 = 左右的padding + 字母的宽度(取决于你的画笔)
        int textWidth = (int) mPaint.measureText("A");// A字母的宽度
        int width = getPaddingLeft() + getPaddingRight() + textWidth;
        // 高度可以直接获取
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 画26个字母

        int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;

        for (int i = 0; i < mLetters.length; i++) {
            // 知道每个字母的中心位置  1  字母的高度一半   2 字母高度一般+前面字符的高度
            int letterCenterY = i * itemHeight + itemHeight / 2 + getPaddingTop();
            // 基线，基于中心位置, 知道中心位置还不会基线，看一下之前的视频
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            int dy = (int) ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);
            int baseLine = letterCenterY + dy;
            // x 绘制在最中间 = 宽度/2 - 文字/2
            int textWidth = (int) mPaint.measureText(mLetters[i]);
            int x = getWidth() / 2 - textWidth / 2;

            // 当前字母 高亮  用两个画笔(最好) 改变颜色
            if (mLetters[i].equals(mCurrentTouchLetter)) {
                mPaint.setColor(Color.RED);
                canvas.drawText(mLetters[i], x, baseLine, mPaint);
            } else {
                mPaint.setColor(Color.BLUE);
                canvas.drawText(mLetters[i], x, baseLine, mPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            // case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                // 计算出当前触摸字母  获取当前的位置
                float currentMoveY = event.getY();
                // 位置 = currentMoveY / 字母高度 ， 通过位置获取字母  优化？
                int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;
                int currentPosition = (int) (currentMoveY / itemHeight);

                if (currentPosition < 0)
                    currentPosition = 0;

                if (currentPosition > mLetters.length - 1)
                    currentPosition = mLetters.length - 1;

                // 要判断 ？

                mCurrentTouchLetter = mLetters[currentPosition];

                if (mListener != null) {
                    mListener.touch(mCurrentTouchLetter, true);
                }

                // 重新绘制
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (mListener != null) {
                    mListener.touch(mCurrentTouchLetter, false);
                }
                break;
        }
        return true;
    }

    private LetterTouchListener mListener;

    public void setOnLetterTouchListener(LetterTouchListener listener) {
        this.mListener = listener;
    }

    // 接口回掉其他View会不会使用？
    public interface LetterTouchListener {
        void touch(CharSequence letter, boolean isTouch);
    }
}
