package com.liushengjie.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.liushengjie.smartbutler.R;
import com.liushengjie.smartbutler.service.SmsService;
import com.liushengjie.smartbutler.utils.ShareUtils;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.ui
 * 文件名：SettingActivity
 * Created by liushengjie on 2017/9/22.
 * 描述：  设置
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private Switch sw_sms;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }

    private void initView() {

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
        }
    }

}

