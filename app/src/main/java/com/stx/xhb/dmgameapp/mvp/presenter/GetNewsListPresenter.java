package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.data.remote.TasksRepositoryProxy;
import com.stx.xhb.dmgameapp.http.HttpResultSubscriber;
import com.stx.xhb.dmgameapp.mvp.contract.GetNewsListContract;

import rx.Subscription;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class GetNewsListPresenter extends BasePresenter<GetNewsListContract.getNewListView> implements GetNewsListContract.getNewsListModel {

    @Override
    public void getNewsList(int page) {
        if (getView() == null) {
            return;
        }
        Subscription subscription = TasksRepositoryProxy.getInstance().getHowNews(page, new HttpResultSubscriber<NewsPageBean>() {
            @Override
            public void onSuccess(NewsPageBean newsPageBean) {
                getView().getNewListSuccess(newsPageBean);
            }

            @Override
            public void onError(String msg, int code) {
                getView().getNewListFailed(TextUtils.isEmpty(msg) ? "服务器请求失败，请重试" : msg);
            }

            @Override
            public void onFinished() {
                getView().hideLoading();
            }
        });
        addSubscription(subscription);
    }


    @Override
    public void detachView() {
        super.detachView();
        TasksRepositoryProxy.getInstance().release();
    }
}
