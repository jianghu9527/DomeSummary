package com.cd.ruileda.cc.view.base;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDex;

import com.cd.ruileda.cc.view.util.SpUtil;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.mmkv.MMKV;
import com.tencent.tinker.loader.app.TinkerApplication;

import static com.tencent.bugly.beta.tinker.TinkerManager.getApplication;


public class BaseApplication extends  Application {



    private BaseApplication tinkerApplicationLike;



    @Override
    public void onCreate() {
        super.onCreate();

        Bugly.init(getApplicationContext(), "36f30eca82", false);
        MMKV.initialize(this);


    }


    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);


    }





}
