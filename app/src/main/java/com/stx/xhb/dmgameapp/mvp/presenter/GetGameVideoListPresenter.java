package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.data.entity.GameDetailsContent;
import com.stx.xhb.dmgameapp.data.entity.GameVideoBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetGameVideoContract;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Author: Mr.xiao on 2017/9/18
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class GetGameVideoListPresenter extends BasePresenter<GetGameVideoContract.getVideoListView,GetGameVideoContract.getGameVideoModel> implements GetGameVideoContract.getGameVideoModel {

    @Override
    public void getVideoList(String id,String key,String type,int page) {
        if (getView()==null){
            return;
        }
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new GameDetailsContent(page,id,key,type)))
                .url(API.GET_GAME_DETAILS)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        getView().showLoading();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getView().getVideoListFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            GameVideoBean videoListEntity = GsonUtil.newGson().fromJson(response, GameVideoBean.class);
                            if (videoListEntity.getCode() == Constants.SERVER_SUCCESS) {
                                getView().getVideoListSuccess(videoListEntity);
                            } else {
                                getView().hideLoading();
                                getView().getVideoListFailed(videoListEntity.getMsg());
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
