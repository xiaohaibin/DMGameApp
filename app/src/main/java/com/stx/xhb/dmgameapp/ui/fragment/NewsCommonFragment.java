package com.stx.xhb.dmgameapp.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.stx.core.base.BaseFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.NewsCommonAdapter;
import com.stx.xhb.dmgameapp.entity.NewsListEntity;
import com.stx.xhb.dmgameapp.presenter.news.getNewsListContract;
import com.stx.xhb.dmgameapp.presenter.news.getNewsListImpl;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;

/**
 * 通用的Fragment
 */
public class NewsCommonFragment extends BaseFragment implements getNewsListContract.getNewListView, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    private String mAppId = "0";
    private NewsCommonAdapter mNewsCommonAdapter;

    public static NewsCommonFragment newInstance(String typeId) {
        Bundle args = new Bundle();
        NewsCommonFragment fragment = new NewsCommonFragment();
        args.putString("id", typeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey("id")) {
                mAppId = bundle.getString("id");
            }
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setRefreshingColor(Color.rgb(0, 140, 240), Color.rgb(0, 140, 240), Color.rgb(0, 140, 240));
        mRecyclerView.setRefreshListener(this);
        mNewsCommonAdapter = new NewsCommonAdapter(getActivity());
        mNewsCommonAdapter.setMore(R.layout.view_more, this);
        mNewsCommonAdapter.setNoMore(R.layout.view_nomore);
        mNewsCommonAdapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                mNewsCommonAdapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                mNewsCommonAdapter.resumeMore();
            }
        });
        mRecyclerView.setAdapter(mNewsCommonAdapter);
    }

    @Override
    protected Class getLogicClazz() {
        return getNewsListContract.class;
    }


    @Override
    protected void lazyLoad() {
        ((getNewsListImpl) mPresenter).getNewsList(mAppId, currentpage);
    }

    @Override
    public void getNewListFailed(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void showLoading() {
        mRecyclerView.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mRecyclerView.setRefreshing(false);
    }

    @Override
    public void getNewListSuccess(NewsListEntity listEntity) {
        if (listEntity != null) {
            if (currentpage == 1) {
                mNewsCommonAdapter.clear();
                if ("1".equals(mAppId) && listEntity.getBanner() != null) {
                    mNewsCommonAdapter.setAdList(listEntity.getBanner().getHtml());
                }
            }
            if (listEntity.getChannel() != null) {
                mNewsCommonAdapter.addAll(listEntity.getChannel().getHtml());
            }
            if (mNewsCommonAdapter.getCount() < page_size) {
                mNewsCommonAdapter.stopMore();
            }
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }


    @Override
    public void onRefresh() {
        currentpage = 1;
        ((getNewsListImpl) mPresenter).getNewsList(mAppId, currentpage);
    }

    @Override
    public void onLoadMore() {
        currentpage++;
        ((getNewsListImpl) mPresenter).getNewsList(mAppId, currentpage);
    }
}
