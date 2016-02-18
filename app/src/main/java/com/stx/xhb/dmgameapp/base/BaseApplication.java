package com.stx.xhb.dmgameapp.base;

import android.app.Application;

import org.xutils.x;

/**
 * Created by xhb on 2016/1/19.
 * 程序主入口，当程序启动的时候，会调用这个方法
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化xutils的操作
        x.Ext.init(this);
        //设置是否输出日志
        x.Ext.setDebug(true);
    }
}
