package com.cd.ruileda.cc.view.base;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDex;

import com.cd.ruileda.cc.view.util.SpUtil;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.mmkv.MMKV;
import com.tencent.tinker.loader.app.TinkerApplication;

import java.util.Locale;

import static com.tencent.bugly.beta.tinker.TinkerManager.getApplication;


public class BaseApplication extends Application {

    private BaseApplication tinkerApplicationLike;
    private Context mContext;



    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        Bugly.init(getApplicationContext(), "36f30eca82", false);
        MMKV.initialize(this);
//        configTinker();

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
    private static final String TAG = "------MyApplication";

    private void configTinker(){
        //是否开启热更新能力，默认为true
        Beta.enableHotfix = true;
        //是否开启自动下载补丁，默认为true
        Beta.canAutoDownloadPatch = true;
        //是否自动合成补丁，默认为true
        Beta.canAutoPatch = true;
        //是否提示用户重启，这里默认设置为false
        Beta.canNotifyUserRestart = true;
        //补丁回调接口
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String s) {
                Log.e(TAG, "补丁下载地址：" + s);
            }

            @Override
            public void onDownloadReceived(long l, long l1) {
                Log.e(TAG, String.format(Locale.getDefault(), "%s %d%%",
                        Beta.strNotificationDownloading,
                        (int) (l1 == 0 ? 0 : l * 100 / l1)));
            }

            @Override
            public void onDownloadSuccess(String s) {
                Log.e(TAG, "补丁下载成功");
                Toast.makeText(mContext, "补丁下载成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadFailure(String s) {
                Log.e(TAG, "补丁下载失败");
                Toast.makeText(mContext, "补丁下载失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplySuccess(String s) {
                Log.e(TAG, "补丁应用成功");
                Toast.makeText(mContext, "补丁应用成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplyFailure(String s) {
                Log.e(TAG, "补丁应用失败");
                Toast.makeText(mContext, "补丁应用失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPatchRollback() {

            }
        };

        //设置开发设备，默认为false，上传补丁如果下发范围指定为“开发设备”，需要调用此接口来标识开发设备
        Bugly.setIsDevelopmentDevice(BaseApplication.this, false);
        // 多渠道需求塞入
        // String channel = WalleChannelReader.getChannel(getApplication());
        // Bugly.setAppChannel(getApplication(), channel);
        // 这里实现SDK初始化，appId替换成你的在平台申请的appId
//        Bugly.init(mContext, "24662872d6", true);
    }



}
