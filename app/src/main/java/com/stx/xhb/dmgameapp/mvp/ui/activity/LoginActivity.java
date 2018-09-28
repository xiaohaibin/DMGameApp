package com.stx.xhb.dmgameapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.stx.core.base.BaseMvpActivity;
import com.stx.core.widget.ClearEditText;
import com.stx.core.widget.HidePwEditText;
import com.stx.core.widget.dialog.DialogMaker;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.UserInfoBean;
import com.stx.xhb.dmgameapp.mvp.contract.LoginContract;
import com.stx.xhb.dmgameapp.mvp.presenter.LoginPresenter;
import com.stx.xhb.dmgameapp.utils.AppUser;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author：xiaohaibin
 * Time：2017/9/13
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：登录页面
 */

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.loginView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.ed_account)
    ClearEditText mEdAccount;
    @Bind(R.id.ed_pwd)
    HidePwEditText mEdPwd;
    @Bind(R.id.btn_login)
    TextView mBtnLogin;
    @Bind(R.id.btn_register)
    TextView mBtnRegister;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initToolBar(mToolbar, "登录");
    }

    @OnClick({R.id.tv_forget, R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forget://忘记密码
                RegisterActivity.start(LoginActivity.this,true);
                break;
            case R.id.btn_login://登录
                mPresenter.login(mEdAccount.getText().toString(), mEdPwd.getText().toString());
                break;
            case R.id.btn_register://注册
                RegisterActivity.start(LoginActivity.this,false);
                break;
            default:
                break;
        }
    }

    @Override
    public void showLoading() {
        DialogMaker.showProgressDialog(this, "正在登录");
    }

    @Override
    public void hideLoading() {
        DialogMaker.dismissProgressDialog();
    }

    @Override
    public void loginSuccess(UserInfoBean infoEntity) {
        AppUser.login(infoEntity);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void loginFailed(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    protected LoginPresenter onLoadPresenter() {
        return new LoginPresenter();
    }
}
