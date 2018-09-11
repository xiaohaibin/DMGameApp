package com.stx.xhb.dmgameapp.mvp.ui.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.stx.core.base.BaseFragment;
import com.stx.core.base.BaseMvpFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.GameViewPagerFragmentAdapter;
import com.stx.xhb.dmgameapp.mvp.ui.news.HotNewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 视频的Fragment
 */
public class GameFragment extends BaseFragment{


    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.video_viewpager)
    ViewPager mVideoViewpager;
    private List<BaseMvpFragment> mFragmentList;


    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_game;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
    }

    @Override
    protected void lazyLoad() {
    }

    //获取控件
    private void initView() {
        mTitle.setText("游戏");
        setAdapter();
    }

    //设置适配器
    private void setAdapter() {
        mFragmentList=new ArrayList<>();
        mFragmentList.add(HotNewsFragment.newInstance());
        mFragmentList.add(HotNewsFragment.newInstance());
        mFragmentList.add(HotNewsFragment.newInstance());
        mFragmentList.add(HotNewsFragment.newInstance());
        mFragmentList.add(HotNewsFragment.newInstance());
        GameViewPagerFragmentAdapter adapter = new GameViewPagerFragmentAdapter(getChildFragmentManager(),mFragmentList);
        mVideoViewpager.setAdapter(adapter);
        mVideoViewpager.setOffscreenPageLimit(mFragmentList.size());
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mVideoViewpager);
    }
}
