package com.stx.xhb.dmgameapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.stx.core.utils.AppUtils;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.base.BaseAppActitity;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 关于界面
 */
public class AboutActivity extends BaseAppActitity {

    @Bind(R.id.about_toolbar)
    Toolbar aboutToolbar;
    @Bind(R.id.version)
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setSatusBar();
        ButterKnife.bind(this);
        initView();
    }

    //初始化控件
    private void initView() {
        version.setText(AppUtils.getVersion(this));
        initToolBar(aboutToolbar, "关于");
    }

    @OnClick({R.id.btn_csdn, R.id.btn_home})
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.btn_csdn://博客
                Uri uri = Uri.parse("http://www.jianshu.com/u/42aed90cf5af");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.btn_home://github
                Uri github = Uri.parse("https://github.com/xiaohaibin/DMGameApp");
                intent = new Intent(Intent.ACTION_VIEW, github);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    /**
     * 创建菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单项，到菜单里面
        getMenuInflater().inflate(R.menu.toolbar, menu);
        //获取菜单项
        MenuItem item = menu.findItem(R.id.share);
        //通过菜单项 ,找到ActionProvide
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        //设置分享意图：
        Intent intent = new Intent(Intent.ACTION_SEND);
        //分享的类型
        intent.setType("text/*");//设置分享文本内容
        //要分享的内容
        intent.putExtra(Intent.EXTRA_TEXT, "快来使用游戏资讯app，掌握最新游戏资讯,看美图！推荐你也来使用，下载地址：http://www.wandoujia.com/apps/com.stx.xhb.dmgameapp");
        //设置分享的意图
        shareActionProvider.setShareIntent(intent);
        //返回值，是否显示菜单
        return true;
    }
}
