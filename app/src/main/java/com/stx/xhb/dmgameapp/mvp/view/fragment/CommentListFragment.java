package com.stx.xhb.dmgameapp.mvp.view.fragment;

import android.os.Bundle;

import com.stx.core.base.BaseMvpFragment;
import com.stx.core.mvp.IPresenter;
import com.stx.xhb.dmgameapp.R;

/**
 * @author: xiaohaibin.
 * @time: 2018/1/31
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class CommentListFragment extends BaseMvpFragment {

    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_comment_list;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

    }
}
