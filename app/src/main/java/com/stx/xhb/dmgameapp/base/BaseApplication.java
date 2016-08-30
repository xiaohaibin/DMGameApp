package com.stx.xhb.dmgameapp.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.umeng.socialize.PlatformConfig;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

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
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        //微信 appid appsecret
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        //新浪微博 appkey appsecret
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        // QQ和Qzone appid appkey
        //极光推送初始化
        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);//设置是否开启log日志，正式打包发布时建议关闭使用
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
