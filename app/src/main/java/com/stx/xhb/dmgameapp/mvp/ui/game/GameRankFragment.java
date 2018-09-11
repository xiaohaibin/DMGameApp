package com.stx.xhb.dmgameapp.mvp.ui.game;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.stx.core.base.BaseMvpFragment;
import com.stx.core.mvp.IPresenter;
import com.stx.xhb.dmgameapp.R;

/**
 * Author: Mr.xiao on 2018/9/11
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class GameRankFragment extends BaseMvpFragment{


    public static GameRankFragment newInstance() {
        return new GameRankFragment();
    }

    @NonNull
    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

    }
}
