package com.stx.xhb.dmgameapp.mvp.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.stx.core.base.BaseMvpFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.NewsDetailsAdapter;
import com.stx.xhb.dmgameapp.entity.CommentListBean;
import com.stx.xhb.dmgameapp.entity.NewsListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetNewsDetailsContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetNewsDetailsPresenter;
import com.stx.xhb.dmgameapp.widget.widget.CustomTitlebar;

import java.util.List;

import butterknife.Bind;

/**
 * @author: xiaohaibin.
 * @time: 2018/2/27
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 资讯详情页
 */

public class NewsDetailsFragment extends BaseMvpFragment<GetNewsDetailsPresenter> implements GetNewsDetailsContract.View {

    @Bind(R.id.ll_comment)
    LinearLayout mLlComment;
    @Bind(R.id.titlebar)
    CustomTitlebar mTitlebar;
    @Bind(R.id.rv_list)
    EasyRecyclerView rvList;
    private String mId;
    private String mKey;
    private NewsDetailsAdapter mNewsDetailsAdapter;
    private String mMUrl;

    public static NewsDetailsFragment newInstance(String url, String id, String key) {
        Bundle args = new Bundle();
        NewsDetailsFragment fragment = new NewsDetailsFragment();
        args.putString("url", url);
        args.putString("id", id);
        args.putString("key", key);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected GetNewsDetailsPresenter onLoadPresenter() {
        return new GetNewsDetailsPresenter();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_news_details_list;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.containsKey("id")) {
                mId = arguments.getString("id");
            }
            if (arguments.containsKey("key")) {
                mKey = arguments.getString("key");
                mTitlebar.setTilte(mKey);
            }
            if (arguments.containsKey("url")) {
                mMUrl = arguments.getString("url");
            }
        }
    }

    private void initView() {
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setRefreshing(false);
        mNewsDetailsAdapter = new NewsDetailsAdapter(getActivity());
        rvList.setAdapter(mNewsDetailsAdapter);
        mTitlebar.setTitleBarOnClickListener(new CustomTitlebar.SimpleOnClickListener(){
            @Override
            public void onClickTitleLeft() {
                getActivity().finish();
            }

            @Override
            public void onClickTitleRight() {
                mNewsDetailsAdapter.clear();
                lazyLoad();
            }
        });
    }

    @Override
    protected void lazyLoad() {
        mNewsDetailsAdapter.setWebData(mMUrl);
        mPresenter.getNewsDetailsData(mId, mKey);
        mPresenter.getCommentListData(mId);
    }

    @Override
    public void setNewsDetailsData(List<NewsListBean.ChannelEntity.HtmlEntity> listEntity) {
        mNewsDetailsAdapter.setNewList(listEntity);
        mNewsDetailsAdapter.setCommentListLabel();
    }

    @Override
    public void setCommentListData(CommentListBean commentListData) {
        mNewsDetailsAdapter.addAll(commentListData.getComments());
    }

    @Override
    public void getNewsDetailsDataFailed() {
        mNewsDetailsAdapter.setCommentListLabel();
    }

    @Override
    public void getCommentListDataFailed() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
