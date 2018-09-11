package com.stx.xhb.dmgameapp.mvp.ui.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.stx.core.base.BaseFragment;
import com.stx.core.base.BaseMvpFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.NewsViewPagerFragmentAdapter;
import com.stx.xhb.dmgameapp.mvp.ui.news.FunsFragment;
import com.stx.xhb.dmgameapp.mvp.ui.news.HotNewsFragment;
import com.stx.xhb.dmgameapp.mvp.ui.news.NewsFragment;
import com.stx.xhb.dmgameapp.mvp.ui.news.OriginalFragment;
import com.stx.xhb.dmgameapp.mvp.ui.news.VideoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 文章的Fragment
 */
public class InfoFragment extends BaseFragment{

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.article_viewpager)
    ViewPager articleViewpager;
    private List<BaseMvpFragment> mFragmentList;
    public static InfoFragment newInstance() {
        return new InfoFragment();
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
        setAdapter();
    }

    //设置适配器
    private void setAdapter() {
        mFragmentList=new ArrayList<>();
        mFragmentList.add(HotNewsFragment.newInstance());
        mFragmentList.add(NewsFragment.newInstance());
        mFragmentList.add(OriginalFragment.newInstance());
        mFragmentList.add(VideoFragment.newInstance());
        mFragmentList.add(FunsFragment.newInstance());
        //实例化适配器
        NewsViewPagerFragmentAdapter adapter = new NewsViewPagerFragmentAdapter(getChildFragmentManager(), mFragmentList);
        //设置适配器
        articleViewpager.setAdapter(adapter);
        articleViewpager.setOffscreenPageLimit(mFragmentList.size());
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(articleViewpager);
    }
}
