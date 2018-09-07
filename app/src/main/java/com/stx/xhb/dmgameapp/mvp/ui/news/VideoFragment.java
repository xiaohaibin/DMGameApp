package com.stx.xhb.dmgameapp.mvp.ui.news;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.utils.ScreenUtil;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.data.entity.VideoListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetVideoContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetVideoListPresenter;
import com.stx.xhb.dmgameapp.adapter.VideoListAdapter;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;

/**
 * Author: Mr.xiao on 2017/9/18
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 视频
 */
public class VideoFragment extends BaseMvpFragment<GetVideoListPresenter> implements GetVideoContract.getVideoListView, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.id_stickynavlayout_innerscrollview)
    EasyRecyclerView mRecyclerView;
    private VideoListAdapter mVideoListAdapter;

    public static VideoFragment newInstance() {
        return new VideoFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.color_eeeeee),
                ScreenUtil.dip2px(getContext(), 12));
        dividerDecoration.setDrawLastItem(false);
        mRecyclerView.addItemDecoration(dividerDecoration);
        mRecyclerView.setRefreshingColor(Color.rgb(255, 99, 71), Color.rgb(255, 99, 71), Color.rgb(255, 99, 71));
        mRecyclerView.setRefreshListener(this);
        mVideoListAdapter = new VideoListAdapter(getActivity());
        mVideoListAdapter.setMore(R.layout.view_more, this);
        mVideoListAdapter.setNoMore(R.layout.view_nomore);
        mVideoListAdapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                mVideoListAdapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                mVideoListAdapter.resumeMore();
            }
        });
        mRecyclerView.setAdapter(mVideoListAdapter);
    }


    @Override
    public void getVideoListSuccess(NewsPageBean videoListBean) {
        if (videoListBean != null) {
            if (currentpage == 1) {
                mVideoListAdapter.clear();
            }
            mVideoListAdapter.addAll(videoListBean.getList());
            if (mVideoListAdapter.getCount() < pageSize) {
                mVideoListAdapter.stopMore();
            }
            if (mVideoListAdapter.getCount() == 0) {
                mRecyclerView.showEmpty();
            }
        }
    }

    @Override
    public void getVideoListFailed(String msg) {
        ToastUtil.show(msg);
    }


    @Override
    public void onLoadMore() {
        currentpage++;
        mPresenter.getVideoList(currentpage);
    }

    @Override
    public void onRefresh() {
        currentpage = 1;
        mPresenter.getVideoList(currentpage);
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
    protected GetVideoListPresenter onLoadPresenter() {
        return new GetVideoListPresenter();
    }
}
