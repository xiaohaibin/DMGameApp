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
    @Bind(R.id.multiplestatusview)
    MultipleStatusView multiplestatusview;

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

    private void initView() {
        mTitle.setText("论坛");
        if (multiplestatusview != null) {
            multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lazyLoad();
                }
            });
        }
    }

    //设置适配器
    private void setAdapter(List<ForumChannelListEntity.HtmlEntity> channelList) {
        //实例化适配器
        ForumViewPagerFragmentAdapter adapter = new ForumViewPagerFragmentAdapter(getChildFragmentManager(), channelList);
        //设置适配器
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(channelList.size());
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void getChannelSuccess(List<ForumChannelListEntity.HtmlEntity> channelList) {
        if (multiplestatusview != null) {
            multiplestatusview.showContent();
        }
        setAdapter(channelList);
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
