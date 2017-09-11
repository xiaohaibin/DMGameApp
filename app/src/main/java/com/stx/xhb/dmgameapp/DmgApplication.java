package com.stx.xhb.dmgameapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.umeng.socialize.PlatformConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

/**
 * Created by xhb on 2016/1/19.
 * 程序主入口，当程序启动的时候，会调用这个方法
 */
public class DmgApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("==3dm=="))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);

        //微信 appid appsecret
        PlatformConfig.setWeixin("wx152334f54a39c3b0", "24949aef9a179c253fdd55f12a576632");
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone("1105172050", "0QeUxsghHOHfZzap");
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
