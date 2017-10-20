package com.stx.xhb.dmgameapp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.stx.core.base.BaseFragment;
import com.stx.core.utils.ScreenUtil;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.VideoListAdapter;
import com.stx.xhb.dmgameapp.entity.GameVideoEntity;
import com.stx.xhb.dmgameapp.presenter.video.getGameVideoContract;
import com.stx.xhb.dmgameapp.presenter.video.getGameVideoListImpl;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;

/**
 * Author: Mr.xiao on 2017/9/18
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 游戏详情视频
 */

public class GameVideoFragment extends BaseFragment implements getGameVideoContract.getVideoListView, RecyclerArrayAdapter.OnLoadMoreListener {

    @Bind(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    private VideoListAdapter mVideoListAdapter;
    private String gameId;
    private String gameKey;

    public static GameVideoFragment newInstance(String id, String key) {
        Bundle bundle = new Bundle();
        GameVideoFragment fragment = new GameVideoFragment();
        bundle.putString("id", id);
        bundle.putString("key", key);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.color_eeeeee),
                ScreenUtil.dip2px(getContext(), 12));
        dividerDecoration.setDrawLastItem(false);
        mRecyclerView.addItemDecoration(dividerDecoration);
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

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey("id")) {
                gameId = bundle.getString("id");
            }
            if (bundle.containsKey("key")) {
                gameKey = bundle.getString("key");
            }
        }
    }

    @Override
    protected Class getLogicClazz() {
        return getGameVideoContract.class;
    }

    @Override
    public void getVideoListSuccess(GameVideoEntity videoListEntity) {
        if (videoListEntity != null) {
            if (currentpage == 1) {
                mVideoListAdapter.clear();
            }
            mVideoListAdapter.addAll(videoListEntity.getHtml());
            if (mVideoListAdapter.getCount() < page_size) {
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
        ((getGameVideoListImpl) mPresenter).getVideoList(gameId, gameKey, "3", currentpage);
    }

    @Override
    protected void lazyLoad() {
        currentpage=1;
        ((getGameVideoListImpl) mPresenter).getVideoList(gameId, gameKey, "3", currentpage);
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
}
