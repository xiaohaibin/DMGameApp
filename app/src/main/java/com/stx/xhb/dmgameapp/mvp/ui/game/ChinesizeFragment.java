package com.stx.xhb.dmgameapp.mvp.ui.game;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.mvp.IPresenter;
import com.stx.xhb.dmgameapp.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/7
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 汉化
 */
public class ChinesizeFragment extends BaseMvpFragment {

    @Bind(R.id.tv_first)
    TextView mTvFirst;
    @Bind(R.id.tv_second)
    TextView mTvSecond;
    @Bind(R.id.rv_list)
    EasyRecyclerView mRvList;

    public static ChinesizeFragment newInstance() {
        return new ChinesizeFragment();
    }

    @NonNull
    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_tab;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

    }


    @OnClick({R.id.tv_first, R.id.tv_second})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_first:
                break;
            case R.id.tv_second:
                break;
            default:
                break;
        }
    }
}
