package com.stx.xhb.dmgameapp;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jaeger.library.StatusBarUtil;
import com.stx.xhb.dmgameapp.adapter.MainFragmentPageAdapter;
import com.stx.xhb.dmgameapp.ui.main.ArticleFragment;
import com.stx.xhb.dmgameapp.ui.main.ForumFragment;
import com.stx.xhb.dmgameapp.ui.main.UserFragment;
import com.stx.xhb.dmgameapp.ui.main.GameFragment;
import com.stx.xhb.dmgameapp.widget.TipsToast;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager main_viewPager;
    private List<Fragment> fragemnts = new ArrayList<>();
    private MainFragmentPageAdapter adapter;
    private RadioGroup rgp;
    private TipsToast tipsToast;
    //退出时间
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.colorPrimary));
        initView();
        initData();
        setAdapter();
        setListener();
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    //初始化控件
    private void initView() {
        main_viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        //设置viewpager的预加载页数，viewpager默认只会预加载左右两边的页面数据
        main_viewPager.setOffscreenPageLimit(4);
        rgp = (RadioGroup) findViewById(R.id.main_rgp);
        //设置默认第一个为选中状态
        RadioButton rb = (RadioButton) rgp.getChildAt(0);
        rb.setChecked(true);
    }

    //初始化数据
    private void initData() {
        ArticleFragment airticle_fragemnt = new ArticleFragment();
        ForumFragment forum_Fragment = new ForumFragment();
        UserFragment game_Fragment = new UserFragment();
        GameFragment video_Fragment = new GameFragment();
        fragemnts.add(airticle_fragemnt);//文章
        fragemnts.add(video_Fragment);//视频
        fragemnts.add(forum_Fragment);//论坛
        fragemnts.add(game_Fragment);//游戏
    }

    //设置适配器
    private void setAdapter() {
        //实例化适配器
        adapter = new MainFragmentPageAdapter(getSupportFragmentManager(), fragemnts);
        //设置适配器
        main_viewPager.setAdapter(adapter);
    }

    //设置监听
    private void setListener() {
        //viewPager的滑动监听
        main_viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //获取当前位置的RadioButton
                RadioButton rb = (RadioButton) rgp.getChildAt(position);
                //设置为true
                rb.setChecked(true);
            }
        });
        //RadioGroup的事件监听
        rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int index = 0; index < rgp.getChildCount(); index++) {
                    RadioButton rb = (RadioButton) rgp.getChildAt(index);
                    if (rb.isChecked()) {
                        main_viewPager.setCurrentItem(index, false);
                        break;
                    }
                }
            }
        });
    }

    /**
     * 按两次退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showTips(R.drawable.tips_smile, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    /**
     * 自定义toast
     *
     * @param iconResId 图片
     * @param tips      提示文字
     */
    private void showTips(int iconResId, String tips) {
        if (tipsToast != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                tipsToast.cancel();
            }
        } else {
            tipsToast = TipsToast.makeText(MainActivity.this.getApplication()
                    .getBaseContext(), tips, TipsToast.LENGTH_SHORT);
        }
        tipsToast.show();
        tipsToast.setIcon(iconResId);
        tipsToast.setText(tips);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
        //此处解决有时候出现getActivity（）出现null的情况
    }
}
