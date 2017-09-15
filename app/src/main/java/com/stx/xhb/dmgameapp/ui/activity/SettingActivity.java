package com.stx.xhb.dmgameapp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.stx.core.utils.AppUtils;
import com.stx.core.utils.CacheManager;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.base.BaseAppActitity;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人设置界面
 */
public class SettingActivity extends BaseAppActitity {

    @Bind(R.id.setting_toolbar)
    Toolbar settingToolbar;
    @Bind(R.id.version)
    TextView version;
    @Bind(R.id.tv_cache)
    TextView mTvCache;
    @Bind(R.id.tv_update)
    TextView mTvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setSatusBar();
        ButterKnife.bind(this);
        initView();
    }

    //初始化控件
    private void initView() {
        version.setText(AppUtils.getVersion(this));
        initToolBar(settingToolbar, "设置");
        mTvCache.setText(CacheManager.getTotalCacheSize(this));

        /***** 获取升级信息 *****/
        UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();
        if (upgradeInfo == null) {
            mTvVersion.setTextColor(getResources().getColor(R.color.color_888888));
            mTvVersion.setText(" 已是最新版本");
        } else {
            mTvVersion.setTextColor(getResources().getColor(R.color.colorPrimary));
            mTvVersion.setText("有新版本，点击立即更新");
        }
    }

    //点击事件
    @OnClick({R.id.setting_iv_clearCache, R.id.setting_iv_version, R.id.setting_iv_heart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_iv_clearCache://清理缓存
                if ("0 KB".equals(CacheManager.getTotalCacheSize(this))) {
                    ToastUtil.show("暂无缓存");
                } else {
                    CacheManager.clearAllCache(this);
                    mTvCache.setText(CacheManager.getTotalCacheSize(this));
                    ToastUtil.show("缓存清理成功");
                }
                break;
            case R.id.setting_iv_version://版本更新
                Beta.checkUpgrade();//检查更新
                break;
            case R.id.setting_iv_heart://评分
                Uri uri = Uri.parse("http://www.wandoujia.com/apps/com.stx.xhb.dmgameapp");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

}
