package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.data.entity.NewsContentBean;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.data.entity.VideoListBean;
import com.stx.xhb.dmgameapp.data.remote.TasksRepositoryProxy;
import com.stx.xhb.dmgameapp.http.HttpResultSubscriber;
import com.stx.xhb.dmgameapp.mvp.contract.GetVideoContract;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;
import rx.Subscription;

/**
 * Author: Mr.xiao on 2017/9/18
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class GetVideoListPresenter extends BasePresenter<GetVideoContract.getVideoListView> implements GetVideoContract.getVideoModel {

    @Override
    public void getVideoList(int currentPage) {
        if (getView() == null) {
            return;
        }
        Subscription subscription = TasksRepositoryProxy.getInstance().getVideoPage(currentPage, new HttpResultSubscriber<NewsPageBean>() {
            @Override
            public void onSuccess(NewsPageBean newsPageBean) {
                getView().getVideoListSuccess(newsPageBean);
            }

            @Override
            public void onError(String msg, int code) {
                getView().getVideoListFailed(TextUtils.isEmpty(msg) ? "服务器请求失败，请重试" : msg);
            }

            @Override
            public void onFinished() {
                getView().hideLoading();
            }
        });
        addSubscription(subscription);
    }
}
