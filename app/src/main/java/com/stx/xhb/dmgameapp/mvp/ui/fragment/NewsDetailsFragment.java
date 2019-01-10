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
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.utils.ScreenUtil;
import com.stx.core.widget.dialog.DialogMaker;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.CommentListBean;
import com.stx.xhb.dmgameapp.data.entity.NewsAboutBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetNewsDetailsContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetNewsDetailsPresenter;
import com.stx.xhb.dmgameapp.mvp.ui.activity.LoginActivity;
import com.stx.xhb.dmgameapp.mvp.ui.activity.NewsDetailsActivity;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.NewsDetailsAdapter;
import com.stx.xhb.dmgameapp.mvp.ui.dialog.PostCommentDilaog;
import com.stx.xhb.dmgameapp.share.ShareDialog;
import com.stx.xhb.dmgameapp.utils.AppUser;
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
    private NewsDetailsAdapter mNewsDetailsAdapter;
    private String mAccurl;
    private PostCommentDilaog mPostCommentDilaog;

    public static NewsDetailsFragment newInstance(String webUrl, String accurl, String key, String img) {
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
            if (arguments.containsKey("accurl")) {
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
                    ((NewsDetailsActivity) Objects.requireNonNull(getActivity())).vpContainer.setCurrentItem(1);
                } else {
                    ((NewsDetailsActivity) (getActivity())).vpContainer.setCurrentItem(1);
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
        getHotComment();
        if (newsAboutBean.getList() != null && !newsAboutBean.getList().isEmpty()) {
            mNewsDetailsAdapter.addListLabel("相关内容");
            mNewsDetailsAdapter.addNewList(newsAboutBean.getList());
        }
    }

    @Override
    public void getNewsDetailsDataFailed(String msg) {
        getHotComment();
    }

    private void getHotComment() {
        mPresenter.getCommentListData(1, mAccurl, AppUser.isLogin() ? AppUser.getUserInfoBean().getUid() : 0);
    }

    @Override
    public void getCommentListDataFailed(String msg) {
        mNewsDetailsAdapter.addListLabel("最新评论");
        mNewsDetailsAdapter.addEmptyCommentFooter();
    }

    @Override
    public void setCommentListData(CommentListBean commentListData) {
        mNewsDetailsAdapter.addListLabel("最新评论");
        if (commentListData.getTotal() == 0) {
            mNewsDetailsAdapter.addEmptyCommentFooter();
            return;
        }
        if (commentListData.getTotal() > 100) {
            tvCommentCount.setText(String.valueOf("99+"));
        } else {
            tvCommentCount.setText(String.valueOf(commentListData.getTotal()));
        }
        mNewsDetailsAdapter.addAll(commentListData.getList());
        mNewsDetailsAdapter.addMoreCommentFooter();
    }

    @Override
    public void showLoading() {
        DialogMaker.showProgressDialog(getContext(), "请稍候...");
    }

    @Override
    public void hideLoading() {
        DialogMaker.dismissProgressDialog();
    }


    @OnClick({R.id.btn_comment, R.id.btn_share, R.id.tv_comment_count})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //评论对话框
            case R.id.btn_comment:
                if (!AppUser.isLogin()) {
                    ToastUtil.show("请登录");
                    LoginActivity.start(getContext());
                    return;
                }
                if (mPostCommentDilaog == null) {
                    mPostCommentDilaog = PostCommentDilaog.newInstance();
                }
                mPostCommentDilaog.show(getFragmentManager(), null);
                mPostCommentDilaog.setOnPostCommentListener(new PostCommentDilaog.onPostCommentListener() {
                    @Override
                    public void onPost(String content) {
                        mPresenter.postComment(mAccurl, content, AppUser.getUserInfoBean().getUid());
                    }
                });
                break;
            //分享
            case R.id.btn_share:
                if (!TextUtils.isEmpty(mUrl)) {
                    ShareDialog.share(getFragmentManager(), mKey, mUrl, mKey, mImg);
                }
                break;
            case R.id.tv_comment_count:
                ((NewsDetailsActivity) (getActivity())).vpContainer.setCurrentItem(1);
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

    @Override
    public void postCommentSuccess() {
        ToastUtil.show("评论成功");
        if (mPostCommentDilaog != null) {
            mPostCommentDilaog.dismiss();
            mPostCommentDilaog.clearContent();
        }
    }

    @Override
    public void postCommentFailed(String msg) {
        ToastUtil.show(msg);
    }
}
