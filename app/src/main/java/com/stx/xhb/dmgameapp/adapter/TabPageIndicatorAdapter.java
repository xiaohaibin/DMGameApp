package com.stx.xhb.dmgameapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xhb on 2016/1/18.
 * 文章界面的Fragment适配器
 */
public class TabPageIndicatorAdapter extends FragmentPagerAdapter {
    //fragments集合
    private List<Fragment> fragments;

    private List<String> channelist;

    public TabPageIndicatorAdapter(FragmentManager fm, List<Fragment> fragments, List<String> channelist) {
        super(fm);
        this.fragments = fragments;
        this.channelist = channelist;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return channelist.size();
    }

    //获取当前位置的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return channelist.get(position % channelist.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
