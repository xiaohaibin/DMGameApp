package com.stx.xhb.dmgameapp.mvp.ui.activity;

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
    protected int getLayoutResource() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initView();
    }

    //初始化控件
    private void initView() {
        version.setText(AppUtils.getVersion(this));
        initToolBar(settingToolbar, "设置");
        mTvCache.setText(CacheManager.getTotalCacheSize(this));

        /**** 获取升级信息 *****/
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
    @OnClick({R.id.setting_iv_clearCache, R.id.setting_iv_version, R.id.setting_iv_heart,R.id.setting_qq})
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
            case R.id.setting_qq:
                joinQQGroup("F9Lc9cp9Cv0JKh1-vnzfcaVrRc9mm-xU");
                break;
            default:
                break;
        }
    }

    public void joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        try {
            startActivity(intent);
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            ToastUtil.show("未安装QQ或安装的版本不支持");
        }
    }

}
