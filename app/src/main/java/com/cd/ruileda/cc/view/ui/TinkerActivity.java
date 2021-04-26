package com.cd.ruileda.cc.view.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.cd.ruileda.cc.view.R;
import com.cd.ruileda.cc.view.util.ToastUtils;


/**
 * 发布新版本
 * tinker-support
 * 热更新
 *
 *
 * 未匹配到可应用补丁包的App版本，请确认补丁包的基线版本是否已发布
 *
 *
 * https://bugly.qq.com/docs/user-guide/instruction-manual-android-hotfix-demo/?v=20200622202242
 */
public class TinkerActivity  extends AppCompatActivity {


    FileProvider  mFileProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.layouttinker);

            findViewById(R.id.buttinlkerrebug).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startTinker();
                }
            });

    }

    public  void startTinker(){

        ToastUtils.showShort(this,"补丁完成1111111");

    }
}
