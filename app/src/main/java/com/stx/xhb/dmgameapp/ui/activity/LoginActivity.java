package com.stx.xhb.dmgameapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stx.core.base.BaseActivity;
import com.stx.core.widget.ClearEditText;
import com.stx.core.widget.HidePwEditText;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.presenter.user.LoginContact;
import com.stx.xhb.dmgameapp.presenter.user.LoginImpl;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author：xiaohaibin
 * Time：2017/9/13
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：登录页面
 */

public class LoginActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.ed_account)
    ClearEditText mEdAccount;
    @Bind(R.id.ed_pwd)
    HidePwEditText mEdPwd;
    @Bind(R.id.btn_question)
    TextView mBtnQuestion;
    @Bind(R.id.ed_answer)
    HidePwEditText mEdAnswer;
    @Bind(R.id.ll_answer)
    LinearLayout mLlAnswer;
    @Bind(R.id.btn_login)
    TextView mBtnLogin;
    @Bind(R.id.btn_register)
    TextView mBtnRegister;
    private String questionsId="0";

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initToolBar(mToolbar, "登录");
    }

    @OnClick({R.id.btn_question, R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_question://选择问题
                break;
            case R.id.btn_login://登录
                ((LoginImpl)mPresenter).login(mEdAccount.getText().toString(),mEdPwd.getText().toString(),questionsId,mEdAnswer.getText().toString());
                break;
            case R.id.btn_register://注册
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
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
        return LoginContact.class;
    }
}
