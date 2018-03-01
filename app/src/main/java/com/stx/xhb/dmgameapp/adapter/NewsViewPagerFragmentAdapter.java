package com.stx.xhb.dmgameapp.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.stx.core.base.BaseMvpFragment;
import com.stx.xhb.dmgameapp.entity.NewsChannelListBean;
import com.stx.xhb.dmgameapp.mvp.view.fragment.NewsCommonFragment;
import com.stx.xhb.dmgameapp.mvp.view.fragment.VideoFragment;

import java.util.List;

/**
 * Author: Mr.xiao on 2017/3/5
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 资讯ViewPager适配器
 */
public class NewsViewPagerFragmentAdapter extends FragmentStatePagerAdapter {
    private List<NewsChannelListBean.HtmlEntity> mNewsTagList;

    public NewsViewPagerFragmentAdapter(FragmentManager fm, List<NewsChannelListBean.HtmlEntity> newsTagList) {
        super(fm);
        this.mNewsTagList = newsTagList;
    }

    @Override
    public BaseMvpFragment getItem(int position) {
        NewsChannelListBean.HtmlEntity entity = mNewsTagList.get(position);
        if ("2".equals(entity.getAppid())) {
            return VideoFragment.newInstance();
        } else {
            return NewsCommonFragment.newInstance(entity.getAppid());
        }
    }

    @Override
    public int getCount() {
        return mNewsTagList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mNewsTagList.get(position % mNewsTagList.size()).getTitle();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
