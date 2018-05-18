package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.entity.NewsContentBean;
import com.stx.xhb.dmgameapp.entity.NewsListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetNewsListContract;
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

public class GetNewsListPresenter extends BasePresenter<GetNewsListContract.getNewListView,GetNewsListContract.getNewsListModel> implements GetNewsListContract.getNewsListModel {
    @Override
    public void getNewsList(String appId, int page) {
        if (getView()==null){
            return;
        }
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new NewsContentBean(appId, page)))
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
                            NewsListBean newsListBean = GsonUtil.newGson().fromJson(response, NewsListBean.class);
                            if (newsListBean.getCode() == Constants.SERVER_SUCCESS) {
                                getView().getNewListSuccess(newsListBean);
                            } else {
                                getView().hideLoading();
                                getView().getNewListFailed(TextUtils.isEmpty(newsListBean.getMsg()) ? "服务器请求失败，请重试" : newsListBean.getMsg());
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
