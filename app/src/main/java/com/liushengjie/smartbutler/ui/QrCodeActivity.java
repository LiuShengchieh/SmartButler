package com.liushengjie.smartbutler.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.liushengjie.smartbutler.R;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.ui
 * 文件名：QrCodeActivity
 * Created by liushengjie on 2017/10/18.
 * 描述：二维码生成
 */

public class QrCodeActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEditText;
    private ImageView mImageView;
    private Button btn_qr_code;
    private Bitmap mBitmap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        initView();
    }

    private void initView() {
        mEditText = (EditText) findViewById(R.id.mEditText);
        mImageView = (ImageView) findViewById(R.id.mImageView);
        btn_qr_code = (Button) findViewById(R.id.btn_qr_code);
        btn_qr_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_qr_code:
                qrCode();
                break;
        }
    }

    //二维码生成并显示
    private void qrCode() {
        //屏幕宽度
        int width = getResources().getDisplayMetrics().widthPixels;
        //获取输入框的值
        String textContent = mEditText.getText().toString();
        //判断输入框是否为空
        if (!TextUtils.isEmpty(textContent)) {
            //生成二维码
            mEditText.setText("");
            mBitmap = CodeUtils.createImage(textContent, width / 2, width / 2,
                    BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            mImageView.setImageBitmap(mBitmap);
        } else {
            Toast.makeText(QrCodeActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
        }
    }

}
