package com.stx.xhb.dmgameapp.mvp.ui.game;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.stx.core.base.BaseMvpFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.GameRankBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetRankGameContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetRankGamePresenter;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.GameCommonAdapter;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.GameRankAdapter;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author: Mr.xiao on 2018/9/11
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 游戏排行
 */
public class GameRankFragment extends BaseMvpFragment<GetRankGamePresenter> implements GetRankGameContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {


    @Bind(R.id.id_stickynavlayout_innerscrollview)
    EasyRecyclerView mRvList;
    private GameRankAdapter mGameCommonAdapter;

    public static GameRankFragment newInstance() {
        return new GameRankFragment();
    }

    @NonNull
    @Override
    protected GetRankGamePresenter onLoadPresenter() {
        return new GetRankGamePresenter();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setRefreshingColor(Color.rgb(255, 99, 71), Color.rgb(255, 99, 71), Color.rgb(255, 99, 71));
        mRvList.setRefreshListener(this);
        mRvList.addItemDecoration(new DividerDecoration(R.color.divider, 1));
        //初始化已售游戏列表适配器
        mGameCommonAdapter = new GameRankAdapter(getActivity());
        mGameCommonAdapter.setMore(R.layout.view_more, this);
        mGameCommonAdapter.setNoMore(R.layout.view_nomore);
        mGameCommonAdapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                mGameCommonAdapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                mGameCommonAdapter.resumeMore();
            }
        });
        mRvList.setAdapter(mGameCommonAdapter);
    }

    @Override
    public void getDataSuccess(GameRankBean gameRankBean) {
        if (gameRankBean != null) {
            if (currentpage == 1) {
                mGameCommonAdapter.clear();
            }
            if (gameRankBean.getList() != null) {
                mGameCommonAdapter.addAll(gameRankBean.getList());
            }
            if (mGameCommonAdapter.getCount() < pageSize) {
                mGameCommonAdapter.stopMore();
            }
            if (mGameCommonAdapter.getCount() == 0) {
                mRvList.showEmpty();
            }
        }
    }

    @Override
    public void getFailed(String msg) {
        ToastUtil.show(msg);
        mGameCommonAdapter.pauseMore();
    }

    @Override
    public void showLoading() {
        if (currentpage == 1) {
            mRvList.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        mRvList.setRefreshing(false);
    }

    @Override
    protected void lazyLoad() {
        if (mGameCommonAdapter != null && mGameCommonAdapter.getCount() <= 0) {
            onRefresh();
        }
    }


    @Override
    public void onRefresh() {
        currentpage = 1;
        mPresenter.getRankGame(currentpage,"0");
    }

    @Override
    public void onLoadMore() {
        currentpage++;
        mPresenter.getRankGame(currentpage,"0");
    }
}
