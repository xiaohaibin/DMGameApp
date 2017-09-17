package com.stx.xhb.dmgameapp.ui.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.stx.core.base.BaseFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.TabPageIndicatorAdapter;

import java.util.List;

import butterknife.Bind;

import static com.stx.xhb.dmgameapp.R.id.video_viewpager;

/**
 * 视频的Fragment
 */
public class GameFragment extends BaseFragment {


    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(video_viewpager)
    ViewPager mVideoViewpager;
    private TabPageIndicatorAdapter adapter;
    private List<Fragment> fragments;
    private List<String> titleList;

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_video;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
        setListener();
    }

    @Override
    protected Class getLogicClazz() {
        return null;
    }


    //获取控件
    private void initView() {
        mTitle.setText("游戏");
    }

    //初始化数据
    private void initData() {

    }

    //设置适配器
    private void setAdapter() {
        adapter = new TabPageIndicatorAdapter(getFragmentManager(), fragments, titleList);
        mVideoViewpager.setAdapter(adapter);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mVideoViewpager);
    }

    //设置事件监听
    private void setListener() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
