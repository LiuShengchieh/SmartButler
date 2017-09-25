package com.liushengjie.smartbutler.ui;

import android.app.Application;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.liushengjie.smartbutler.MainActivity;
import com.liushengjie.smartbutler.R;
import com.liushengjie.smartbutler.utils.ShareUtils;
import com.liushengjie.smartbutler.utils.StaticClass;
import com.liushengjie.smartbutler.utils.UtilTools;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.ui
 * 文件名：SplashActivity
 * Created by liushengjie on 2017/9/22.
 * 描述：      闪屏页
 */

public class SplashActivity extends AppCompatActivity {

    /*
    * 1. 延时2000ms
    * 2. 判断程序是否第一次运行
    * 3. 自定义字体
    * 4. Activity全屏主题
    * */

    private TextView tv_splash;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case (StaticClass.HANDLER_SPLASH):
                    //判断程序是否第一次运行
                    if (isFirst()) {
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }

    //初始化view
    private void initView() {

        //延时2000ms
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 2000);

        tv_splash = (TextView) findViewById(R.id.tv_splash);

        //设置字体
        UtilTools.setFont(this, tv_splash);

    }

    public boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this, StaticClass.SHARE_IS_FIRST, true);
        if (isFirst) {
            //标记已经启动过app
            ShareUtils.putBoolean(this, StaticClass.SHARE_IS_FIRST, false);
            return true;
        } else {
            return false;
        }
    }

    //禁止返回键
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
