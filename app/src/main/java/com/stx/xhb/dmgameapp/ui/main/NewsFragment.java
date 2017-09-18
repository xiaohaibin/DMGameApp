package com.stx.xhb.dmgameapp.ui.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.stx.core.base.BaseFragment;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.NewsViewPagerFragmentAdapter;
import com.stx.xhb.dmgameapp.entity.NewsChannelListEntity;
import com.stx.xhb.dmgameapp.presenter.news.NewsContract;
import com.stx.xhb.dmgameapp.presenter.news.NewsImpl;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import java.util.List;

import butterknife.Bind;

/**
 * 文章的Fragment
 */
public class NewsFragment extends BaseFragment implements NewsImpl.getChannelListView {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.article_viewpager)
    ViewPager article_viewpager;
    private NewsViewPagerFragmentAdapter adapter;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_news;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
        setListener();
    }

    @Override
    protected Class getLogicClazz() {
        return NewsContract.class;
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
    private void setAdapter(List<NewsChannelListEntity.HtmlEntity> channelList) {
        //实例化适配器
        adapter = new NewsViewPagerFragmentAdapter(getFragmentManager(), channelList);
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
