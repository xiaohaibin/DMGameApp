package com.stx.core.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.jaeger.library.StatusBarUtil;
import com.stx.core.R;
import com.stx.core.log.Logger;
import com.stx.core.mvp.IPresenter;
import com.stx.core.mvp.IView;
import com.stx.core.utils.AppManager;
import com.umeng.analytics.MobclickAgent;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.ButterKnife;

/**
 * @author Mr.xiao
 */
public abstract class BaseMvpActivity<P extends IPresenter> extends AppCompatActivity implements IView {

    /**
     * 当前页码
     */
    protected int currentpage = 1;

    /**
     * 页面数据量
     */
    protected int pageSize = 20;

    protected P mPresenter;

    protected abstract P onLoadPresenter();

    protected abstract int getLayoutResource();

    protected abstract void onInitialization(Bundle bundle);


    protected void onInitPresenter() {
        mPresenter = onLoadPresenter();
        if (mPresenter!=null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("name (%s.java:0)", getClass().getSimpleName());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.common_main));
        AppManager.getAppManager().addActivity(this);
        if (getLayoutResource() != 0) {
            setContentView(getLayoutResource());
            ButterKnife.bind(this);
        }
        onInitialization(savedInstanceState);
        onInitPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null && mPresenter.isViewBind()) {
            mPresenter.onStart();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        OkHttpUtils.getInstance().cancelTag(this);
        AppManager.getAppManager().finishActivity(this);
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


    public int setToolBar(Toolbar toolbar, boolean isChangeToolbar, boolean isChangeStatusBar, DrawerLayout drawerLayout) {
        int vibrantColor = Color.TRANSPARENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.BLACK);
        }
        if (isChangeToolbar) {
            toolbar.setBackgroundColor(vibrantColor);
        }
        if (isChangeStatusBar) {
            StatusBarUtil.setColor(this, vibrantColor);
        }
        if (drawerLayout != null) {
            StatusBarUtil.setColorForDrawerLayout(this, drawerLayout, vibrantColor);
        }
        return vibrantColor;
    }

    protected void initToolBar(Toolbar toolbar, String title) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.common_main)));
                //设置显示返回上一级的图标
                actionBar.setDisplayHomeAsUpEnabled(true);
                //设置标题
                actionBar.setTitle(title);
                actionBar.setDisplayShowCustomEnabled(true);
                //设置标题栏字体颜色
                toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
                toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
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

    public P getPresenter() {
        return mPresenter;
    }
}
