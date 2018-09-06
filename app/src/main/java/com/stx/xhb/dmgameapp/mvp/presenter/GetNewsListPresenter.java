package com.stx.xhb.dmgameapp.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.qq.e.ads.nativ.ADSize;
import com.qq.e.ads.nativ.NativeExpressAD;
import com.qq.e.ads.nativ.NativeExpressADView;
import com.qq.e.comm.util.AdError;
import com.qq.e.comm.util.StringUtil;
import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.DateUtils;
import com.stx.core.utils.GsonUtil;
import com.stx.core.utils.StringUtils;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.data.entity.NewsContent;
import com.stx.xhb.dmgameapp.data.entity.NewsContentBean;
import com.stx.xhb.dmgameapp.data.entity.NewsListBean;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetNewsListContract;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class GetNewsListPresenter extends BasePresenter<GetNewsListContract.getNewListView, GetNewsListContract.getNewsListModel> implements GetNewsListContract.getNewsListModel {

    @Override
    public void getNewsList(int page) {
        if (getView() == null) {
            return;
        }
        long time = System.currentTimeMillis();
        String str = StringUtils.getMD5("10" + page + time);
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new NewsContent(page,time,str)))
                .url(API.NEW_HOT_NEWS_PAGE)
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
                            NewsPageBean newsListBean = GsonUtil.newGson().fromJson(response, NewsPageBean.class);
                            if (newsListBean.getCode() == Constants.SERVER_SUCCESS) {
                                getView().getNewListSuccess(newsListBean.getData());
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
