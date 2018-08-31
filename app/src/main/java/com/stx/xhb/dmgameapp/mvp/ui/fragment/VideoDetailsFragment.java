package com.stx.xhb.dmgameapp.mvp.ui.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.utils.ScreenUtil;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.VideoDetailsAdapter;
import com.stx.xhb.dmgameapp.data.entity.CommentListBean;
import com.stx.xhb.dmgameapp.data.entity.VideoListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetVideoDetailsContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetVideoDetailsPresenter;
import com.stx.xhb.dmgameapp.share.ShareDialog;
import com.stx.xhb.dmgameapp.widget.widget.CustomTitlebar;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author：xiaohaibin
 * Time：2017/9/21
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：视频详情
 */

public class VideoDetailsFragment extends BaseMvpFragment<GetVideoDetailsPresenter> implements GetVideoDetailsContract.View, SwipeRefreshLayout.OnRefreshListener {


    @Bind(R.id.web_video)
    WebView mWebVideo;
    @Bind(R.id.titlebar)
    CustomTitlebar mTitlebar;
    @Bind(R.id.rv_list)
    EasyRecyclerView mRvList;
    @Bind(R.id.tv_comment_count)
    TextView mTvCommentCount;
    private String mId;
    private String mKey;
    private String mUrl;
    private String mImg;
    private VideoDetailsAdapter mVideoDetailsAdapter;

    public static VideoDetailsFragment newInstance(String url, String id, String key, String img) {
        Bundle args = new Bundle();
        VideoDetailsFragment fragment = new VideoDetailsFragment();
        args.putString("url", url);
        args.putString("id", id);
        args.putString("key", key);
        args.putString("img", img);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_video_detail;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
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
        initView();
    }

    private void initView() {
        WebSettings settings = mWebVideo.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存
        settings.setAppCacheEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //不显示webview缩放按钮
        settings.setDisplayZoomControls(false);
        // 解决HTTPS协议下出现的mixed content问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setGeolocationEnabled(true);
        mWebVideo.loadUrl(mUrl);

        mRvList.addItemDecoration(new DividerDecoration(ContextCompat.getColor(getActivity(), R.color.color_ededed), 1, ScreenUtil.dip2px(getActivity(), 12), ScreenUtil.dip2px(getActivity(), 12)));
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.setRefreshingColor(Color.rgb(255, 99, 71), Color.rgb(255, 99, 71), Color.rgb(255, 99, 71));
        mRvList.setRefreshListener(this);
        mVideoDetailsAdapter = new VideoDetailsAdapter(getActivity());
        mRvList.setAdapter(mVideoDetailsAdapter);
        mTitlebar.setTitleBarOnClickListener(new CustomTitlebar.SimpleOnClickListener() {
            @Override
            public void onClickTitleLeft() {
                getActivity().finish();
            }
        });
    }

    @Override
    protected void lazyLoad() {
        mPresenter.getVideoDetailsData(mId, mKey);
        mPresenter.getCommentListData(mId);
    }

    @Override
    public void setVideoDetailsData(List<VideoListBean.VideoBean> listEntity) {
        mVideoDetailsAdapter.addVideoList(listEntity);
        mVideoDetailsAdapter.addCommentListLabel();
    }

    @Override
    public void setCommentListData(CommentListBean commentListData) {
        if (commentListData.getComments().size() > 100) {
            mTvCommentCount.setText("99+");
        } else {
            mTvCommentCount.setText(String.valueOf(commentListData.getComments().size()));
        }
        mVideoDetailsAdapter.addAll(commentListData.getComments());
        mVideoDetailsAdapter.addMoreCommentFooter();
    }

    @Override
    public void getVideoDetailsDataFailed() {
        mVideoDetailsAdapter.addCommentListLabel();
    }

    @Override
    public void getCommentListDataFailed() {
        mVideoDetailsAdapter.addEmptyCommentFooter();
    }

    @Override
    public void showLoading() {
        if (currentpage == 1) {
            mRvList.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        if (currentpage == 1) {
            mRvList.setRefreshing(false);
        }
    }

    @Override
    protected GetVideoDetailsPresenter onLoadPresenter() {
        return new GetVideoDetailsPresenter();
    }

    @Override
    public void onRefresh() {
        mVideoDetailsAdapter.clear();
        mVideoDetailsAdapter.removeAllHeader();
        mVideoDetailsAdapter.removeAllFooter();
        lazyLoad();
    }


    @OnClick({R.id.btn_comment, R.id.tv_comment_count, R.id.btn_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_comment:
                break;
            case R.id.tv_comment_count:
                break;
            case R.id.btn_share:
                if (!TextUtils.isEmpty(mUrl)) {
                    ShareDialog.share(getFragmentManager(), mKey, mUrl, mKey, mImg);
                }
                break;
            default:
                break;
        }
    }
}
