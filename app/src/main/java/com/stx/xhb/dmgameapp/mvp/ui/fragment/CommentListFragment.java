package com.stx.xhb.dmgameapp.mvp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.utils.ScreenUtil;
import com.stx.core.utils.SoftKeyBoardUtils;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.CommentListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetCommentListContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetCommentListPresenter;
import com.stx.xhb.dmgameapp.mvp.ui.activity.NewsDetailsActivity;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.CommentListAdapter;
import com.stx.xhb.dmgameapp.utils.AppUser;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.stx.xhb.dmgameapp.widget.widget.CustomTitlebar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: xiaohaibin.
 * @time: 2018/1/31
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 评论列表
 */
public class CommentListFragment extends BaseMvpFragment<GetCommentListPresenter> implements SwipeRefreshLayout.OnRefreshListener, GetCommentListContract.View, RecyclerArrayAdapter.OnLoadMoreListener {

    @Bind(R.id.titlebar)
    CustomTitlebar mTitlebar;
    @Bind(R.id.rv_comment_list)
    EasyRecyclerView mRvCommentList;
    @Bind(R.id.ed_comment)
    EditText mEdComment;
    private String url;
    private CommentListAdapter mCommentListAdapter;

    public static CommentListFragment newInstance(String url) {
        Bundle args = new Bundle();
        CommentListFragment fragment = new CommentListFragment();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
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
            if (arguments.containsKey("url")) {
                url = arguments.getString("url");
            }
        }
        mTitlebar.setTilte("全部评论");
        mRvCommentList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvCommentList.addItemDecoration(new DividerDecoration(ContextCompat.getColor(getActivity(), R.color.color_ededed), 1, ScreenUtil.dip2px(getActivity(), 12), ScreenUtil.dip2px(getActivity(), 12)));
        mRvCommentList.setRefreshingColor(Color.rgb(255, 99, 71), Color.rgb(255, 99, 71), Color.rgb(255, 99, 71));
        mRvCommentList.setRefreshListener(this);
        mCommentListAdapter = new CommentListAdapter(getActivity());
        mCommentListAdapter.setNoMore(R.layout.view_nomore);
        mCommentListAdapter.setMore(R.layout.view_more, this);
        mCommentListAdapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                mCommentListAdapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                mCommentListAdapter.resumeMore();
            }
        });
        mTitlebar.setTitleBarOnClickListener(new CustomTitlebar.SimpleOnClickListener() {
            @Override
            public void onClickTitleLeft() {
                ((NewsDetailsActivity) getActivity()).vpContainer.setCurrentItem(0);
            }
        });
        mRvCommentList.setAdapter(mCommentListAdapter);
    }

    @Override
    protected void lazyLoad() {
        getData();
    }

    private void getData() {
        if (!TextUtils.isEmpty(url)) {
            mPresenter.getCommentListData(currentpage, url, AppUser.isLogin() ? AppUser.getUserInfoBean().getUid() : 0);
        }
    }

    @OnClick({R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                String content = mEdComment.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    ToastUtil.show("请输入评论内容");
                    return;
                }
                mPresenter.postComment(url, content, AppUser.isLogin() ? AppUser.getUserInfoBean().getUid() : 0);
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
        }
        if (commentListData != null) {
            mCommentListAdapter.addAll(commentListData.getList());
            if (commentListData.getTotal() <= pageSize) {
                mCommentListAdapter.stopMore();
            }
        }
    }

    @Override
    public void getCommentListDataFailed() {
        mRvCommentList.showEmpty();
        mCommentListAdapter.pauseMore();
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

    @Override
    public void onLoadMore() {
        currentpage++;
        getData();
    }

    @Override
    public void postCommentSuccess() {
        mEdComment.setText("");
        ToastUtil.show("评论成功");
        SoftKeyBoardUtils.closeSoftInput(getContext());
        getData();
    }

    @Override
    public void postCommentFailed(String msg) {
        ToastUtil.show(msg);
    }
}
