package com.stx.xhb.dmgameapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.stx.core.base.BaseActivity;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.ForumDetailsListAdapter;
import com.stx.xhb.dmgameapp.entity.ForumListEntity;
import com.stx.xhb.dmgameapp.presenter.forum.getForumDetailsListContract;
import com.stx.xhb.dmgameapp.presenter.forum.getForumDetailsListImpl;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import java.util.List;

import butterknife.Bind;

/**
 * Author：xiaohaibin
 * Time：2017/9/21
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：论坛列表
 */

public class ForumListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener, getForumDetailsListContract.getForumListDataView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_list)
    EasyRecyclerView mRecyclerView;
    private String fid = "";
    private ForumDetailsListAdapter forumDetailsListAdapter;

    public static void start(Context context, String fid, String title) {
        Bundle bundle = new Bundle();
        bundle.putString("fid", fid);
        bundle.putString("title", title);
        Intent intent = new Intent(context, ForumListActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_forum_list;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("fid")) {
                fid = extras.getString("fid");
            }
            if (extras.containsKey("title")) {
                String title = extras.getString("title");
                initToolBar(toolbar, title);
            }
        }
        initView();
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setRefreshingColor(Color.rgb(255, 99, 71), Color.rgb(255, 99, 71), Color.rgb(255, 99, 71));
        mRecyclerView.setRefreshListener(this);
        forumDetailsListAdapter = new ForumDetailsListAdapter(this);
        forumDetailsListAdapter.setMore(R.layout.view_more, this);
        forumDetailsListAdapter.setNoMore(R.layout.view_nomore);
        forumDetailsListAdapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                forumDetailsListAdapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                forumDetailsListAdapter.resumeMore();
            }
        });
        mRecyclerView.setAdapter(forumDetailsListAdapter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onRefresh();
    }

    @Override
    protected Class getLogicClazz() {
        return getForumDetailsListContract.class;
    }

    @Override
    public void getForumListDataSuccess(List<ForumListEntity.HtmlBean> data) {
        if (currentpage == 1) {
            forumDetailsListAdapter.clear();
        }
        forumDetailsListAdapter.addAll(data);
    }

    @Override
    public void getForumListDataFailed(String msg) {
        ToastUtil.show(msg);
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
    public void onRefresh() {
        currentpage = 1;
        ((getForumDetailsListImpl) mPresenter).getForumListData(fid, currentpage);
    }

    @Override
    public void onLoadMore() {
        currentpage++;
        ((getForumDetailsListImpl) mPresenter).getForumListData(fid, currentpage);
    }
}
