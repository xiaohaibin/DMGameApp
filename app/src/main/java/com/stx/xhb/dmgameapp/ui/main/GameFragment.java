package com.stx.xhb.dmgameapp.ui.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.stx.core.base.BaseFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.GameViewPagerFragmentAdapter;
import com.stx.xhb.dmgameapp.entity.GameChannelListEntity;
import com.stx.xhb.dmgameapp.presenter.game.getGameChannelContract;
import com.stx.xhb.dmgameapp.presenter.game.getGameChannelImpl;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import java.util.List;

import butterknife.Bind;

/**
 * 视频的Fragment
 */
public class GameFragment extends BaseFragment implements getGameChannelContract.getChannelListView {


    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.video_viewpager)
    ViewPager mVideoViewpager;
    private GameViewPagerFragmentAdapter adapter;

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
    protected Class getLogicClazz() {
        return getGameChannelContract.class;
    }

    @Override
    protected void lazyLoad() {
        ((getGameChannelImpl) mPresenter).getChannelList();
    }

    //获取控件
    private void initView() {
        mTitle.setText("游戏");
    }

    //设置适配器
    private void setAdapter(List<GameChannelListEntity.HtmlEntity> channelList) {
        adapter = new GameViewPagerFragmentAdapter(getChildFragmentManager(), channelList);
        mVideoViewpager.setAdapter(adapter);
        mVideoViewpager.setOffscreenPageLimit(channelList.size());
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mVideoViewpager);
    }


    @Override
    public void getChannelSuccess(List<GameChannelListEntity.HtmlEntity> channelList) {
        if (channelList.size() > 4) {
            channelList.remove(4);
            setAdapter(channelList);
        } else {
            setAdapter(channelList);
        }
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
