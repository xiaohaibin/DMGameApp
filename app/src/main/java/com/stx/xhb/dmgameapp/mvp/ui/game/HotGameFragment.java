package com.stx.xhb.dmgameapp.mvp.ui.game;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.stx.core.base.BaseMvpFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.GameListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetGameListContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetGameListPresenter;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.GameListAdapter;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：热门游戏
 */

public class HotGameFragment extends BaseMvpFragment<GetGameListPresenter> implements GetGameListContract.getGameListDataView, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.id_stickynavlayout_innerscrollview)
    EasyRecyclerView mRecyclerView;
    private GameListAdapter mGameListAdapter;

    public static HotGameFragment newInstance() {
        return new HotGameFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerDecoration(ContextCompat.getColor(getActivity(),R.color.divider),2));
        mRecyclerView.setRefreshingColor(Color.rgb(255, 99, 71), Color.rgb(255, 99, 71), Color.rgb(255, 99, 71));
        mRecyclerView.setRefreshListener(this);
        mGameListAdapter = new GameListAdapter(getActivity());
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
    public void getGameListDataSuccess(GameListBean dataBean) {
        mGameListAdapter.clear();
        if (dataBean.getSlides() != null ) {
            mGameListAdapter.setAdList(dataBean.getSlides());
        }
        if (dataBean.getNewgame() != null ) {
            mGameListAdapter.setNewGameList(dataBean.getNewgame());
        }
        if (dataBean.getExpectgame() != null) {
            mGameListAdapter.setMostExpected(dataBean.getExpectgame());
        }
        if (dataBean.getClassicgame()!=null){
            mGameListAdapter.setClassicGame(dataBean.getClassicgame());
        }
        if (dataBean.getHotgame() != null) {
            mGameListAdapter.setHotGame(dataBean.getHotgame());
        }
        mGameListAdapter.stopMore();
    }

    @Override
    public void getGameListDataFailed(String msg) {
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
    public void onRefresh() {
        mPresenter.getGameListData();
    }

    @NonNull
    @Override
    protected GetGameListPresenter onLoadPresenter() {
        return new GetGameListPresenter();
    }
}
