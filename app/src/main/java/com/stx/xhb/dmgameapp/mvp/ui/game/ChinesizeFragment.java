package com.stx.xhb.dmgameapp.mvp.ui.game;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.stx.core.base.BaseMvpFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.SaleGameBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetChinesizeListContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetChinesizeListPresenter;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.GameCommonAdapter;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/7
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 汉化
 */
public class ChinesizeFragment extends BaseMvpFragment<GetChinesizeListPresenter> implements SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener, GetChinesizeListContract.View {

    @Bind(R.id.tv_first)
    TextView mTvFirst;
    @Bind(R.id.tv_second)
    TextView mTvSecond;
    @Bind(R.id.rv_list)
    EasyRecyclerView mRvList;
    private boolean selectFirst = true;
    private GameCommonAdapter mNewCommonAdapter;
    private GameCommonAdapter mHotGameAdapter;

    public static ChinesizeFragment newInstance() {
        return new ChinesizeFragment();
    }

    @NonNull
    @Override
    protected GetChinesizeListPresenter onLoadPresenter() {
        return new GetChinesizeListPresenter();
    }

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_tab;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        mTvFirst.setText("最新");
        mTvSecond.setText("热门");
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setRefreshingColor(Color.rgb(255, 99, 71), Color.rgb(255, 99, 71), Color.rgb(255, 99, 71));
        mRvList.setRefreshListener(this);
        mRvList.addItemDecoration(new DividerDecoration(R.color.divider, 1));

        mNewCommonAdapter = new GameCommonAdapter(getActivity());
        mNewCommonAdapter.setMore(R.layout.view_more, this);
        mNewCommonAdapter.setNoMore(R.layout.view_nomore);
        mNewCommonAdapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                mNewCommonAdapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                mNewCommonAdapter.resumeMore();
            }
        });
        mRvList.setAdapter(mNewCommonAdapter);


        mHotGameAdapter = new GameCommonAdapter(getActivity());
        mHotGameAdapter.setMore(R.layout.view_more, this);
        mHotGameAdapter.setNoMore(R.layout.view_nomore);
        mHotGameAdapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                mHotGameAdapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                mHotGameAdapter.resumeMore();
            }
        });
    }


    @OnClick({R.id.tv_first, R.id.tv_second})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_first:
                setCheckButton(true);
                mRvList.setAdapter(mNewCommonAdapter);
                break;
            case R.id.tv_second:
                setCheckButton(false);
                mRvList.setAdapter(mHotGameAdapter);
                break;
            default:
                break;
        }
    }

    @Override
    protected void lazyLoad() {
        if (mNewCommonAdapter != null && mNewCommonAdapter.getCount() <= 0) {
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        currentpage = 1;
        mPresenter.getChinesizeGame(currentpage, selectFirst ? 1 : 2);
    }

    @Override
    public void onLoadMore() {
        currentpage++;
        mPresenter.getChinesizeGame(currentpage, selectFirst ? 1 : 2);
    }

    @Override
    public void getNewGame(SaleGameBean saleGameBean) {
        if (saleGameBean != null) {
            if (currentpage == 1) {
                mNewCommonAdapter.clear();
            }
            if (saleGameBean.getList() != null) {
                mNewCommonAdapter.addAll(saleGameBean.getList());
            }
            if (mNewCommonAdapter.getCount() < pageSize) {
                mNewCommonAdapter.stopMore();
            }
            if (mNewCommonAdapter.getCount() == 0) {
                mRvList.showEmpty();
            }
        }
    }

    @Override
    public void getHotGame(SaleGameBean saleGameBean) {
        if (saleGameBean != null) {
            if (currentpage == 1) {
                mHotGameAdapter.clear();
            }
            if (saleGameBean.getList() != null) {
                mHotGameAdapter.addAll(saleGameBean.getList());
            }
            if (mHotGameAdapter.getCount() < pageSize) {
                mHotGameAdapter.stopMore();
            }
            if (mHotGameAdapter.getCount() == 0) {
                mRvList.showEmpty();
            }
        }
    }

    @Override
    public void getFailed(String msg) {
        ToastUtil.show(msg);
        if (currentpage == 1) {
            mRvList.setRefreshing(false);
        }
        if (selectFirst) {
            mNewCommonAdapter.pauseMore();
        } else {
            mHotGameAdapter.pauseMore();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    private void setCheckButton(boolean isFirst) {
        selectFirst = isFirst;
        if (isFirst) {
            mTvFirst.setTextColor(getResources().getColor(R.color.colorBackground));
            mTvSecond.setTextColor(getResources().getColor(R.color.color_888888));
        } else {
            mTvSecond.setTextColor(getResources().getColor(R.color.colorBackground));
            mTvFirst.setTextColor(getResources().getColor(R.color.color_888888));
        }
        onRefresh();
    }
}
