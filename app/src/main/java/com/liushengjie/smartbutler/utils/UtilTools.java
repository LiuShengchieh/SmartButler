package com.liushengjie.smartbutler.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.utils
 * 文件名：UtilTools
 * Created by liushengjie on 2017/9/21.
 * 描述：   工具类
 */

public class UtilTools {

    //设置字体
    public static void setFont(Context mContent, TextView textView) {
        Typeface fontType = Typeface.createFromAsset(mContent.getAssets(), "fonts/FONT.TTF");
        textView.setTypeface(fontType);
    }

}
