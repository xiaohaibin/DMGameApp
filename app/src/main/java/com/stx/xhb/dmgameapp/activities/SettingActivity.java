package com.stx.xhb.dmgameapp.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.autoupdate.IFlytekUpdate;
import com.iflytek.autoupdate.IFlytekUpdateListener;
import com.iflytek.autoupdate.UpdateConstants;
import com.iflytek.autoupdate.UpdateErrorCode;
import com.iflytek.autoupdate.UpdateInfo;
import com.iflytek.autoupdate.UpdateType;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.utils.DataCleanManager;
import com.stx.xhb.dmgameapp.utils.SystemBarTintManager;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.stx.xhb.dmgameapp.utils.VersionUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人设置界面
 */
public class SettingActivity extends AppCompatActivity {

    @Bind(R.id.setting_toolbar)
    Toolbar settingToolbar;
    @Bind(R.id.version)
    TextView version;
    @Bind(R.id.tv_cache)
    TextView mTvCache;
    private IFlytekUpdate mUpdManager;
    private Toast mToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initWindow();
        initView();
        setListener();
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
        version.setText(VersionUtils.getVersion(this));
        setSupportActionBar(settingToolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorBackground)));
        //设置显示返回上一级的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置标题
        getSupportActionBar().setTitle("设置");
        //设置标题栏字体颜色
        settingToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        settingToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        mTvCache.setText(DataCleanManager.getTotalCacheSize(this));
        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }

    //点击事件
    @OnClick({R.id.setting_iv_clearCache, R.id.setting_iv_version, R.id.setting_iv_heart, R.id.setting_iv_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_iv_clearCache://清理缓存
                if ("0 KB".equals(DataCleanManager.getTotalCacheSize(this))) {
                    ToastUtil.showAtCenter(SettingActivity.this, "暂无缓存");
                } else {
                    DataCleanManager.clearAllCache(this);
                    mTvCache.setText(DataCleanManager.getTotalCacheSize(this));
                    ToastUtil.showAtCenter(SettingActivity.this, "缓存清理成功");
                }
                break;
            case R.id.setting_iv_version://版本更新
                checkUpdate();
                break;
            case R.id.setting_iv_heart://评分
                Uri uri = Uri.parse("http://www.wandoujia.com/apps/com.stx.xhb.dmgameapp");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.setting_iv_about://关于
                Intent about = new Intent(this, AboutActivity.class);
                startActivity(about);
                break;
        }
    }

    //设置事件监听
    private void setListener() {
        //toolbar的点击事件
        settingToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onResume(this);       //统计时长

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
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
        mUpdManager.autoUpdate(SettingActivity.this, new IFlytekUpdateListener() {
            @Override
            public void onResult(int errorcode, UpdateInfo result) {
                Log.i("----result", result.getUpdateType() + "");
                if (UpdateErrorCode.OK == errorcode && null != result) {
                    if (UpdateType.NoNeed == result.getUpdateType()) {
                        showTip("已经是最新版本!");
                        return;
                    }
                    mUpdManager.showUpdateInfo(SettingActivity.this, result);
                } else {
                    showTip("请求更新失败\n更新错误码：" + errorcode);
                }
            }
        });
    }

    private void showTip(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mToast.setText(str);
                mToast.show();
            }
        });
    }
}
