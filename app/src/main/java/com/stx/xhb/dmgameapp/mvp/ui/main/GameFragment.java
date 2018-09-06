package com.stx.xhb.dmgameapp.mvp.ui.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.stx.core.base.BaseFragment;
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.utils.NetUtils;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.GameChannelListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetGameChannelContract;
import com.stx.xhb.dmgameapp.mvp.presenter.GetGameChannelPresenter;
import com.stx.xhb.dmgameapp.adapter.GameViewPagerFragmentAdapter;
import com.stx.xhb.dmgameapp.mvp.ui.fragment.NewsCommonFragment;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

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
        mFragmentList.add(NewsCommonFragment.newInstance());
        mFragmentList.add(NewsCommonFragment.newInstance());
        mFragmentList.add(NewsCommonFragment.newInstance());
        mFragmentList.add(NewsCommonFragment.newInstance());
        mFragmentList.add(NewsCommonFragment.newInstance());
        GameViewPagerFragmentAdapter adapter = new GameViewPagerFragmentAdapter(getChildFragmentManager(),mFragmentList);
        mVideoViewpager.setAdapter(adapter);
        mVideoViewpager.setOffscreenPageLimit(mFragmentList.size());
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mVideoViewpager);
    }
}
