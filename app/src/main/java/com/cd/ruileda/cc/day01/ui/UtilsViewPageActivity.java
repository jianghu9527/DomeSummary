package com.cd.ruileda.cc.day01.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.text.style.UpdateAppearance;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cd.ruileda.cc.day01.R;
import com.cd.ruileda.cc.day01.widget.ColorTrackTextView;


/**
 * 字体变色效果分析
 */
public class UtilsViewPageActivity  extends AppCompatActivity {


    Button startsleft,startsright;
    ColorTrackTextView mColorTrackTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.item_utilsviewpage);

        mColorTrackTextView= findViewById(R.id.colortracktextview);

        startsleft= findViewById(R.id.startbuts_left);
        startsleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lefttoRight();

            }
        });

        startsright= findViewById(R.id.startbuts_right);
        startsright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Righttoleft();
            }
        });

    }



    public void  lefttoRight(){
        mColorTrackTextView.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
        ValueAnimator  valueAnimator=  ObjectAnimator.ofFloat(0,1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                  float current= (float) valueAnimator.getAnimatedValue();

                mColorTrackTextView.setCurrentProgress(current);
            }
        });
        valueAnimator.start();
    }

    public void  Righttoleft(){
        mColorTrackTextView.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
        ValueAnimator  valueAnimator=  ObjectAnimator.ofFloat(0,1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float current= (float) valueAnimator.getAnimatedValue();

                mColorTrackTextView.setCurrentProgress(current);
            }
        });
        valueAnimator.start();
    }
}
