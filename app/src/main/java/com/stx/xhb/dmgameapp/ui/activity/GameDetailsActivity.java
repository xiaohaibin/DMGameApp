package com.stx.xhb.dmgameapp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.stx.core.base.BaseActivity;
import com.stx.xhb.dmgameapp.R;

import butterknife.Bind;

/**
 * 游戏详情
 */
public class GameDetailsActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_game_details;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initToolBar(mToolbar, "游戏详情");
    }

    @Override
    protected Class getLogicClazz() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
