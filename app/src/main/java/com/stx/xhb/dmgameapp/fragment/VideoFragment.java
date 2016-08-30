package com.stx.xhb.dmgameapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.TabPageIndicatorAdapter;
import com.stx.xhb.dmgameapp.fragment.innerFragments.CommondFragment;
import com.viewpagerindicator.TabPageIndicator;

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
    private TabPageIndicator indicator;
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
        //隐藏toolbar menu控件
        ImageButton main_action_menu= (ImageButton) view.findViewById(R.id.main_action_menu);
        main_action_menu.setVisibility(View.GONE);
        //获取到标题栏控件
        tv_title = (TextView) view.findViewById(R.id.title);
        tv_title.setText("视频");
        video_viewpager = (ViewPager) view.findViewById(R.id.video_viewpager);
        //实例化indicator
        indicator = (TabPageIndicator) view.findViewById(R.id.video_indicator);
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
        indicator.setViewPager(video_viewpager);
    }

    //设置事件监听
    private void setListener() {
        //indicator的事件处理方法
        indicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                tv_title.setText(TITLE[position]);
            }
        });
    }

}
