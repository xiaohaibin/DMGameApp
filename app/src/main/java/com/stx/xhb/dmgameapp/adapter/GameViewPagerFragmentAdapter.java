package com.stx.xhb.dmgameapp.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.stx.core.base.BaseFragment;
import com.stx.xhb.dmgameapp.entity.GameChannelListEntity;
import com.stx.xhb.dmgameapp.ui.fragment.GameCommonFragment;

import java.util.List;

/**
 * Author: Mr.xiao on 2017/3/5
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 游戏ViewPager适配器
 */
public class GameViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private List<GameChannelListEntity.HtmlEntity> mNewsTagList;

    public GameViewPagerFragmentAdapter(FragmentManager fm, List<GameChannelListEntity.HtmlEntity> newsTagList) {
        super(fm);
        this.mNewsTagList = newsTagList;
    }

    @Override
    public BaseFragment getItem(int position) {
        GameChannelListEntity.HtmlEntity entity = mNewsTagList.get(position);
        return GameCommonFragment.newInstance(entity.getAppid());
    }

    @Override
    public int getCount() {
        return mNewsTagList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mNewsTagList.get(position % mNewsTagList.size()).getTitle();
    }

}
