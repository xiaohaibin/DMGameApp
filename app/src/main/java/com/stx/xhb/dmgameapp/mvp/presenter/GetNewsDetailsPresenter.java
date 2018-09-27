package com.stx.xhb.dmgameapp.mvp.presenter;

import com.stx.core.mvp.BasePresenter;
import com.stx.xhb.dmgameapp.data.callback.LoadTaskCallback;
import com.stx.xhb.dmgameapp.data.entity.CommentListBean;
import com.stx.xhb.dmgameapp.data.entity.NewsAboutBean;
import com.stx.xhb.dmgameapp.data.remote.TasksRepositoryProxy;
import com.stx.xhb.dmgameapp.mvp.contract.GetNewsDetailsContract;

import rx.Subscription;

/**
 * @author: xiaohaibin.
 * @time: 2018/2/27
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class GetNewsDetailsPresenter extends BasePresenter<GetNewsDetailsContract.View> implements GetNewsDetailsContract.Model {

    @Override
    public void getNewsDetailsData(String url) {
        if (getView() == null) {
            return;
        }
        Subscription subscription = TasksRepositoryProxy.getInstance().getNewsAbout(url, new LoadTaskCallback<NewsAboutBean>() {
            @Override
            public void onStart() {
                getView().showLoading();
            }

            @Override
            public void onTaskLoaded(NewsAboutBean data) {
                getView().setNewsDetailsData(data);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().getNewsDetailsDataFailed(msg);
            }

            @Override
            public void onCompleted() {
                getView().hideLoading();
            }
        });
        addSubscription(subscription);
    }

    @Override
    public void getCommentListData(int currentPage, String arcurl, int uid) {
        if (getView() == null) {
            return;
        }
        Subscription subscription = TasksRepositoryProxy.getInstance().getHotComment(currentPage, arcurl, uid, new LoadTaskCallback<CommentListBean>() {
            @Override
            public void onStart() {
                getView().showLoading();
            }

            @Override
            public void onTaskLoaded(CommentListBean data) {
                getView().setCommentListData(data);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().getCommentListDataFailed(msg);
            }

            @Override
            public void onCompleted() {
                getView().hideLoading();
            }
        });
        addSubscription(subscription);

    }
}
