package com.cd.ruileda.cc.view.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cd.ruileda.cc.view.R;
import com.cd.ruileda.cc.view.widget.LetterSideBar;

public class LetterSideBarActivity extends AppCompatActivity implements LetterSideBar.LetterTouchListener {

    private TextView mLetterTv;
    private LetterSideBar mLetterSideBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.letter_activity_main);
        mLetterTv = (TextView) findViewById(R.id.letter_tv);
        mLetterSideBar = (LetterSideBar) findViewById(R.id.letter_side_bar);
        mLetterSideBar.setOnLetterTouchListener(this);
    }

    @Override
    public void touch(CharSequence letter,boolean isTouch) {
        if(isTouch) {
            mLetterTv.setVisibility(View.VISIBLE);
            mLetterTv.setText(letter);
        }else{
            mLetterTv.setVisibility(View.GONE);
        }
    }

}
