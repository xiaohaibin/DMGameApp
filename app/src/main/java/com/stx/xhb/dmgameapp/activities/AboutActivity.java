package com.stx.xhb.dmgameapp.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.utils.VersionUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 关于界面
 */
public class AboutActivity extends AppCompatActivity {

    @Bind(R.id.about_toolbar)
    Toolbar aboutToolbar;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.version)
    TextView version;
    @Bind(R.id.btn_csdn)
    Button btnCsdn;
    @Bind(R.id.btn_home)
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        initView();
        setListener();
    }
    //设置事件监听
    private void setListener() {
            aboutToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
    }

    //初始化控件
    private void initView() {
        version.setText(VersionUtils.getVersion(this));
        setSupportActionBar(aboutToolbar);
//        aboutToolbar.setOverflowIcon(getResources().getDrawable(R.drawable.abc_ic_menu_share_mtrl_alpha));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorBackground)));
        //设置显示返回上一级的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置标题
        getSupportActionBar().setTitle("关于");
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        //设置标题栏字体颜色
        aboutToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        aboutToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
    }

    @OnClick({R.id.btn_csdn, R.id.btn_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_csdn://博客
                Uri uri = Uri.parse("http://blog.csdn.net/jxnk25");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
            case R.id.btn_home://github
                Uri github = Uri.parse("https://github.com/xiaohaibin/DMGameApp");
                Intent intent1=new Intent(Intent.ACTION_VIEW,github);
                startActivity(intent1);
                break;
        }
    }

    /**
     * 创建菜单
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
        Intent intent=new Intent(Intent.ACTION_SEND);
        //分享的类型
        intent.setType("text/*");//设置分享文本内容
        //要分享的内容
        intent.putExtra(Intent.EXTRA_TEXT,"快来使用三大妈app，掌握最新游戏资讯！推荐你也来使用，下载地址：http://www.wandoujia.com/apps/com.stx.xhb.dmgameapp");
//        intent.putExtra(Intent.EXTRA_STREAM,"要分享的图片的地址");
        //设置分享的意图
        shareActionProvider.setShareIntent(intent);
        //返回值，是否显示菜单
        return true;
    }
}
