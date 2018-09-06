package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.data.entity.ForumListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetForumDetailsListContract;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Author : jxnk25
 * Time: 2017/11/1 0001
 * Email:xhb_199409@163.com
 * Email:xhb_199409@163.com
 * Github:https://github.com/xiaohaibin/
 * Drscribe:
 */

public class GetForumDetailsListPresenter extends BasePresenter<GetForumDetailsListContract.getForumListDataView> implements GetForumDetailsListContract.getForumListModel{
    @Override
    public void getForumListData(String fid,int page) {
        if (getView()==null){
            return;
        }
        OkHttpUtils
                .postString()
                .content(GsonUtil.newGson().toJson(new ForumListContent(fid,page,"forumdisplay")))
                .url(API.USER_API)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        getView().showLoading();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getView().getForumListDataFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            ForumListBean forumListBean = GsonUtil.newGson().fromJson(response, ForumListBean.class);
                            if (forumListBean.getCode() == Constants.SERVER_SUCCESS) {
                                if (forumListBean.getHtml() != null) {
                                    getView().getForumListDataSuccess(forumListBean.getHtml());
                                }
                            } else {
                                getView().hideLoading();
                                getView().getForumListDataFailed(TextUtils.isEmpty(forumListBean.getMsg()) ? "服务器请求失败，请重试" : forumListBean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onAfter(int id) {
                        getView().hideLoading();
                    }
                });
    }

    class ForumListContent{

        /**
         * fid : 316
         * page : 1
         * module : forumdisplay
         */

        private String fid;
        private int page;
        private String module;

        public ForumListContent(String fid, int page, String module) {
            this.fid = fid;
            this.page = page;
            this.module = module;
        }
    }
}
