package com.stx.xhb.dmgameapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.activities.SettingActivity;
import com.stx.xhb.dmgameapp.adapter.TabPageIndicatorAdapter;
import com.stx.xhb.dmgameapp.fragment.innerFragments.CommondFragment;
import com.stx.xhb.dmgameapp.fragment.innerFragments.NewsFragment;
import com.viewpagerindicator.TabPageIndicator;

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
    private TabPageIndicator indicator;
    private TabPageIndicatorAdapter adapter;
    //fragment的集合
    private List<Fragment> fragments = new ArrayList<>();
    private TextView tv_title;
    private ImageButton main_action_menu;

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
        //隐藏toolbar menu控件
        main_action_menu = (ImageButton) view.findViewById(R.id.main_action_menu);
        //获取到标题栏控件
        tv_title = (TextView) view.findViewById(R.id.title);
        tv_title.setText("文章");
        article_viewpager = (ViewPager) view.findViewById(R.id.article_viewpager);
        //实例化TabPageIndicator然后设置ViewPager与之关联
        indicator = (TabPageIndicator) view.findViewById(R.id.article_indicator);

    }

    //初始化数据
    private void initData() {

        NewsFragment newsFragment = new NewsFragment();//新闻
        fragments.add(newsFragment);
        //循环创建7个子fragment
        for (int i = 0; i < TYPE_ID.length; i++) {
            CommondFragment fragment = new CommondFragment();//杂谈
            Bundle bundle = new Bundle();
            bundle.putInt("typeid", TYPE_ID[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
    }

    //设置适配器
    private void setAdapter() {
        //实例化适配器
        adapter = new TabPageIndicatorAdapter(getFragmentManager(), fragments, TITLE);
        //设置适配器
        article_viewpager.setAdapter(adapter);
        indicator.setViewPager(article_viewpager, 0);
    }

    //设置监听
    private void setListener() {
        //indicator的滑动监听事件
        indicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //动态改变标题栏文字
                tv_title.setText(TITLE[position]);
            }
        });
        //main_action_menu的点击事件
        main_action_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到个人设置界面
                Intent intent=new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
    }


}
