package com.stx.xhb.dmgameapp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.stx.core.base.BaseActivity;
import com.stx.core.widget.ClearEditText;
import com.stx.core.widget.HidePwEditText;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.UserInfoEntity;
import com.stx.xhb.dmgameapp.presenter.user.RegisterContact;
import com.stx.xhb.dmgameapp.presenter.user.RegisterImpl;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author：xiaohaibin
 * Time：2017/9/13
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：注册页面
 */

public class RegisterActivity extends BaseActivity implements RegisterImpl.registerView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.ed_account)
    ClearEditText mEdAccount;
    @Bind(R.id.ed_pwd)
    HidePwEditText mEdPwd;
    @Bind(R.id.ed_sure_pwd)
    HidePwEditText mEdSurePwd;
    @Bind(R.id.ed_sure_email)
    HidePwEditText mEdSureEmail;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initToolBar(mToolbar, "注册");
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        ((RegisterImpl) mPresenter).register(mEdAccount.getText().toString(), mEdPwd.getText().toString(), mEdSurePwd.getText().toString(), mEdSureEmail.getText().toString());
    }

    @Override
    public void registerSuccess(UserInfoEntity infoEntity) {

    }

    @Override
    public void registerFailed(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected Class getLogicClazz() {
        return RegisterContact.class;
    }
}
