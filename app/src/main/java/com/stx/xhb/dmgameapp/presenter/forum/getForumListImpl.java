package com.stx.xhb.dmgameapp.presenter.forum;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.entity.ForumEntity;
import com.stx.xhb.dmgameapp.utils.JsonResponse;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Author：xiaohaibin
 * Time：2017/9/21
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class getForumListImpl extends BasePresenter<getForumListContract.getForumListView> implements getForumListContract {
    @Override
    public void getForumListData(String fid) {
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new ForumContentEntity(fid, "forums")))
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
                            try {
                                JsonResponse jsonResponse = new JsonResponse(new JSONObject(response));
                                if (jsonResponse.isSuccess()) {
                                    List<ForumEntity> dataList = new ArrayList<>();
                                    JSONArray array = jsonResponse.getDataList();
                                    JSONObject object = jsonResponse.getObject();
                                    if (array != null && object != null) {
                                        for (int i = 0; i < array.length(); i++) {
                                            ForumEntity forumEntity = new ForumEntity();
                                            JSONObject jsonObject = object.getJSONObject(array.getString(i));
                                            forumEntity.setFid(jsonObject.getString("fid"));
                                            forumEntity.setName(jsonObject.getString("name"));
                                            forumEntity.setTodayposts(jsonObject.getString("todayposts"));
                                            forumEntity.setRank(jsonObject.getString("rank"));
                                            forumEntity.setType(jsonObject.getString("type"));
                                            forumEntity.setIcon(jsonObject.getString("icon"));
                                            dataList.add(forumEntity);
                                        }
                                        getView().getForumListDataSuccess(dataList);
                                    } else {
                                        getView().hideLoading();
                                        getView().getForumListDataFailed(jsonResponse.getMsg());
                                    }
                                } else {
                                    getView().hideLoading();
                                    getView().getForumListDataFailed(jsonResponse.getMsg());
                                }
                            } catch (JSONException e) {
                                getView().hideLoading();
                                getView().getForumListDataFailed(e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onAfter(int id) {
                        getView().hideLoading();
                    }
                });
    }


    private class ForumContentEntity {

        /**
         * fid : 441
         * module : forums
         */

        private String fid;
        private String module;

        ForumContentEntity(String fid, String module) {
            this.fid = fid;
            this.module = module;
        }
    }
}
