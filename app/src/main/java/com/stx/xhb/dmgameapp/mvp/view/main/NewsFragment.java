package com.stx.xhb.dmgameapp.mvp.view.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.utils.NetUtils;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.NewsChannelListEntity;
import com.stx.xhb.dmgameapp.mvp.contract.GetNewsChannelContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetNewsChannelPresenter;
import com.stx.xhb.dmgameapp.adapter.NewsViewPagerFragmentAdapter;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import java.util.List;

import butterknife.Bind;

/**
 * 文章的Fragment
 */
public class NewsFragment extends BaseMvpFragment<GetNewsChannelPresenter> implements GetNewsChannelContract.getChannelListView {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.article_viewpager)
    ViewPager articleViewpager;
    @Bind(R.id.multiplestatusview)
    MultipleStatusView multiplestatusview;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_news;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
    }

    //获取控件
    private void initView() {
        mTitle.setText("资讯");
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    lazyLoad();
            }
        });
    }

    @Override
    protected void lazyLoad() {
        mPresenter.getChannelList();
    }

    //设置适配器
    private void setAdapter(List<NewsChannelListEntity.HtmlEntity> channelList) {
        //实例化适配器
        NewsViewPagerFragmentAdapter adapter = new NewsViewPagerFragmentAdapter(getChildFragmentManager(), channelList);
        //设置适配器
        articleViewpager.setAdapter(adapter);
        articleViewpager.setOffscreenPageLimit(channelList.size());
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(articleViewpager);
    }

    @Override
    public void getChannelSuccess(List<NewsChannelListEntity.HtmlEntity> channelList) {
        if (multiplestatusview!=null){
            multiplestatusview.showContent();
        }
        setAdapter(channelList);
    }

    @Override
    public void getChanelFailed(String msg) {
        ToastUtil.show(msg);
        if (multiplestatusview != null) {
            multiplestatusview.showError();
        }
    }

    @Override
    public void showLoading() {
        if (multiplestatusview != null) {
            multiplestatusview.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (multiplestatusview != null) {
            if (!NetUtils.isNetConnected(getActivity())) {
                multiplestatusview.showNoNetwork();
            }
        }
    }

    @Override
    protected GetNewsChannelPresenter onLoadPresenter() {
        return new GetNewsChannelPresenter();
    }
}
