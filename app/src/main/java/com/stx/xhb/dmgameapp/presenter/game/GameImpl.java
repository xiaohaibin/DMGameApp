package com.stx.xhb.dmgameapp.presenter.game;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.entity.GameChannelListEntity;
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

public class GameImpl extends BasePresenter<GameContract.getChannelListView> implements GameContract{

    @Override
    public void getChannelList() {
        OkHttpUtils.get()
                .url(API.GET_GAME_CHANNEL)
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
                            GameChannelListEntity forumChannelListEntity = GsonUtil.newGson().fromJson(response, GameChannelListEntity.class);
                            if (forumChannelListEntity.getCode() == Constants.SERVER_SUCCESS) {
                                if (forumChannelListEntity.getHtml() != null) {
                                    getView().getChannelSuccess(forumChannelListEntity.getHtml());
                                }
                            } else {
                                getView().hideLoading();
                                getView().getChanelFailed(forumChannelListEntity.getMsg());
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
