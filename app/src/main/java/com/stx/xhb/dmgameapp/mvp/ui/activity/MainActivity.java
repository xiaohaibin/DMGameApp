package com.stx.xhb.dmgameapp.mvp.ui.activity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jaeger.library.StatusBarUtil;
import com.stx.core.utils.AppManager;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.base.BaseAppActitity;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.MainFragmentPageAdapter;
import com.stx.xhb.dmgameapp.mvp.ui.main.ForumFragment;
import com.stx.xhb.dmgameapp.mvp.ui.main.GameFragment;
import com.stx.xhb.dmgameapp.mvp.ui.main.InfoFragment;
import com.stx.xhb.dmgameapp.mvp.ui.main.UserFragment;
import com.stx.xhb.dmgameapp.widget.TipsToast;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiao.haibin
 */
public class MainActivity extends BaseAppActitity {

    private ViewPager mainViewpager;
    private List<Fragment> fragemnts;
    private RadioGroup rgp;
    private TipsToast tipsToast;
    private long exitTime = 0;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        requestPemissions();
        initView();
        initData();
        setAdapter();
        setListener();
    }


    //初始化控件
    private void initView() {
        mainViewpager = findViewById(R.id.main_viewpager);
        rgp = findViewById(R.id.main_rgp);
        //设置默认第一个为选中状态
        RadioButton rb = (RadioButton) rgp.getChildAt(0);
        rb.setChecked(true);
    }

    //初始化数据
    private void initData() {
        fragemnts = new ArrayList<>();
        fragemnts.add(InfoFragment.newInstance());
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
                AppManager.getAppManager().finishAllActivity();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 自定义toast
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

    private void requestPemissions() {
        requestPermission(new OnPermissionResponseListener() {
            @Override
            public void onSuccess(String[] permissions) {

            }

            @Override
            public void onFail() {
                startAppSettings();
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE);
    }
}
