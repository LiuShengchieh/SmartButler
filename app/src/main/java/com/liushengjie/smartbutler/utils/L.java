package com.liushengjie.smartbutler.utils;

import android.util.Log;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.utils
 * 文件名：L
 * Created by liushengjie on 2017/9/22.
 * 描述：      Log封装类
 */

public class L {

    //开关
    public static final boolean DEBUG = true;

    //TAG
    public static final String TAG = "smartbutler";

    //五个等级 DIWEF

    public static void d(String text) {
        if (DEBUG) {
            Log.d(TAG, text);
        }
    }

    public static void i(String text) {
        if (DEBUG) {
            Log.i(TAG, text);
        }
    }

    public static void w(String text) {
        if (DEBUG) {
            Log.w(TAG, text);
        }
    }

    public static void e(String text) {
        if (DEBUG) {
            Log.e(TAG, text);
        }
    }

}
