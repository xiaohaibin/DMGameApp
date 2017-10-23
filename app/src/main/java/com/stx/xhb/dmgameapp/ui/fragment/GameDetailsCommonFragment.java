package com.stx.xhb.dmgameapp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class GameDetailsCommonFragment extends BaseFragment {


    @Bind(R.id.id_stickynavlayout_innerscrollview)
    RecyclerView recyclerView;

    public static GameDetailsCommonFragment newInstance() {
        Bundle args = new Bundle();
        GameDetailsCommonFragment fragment = new GameDetailsCommonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_recyclerview;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
