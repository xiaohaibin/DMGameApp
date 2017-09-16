package com.stx.xhb.dmgameapp.ui.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.stx.core.base.BaseFragment;
import com.stx.core.widget.LabelIndicatorView;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.ui.activity.AboutActivity;
import com.stx.xhb.dmgameapp.ui.activity.SettingActivity;
import com.stx.xhb.dmgameapp.ui.activity.LoginActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 个人中心Fragment
 */
public class MyFragment extends BaseFragment {

    @Bind(R.id.iv_user_img)
    PorterShapeImageView mIvUserImg;
    @Bind(R.id.tv_account)
    TextView mTvAccount;
    @Bind(R.id.tv_info)
    TextView mTvInfo;
    @Bind(R.id.ll_login_info)
    LinearLayout mLlLoginInfo;
    @Bind(R.id.ll_no_login)
    LinearLayout mLlNoLogin;
    @Bind(R.id.tv_collect)
    TextView mTvCollect;
    @Bind(R.id.tv_message)
    TextView mTvMessage;
    @Bind(R.id.btn_setting)
    LabelIndicatorView mBtnSetting;
    @Bind(R.id.btn_about)
    LabelIndicatorView mBtnAbout;
    @Bind(R.id.btn_login_out)
    TextView mBtnLoginOut;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

    }

    @OnClick({R.id.ll_no_login, R.id.tv_collect, R.id.tv_message, R.id.btn_setting, R.id.btn_about, R.id.btn_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_no_login://登录
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.tv_collect://收藏
                break;
            case R.id.tv_message://消息
                break;
            case R.id.btn_setting://设置
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.btn_about://关于
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.btn_login_out://退出登录
                break;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected Class getLogicClazz() {
        return null;
    }
}
