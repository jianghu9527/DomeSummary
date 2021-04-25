package com.cd.ruileda.cc.view.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cd.ruileda.cc.view.R;
import com.cd.ruileda.cc.view.widget.ProgressBarView;
import com.cd.ruileda.cc.view.widget.ShapeView;


/**
 * 进度条
 */
public class ProgressActivity extends AppCompatActivity {
    ProgressBarView viewprogress;
        private ShapeView mShapeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutprogress);
        viewprogress=  findViewById(R.id.viewprogress);
        viewprogress.setMax(100);
        mShapeView = (ShapeView) findViewById(R.id.shape_view);


        findViewById(R.id.buttonstart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProgressAnimator();
            }
        });

    }

    public void exchange(View view) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mShapeView.exchange();
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();
    }
    private void  getProgressAnimator(){
        ValueAnimator animator = ObjectAnimator.ofFloat(0, 40);
        animator.setDuration(2000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                viewprogress.setProgress((int) progress);
            }
        });

    }


}
