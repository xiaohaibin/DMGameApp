package com.stx.core.base;

import android.app.Application;
import android.content.Context;

import com.stx.core.BuildConfig;
import com.stx.core.log.LogLevel;
import com.stx.core.log.Logger;

/**
 * @author Mr.xiao
 */
public class BaseApplication extends Application {

    private static BaseApplication ourInstance = new BaseApplication();
    private static Context mContext;

    public static BaseApplication getInstance() {
        return ourInstance;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        mContext = getApplicationContext();

        Logger.init(getPackageName())
                .methodCount(3)
                .hideThreadInfo()
                .logLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
    }
}
