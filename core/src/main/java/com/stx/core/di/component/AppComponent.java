package com.stx.core.di.component;

import android.app.Application;

import com.stx.core.di.moudle.AppMoudle;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author: xiaohaibin.
 * @time: 2017/12/29
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
@Singleton
@Component(modules = AppMoudle.class)
public interface AppComponent {

    Application application();


}
