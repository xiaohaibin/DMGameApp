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
import com.stx.xhb.dmgameapp.entity.GameListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetGameListContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetGameListPresenter;
import com.stx.xhb.dmgameapp.adapter.GameListAdapter;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import java.util.List;

import butterknife.Bind;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class GameCommonFragment extends BaseMvpFragment<GetGameListPresenter> implements GetGameListContract.getGameListDataView, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.id_stickynavlayout_innerscrollview)
    EasyRecyclerView mRecyclerView;
    private GameListAdapter mGameListAdapter;
    private String mAppId = "0";

    public static GameCommonFragment newInstance(String typeId) {
        Bundle args = new Bundle();
        GameCommonFragment fragment = new GameCommonFragment();
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
                mAppId = bundle.getString("id");
                Log.i("===>mAppId", mAppId);
            }
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setRefreshingColor(Color.rgb(255, 99, 71), Color.rgb(255, 99, 71), Color.rgb(255, 99, 71));
        mRecyclerView.setRefreshListener(this);
        mGameListAdapter = new GameListAdapter(getActivity());
        mGameListAdapter.setMore(R.layout.view_more, this);
        mGameListAdapter.setNoMore(R.layout.view_nomore);
        mGameListAdapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                mGameListAdapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                mGameListAdapter.resumeMore();
            }
        });
        mRecyclerView.setAdapter(mGameListAdapter);
    }

    @Override
    protected void lazyLoad() {
        onRefresh();
    }

    @Override
    public void getGameListDataSuccess(List<GameListBean.HtmlEntity> listEntity) {
        if (currentpage == 1) {
            mGameListAdapter.clear();
        }
        if (listEntity != null) {
            mGameListAdapter.addAll(listEntity);
        }
        if (mGameListAdapter.getCount() < pageSize) {
            mGameListAdapter.stopMore();
        }
        if (mGameListAdapter.getCount() == 0) {
            mRecyclerView.showEmpty();
        }
    }

    @Override
    public void getGameListDataFailed(String msg) {
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
        mPresenter.getGameListData(mAppId, currentpage);
    }

    @Override
    public void onLoadMore() {
        currentpage++;
        mPresenter.getGameListData(mAppId, currentpage);
    }

    @Override
    protected GetGameListPresenter onLoadPresenter() {
        return new GetGameListPresenter();
    }
}
