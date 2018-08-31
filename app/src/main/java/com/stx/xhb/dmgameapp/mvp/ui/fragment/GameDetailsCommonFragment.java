package com.stx.xhb.dmgameapp.mvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.utils.ScreenUtil;
import com.stx.core.widget.dialog.DialogMaker;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.NewsListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetGameDetailsCommonContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetGameDetailsCommonPresenter;
import com.stx.xhb.dmgameapp.adapter.GameNewsListAdapter;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.stx.xhb.dmgameapp.widget.CustomLoadMoreView;

import java.util.List;

import butterknife.Bind;

/**
 * Author : jxnk25
 * Time: 2017/10/19 0019
 * Email:xhb_199409@163.com
 * Email:xhb_199409@163.com
 * Github:https://github.com/xiaohaibin/
 * Drscribe:游戏资讯
 */

public class GameDetailsCommonFragment extends BaseMvpFragment<GetGameDetailsCommonPresenter> implements GetGameDetailsCommonContract.getGameDetailsDataView, BaseQuickAdapter.RequestLoadMoreListener {

    @Bind(R.id.id_stickynavlayout_innerscrollview)
    RecyclerView mRecyclerView;
    private String type = "";
    private String id = "";
    private String key = "";
    private GameNewsListAdapter gameNewsListAdapter;

    public static GameDetailsCommonFragment newInstance(String type, String id, String key) {
        Bundle args = new Bundle();
        args.putString("type", type);
        args.putString("id", id);
        args.putString("key", key);
        GameDetailsCommonFragment fragment = new GameDetailsCommonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_common_recyclerview;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initVariable();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.color_eeeeee),
                ScreenUtil.dip2px(getContext(), 1), ScreenUtil.dip2px(getContext(), 12), ScreenUtil.dip2px(getContext(), 12));
        dividerDecoration.setDrawLastItem(false);
        mRecyclerView.addItemDecoration(dividerDecoration);
        setAdapter();
    }

    private void initVariable() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey("type")) {
                type = bundle.getString("type");
            }
            if (bundle.containsKey("id")) {
                id = bundle.getString("id");
            }
            if (bundle.containsKey("key")) {
                key = bundle.getString("key");
            }
        }
    }


    @Override
    protected void lazyLoad() {
        currentpage = 1;
        getListData();
    }

    private void getListData() {
        switch (type) {
            //新闻
            case "1":
                mPresenter.getGameNewsListData("1", id, key, currentpage);
                break;
            //攻略
            case "2":
                mPresenter.getGameToolsListData("2", id, key, currentpage);
                break;
            default:
                mPresenter.getGameNewsListData("1", id, key, currentpage);
                break;
        }
    }

    private void setAdapter() {
        gameNewsListAdapter = new GameNewsListAdapter(getActivity());
        gameNewsListAdapter.setOnLoadMoreListener(this, mRecyclerView);
        gameNewsListAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        gameNewsListAdapter.setLoadMoreView(new CustomLoadMoreView());
        mRecyclerView.setAdapter(gameNewsListAdapter);
    }

    @Override
    public void showLoading() {
        if (currentpage == 1) {
            DialogMaker.showProgressDialog(getActivity(), "正在加载...");
        }
    }

    @Override
    public void hideLoading() {
        if (currentpage == 1) {
            DialogMaker.dismissProgressDialog();
        }
        gameNewsListAdapter.loadMoreComplete();
    }

    @Override
    public void getGameNewsListDataSuccess(List<NewsListBean.ChannelEntity.HtmlEntity> list) {
        if (list != null) {
            if (currentpage == 1) {
                gameNewsListAdapter.setNewData(list);
            } else {
                gameNewsListAdapter.addData(list);
            }
            if (list.isEmpty()) {
                gameNewsListAdapter.setEmptyView(R.layout.view_empty);
            }
        }
    }

    @Override
    public void getGameNewsListFailed(String msg) {
        ToastUtil.show(msg);
        gameNewsListAdapter.loadMoreFail();
    }

    @Override
    public void getGameToolsListDataSuccess(List<NewsListBean.ChannelEntity.HtmlEntity> list) {
        if (list != null) {
            if (currentpage == 1) {
                gameNewsListAdapter.setNewData(list);
            } else {
                gameNewsListAdapter.addData(list);
            }
            if (list.isEmpty()) {
                gameNewsListAdapter.setEmptyView(R.layout.view_empty);
            }
            if (gameNewsListAdapter.getData().size() < pageSize) {
                gameNewsListAdapter.loadMoreEnd(true);
            } else {
                gameNewsListAdapter.loadMoreEnd(false);
            }
        }
    }

    @Override
    public void getGameToolsListFailed(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void onLoadMoreRequested() {
        currentpage++;
        getListData();
    }

    @Override
    protected GetGameDetailsCommonPresenter onLoadPresenter() {
        return new GetGameDetailsCommonPresenter();
    }
}
