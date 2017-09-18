package com.stx.xhb.dmgameapp.ui.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.stx.core.base.BaseFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.ForumChannelListEntity;
import com.stx.xhb.dmgameapp.presenter.forum.ForumContract;
import com.stx.xhb.dmgameapp.presenter.forum.ForumImpl;
import com.stx.xhb.dmgameapp.ui.fragment.ForumCommonFragment;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 论坛的Fragment
 */
public class ForumFragment extends BaseFragment implements ForumContract.getChannelListView {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager_forum)
    ViewPager mViewPager;
//    private TabPageIndicatorAdapter adapter;
    private List<Fragment> fragments;
    private List<String> titleList;


    public static ForumFragment newInstance() {
        return new ForumFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_forum;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initData();
        initView();
        setListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ForumImpl) mPresenter).getChannelList();
    }

    @Override
    protected Class getLogicClazz() {
        return ForumContract.class;
    }

    private void initData() {
        fragments = new ArrayList<>();
        titleList = new ArrayList<>();
    }

    //获取控件
    private void initView() {
        mTitle.setText("论坛");
    }

    //设置适配器
    private void setAdapter() {
        //实例化适配器
//        adapter = new TabPageIndicatorAdapter(getFragmentManager(), fragments, titleList);
        //设置适配器
//        mViewPager.setAdapter(adapter);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    //设置监听
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
    public void getChannelSuccess(List<ForumChannelListEntity.HtmlEntity> channelList) {
        fragments.clear();
        titleList.clear();
        for (int i = 0; i < channelList.size(); i++) {
            fragments.add(ForumCommonFragment.newInstance(channelList.get(i).getFid()));
            titleList.add(channelList.get(i).getName());
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
