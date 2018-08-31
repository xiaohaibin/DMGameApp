package com.stx.xhb.dmgameapp.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.stx.core.base.BaseMvpFragment;
import com.stx.xhb.dmgameapp.data.entity.ForumChannelListBean;
import com.stx.xhb.dmgameapp.mvp.ui.fragment.ForumCommonFragment;

import java.util.List;

/**
 * @author: Mr.xiao on 2017/3/5
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 论坛ViewPager适配器
 */
public class ForumViewPagerFragmentAdapter extends FragmentStatePagerAdapter {
    private List<ForumChannelListBean.HtmlEntity> mNewsTagList;

    public ForumViewPagerFragmentAdapter(FragmentManager fm, List<ForumChannelListBean.HtmlEntity> newsTagList) {
        super(fm);
        this.mNewsTagList = newsTagList;
    }

    @Override
    public BaseMvpFragment getItem(int position) {
        ForumChannelListBean.HtmlEntity entity = mNewsTagList.get(position);
        return ForumCommonFragment.newInstance(entity.getFid());
    }

    @Override
    public int getCount() {
        return mNewsTagList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mNewsTagList.get(position % mNewsTagList.size()).getName();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
