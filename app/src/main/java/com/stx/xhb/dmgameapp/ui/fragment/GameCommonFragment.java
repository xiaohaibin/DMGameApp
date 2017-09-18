package com.stx.xhb.dmgameapp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.stx.core.base.BaseFragment;
import com.stx.xhb.dmgameapp.R;

import butterknife.Bind;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class GameCommonFragment extends BaseFragment{

    @Bind(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;

    public static GameCommonFragment newInstance(String typeId) {
        Bundle args = new Bundle();
        GameCommonFragment fragment = new GameCommonFragment();
        args.putString("id", typeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setRefreshingColor(Color.rgb(255, 99, 71), Color.rgb(255, 99, 71), Color.rgb(255, 99, 71));
    }

    @Override
    protected Class getLogicClazz() {
        return null;
    }
}
