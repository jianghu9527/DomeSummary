package com.cd.ruileda.cc.view.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cd.ruileda.cc.view.R;
import com.cd.ruileda.cc.view.ui.MainActivity;

public class LancherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutlancher);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                   Intent mintent=new Intent(LancherActivity.this, MainActivity.class);
                    startActivity(mintent);
                    finish();

            }
        },5000);
    }
}
