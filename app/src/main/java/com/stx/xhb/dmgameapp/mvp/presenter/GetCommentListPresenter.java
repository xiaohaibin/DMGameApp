package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.entity.CommentListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetCommentListContract;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * @author: xiaohaibin.
 * @time: 2018/3/2
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class GetCommentListPresenter extends BasePresenter<GetCommentListContract.View,GetCommentListContract.Model> implements GetCommentListContract.Model{

    @Override
    public void getCommentListData(String id) {
        OkHttpUtils.get()
                .url(String.format(API.GET_COMMENT_LIST, "news_" + id))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        getView().showLoading();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getView().getCommentListDataFailed();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            CommentListBean commentListBean = GsonUtil.newGson().fromJson(response, CommentListBean.class);
                            if (commentListBean.getComments() != null && !commentListBean.getComments().isEmpty()) {
                                getView().setCommentListData(commentListBean);
                            } else {
                                getView().hideLoading();
                                getView().getCommentListDataFailed();
                            }
                        }
                    }

                    @Override
                    public void onAfter(int id) {
                        getView().hideLoading();
                    }
                });
    }
}
