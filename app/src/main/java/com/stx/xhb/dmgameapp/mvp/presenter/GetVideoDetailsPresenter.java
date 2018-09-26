package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.data.entity.CommentListBean;
import com.stx.xhb.dmgameapp.data.entity.NewsDetailsContentBean;
import com.stx.xhb.dmgameapp.data.entity.VideoListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetVideoDetailsContract;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * @author: xiaohaibin.
 * @time: 2018/3/2
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 获取视频详情
 */

public class GetVideoDetailsPresenter extends BasePresenter<GetVideoDetailsContract.View> implements GetVideoDetailsContract.Model{

    @Override
    public void getVideoDetailsData(String id, String key) {
        if (getView()==null){
            return;
        }
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new NewsDetailsContentBean("1", id, key, "2")))
                .url(API.NEWS_CHANNEL_DATA)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        getView().showLoading();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getView().getVideoDetailsDataFailed();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            VideoListBean videoListBean = GsonUtil.newGson().fromJson(response, VideoListBean.class);
                            if (videoListBean.getCode() == Constants.SERVER_SUCCESS) {
                                if (videoListBean.getVideo() != null) {
                                    getView().setVideoDetailsData(videoListBean.getVideo());
                                }else {
                                    getView().hideLoading();
                                    getView().getVideoDetailsDataFailed();
                                }
                            } else {
                                getView().hideLoading();
                                getView().getVideoDetailsDataFailed();
                            }
                        }
                    }

                    @Override
                    public void onAfter(int id) {
                        getView().hideLoading();
                    }
                });
    }

    @Override
    public void getCommentListData(String id) {

    }
}
