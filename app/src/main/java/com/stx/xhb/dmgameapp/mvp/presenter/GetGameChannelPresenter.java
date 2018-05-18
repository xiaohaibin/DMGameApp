package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.entity.GameChannelListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetGameChannelContract;
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

public class GetGameChannelPresenter extends BasePresenter<GetGameChannelContract.getChannelListView,GetGameChannelContract.getGameChannelModel> implements GetGameChannelContract.getGameChannelModel {

    @Override
    public void getChannelList() {
        if (getView()==null){
            return;
        }
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
                            GameChannelListBean gameChannelListBean = GsonUtil.newGson().fromJson(response, GameChannelListBean.class);
                            if (gameChannelListBean.getCode() == Constants.SERVER_SUCCESS) {
                                if (gameChannelListBean.getHtml() != null) {
                                    getView().getChannelSuccess(gameChannelListBean.getHtml());
                                }
                            } else {
                                getView().hideLoading();
                                getView().getChanelFailed(TextUtils.isEmpty(gameChannelListBean.getMsg()) ? "服务器请求失败，请重试" : gameChannelListBean.getMsg());
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
