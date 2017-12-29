package com.stx.core.mvp.base;

import com.stx.core.mvp.IPresenter;
import com.stx.core.mvp.IView;

/**
 * @author: xiaohaibin.
 * @time: 2017/12/29
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class BasePresenter<V extends IView> implements IPresenter {

    protected final String TAG = this.getClass().getSimpleName();
    protected V mRootView;

    public BasePresenter(V mRootView) {
        this.mRootView = mRootView;
        onStart();
    }

    public BasePresenter() {
        onStart();
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {
        this.mRootView = null;
    }
}
