package com.stx.xhb.dmgameapp.ui.main;


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
import com.stx.xhb.dmgameapp.ui.fragment.CommonFragment;
import com.stx.xhb.dmgameapp.ui.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章的Fragment
 */
public class ArticleFragment extends Fragment {
    //标题
    private static final String[] TITLE = new String[]{"新闻", "杂谈", "评测", "前瞻",
            "原创", "盘点", "硬件", "时事"};
    //分类id集合
    private static final int[] TYPE_ID = new int[]{
            151, 154, 153, 196, 197, 152, 199
    };
    private View view;
    private ViewPager article_viewpager;
    private TabPageIndicatorAdapter adapter;
    //fragment的集合
    private List<Fragment> fragments;
    private TextView tv_title;
    private TabLayout mTabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_article, container, false);
        initData();
        initView();
        setAdapter();
        setListener();
        return view;
    }

    //获取控件
    private void initView() {
        //获取到标题栏控件
        tv_title = (TextView) view.findViewById(R.id.title);
        tv_title.setText("文章");
        article_viewpager = (ViewPager) view.findViewById(R.id.article_viewpager);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
    }

    //初始化数据
    private void initData() {
        fragments=new ArrayList<>();
        NewsFragment newsFragment = new NewsFragment();//新闻
        fragments.add(newsFragment);
        //循环创建7个子fragment
        for (int i = 0; i < TYPE_ID.length; i++) {
            CommonFragment fragment = new CommonFragment();//杂谈
            Bundle bundle = new Bundle();
            bundle.putInt("typeid", TYPE_ID[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
    }

    //设置适配器
    private void setAdapter() {
        //实例化适配器
//        adapter = new TabPageIndicatorAdapter(getFragmentManager(), fragments, TITLE);
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
