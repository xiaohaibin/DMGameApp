package com.stx.xhb.dmgameapp.mvp.ui.game;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.mvp.IPresenter;
import com.stx.xhb.dmgameapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/7
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 热门游戏
 */
public class HotGameFragment extends BaseMvpFragment {

    @Bind(R.id.id_stickynavlayout_innerscrollview)
    EasyRecyclerView mRecyclerView;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }
}
