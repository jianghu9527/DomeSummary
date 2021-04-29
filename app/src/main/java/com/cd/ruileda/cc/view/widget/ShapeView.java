package com.cd.ruileda.cc.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Email 240336124@qq.com
 * Created by Darren on 2017/5/27.
 * Version 1.0
 * Description:
 */
public class ShapeView extends View {
    private Shape mCurrentShape = Shape.Circle;
    Paint mPaint;
    private Path mPath;

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 只保证是正方形
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        switch (mCurrentShape) {
            case Circle:
                // 画圆形
                int center = getWidth() / 2;
                mPaint.setColor(Color.YELLOW);
                canvas.drawCircle(center, center, center, mPaint);
                break;
            case Square:
                // 画正方形
                mPaint.setColor(Color.BLUE);
                canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
                break;
            case Triangle:
                // 画三角  Path 画路线
                mPaint.setColor(Color.RED);
                if (mPath == null) {
                    // 画路径
                    mPath = new Path();
                    mPath.moveTo(getWidth() / 2, 0);
                    mPath.lineTo(0, (float) ((getWidth()/2)*Math.sqrt(3)));
                    mPath.lineTo(getWidth(), (float) ((getWidth()/2)*Math.sqrt(3)));
                    // path.lineTo(getWidth()/2,0);
                    mPath.close();// 把路径闭合
                }
                canvas.drawPath(mPath, mPaint);
                break;
        }
    }

    public void exchange() {
        switch (mCurrentShape) {
            case Circle://圆形
                mCurrentShape = Shape.Square;
                break;
            case Square://四边形
                mCurrentShape = Shape.Triangle;
                break;
            case Triangle://
                // 画三角  Path 画路线
                mCurrentShape = Shape.Circle;
                break;
        }
        invalidate();
    }

    public enum Shape {
        Circle, Square, Triangle
    }

    public Shape getCurrentShape() {
        return mCurrentShape;
    }
}
