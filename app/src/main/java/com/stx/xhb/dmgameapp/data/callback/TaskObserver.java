package com.stx.xhb.dmgameapp.data.callback;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/11
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: TaskObserver 数据统一回调接口，方便快速切换数据获取框架，降低耦合度
 */
public interface TaskObserver<T> {

    void onTaskLoaded(T data);

    void onDataNotAvailable(String msg);

}
