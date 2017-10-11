package com.liushengjie.smartbutler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.liushengjie.smartbutler.R;
import com.liushengjie.smartbutler.utils.L;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.ui
 * 文件名：WebViewActivity
 * Created by liushengjie on 2017/10/11.
 * 描述：新闻详情
 */

public class WebViewActivity extends BaseActivity {

    // 加载进度条
    private ProgressBar mProgressBar;
    // 网页显示
    private WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        initView();
    }

    //初始化View
    private void initView() {

        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        mWebView = (WebView) findViewById(R.id.mWebView);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        final String url = intent.getStringExtra("url");
        L.i("url: "  + url);

        //设置title
        getSupportActionBar().setTitle(title);

        //加载网页的设置

        //支持js
        mWebView.getSettings().setJavaScriptEnabled(true);
        //支持缩放
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);

        mWebView.setWebViewClient(new WebViewClient());

        //加载网页
        mWebView.loadUrl(url);

        mWebView.setWebViewClient(new android.webkit.WebViewClient() {

            //进度条监听
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
            }
        });

    }

}

