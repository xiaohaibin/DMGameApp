package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.xhb.dmgameapp.data.callback.LoadTaskCallback;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.data.remote.TasksRepositoryProxy;
import com.stx.xhb.dmgameapp.mvp.contract.GetNewsListContract;

import rx.Subscription;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：热点新闻
 */
public class GetNewsListPresenter extends BasePresenter<GetNewsListContract.getNewListView> implements GetNewsListContract.getNewsListModel {

    @Override
    public void getNewsList(int page) {
        if (getView() == null) {
            return;
        }
        Subscription subscription = TasksRepositoryProxy.getInstance().getNews(page, new LoadTaskCallback<NewsPageBean>() {
            @Override
            public void onTaskLoaded(NewsPageBean data) {
                getView().getNewListSuccess(data);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().getNewListFailed(TextUtils.isEmpty(msg) ? "服务器请求失败，请重试" : msg);
            }

            @Override
            public void onCompleted() {
                getView().hideLoading();
            }
        });
        addSubscription(subscription);
    }


    @Override
    public void getHotNewsList(int currentPage) {
        if (getView() == null) {
            return;
        }
        Subscription subscription = TasksRepositoryProxy.getInstance().getHowNews(currentPage, new LoadTaskCallback<NewsPageBean>() {

            @Override
            public void onStart() {
                getView().showLoading();
            }

            @Override
            public void onTaskLoaded(NewsPageBean data) {
                getView().getNewListSuccess(data);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().getNewListFailed(TextUtils.isEmpty(msg) ? "服务器请求失败，请重试" : msg);
            }

            @Override
            public void onCompleted() {
                getView().hideLoading();
            }
        });
        addSubscription(subscription);
    }

    @Override
    public void getOriginalPage(int currentPage) {
        if (getView() == null) {
            return;
        }
        Subscription subscription = TasksRepositoryProxy.getInstance().getOriginalPage(currentPage, new LoadTaskCallback<NewsPageBean>() {
            @Override
            public void onStart() {
                getView().showLoading();
            }

            @Override
            public void onTaskLoaded(NewsPageBean data) {
                getView().getNewListSuccess(data);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().getNewListFailed(TextUtils.isEmpty(msg) ? "服务器请求失败，请重试" : msg);
            }

            @Override
            public void onCompleted() {
                getView().hideLoading();
            }
        });
        addSubscription(subscription);
    }


    @Override
    public void getAmusePage(int currentPage) {
        if (getView() == null) {
            return;
        }
        Subscription subscription = TasksRepositoryProxy.getInstance().getAmusePage(currentPage,new LoadTaskCallback<NewsPageBean>() {

            @Override
            public void onStart() {
                getView().showLoading();
            }

            @Override
            public void onTaskLoaded(NewsPageBean data) {
                getView().getNewListSuccess(data);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().getNewListFailed(TextUtils.isEmpty(msg) ? "服务器请求失败，请重试" : msg);
            }

            @Override
            public void onCompleted() {
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
