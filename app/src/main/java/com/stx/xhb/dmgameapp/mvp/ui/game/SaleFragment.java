package com.stx.xhb.dmgameapp.mvp.ui.game;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
import com.stx.xhb.dmgameapp.mvp.contract.GetSaleListContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetSaleListPresenter;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.GameCommonAdapter;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.NewsCommonAdapter;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/7
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 发售
 */
public class SaleFragment extends BaseMvpFragment<GetSaleListPresenter> implements GetSaleListContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    @Bind(R.id.tv_first)
    TextView mTvSale;
    @Bind(R.id.tv_second)
    TextView mTvUnSale;
    @Bind(R.id.rv_list)
    EasyRecyclerView mRecyclerView;
    private GameCommonAdapter mGameCommonAdapter;
    private GameCommonAdapter mUnSaleGameAdapter;
    private boolean selectSale = true;

    public static SaleFragment newInstance() {
        return new SaleFragment();
    }

    @NonNull
    @Override
    protected GetSaleListPresenter onLoadPresenter() {
        return new GetSaleListPresenter();
    }

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_tab;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setRefreshingColor(Color.rgb(255, 99, 71), Color.rgb(255, 99, 71), Color.rgb(255, 99, 71));
        mRecyclerView.setRefreshListener(this);
        mRecyclerView.addItemDecoration(new DividerDecoration(R.color.divider, 1));
        //初始化已售游戏列表适配器
        mGameCommonAdapter = new GameCommonAdapter(getActivity());
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
        mRecyclerView.setAdapter(mGameCommonAdapter);
        //初始化未售游戏列表适配器
        mUnSaleGameAdapter = new GameCommonAdapter(getActivity());
        mUnSaleGameAdapter.setMore(R.layout.view_more, this);
        mUnSaleGameAdapter.setNoMore(R.layout.view_nomore);
        mUnSaleGameAdapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                mUnSaleGameAdapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                mUnSaleGameAdapter.resumeMore();
            }
        });
    }

    @Override
    protected void lazyLoad() {
        if (mGameCommonAdapter != null && mGameCommonAdapter.getCount() <= 0) {
            onRefresh();
        }
    }


    @OnClick({R.id.tv_first, R.id.tv_second})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_first:
                setCheckButton(true);
                mRecyclerView.setAdapter(mGameCommonAdapter);
                break;
            case R.id.tv_second:
                setCheckButton(false);
                mRecyclerView.setAdapter(mUnSaleGameAdapter);
                break;
            default:
                break;
        }
    }

    @Override
    public void getSaleList(SaleGameBean saleGameBean) {
        if (saleGameBean != null) {
            if (currentpage == 1) {
                mGameCommonAdapter.clear();
            }
            if (saleGameBean.getList() != null) {
                mGameCommonAdapter.addAll(saleGameBean.getList());
            }
            if (mGameCommonAdapter.getCount() < pageSize) {
                mGameCommonAdapter.stopMore();
            }
            if (mGameCommonAdapter.getCount() == 0) {
                mRecyclerView.showEmpty();
            }
        }
    }

    @Override
    public void getUnSaleList(SaleGameBean saleGameBean) {
        if (saleGameBean != null) {
            if (currentpage == 1) {
                mUnSaleGameAdapter.clear();
            }
            if (saleGameBean.getList() != null) {
                mUnSaleGameAdapter.addAll(saleGameBean.getList());
            }
            if (mUnSaleGameAdapter.getCount() < pageSize) {
                mUnSaleGameAdapter.stopMore();
            }
            if (mUnSaleGameAdapter.getCount() == 0) {
                mRecyclerView.showEmpty();
            }
        }
    }

    @Override
    public void getFailed(String msg) {
        ToastUtil.show(msg);
        if (currentpage == 1) {
            mRecyclerView.setRefreshing(false);
        }
        if (selectSale) {
            mGameCommonAdapter.pauseMore();
        } else {
            mUnSaleGameAdapter.pauseMore();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onRefresh() {
        currentpage = 1;
        if (selectSale) {
            mPresenter.getSaleList(currentpage);
        } else {
            mPresenter.getUnSaleList(currentpage);
        }
    }

    @Override
    public void onLoadMore() {
        currentpage++;
        if (selectSale) {
            mPresenter.getSaleList(currentpage);
        } else {
            mPresenter.getUnSaleList(currentpage);
        }
    }

    private void setCheckButton(boolean isFirst) {
        selectSale = isFirst;
        if (isFirst) {
            mTvSale.setTextColor(getResources().getColor(R.color.colorBackground));
            mTvUnSale.setTextColor(getResources().getColor(R.color.color_888888));
        } else {
            mTvUnSale.setTextColor(getResources().getColor(R.color.colorBackground));
            mTvSale.setTextColor(getResources().getColor(R.color.color_888888));
        }
        onRefresh();
    }
}
