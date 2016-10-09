package com.stx.xhb.dmgameapp;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.iflytek.autoupdate.IFlytekUpdate;
import com.iflytek.autoupdate.IFlytekUpdateListener;
import com.iflytek.autoupdate.UpdateConstants;
import com.iflytek.autoupdate.UpdateErrorCode;
import com.iflytek.autoupdate.UpdateInfo;
import com.iflytek.autoupdate.UpdateType;
import com.stx.xhb.dmgameapp.adapter.MainFragmentPageAdapter;
import com.stx.xhb.dmgameapp.fragment.ArticleFragment;
import com.stx.xhb.dmgameapp.fragment.ForumFragment;
import com.stx.xhb.dmgameapp.fragment.GameFragment;
import com.stx.xhb.dmgameapp.fragment.VideoFragment;
import com.stx.xhb.dmgameapp.utils.SystemBarTintManager;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.stx.xhb.dmgameapp.view.TipsToast;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ViewPager main_viewPager;
    private List<Fragment> fragemnts = new ArrayList<>();
    private MainFragmentPageAdapter adapter;
    private RadioGroup rgp;
    private TipsToast tipsToast;
    //退出时间
    private long exitTime = 0;
    private IFlytekUpdate mUpdManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWindow();
        initView();
        initData();
        setAdapter();
        setListener();
        checkUpdate();
    }

    private void checkUpdate() {
        //初始化自动更新对象
        mUpdManager = IFlytekUpdate.getInstance(this);
        //开启调试模式，默认不开启
        mUpdManager.setDebugMode(true);
        //开启wifi环境下检测更新，仅对自动更新有效，强制更新则生效
        mUpdManager.setParameter(UpdateConstants.EXTRA_WIFIONLY, "true");
        //设置通知栏使用应用icon，详情请见示例
        mUpdManager.setParameter(UpdateConstants.EXTRA_NOTI_ICON, "true");
        //设置更新提示类型，默认为通知栏提示
        mUpdManager.setParameter(UpdateConstants.EXTRA_STYLE, UpdateConstants.UPDATE_UI_DIALOG);
        // 启动自动更新
        mUpdManager.autoUpdate(MainActivity.this, new IFlytekUpdateListener() {
            @Override
            public void onResult(int errorcode, UpdateInfo result) {
                Log.i("---->UPDAT",result.toString());
                if (errorcode == UpdateErrorCode.OK && result != null) {
                    if (result.getUpdateType() == UpdateType.NoNeed) {
                        return;
                    }
                    mUpdManager.showUpdateInfo(MainActivity.this, result);
                } else {
                    ToastUtil.showShort(MainActivity.this, "请求更新失败\n更新错误码：" + errorcode);
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    //初始化窗体布局
    private void initWindow() {
        SystemBarTintManager tintManager;
        //由于沉浸式状态栏需要在Android4.4.4以上才能使用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorBackground));
            tintManager.setStatusBarTintEnabled(true);
        }
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
        GameFragment game_Fragment = new GameFragment();
        VideoFragment video_Fragment = new VideoFragment();
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

    @Override
    protected void onStop() {
        super.onStop();
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
