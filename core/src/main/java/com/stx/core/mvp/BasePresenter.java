package com.stx.core.mvp;

/**
 * Author：xiaohaibin
 * Time：2017/9/12
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class BasePresenter<V extends IView,M extends IModel> implements IPresenter<V> {

    protected static final String TAG="BasePresenter";
    protected V mView;
    protected M mModel;

    @Override
    public void onStart() {

    }

    @Override
    public void attachView(V mvpView) {
        this.mView = mvpView;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    @Override
    public boolean isViewBind() {
        return mView != null;
    }

    public V getView() {
        return mView;
    }

    public M getModel(){
        return mModel;
    }

}
