package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.entity.NewsContentEntity;
import com.stx.xhb.dmgameapp.entity.NewsListEntity;
import com.stx.xhb.dmgameapp.mvp.contract.getNewsListContract;
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

public class getNewsListPresenter extends BasePresenter<getNewsListContract.getNewListView,getNewsListContract.getNewsListModel> implements getNewsListContract.getNewsListModel {
    @Override
    public void getNewsList(String appId, int page) {
        Log.i("===dsds",appId+"====");
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new NewsContentEntity(appId, page)))
                .url(API.NEWS_CHANNEL_DATA)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        getView().showLoading();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getView().getNewListFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            NewsListEntity newsListEntity = GsonUtil.newGson().fromJson(response, NewsListEntity.class);
                            if (newsListEntity.getCode() == Constants.SERVER_SUCCESS) {
                                getView().getNewListSuccess(newsListEntity);
                            } else {
                                getView().hideLoading();
                                getView().getNewListFailed(TextUtils.isEmpty(newsListEntity.getMsg()) ? "服务器请求失败，请重试" : newsListEntity.getMsg());
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
