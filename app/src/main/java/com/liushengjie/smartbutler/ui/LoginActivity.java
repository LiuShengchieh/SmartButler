package com.liushengjie.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.liushengjie.smartbutler.R;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.ui
 * 文件名：LoginActivity
 * Created by liushengjie on 2017/9/25.
 * 描述：      登录
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //注册按钮
    private Button btn_registered;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    private void initView() {

        btn_registered = (Button) findViewById(R.id.btn_registered);
        btn_registered.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_registered:
                startActivity(new Intent(this, RegisteredActivity.class));
                break;
        }
    }
}
