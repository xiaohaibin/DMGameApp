package com.stx.xhb.dmgameapp.mvp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.utils.ScreenUtil;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.NewsDetailsAdapter;
import com.stx.xhb.dmgameapp.data.entity.CommentListBean;
import com.stx.xhb.dmgameapp.data.entity.NewsListBean;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetNewsDetailsContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetNewsDetailsPresenter;
import com.stx.xhb.dmgameapp.share.ShareDialog;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.stx.xhb.dmgameapp.widget.widget.CustomTitlebar;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author: xiaohaibin.
 * @time: 2018/2/27
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 资讯详情页
 */

public class NewsDetailsFragment extends BaseMvpFragment<GetNewsDetailsPresenter> implements GetNewsDetailsContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.titlebar)
    CustomTitlebar mTitlebar;
    @Bind(R.id.rv_list)
    EasyRecyclerView rvList;
    @Bind(R.id.tv_comment_count)
    TextView tvCommentCount;
    private String mId;
    private String mKey;
    private String mUrl;
    private String mImg;
    private NewsDetailsAdapter mNewsDetailsAdapter;

    public static NewsDetailsFragment newInstance(String url, String id,String key, String img) {
        Bundle args = new Bundle();
        NewsDetailsFragment fragment = new NewsDetailsFragment();
        args.putString("url", url);
        args.putString("id", id);
        args.putString("key", key);
        args.putString("img", img);
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
                mUrl = arguments.getString("url");
            }
            if (arguments.containsKey("img")) {
                mImg = arguments.getString("img");
            }
        }
    }

    private void initView() {
        rvList.addItemDecoration(new DividerDecoration(ContextCompat.getColor(getActivity(), R.color.color_ededed), 1, ScreenUtil.dip2px(getActivity(), 12), ScreenUtil.dip2px(getActivity(), 12)));
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNewsDetailsAdapter = new NewsDetailsAdapter(getActivity());
        rvList.setAdapter(mNewsDetailsAdapter);
        rvList.setRefreshingColor(Color.rgb(255, 99, 71), Color.rgb(255, 99, 71), Color.rgb(255, 99, 71));
        rvList.setRefreshListener(this);
        mTitlebar.setTitleBarOnClickListener(new CustomTitlebar.SimpleOnClickListener() {
            @Override
            public void onClickTitleLeft() {
                getActivity().finish();
            }
        });
    }

    @Override
    protected void lazyLoad() {
        mNewsDetailsAdapter.setWebData(mUrl);
        mPresenter.getNewsDetailsData(mId, mKey);
        mPresenter.getCommentListData(mId);
    }

    @Override
    public void setNewsDetailsData(List<NewsPageBean.DataBean.ListBean> listEntity) {
        mNewsDetailsAdapter.addNewList(listEntity);
        mNewsDetailsAdapter.addCommentListLabel();
    }

    @Override
    public void setCommentListData(CommentListBean commentListData) {
        if (commentListData.getComments().size() > 100) {
            tvCommentCount.setText("99+");
        } else {
            tvCommentCount.setText(String.valueOf(commentListData.getComments().size()));
        }
        mNewsDetailsAdapter.addAll(commentListData.getComments());
        mNewsDetailsAdapter.addMoreCommentFooter();
    }

    @Override
    public void getNewsDetailsDataFailed() {
        mNewsDetailsAdapter.addCommentListLabel();
    }

    @Override
    public void getCommentListDataFailed() {
        mNewsDetailsAdapter.addEmptyCommentFooter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @OnClick({R.id.btn_comment, R.id.btn_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //评论对话框
            case R.id.btn_comment:
                ToastUtil.show("评论");
                break;
            //分享
            case R.id.btn_share:
                if (!TextUtils.isEmpty(mUrl)) {
                    ShareDialog.share(getFragmentManager(), mKey, mUrl, mKey, mImg);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        mNewsDetailsAdapter.clear();
        mNewsDetailsAdapter.removeAllHeader();
        mNewsDetailsAdapter.removeAllFooter();
        lazyLoad();
    }
}
