package com.cd.ruileda.cc.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by hcDarren on 2017/7/30.
 */

public class MessageBubbleView extends View {
    // 两个圆的圆形
    private PointF mFixationPoint, mDragPoint;
    // 拖拽圆的半径
    private int mDragRadius = 10;
    // 画笔
    private Paint mPaint;
    // 固定圆的最大半径（初始半径）
    private int mFixationRadiusMax = 7;
    private int mFixationRadiusMin = 3;
    private int mFixationRadius;

    public MessageBubbleView(Context context) {
        this(context, null);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragRadius = dip2px(mDragRadius);
        mFixationRadiusMax = dip2px(mFixationRadiusMax);
        mFixationRadiusMin = dip2px(mFixationRadiusMin);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mDragPoint == null || mFixationPoint == null) {
            return;
        }
        // 画两个圆
        // 拖拽圆 还有一个是拖拽圆半径是不变的位置是跟随我手指移动
        canvas.drawCircle(mDragPoint.x, mDragPoint.y, mDragRadius, mPaint);
        // 画固定圆  有一个初始化大小 而且他的半径是随着距离的增大而减小 小到一定层度就不见了（不画了）
        // 两个点的距离

        Path bezeierPath = getBezeierPath();
        if (bezeierPath!=null) {
            // 小到一定层度就不见了（不画了）
            canvas.drawCircle(mFixationPoint.x, mFixationPoint.y, mFixationRadius, mPaint);
            // 画贝塞尔曲线
            canvas.drawPath(bezeierPath, mPaint);
        }
    }

    /**
     * 获取两个圆之间的距离
     *
     * @param point1
     * @param point2
     * @return
     */
    private double getDistance(PointF point1, PointF point2) {
        return Math.sqrt((point1.x - point2.x) * (point1.x - point2.x) + (point1.y - point2.y) * (point1.y - point2.y));
    }


    // 3.1 有两个圆，一个是固定圆位置固定不动但是半径会变化（两个圆之间的距离越远半径就越小）
    // 还有一个是拖拽圆半径是不变的位置是跟随我手指移动
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 手指按下要去指定当前的位置
                float downX = event.getX();
                float downY = event.getY();
                initPoint(downX, downY);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                updateDragPoint(moveX, moveY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }

    /**
     * 更新当前拖拽点的位置
     *
     * @param moveX
     * @param moveY
     */
    private void updateDragPoint(float moveX, float moveY) {
        mDragPoint.x = moveX;
        mDragPoint.y = moveY;
    }

    /**
     * 初始化位置
     *
     * @param downX
     * @param downY
     */
    private void initPoint(float downX, float downY) {
        mFixationPoint = new PointF(downX, downY);
        mDragPoint = new PointF(downX, downY);
    }

    /**
     * 获取贝塞尔的路径
     * @return
     */
    public Path getBezeierPath() {
        double distance = getDistance(mDragPoint, mFixationPoint);

        mFixationRadius = (int) (mFixationRadiusMax - distance / 14);
        if (mFixationRadius < mFixationRadiusMin) {
            // 超过一定距离 贝塞尔和固定圆都不要画了
            return null;
        }

        Path bezeierPath = new Path();

        // 求角 a
        // 求斜率
        float dy = (mDragPoint.y-mFixationPoint.y);
        float dx = (mDragPoint.x-mFixationPoint.x);
        float tanA = dy/dx;
        // 求角a
        double arcTanA = Math.atan(tanA);

        // p0
        float p0x = (float) (mFixationPoint.x + mFixationRadius*Math.sin(arcTanA));
        float p0y = (float) (mFixationPoint.y - mFixationRadius*Math.cos(arcTanA));

        // p1
        float p1x = (float) (mDragPoint.x + mDragRadius*Math.sin(arcTanA));
        float p1y = (float) (mDragPoint.y - mDragRadius*Math.cos(arcTanA));

        // p2
        float p2x = (float) (mDragPoint.x - mDragRadius*Math.sin(arcTanA));
        float p2y = (float) (mDragPoint.y + mDragRadius*Math.cos(arcTanA));

        // p3
        float p3x = (float) (mFixationPoint.x - mFixationRadius*Math.sin(arcTanA));
        float p3y = (float) (mFixationPoint.y + mFixationRadius*Math.cos(arcTanA));

        // 拼装 贝塞尔的曲线路径
        bezeierPath.moveTo(p0x,p0y); // 移动
        // 两个点
        PointF controlPoint = getControlPoint();
        // 画了第一条  第一个点（控制点,两个圆心的中心点），终点
        bezeierPath.quadTo(controlPoint.x,controlPoint.y,p1x,p1y);

        // 画第二条
        bezeierPath.lineTo(p2x,p2y); // 链接到
        bezeierPath.quadTo(controlPoint.x,controlPoint.y,p3x,p3y);
        bezeierPath.close();

        return bezeierPath;
    }

    public PointF getControlPoint() {
        return new PointF((mDragPoint.x+mFixationPoint.x)/2,(mDragPoint.y+mFixationPoint.y)/2);
    }
}
