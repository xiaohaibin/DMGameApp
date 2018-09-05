package com.stx.xhb.dmgameapp.mvp.ui.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.mvp.IPresenter;
import com.stx.core.utils.NetUtils;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.NewsChannelListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetNewsChannelContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetNewsChannelPresenter;
import com.stx.xhb.dmgameapp.adapter.NewsViewPagerFragmentAdapter;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import java.util.List;

import butterknife.Bind;

/**
 * 文章的Fragment
 */
public class NewsFragment extends BaseMvpFragment{

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.article_viewpager)
    ViewPager articleViewpager;
    private List<BaseMvpFragment> mFragmentList;

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
    }

    //设置适配器
    private void setAdapter() {
        //实例化适配器
        NewsViewPagerFragmentAdapter adapter = new NewsViewPagerFragmentAdapter(getChildFragmentManager(), mFragmentList);
        //设置适配器
        articleViewpager.setAdapter(adapter);
        articleViewpager.setOffscreenPageLimit(mFragmentList.size());
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(articleViewpager);
    }

    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }
}
