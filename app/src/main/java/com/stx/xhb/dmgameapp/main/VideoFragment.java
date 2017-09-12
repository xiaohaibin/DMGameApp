package com.stx.xhb.dmgameapp.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.TabPageIndicatorAdapter;
import com.stx.xhb.dmgameapp.fragment.CommondFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频的Fragment
 */
public class VideoFragment extends Fragment {
    //标题
    private static final String[] TITLE = new String[]{"视频", "游戏", "恶搞", "原创",
            "电影", "试玩", "预告", "评测", "攻略", "解说", "周边"};
    //分类id集合
    private static final int[] TYPE_ID = new int[]{
            194, 180, 221, 214, 213, 212, 211, 210, 203, 256, 201
    };
    private View view;
    private ViewPager video_viewpager;
    private TabLayout mTabLayout;
    private TabPageIndicatorAdapter adapter;
    //fragment的集合
    private List<Fragment> fragments = new ArrayList<>();
    private TextView tv_title;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container, false);
        initView();
        initData();
        setAdapter();
        setListener();
        return view;
    }

    //获取控件
    private void initView() {
        //获取到标题栏控件
        tv_title = (TextView) view.findViewById(R.id.title);
        tv_title.setText("视频");
        video_viewpager = (ViewPager) view.findViewById(R.id.video_viewpager);
        //实例化indicator
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
    }

    //初始化数据
    private void initData() {
        //循环创建11个Fragment
        for (int i = 0; i < TYPE_ID.length; i++) {
            CommondFragment fragment = new CommondFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("typeid", TYPE_ID[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

    }

    //设置适配器
    private void setAdapter() {
        adapter = new TabPageIndicatorAdapter(getFragmentManager(), fragments, TITLE);
        video_viewpager.setAdapter(adapter);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(video_viewpager);
    }

    //设置事件监听
    private void setListener() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //动态改变标题栏文字
                tv_title.setText(TITLE[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
