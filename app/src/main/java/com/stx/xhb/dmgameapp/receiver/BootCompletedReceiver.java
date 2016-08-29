package com.stx.xhb.dmgameapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by xhb on 2016/8/29.
 * 接收开机广播
 */
public class BootCompletedReceiver extends BroadcastReceiver {
    private final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
            if (ACTION_BOOT.equals(action)){
                //极光推送初始化
                JPushInterface.init(context);
            }
    }
}
