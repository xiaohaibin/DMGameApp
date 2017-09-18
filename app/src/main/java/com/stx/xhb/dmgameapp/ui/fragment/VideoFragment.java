package com.stx.xhb.dmgameapp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.stx.core.base.BaseFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.VideoListEntity;
import com.stx.xhb.dmgameapp.presenter.video.getVideoContract;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;

/**
 * Author: Mr.xiao on 2017/9/18
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class VideoFragment extends BaseFragment implements getVideoContract.getVideoListView, RecyclerArrayAdapter.OnLoadMoreListener, android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;

    public static NewsCommonFragment newInstance() {
        return new NewsCommonFragment();
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setRefreshingColor(Color.rgb(0, 140, 240), Color.rgb(0, 140, 240), Color.rgb(0, 140, 240));
        mRecyclerView.setRefreshListener(this);
    }

    @Override
    protected Class getLogicClazz() {
        return getVideoContract.class;
    }

    @Override
    public void getVideoListSuccess(VideoListEntity videoListEntity) {
    }

    @Override
    public void getVideoListFailed(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
