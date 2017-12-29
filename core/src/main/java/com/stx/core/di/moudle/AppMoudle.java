package com.stx.core.di.moudle;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author: xiaohaibin.
 * @time: 2017/12/29
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
@Module
public class AppMoudle {

    private Application mApplication;

    public AppMoudle(Application application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return mApplication;
    }

}
