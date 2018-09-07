package com.stx.xhb.dmgameapp.mvp.ui.game;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.stx.core.base.BaseMvpFragment;
import com.stx.core.mvp.IPresenter;
import com.stx.xhb.dmgameapp.R;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/7
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 发售
 */
public class SaleFragment extends BaseMvpFragment {

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
