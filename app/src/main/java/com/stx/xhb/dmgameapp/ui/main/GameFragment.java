package com.stx.xhb.dmgameapp.ui.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.stx.core.base.BaseFragment;
import com.stx.core.utils.NetUtils;
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
    @Bind(R.id.multiplestatusview)
    MultipleStatusView multiplestatusview;

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
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lazyLoad();
            }
        });
    }

    //设置适配器
    private void setAdapter(List<GameChannelListEntity.HtmlEntity> channelList) {
        GameViewPagerFragmentAdapter adapter = new GameViewPagerFragmentAdapter(getChildFragmentManager(), channelList);
        mVideoViewpager.setAdapter(adapter);
        mVideoViewpager.setOffscreenPageLimit(channelList.size());
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mVideoViewpager);
    }


    @Override
    public void getChannelSuccess(List<GameChannelListEntity.HtmlEntity> channelList) {
        if (multiplestatusview != null) {
            multiplestatusview.showContent();
        }
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
}
