package com.stx.xhb.dmgameapp.ui.fragment;

import android.os.Bundle;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.stx.core.base.BaseFragment;
import com.stx.xhb.dmgameapp.R;

import butterknife.Bind;

/**
 * Author : jxnk25
 * Time: 2017/10/19 0019
 * Email:xhb_199409@163.com
 * Email:xhb_199409@163.com
 * Github:https://github.com/xiaohaibin/
 * Drscribe:游戏资讯
 */

public class GameDetailsCommonFragment extends BaseFragment{

    @Bind(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;

    public static GameDetailsCommonFragment newInstance() {
        Bundle args = new Bundle();
        GameDetailsCommonFragment fragment = new GameDetailsCommonFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

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
