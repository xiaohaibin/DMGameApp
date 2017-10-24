package com.stx.xhb.dmgameapp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.stx.core.base.BaseFragment;
import com.stx.core.utils.GsonUtil;
import com.stx.core.utils.ScreenUtil;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.ForumListAdapter;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.entity.ForumEntity;
import com.stx.xhb.dmgameapp.presenter.forum.getForumListContract;
import com.stx.xhb.dmgameapp.presenter.forum.getForumListImpl;
import com.stx.xhb.dmgameapp.utils.JsonResponse;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class ForumCommonFragment extends BaseFragment implements getForumListContract.getForumListView, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.id_stickynavlayout_innerscrollview)
    EasyRecyclerView mRecyclerView;
    private String mId = "0";
    private ForumListAdapter mForumListAdapter;

    public static ForumCommonFragment newInstance(String typeId) {
        Bundle args = new Bundle();
        ForumCommonFragment fragment = new ForumCommonFragment();
        args.putString("id", typeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey("id")) {
                mId = bundle.getString("id");
            }
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.color_eeeeee),
                ScreenUtil.dip2px(getContext(), 1));
        dividerDecoration.setDrawLastItem(false);
        mRecyclerView.addItemDecoration(dividerDecoration);
        mRecyclerView.setRefreshingColor(Color.rgb(255, 99, 71), Color.rgb(255, 99, 71), Color.rgb(255, 99, 71));
        mRecyclerView.setRefreshListener(this);
        mForumListAdapter = new ForumListAdapter(getActivity());
        mForumListAdapter.setNoMore(R.layout.view_nomore);
        mForumListAdapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                mForumListAdapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                mForumListAdapter.resumeMore();
            }
        });
        mRecyclerView.setAdapter(mForumListAdapter);
    }

    @Override
    protected Class getLogicClazz() {
        return getForumListContract.class;
    }

    @Override
    protected void lazyLoad() {
        onRefresh();
    }

    @Override
    public void showLoading() {
        if (currentpage == 1) {
            mRecyclerView.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        if (currentpage == 1) {
            mRecyclerView.setRefreshing(false);
        }
    }

    @Override
    public void getForumListDataSuccess(List<ForumEntity> listData) {
        if (currentpage == 1) {
            mForumListAdapter.clear();
        }
        mForumListAdapter.addAll(listData);
        if (mForumListAdapter.getCount() < page_size) {
            mForumListAdapter.stopMore();
        }
        if (mForumListAdapter.getCount() == 0) {
            mRecyclerView.showEmpty();
        }
    }

    @Override
    public void getForumListDataFailed(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void onRefresh() {
        currentpage = 1;
//        ((getForumListImpl) mPresenter).getForumListData(mId);
        getData();
    }


    private void getData() {
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new getForumListImpl.ForumContentEntity(mId, "forums")))
                .url(API.USER_API)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        if (currentpage == 1) {
                            mRecyclerView.setRefreshing(true);
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.show(e.getMessage());
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
                                        if (currentpage == 1) {
                                            mForumListAdapter.clear();
                                        }
                                        mForumListAdapter.addAll(dataList);
                                        if (mForumListAdapter.getCount() < page_size) {
                                            mForumListAdapter.stopMore();
                                        }
                                        if (mForumListAdapter.getCount() == 0) {
                                            mRecyclerView.showEmpty();
                                        }
                                    } else {
                                        if (currentpage == 1) {
                                            mRecyclerView.setRefreshing(false);
                                        }
                                        ToastUtil.show(jsonResponse.getMsg());
                                    }
                                } else {
                                    if (currentpage == 1) {
                                        mRecyclerView.setRefreshing(false);
                                    }
                                    if (mForumListAdapter.getCount() == 0) {
                                        mRecyclerView.showEmpty();
                                    }
                                    ToastUtil.show(jsonResponse.getMsg());
                                }
                            } catch (JSONException e) {
                                if (currentpage == 1) {
                                    mRecyclerView.setRefreshing(false);
                                }
                                ToastUtil.show(e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onAfter(int id) {
                        if (currentpage == 1) {
                            mRecyclerView.setRefreshing(false);
                        }
                    }
                });
    }
}
