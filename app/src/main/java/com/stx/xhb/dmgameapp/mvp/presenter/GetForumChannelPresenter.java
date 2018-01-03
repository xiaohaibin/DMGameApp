package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.entity.CommonContentEntity;
import com.stx.xhb.dmgameapp.entity.ForumChannelListEntity;
import com.stx.xhb.dmgameapp.mvp.contract.GetForumChannelContract;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class GetForumChannelPresenter extends BasePresenter<GetForumChannelContract.getChannelListView,GetForumChannelContract.getChanelModel> implements GetForumChannelContract.getChanelModel {

    @Override
    public void getChannelList() {
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new CommonContentEntity("groups")))
                .url(API.USER_API)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        getView().showLoading();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getView().getChanelFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            ForumChannelListEntity forumChannelListEntity = GsonUtil.newGson().fromJson(response, ForumChannelListEntity.class);
                            if (forumChannelListEntity.getCode() == Constants.SERVER_SUCCESS) {
                                if (forumChannelListEntity.getHtml() != null) {
                                    getView().getChannelSuccess(forumChannelListEntity.getHtml());
                                }
                            } else {
                                getView().hideLoading();
                                getView().getChanelFailed(TextUtils.isEmpty(forumChannelListEntity.getMsg()) ? "服务器请求失败，请重试" : forumChannelListEntity.getMsg());
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
