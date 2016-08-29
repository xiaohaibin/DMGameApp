package com.stx.xhb.dmgameapp.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.stx.xhb.dmgameapp.utils.VersionUtils;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;

import org.xutils.x;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
        setListener();
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

    }

    //点击事件
    @OnClick({R.id.setting_iv_clearCache, R.id.setting_iv_version, R.id.setting_iv_heart, R.id.setting_iv_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_iv_clearCache://清理缓存
                x.image().clearCacheFiles();
                ToastUtil.showAtCenter(SettingActivity.this, "缓存清理成功");
                break;
            case R.id.setting_iv_version://版本更新
                UmengUpdateAgent.setUpdateOnlyWifi(false);
                UmengUpdateAgent.forceUpdate(this);
                //友盟自动更新回调
                UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
                    @Override
                    public void onUpdateReturned(int i, UpdateResponse updateResponse) {
                        if (i == 0) {
                            ToastUtil.showAtCenter(SettingActivity.this, "有更新");
                        } else if (i == 1) {
                            ToastUtil.showAtCenter(SettingActivity.this, "暂无更新");
                        } else if (i == 2) {
                            ToastUtil.showAtCenter(SettingActivity.this, "非wifi状态");
                        } else if (i == 3) {
                            ToastUtil.showAtCenter(SettingActivity.this, "请求超时");
                        }
                    }
                });
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


}
