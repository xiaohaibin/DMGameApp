package com.stx.xhb.dmgameapp.user;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.stx.core.base.BaseActivity;
import com.stx.core.widget.ClearEditText;
import com.stx.core.widget.HidePwEditText;
import com.stx.xhb.dmgameapp.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author：xiaohaibin
 * Time：2017/9/13
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：注册页面
 */

public class RegisterActivity extends BaseActivity {

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
    @Bind(R.id.btn_register)
    TextView mBtnRegister;

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

    }
}
