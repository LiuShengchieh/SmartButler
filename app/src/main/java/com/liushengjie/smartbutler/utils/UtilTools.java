package com.liushengjie.smartbutler.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

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

    //保存图片到shareutils
    public static void putImageToShare(Context mContext, ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        //第一步：将bitmap压缩成字节数组输出流
        ByteArrayOutputStream byStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byStream);
        //第二步：利用base64将字节数组输出流转换为string
        byte[] byteArray = byStream.toByteArray();
        String imgString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //第三步：将string保存到shareutils
        ShareUtils.putString(mContext, "image_title", imgString);
    }

    //读取图片
    public static void getImageFromShare(Context mContext, ImageView imageView) {
        //第一步：拿到string
        String imgString = ShareUtils.getString(mContext, "image_title", "");
        if (!imgString.equals("")) {
            //第二步：通过base64将string转为字节数组
            byte[] byteArray = Base64.decode(imgString, Base64.DEFAULT);
            ByteArrayInputStream byStream = new ByteArrayInputStream(byteArray);
            //第三步：生成bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(byStream);
            imageView.setImageBitmap(bitmap);
        }
    }

}
