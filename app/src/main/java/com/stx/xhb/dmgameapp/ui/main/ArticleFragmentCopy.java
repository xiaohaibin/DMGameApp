package com.stx.xhb.dmgameapp.ui.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.stx.core.base.BaseFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.TabPageIndicatorAdapter;
import com.stx.xhb.dmgameapp.entity.NewsChannelListEntity;
import com.stx.xhb.dmgameapp.presenter.news.NewsContract;
import com.stx.xhb.dmgameapp.presenter.news.NewsImpl;
import com.stx.xhb.dmgameapp.ui.fragment.CommonFragment;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 文章的Fragment
 */
public class ArticleFragmentCopy extends BaseFragment implements NewsImpl.getChannelListView {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.article_viewpager)
    ViewPager article_viewpager;
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
        return R.layout.fragment_article;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initData();
        initView();
        setListener();
    }

    @Override
    protected Class getLogicClazz() {
        return NewsContract.class;
    }

    private void initData() {
        fragments = new ArrayList<>();
        titleList = new ArrayList<>();
    }

    //获取控件
    private void initView() {
        mTitle.setText("最新");
    }

    @Override
    public void onResume() {
        super.onResume();
        ((NewsImpl) mPresenter).getChannelList();
    }

    //设置适配器
    private void setAdapter() {
        //实例化适配器
        adapter = new TabPageIndicatorAdapter(getFragmentManager(), fragments, titleList);
        //设置适配器
        article_viewpager.setAdapter(adapter);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(article_viewpager);
    }

    //设置监听
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

    @Override
    public void getChannelSuccess(List<NewsChannelListEntity.HtmlEntity> channelList) {
        fragments.clear();
        titleList.clear();
        for (int i = 0; i < channelList.size(); i++) {
            fragments.add(CommonFragment.newInstance(channelList.get(i).getAppid()));
            titleList.add(channelList.get(i).getTitle());
        }
        setAdapter();
    }

    @Override
    public void getChanelFailed(String msg) {
        ToastUtil.show(msg);
    }
}
