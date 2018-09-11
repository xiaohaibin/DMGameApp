package com.stx.xhb.dmgameapp.data.callback;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/11
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: LoadTaskCallback
 */
public abstract class LoadTaskCallback<T> implements TaskObserver<T> {

    public void onStart(){ }

    public void onCompleted(){ }
}
