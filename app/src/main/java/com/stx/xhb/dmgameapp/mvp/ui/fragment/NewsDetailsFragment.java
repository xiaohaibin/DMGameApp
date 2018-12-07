package com.stx.xhb.dmgameapp.mvp.ui.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.utils.ScreenUtil;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.CommentListBean;
import com.stx.xhb.dmgameapp.data.entity.NewsAboutBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetNewsDetailsContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetNewsDetailsPresenter;
import com.stx.xhb.dmgameapp.mvp.ui.activity.NewsDetailsActivity;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.NewsDetailsAdapter;
import com.stx.xhb.dmgameapp.share.ShareDialog;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.stx.xhb.dmgameapp.widget.widget.CustomTitlebar;

import java.util.Objects;

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
    private String mKey;
    private String mUrl;
    private String mImg;
    private int uid=0;
    private NewsDetailsAdapter mNewsDetailsAdapter;
    private String mAccurl;

    public static NewsDetailsFragment newInstance(String webUrl,String accurl,String key, String img) {
        Bundle args = new Bundle();
        NewsDetailsFragment fragment = new NewsDetailsFragment();
        args.putString("webUrl", webUrl);
        args.putString("accurl", accurl);
        args.putString("key", key);
        args.putString("img", img);
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
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
            if (arguments.containsKey("key")) {
                mKey = arguments.getString("key");
                mTitlebar.setTilte(mKey);
            }
            if (arguments.containsKey("webUrl")) {
                mUrl = arguments.getString("webUrl");
            }
            if (arguments.containsKey("accurl")){
                mAccurl = arguments.getString("accurl");
            }
            if (arguments.containsKey("img")) {
                mImg = arguments.getString("img");
            }
        }
        mNewsDetailsAdapter.setOnClickMoreCommentListener(new NewsDetailsAdapter.OnClickMoreCommentListener() {
            @Override
            public void onClick() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    ((NewsDetailsActivity)Objects.requireNonNull(getActivity())).vpContainer.setCurrentItem(1);
                }else {
                    ((NewsDetailsActivity)(getActivity())).vpContainer.setCurrentItem(1);
                }
            }
        });
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
        mPresenter.getNewsDetailsData(mAccurl);
    }

    @Override
    public void setNewsDetailsData(NewsAboutBean newsAboutBean) {
        mPresenter.getCommentListData(1,mAccurl,uid);
        if (newsAboutBean.getList()!=null&&!newsAboutBean.getList().isEmpty()) {
            mNewsDetailsAdapter.addListLabel("相关内容");
            mNewsDetailsAdapter.addNewList(newsAboutBean.getList());
        }
    }

    @Override
    public void getNewsDetailsDataFailed(String msg) {
        mPresenter.getCommentListData(1,mAccurl,uid);
    }

    @Override
    public void getCommentListDataFailed(String msg) {
        mNewsDetailsAdapter.addEmptyCommentFooter();
    }

    @Override
    public void setCommentListData(CommentListBean commentListData) {
        if (commentListData.getTotal() > 100) {
            tvCommentCount.setText(String.valueOf("99+"));
        } else {
            tvCommentCount.setText(String.valueOf(commentListData.getTotal()));
        }
        mNewsDetailsAdapter.addListLabel("最新评论");
        mNewsDetailsAdapter.addAll(commentListData.getList());
        mNewsDetailsAdapter.addMoreCommentFooter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @OnClick({R.id.btn_comment, R.id.btn_share,R.id.tv_comment_count})
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
            case R.id.tv_comment_count:
                ((NewsDetailsActivity)(getActivity())).vpContainer.setCurrentItem(1);
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
