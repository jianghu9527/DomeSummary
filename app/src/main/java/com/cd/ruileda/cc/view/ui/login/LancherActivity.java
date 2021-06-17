package com.cd.ruileda.cc.view.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cd.ruileda.cc.view.R;
import com.cd.ruileda.cc.view.common.CommonPath;
import com.cd.ruileda.cc.view.ui.main.MainActivity;
import com.cd.ruileda.cc.view.util.FileSizeUtil;

public class LancherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.layoutlancher);
          FileSizeUtil.FileCreatePath(CommonPath.TABLE_DIR);

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
