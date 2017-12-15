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
import com.stx.xhb.dmgameapp.ui.main.NewsFragment;
import com.stx.xhb.dmgameapp.ui.main.ForumFragment;
import com.stx.xhb.dmgameapp.ui.main.GameFragment;
import com.stx.xhb.dmgameapp.ui.main.UserFragment;
import com.stx.xhb.dmgameapp.widget.TipsToast;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mainViewpager;
    private List<Fragment> fragemnts ;
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
        mainViewpager = (ViewPager) findViewById(R.id.main_viewpager);
        rgp = (RadioGroup) findViewById(R.id.main_rgp);
        //设置默认第一个为选中状态
        RadioButton rb = (RadioButton) rgp.getChildAt(0);
        rb.setChecked(true);
    }

    //初始化数据
    private void initData() {
        fragemnts = new ArrayList<>();
        fragemnts.add(NewsFragment.newInstance());
        fragemnts.add(GameFragment.newInstance());
        fragemnts.add(ForumFragment.newInstance());
        fragemnts.add(UserFragment.newInstance());
    }

    //设置适配器
    private void setAdapter() {
        //实例化适配器
        MainFragmentPageAdapter adapter = new MainFragmentPageAdapter(getSupportFragmentManager(), fragemnts);
        mainViewpager.setOffscreenPageLimit(fragemnts.size());
        //设置适配器
        mainViewpager.setAdapter(adapter);
    }

    //设置监听
    private void setListener() {
        //viewPager的滑动监听
        mainViewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
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
                        mainViewpager.setCurrentItem(index, false);
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
