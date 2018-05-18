package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.entity.ForumBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetForumListContract;
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

public class GetForumListPresenter extends BasePresenter<GetForumListContract.getForumListView,GetForumListContract.getForumListModel> implements GetForumListContract.getForumListModel {
    @Override
    public void getForumListData(String fid) {
        if (getView()==null){
            return;
        }
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
                                    List<ForumBean> dataList = new ArrayList<>();
                                    JSONArray array = jsonResponse.getDataList();
                                    JSONObject object = jsonResponse.getObject();
                                    if (array != null && object != null) {
                                        for (int i = 0; i < array.length(); i++) {
                                            ForumBean forumBean = new ForumBean();
                                            JSONObject jsonObject = object.getJSONObject(array.getString(i));
                                            forumBean.setFid(jsonObject.getString("fid"));
                                            forumBean.setName(jsonObject.getString("name"));
                                            forumBean.setTodayposts(jsonObject.getString("todayposts"));
                                            forumBean.setRank(jsonObject.getString("rank"));
                                            forumBean.setType(jsonObject.getString("type"));
                                            forumBean.setIcon(jsonObject.getString("icon"));
                                            dataList.add(forumBean);
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


    public static class ForumContentEntity {

        /**
         * fid : 441
         * module : forums
         */

        private String fid;
        private String module;

        public ForumContentEntity(String fid, String module) {
            this.fid = fid;
            this.module = module;
        }
    }
}
