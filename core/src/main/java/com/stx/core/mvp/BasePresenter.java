package com.stx.core.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Author：xiaohaibin
 * Time：2017/9/12
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {

    private CompositeSubscription mCompositeSubscription;

    protected V mView;

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
        unSubsription();
    }

    @Override
    public boolean isViewBind() {
        return mView != null;
    }

    public V getView() {
        return mView;
    }

    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        this.mCompositeSubscription.add(s);
    }

    public void unSubsription() {
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }


}
