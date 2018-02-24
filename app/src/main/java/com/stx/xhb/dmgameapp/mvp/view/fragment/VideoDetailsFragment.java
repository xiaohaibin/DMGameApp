package com.stx.xhb.dmgameapp.mvp.view.fragment;

import android.os.Bundle;

import com.stx.core.base.BaseMvpActivity;
import com.stx.core.mvp.IPresenter;
import com.stx.xhb.dmgameapp.R;

/**
 * Author：xiaohaibin
 * Time：2017/9/21
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：视频详情
 */

public class VideoDetailsFragment extends BaseMvpActivity {


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_video_detail;
    }

    @Override
    protected void onInitialization(Bundle bundle) {

    }

    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }
}
