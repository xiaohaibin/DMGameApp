package com.stx.xhb.dmgameapp.ui.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.stx.core.base.BaseFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.ForumViewPagerFragmentAdapter;
import com.stx.xhb.dmgameapp.entity.ForumChannelListEntity;
import com.stx.xhb.dmgameapp.presenter.forum.getForumChannelContract;
import com.stx.xhb.dmgameapp.presenter.forum.getForumChannelImpl;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import java.util.List;

import butterknife.Bind;

/**
 * 论坛的Fragment
 */
public class ForumFragment extends BaseFragment implements getForumChannelContract.getChannelListView {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager_forum)
    ViewPager mViewPager;
    private ForumViewPagerFragmentAdapter adapter;


    public static ForumFragment newInstance() {
        return new ForumFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_forum;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
    }

    @Override
    protected void lazyLoad() {
        ((getForumChannelImpl) mPresenter).getChannelList();
    }

    @Override
    protected Class getLogicClazz() {
        return getForumChannelContract.class;
    }

    //获取控件
    private void initView() {
        mTitle.setText("论坛");
    }

    //设置适配器
    private void setAdapter(List<ForumChannelListEntity.HtmlEntity> channelList) {
        //实例化适配器
        adapter = new ForumViewPagerFragmentAdapter(getChildFragmentManager(),channelList);
        //设置适配器
        mViewPager.setAdapter(adapter);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void getChannelSuccess(List<ForumChannelListEntity.HtmlEntity> channelList) {
        setAdapter(channelList);
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
