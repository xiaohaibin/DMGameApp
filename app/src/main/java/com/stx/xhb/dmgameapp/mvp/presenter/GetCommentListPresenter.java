package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.data.callback.LoadTaskCallback;
import com.stx.xhb.dmgameapp.data.entity.CommentListBean;
import com.stx.xhb.dmgameapp.data.entity.PostCommentRepsonse;
import com.stx.xhb.dmgameapp.data.remote.TasksRepositoryProxy;
import com.stx.xhb.dmgameapp.mvp.contract.GetCommentListContract;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;
import rx.Subscription;

/**
 * @author: xiaohaibin.
 * @time: 2018/3/2
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class GetCommentListPresenter extends BasePresenter<GetCommentListContract.View> implements GetCommentListContract.Model {

    @Override
    public void getCommentListData(int currentPage, String arcurl, int uid) {
        if (getView() == null) {
            return;
        }
        Subscription subscription = TasksRepositoryProxy.getInstance().getComment(currentPage, arcurl, uid, new LoadTaskCallback<CommentListBean>() {
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
                getView().getCommentListDataFailed();
            }

            @Override
            public void onCompleted() {
                getView().hideLoading();
            }
        });
        addSubscription(subscription);
    }

    @Override
    public void postComment(String arcurl, String comment, int uid) {
        if (getView() == null) {
            return;
        }
        TasksRepositoryProxy.getInstance().postComment(arcurl, comment, uid, new LoadTaskCallback<PostCommentRepsonse>() {
            @Override
            public void onStart() {
                getView().showLoading();
            }

            @Override
            public void onTaskLoaded(PostCommentRepsonse data) {
                getView().postCommentSuccess();
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().postCommentFailed(msg);
            }

            @Override
            public void onCompleted() {
                getView().hideLoading();
            }
        });
    }

    @Override
    public void replyComment(String uid, int id, String arcurl, String content) {
        if (getView()==null){
            return;
        }
        TasksRepositoryProxy.getInstance().replyComment(uid, id, arcurl, content, new LoadTaskCallback<PostCommentRepsonse>() {
            @Override
            public void onStart() {
                getView().showLoading();
            }
            @Override
            public void onTaskLoaded(PostCommentRepsonse data) {
                   getView().postCommentSuccess();
            }

            @Override
            public void onDataNotAvailable(String msg) {
                   getView().postCommentFailed(msg);
            }

            @Override
            public void onCompleted() {
                getView().hideLoading();
            }
        });
    }
}
