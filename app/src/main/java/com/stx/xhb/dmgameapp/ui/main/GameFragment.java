package com.stx.xhb.dmgameapp.ui.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.stx.core.base.BaseFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.GameChannelListEntity;
import com.stx.xhb.dmgameapp.presenter.game.GameContract;
import com.stx.xhb.dmgameapp.presenter.game.GameImpl;
import com.stx.xhb.dmgameapp.ui.fragment.GameCommonFragment;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 视频的Fragment
 */
public class GameFragment extends BaseFragment implements GameContract.getChannelListView {


    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.video_viewpager)
    ViewPager mVideoViewpager;
//    private TabPageIndicatorAdapter adapter;
    private List<Fragment> fragments;
    private List<String> titleList;

    public static GameFragment newInstance() {
        return new GameFragment();
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_game;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initData();
        initView();
        setListener();
    }

    @Override
    protected Class getLogicClazz() {
        return GameContract.class;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((GameImpl) mPresenter).getChannelList();
    }

    //获取控件
    private void initView() {
        mTitle.setText("游戏");
    }

    //初始化数据
    private void initData() {
        fragments = new ArrayList<>();
        titleList = new ArrayList<>();
    }

    //设置适配器
    private void setAdapter() {
//        adapter = new TabPageIndicatorAdapter(getFragmentManager(), fragments, titleList);
//        mVideoViewpager.setAdapter(adapter);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mVideoViewpager);
    }

    //设置事件监听
    private void setListener() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mTitle.setText(titleList.get(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void getChannelSuccess(List<GameChannelListEntity.HtmlEntity> channelList) {
        fragments.clear();
        titleList.clear();
        for (int i = 0; i < channelList.size(); i++) {
            fragments.add(GameCommonFragment.newInstance(channelList.get(i).getAppid()));
            titleList.add(channelList.get(i).getTitle());
        }
        setAdapter();
    }

    @Override
    public void getChanelFailed(String msg) {
        ToastUtil.show(msg);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
