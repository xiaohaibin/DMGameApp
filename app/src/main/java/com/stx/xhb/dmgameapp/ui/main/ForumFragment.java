package com.stx.xhb.dmgameapp.ui.main;


import android.os.Bundle;
import android.widget.TextView;

import com.stx.core.base.BaseFragment;
import com.stx.xhb.dmgameapp.R;

import butterknife.Bind;

/**
 * 论坛的Fragment
 */
public class ForumFragment extends BaseFragment {

    @Bind(R.id.title)
    TextView mTitle;

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_forum;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initView();
    }

    @Override
    protected Class getLogicClazz() {
        return null;
    }

    //获取控件
    private void initView() {
        mTitle.setText("论坛");
    }
}
