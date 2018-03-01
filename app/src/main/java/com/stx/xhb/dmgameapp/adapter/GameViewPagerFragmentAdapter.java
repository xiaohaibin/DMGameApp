package com.stx.xhb.dmgameapp.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.stx.core.base.BaseMvpFragment;
import com.stx.xhb.dmgameapp.entity.GameChannelListBean;
import com.stx.xhb.dmgameapp.mvp.view.fragment.GameCommonFragment;

import java.util.List;

/**
 * Author: Mr.xiao on 2017/3/5
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 游戏ViewPager适配器
 */
public class GameViewPagerFragmentAdapter extends FragmentStatePagerAdapter {
    private List<GameChannelListBean.HtmlEntity> mNewsTagList;

    public GameViewPagerFragmentAdapter(FragmentManager fm, List<GameChannelListBean.HtmlEntity> newsTagList) {
        super(fm);
        this.mNewsTagList = newsTagList;
    }

    @Override
    public BaseMvpFragment getItem(int position) {
        GameChannelListBean.HtmlEntity entity = mNewsTagList.get(position);
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

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
