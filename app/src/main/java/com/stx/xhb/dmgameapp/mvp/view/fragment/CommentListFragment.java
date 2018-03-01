package com.stx.xhb.dmgameapp.mvp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.mvp.IPresenter;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.widget.widget.CustomTitlebar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: xiaohaibin.
 * @time: 2018/1/31
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class CommentListFragment extends BaseMvpFragment {

    @Bind(R.id.titlebar)
    CustomTitlebar mTitlebar;
    @Bind(R.id.rv_comment_list)
    EasyRecyclerView mRvCommentList;
    @Bind(R.id.ll_comment)
    LinearLayout mLlComment;

    public static CommentListFragment newInstance() {
        Bundle args = new Bundle();
        CommentListFragment fragment = new CommentListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_comment_list;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        mTitlebar.setTilte("全部评论");
    }
}
