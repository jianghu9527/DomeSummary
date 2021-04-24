package com.cd.ruileda.cc.view.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.cd.ruileda.cc.view.R;
import com.cd.ruileda.cc.view.widget.QQstepView;

public class QQActivity extends AppCompatActivity {
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QQstepView mQQstepView=  findViewById(R.id.mmQQstepView);
        mQQstepView.seStepMax(4000);
//        mQQstepView.seCurrentStep(2400);

 findViewById(R.id.buttomtm).setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {

         getInit(  mQQstepView);

     }
 });




    }

    private  void getInit(QQstepView mQQstepView){

        ValueAnimator  valueAnimator= ObjectAnimator.ofFloat(0,2477);
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                float current= (float) valueAnimator.getAnimatedValue();
                mQQstepView.seCurrentStep((int)current);
            }
        });
        valueAnimator.start();
    }

}