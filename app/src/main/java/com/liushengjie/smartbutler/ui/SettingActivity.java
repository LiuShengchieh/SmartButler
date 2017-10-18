package com.liushengjie.smartbutler.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.liushengjie.smartbutler.R;
import com.liushengjie.smartbutler.service.SmsService;
import com.liushengjie.smartbutler.utils.L;
import com.liushengjie.smartbutler.utils.ShareUtils;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.ui
 * 文件名：SettingActivity
 * Created by liushengjie on 2017/9/22.
 * 描述：  设置
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    public static final int REQUEST_CODE = 111;

    //短信开关
    private Switch sw_sms;

    //扫一扫
    private LinearLayout ll_scan;
    //扫描结果
    private TextView tv_scan_result;
    //生成二维码
    private LinearLayout ll_qr_code;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }

    private void initView() {

        ll_scan = (LinearLayout) findViewById(R.id.ll_scan);
        ll_scan.setOnClickListener(this);

        tv_scan_result = (TextView) findViewById(R.id.tv_scan_result);

        ll_qr_code = (LinearLayout) findViewById(R.id.ll_qr_code);
        ll_qr_code.setOnClickListener(this);

        sw_sms = (Switch) findViewById(R.id.sw_sms);
        sw_sms.setOnClickListener(this);

        //读取状态
        boolean isSms = ShareUtils.getBoolean(this, "isSms", false);
        sw_sms.setChecked(isSms);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sw_sms:
                //切换相反
                sw_sms.setSelected(!sw_sms.isSelected());
                //保存状态
                ShareUtils.putBoolean(this, "isSms", sw_sms.isChecked());
                if (sw_sms.isChecked()) {
                    startService(new Intent(this, SmsService.class));
                } else {
                    stopService(new Intent(this, SmsService.class));
                }
                break;
            //扫一扫
            case R.id.ll_scan:
                if (ContextCompat.checkSelfPermission(SettingActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SettingActivity.this, new
                    String[] { Manifest.permission.CAMERA }, 1);
                } else {
                    Intent intent = new Intent(SettingActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }
                break;
            //二维码生成
            case R.id.ll_qr_code:
                startActivity(new Intent(this, QrCodeActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    tv_scan_result.setText(result);
                    L.e("解析二维码成功");
                    //Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    L.e("解析二维码失败");
                    //Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    //权限请求回调结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(SettingActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

}

