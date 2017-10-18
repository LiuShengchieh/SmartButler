package com.liushengjie.smartbutler.application;

import android.app.Application;

import com.liushengjie.smartbutler.utils.StaticClass;
import com.tencent.bugly.crashreport.CrashReport;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import cn.bmob.v3.Bmob;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.application
 * 文件名：BaseApplication
 * Created by liushengjie on 2017/9/21.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, true);

        //初始化bmob
        Bmob.initialize(this, StaticClass.BMOB_APP_ID);

        //初始化二维码
        ZXingLibrary.initDisplayOpinion(this);
    }
}
