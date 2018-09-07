package com.stx.xhb.dmgameapp.mvp.ui.news;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.stx.core.base.BaseMvpFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.NewsCommonAdapter;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetNewsListContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetNewsListPresenter;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;

/**
 * 新闻
 * @author Mr.xiao
 */
public class NewsFragment extends BaseMvpFragment<GetNewsListPresenter> implements GetNewsListContract.getNewListView, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.id_stickynavlayout_innerscrollview)
    EasyRecyclerView mRecyclerView;
    private NewsCommonAdapter mNewsCommonAdapter;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
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
        if (currentpage == 1){
            mRecyclerView.setRefreshing(true);
        }
        mNewsCommonAdapter.pauseMore();
    }

    @Override
    public void getNewListSuccess(NewsPageBean listEntity) {
        if (listEntity != null) {
            if (currentpage == 1) {
                mNewsCommonAdapter.clear();
                mNewsCommonAdapter.removeAllHeader();
                if (listEntity.getSlides() != null) {
                    mNewsCommonAdapter.setAdList(listEntity.getSlides());
                    ToastUtil.show("已是最新数据");
                }
            }
            if (listEntity.getList() != null) {
                mNewsCommonAdapter.addAll(listEntity.getList());
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
        mPresenter.getNewsList(currentpage);
    }

    @Override
    public void onLoadMore() {
        currentpage++;
        mPresenter.getNewsList(currentpage);
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

    @NonNull
    @Override
    protected GetNewsListPresenter onLoadPresenter() {
        return new GetNewsListPresenter();
    }
}
