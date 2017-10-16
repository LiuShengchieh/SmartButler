package com.liushengjie.smartbutler.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.IBinder;
import android.telephony.SmsMessage;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.liushengjie.smartbutler.R;
import com.liushengjie.smartbutler.utils.L;
import com.liushengjie.smartbutler.utils.StaticClass;
import com.liushengjie.smartbutler.view.DispatchLinearLayout;

/**
 * 项目名：SmartButler
 * 包名：  com.liushengjie.smartbutler.service
 * 文件名：SmsService
 * Created by liushengjie on 2017/10/14.
 * 描述：短信监听服务
 */

public class SmsService extends Service implements View.OnClickListener{

    private SmsReceiver smsReceiver;

    //发件人号码
    private String smsPhone;
    //短信内容
    private String smsContent;

    //窗口管理
    private WindowManager wm;
    //布局参数
    private WindowManager.LayoutParams layoutParams;
    //View
    private DispatchLinearLayout mView;

    private TextView tv_phone;
    private TextView tv_content;
    private Button btn_send_sms;

    private HomeWatchReceiver homeWatchReceiver;

    public static final String SYSTEM_DIALOGS_REASON_KEY = "reason";
    public static final String SYSTEM_DIALOGS_HOME_KEY = "homekey";

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    //初始化
    private void init() {
        L.i("init service");

        //动态注册
        smsReceiver = new SmsReceiver();
        IntentFilter intent = new IntentFilter();
        //添加action
        intent.addAction(StaticClass.SMS_ACTION);
        //设置权限
        intent.setPriority(Integer.MAX_VALUE);
        //注册
        registerReceiver(smsReceiver, intent);

        homeWatchReceiver = new HomeWatchReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(homeWatchReceiver, intentFilter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.i("stop service");

        //解除注册
        unregisterReceiver(smsReceiver);
        unregisterReceiver(homeWatchReceiver);
    }

    //短信广播
    public class SmsReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (StaticClass.SMS_ACTION.equals(action)) {
                L.i("来短信了");
                //获取短信内容，返回的是一个object数组
                Object [] objs = (Object[]) intent.getExtras().get("pdus");
                //遍历数组得到数据
                for (Object obj : objs) {
                    //数组元素转换成短信对象
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) obj);
                    //发件人
                    smsPhone = sms.getOriginatingAddress();
                    //内容
                    smsContent = sms.getDisplayMessageBody();
                    L.i("短信的内容：" + smsPhone + ":" + smsContent);
                    showWindow();

                }
            }
        }
    }

    //窗口提示
    private void showWindow() {
        //获取系统服务
        wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        //获取布局参数
        layoutParams = new WindowManager.LayoutParams();
        //定义宽高
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        //定义标记
        layoutParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        //定义格式
        layoutParams.format = PixelFormat.TRANSLUCENT;
        //定义类型
        layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        //加载布局
        mView = (DispatchLinearLayout) View.inflate(getApplicationContext(), R.layout.sms_item, null);

        tv_phone = mView.findViewById(R.id.tv_phone);
        tv_content = mView.findViewById(R.id.tv_content);
        btn_send_sms = mView.findViewById(R.id.btn_send_sms);
        btn_send_sms.setOnClickListener(this);

        //设置数据
        L.i("发件人：" + smsPhone);
        L.i("短信内容：" + smsContent);
        tv_phone.setText("发件人：" + smsPhone);
        tv_content.setText(smsContent);

        //添加View到窗口
        wm.addView(mView, layoutParams);

        mView.setDispatchKeyEventListener(mDispatchKeyEventListener);

    }

    //事件分发（返回键监听）
    private DispatchLinearLayout.DispatchKeyEventListener mDispatchKeyEventListener =
            new DispatchLinearLayout.DispatchKeyEventListener() {
                @Override
                public boolean dispatchKeyEvent(KeyEvent event) {
                    //判断是否按了返回键
                    if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                        L.i("按了返回键");
                        if (mView.getParent() != null) {
                            wm.removeView(mView);
                        }
                        return true;
                    }
                    return false;
                }
            };

    //点击事件
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_sms:
                sendSms();
                //消失窗口
                if (mView.getParent() != null) {
                    wm.removeView(mView);
                }
                break;
        }
    }

    //回复短信
    private void sendSms() {
        Uri uri = Uri.parse("smsto:" + smsPhone);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        //设置启动模式
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("sms_body", "");
        startActivity(intent);
    }

    //广播（home键监听）
    class HomeWatchReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_DIALOGS_REASON_KEY);
                if (SYSTEM_DIALOGS_HOME_KEY.equals(reason)) {
                    L.i("点击了home 键");
                    if (mView.getParent() != null) {
                        wm.removeView(mView);
                    }
                }
            }
        }
    }

}
