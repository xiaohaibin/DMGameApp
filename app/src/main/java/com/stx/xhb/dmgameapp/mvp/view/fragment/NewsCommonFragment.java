package com.stx.xhb.dmgameapp.mvp.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.stx.core.base.BaseMvpFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.NewsListEntity;
import com.stx.xhb.dmgameapp.mvp.contract.GetNewsListContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetNewsListPresenter;
import com.stx.xhb.dmgameapp.adapter.NewsCommonAdapter;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;

/**
 * 通用的Fragment
 */
public class NewsCommonFragment extends BaseMvpFragment<GetNewsListPresenter> implements GetNewsListContract.getNewListView, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.id_stickynavlayout_innerscrollview)
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
        mRecyclerView.setRefreshingColor(Color.rgb(255, 99, 71), Color.rgb(255, 99, 71), Color.rgb(255, 99, 71));
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
    protected void lazyLoad() {
        if (mNewsCommonAdapter != null && mNewsCommonAdapter.getCount() <= 0) {
            onRefresh();
        }
    }

    @Override
    public void getNewListFailed(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void getNewListSuccess(NewsListEntity listEntity) {
        if (listEntity != null) {
            if (currentpage == 1) {
                mNewsCommonAdapter.clear();
                if ("1".equals(mAppId) && listEntity.getBanner() != null) {
                    mNewsCommonAdapter.setAdList(listEntity.getBanner().getHtml());
                    ToastUtil.show("已是最新数据");
                }
            }
            if (listEntity.getChannel() != null) {
                mNewsCommonAdapter.addAll(listEntity.getChannel().getHtml());
            }
            if (mNewsCommonAdapter.getCount() < pageSize) {
                mNewsCommonAdapter.stopMore();
            }
            if (mNewsCommonAdapter.getCount() == 0) {
                mRecyclerView.showEmpty();
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
        mPresenter.getNewsList(mAppId, currentpage);
    }

    @Override
    public void onLoadMore() {
        currentpage++;
        mPresenter.getNewsList(mAppId, currentpage);
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
    protected GetNewsListPresenter onLoadPresenter() {
        return new GetNewsListPresenter();
    }
}
