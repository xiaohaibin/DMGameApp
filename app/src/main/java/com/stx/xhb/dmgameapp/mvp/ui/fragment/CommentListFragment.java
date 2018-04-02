package com.stx.xhb.dmgameapp.mvp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.utils.ScreenUtil;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.CommentListAdapter;
import com.stx.xhb.dmgameapp.entity.CommentListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetCommentListContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetCommentListPresenter;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.stx.xhb.dmgameapp.widget.widget.CustomTitlebar;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author: xiaohaibin.
 * @time: 2018/1/31
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class CommentListFragment extends BaseMvpFragment<GetCommentListPresenter> implements SwipeRefreshLayout.OnRefreshListener, GetCommentListContract.View {

    @Bind(R.id.titlebar)
    CustomTitlebar mTitlebar;
    @Bind(R.id.rv_comment_list)
    EasyRecyclerView mRvCommentList;
    private String mId;
    private CommentListAdapter mCommentListAdapter;

    public static CommentListFragment newInstance(String id) {
        Bundle args = new Bundle();
        CommentListFragment fragment = new CommentListFragment();
        args.putString("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected GetCommentListPresenter onLoadPresenter() {
        return new GetCommentListPresenter();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_comment_list;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.containsKey("id")) {
                mId = arguments.getString("id");
            }
        }
        mTitlebar.setTilte("全部评论");
        mRvCommentList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvCommentList.addItemDecoration(new DividerDecoration(ContextCompat.getColor(getActivity(), R.color.color_ededed), 1, ScreenUtil.dip2px(getActivity(), 12), ScreenUtil.dip2px(getActivity(), 12)));
        mRvCommentList.setRefreshingColor(Color.rgb(255, 99, 71), Color.rgb(255, 99, 71), Color.rgb(255, 99, 71));
        mRvCommentList.setRefreshListener(this);
        mCommentListAdapter = new CommentListAdapter(getActivity());
        mCommentListAdapter.setNoMore(R.layout.view_nomore);
        mRvCommentList.setAdapter(mCommentListAdapter);
    }

    @Override
    protected void lazyLoad() {
        getData();
    }

    private void getData() {
        if (!TextUtils.isEmpty(mId)) {
            mPresenter.getCommentListData(mId);
        }
    }

    @OnClick({R.id.btn_comment, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_comment:
                ToastUtil.show("评论");
                break;
            case R.id.btn_send:
                ToastUtil.show("发送");
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        getData();
    }

    @Override
    public void setCommentListData(CommentListBean commentListData) {
        if (currentpage == 1) {
            mCommentListAdapter.clear();
            mCommentListAdapter.addAll(commentListData.getComments());
        }
        if (commentListData.getCmt_sum() <= pageSize) {
            mCommentListAdapter.stopMore();
        }
    }

    @Override
    public void getCommentListDataFailed() {
        mRvCommentList.showEmpty();
    }

    @Override
    public void showLoading() {
        if (currentpage == 1) {
            mRvCommentList.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        if (currentpage == 1) {
            mRvCommentList.setRefreshing(false);
        }
    }
}
