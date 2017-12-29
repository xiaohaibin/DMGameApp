package com.stx.core.base;

import android.app.Application;
import android.content.Context;

import com.stx.core.BuildConfig;
import com.stx.core.di.component.AppComponent;
import com.stx.core.di.component.DaggerAppComponent;
import com.stx.core.di.moudle.AppMoudle;
import com.stx.core.log.LogLevel;
import com.stx.core.log.Logger;

/**
 * @author Mr.xiao
 */
public class BaseApplication extends Application {

    private static BaseApplication ourInstance;
    private static AppComponent sAppComponent = null;

    public static BaseApplication getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        if (sAppComponent == null) {
            DaggerAppComponent.builder()
                    .appMoudle(new AppMoudle(this))
                    .build();
        }
        Logger.init(getPackageName())
                .methodCount(3)
                .hideThreadInfo()
                .logLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);

    }

    public AppComponent getAppComponent() {
        return sAppComponent;
    }
}
