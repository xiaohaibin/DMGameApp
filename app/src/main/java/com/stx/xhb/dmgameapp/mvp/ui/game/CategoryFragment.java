package com.stx.xhb.dmgameapp.mvp.ui.game;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.mvp.IPresenter;
import com.stx.xhb.dmgameapp.R;

import butterknife.Bind;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/20
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 游戏分类
 */
public class CategoryFragment extends BaseMvpFragment {

    @Bind(R.id.id_stickynavlayout_innerscrollview)
    EasyRecyclerView mRvList;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
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
