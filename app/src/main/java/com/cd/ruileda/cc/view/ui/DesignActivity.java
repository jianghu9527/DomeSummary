package com.cd.ruileda.cc.view.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cd.ruileda.cc.view.R;
import com.cd.ruileda.cc.view.widget.StatusBarUtil;

/**
 * 沉浸式状态栏
 *
 *
 */
public class DesignActivity  extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutdesgin);
        StatusBarUtil.setActivityTranslucent(this);

    }
}
