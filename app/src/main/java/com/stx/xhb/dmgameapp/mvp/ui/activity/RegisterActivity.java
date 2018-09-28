package com.stx.xhb.dmgameapp.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.stx.core.base.BaseMvpActivity;
import com.stx.core.widget.ClearEditText;
import com.stx.core.widget.GetCodeButton;
import com.stx.core.widget.HidePwEditText;
import com.stx.core.widget.dialog.DialogMaker;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.UserInfoBean;
import com.stx.xhb.dmgameapp.mvp.contract.RegisterContract;
import com.stx.xhb.dmgameapp.mvp.presenter.RegisterPresenter;
import com.stx.xhb.dmgameapp.utils.AppUser;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：xiaohaibin
 * Time：2017/9/13
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：注册页面/忘记密码
 */
public class RegisterActivity extends BaseMvpActivity<RegisterPresenter> implements RegisterContract.registerView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.ed_account)
    ClearEditText mEdAccount;
    @Bind(R.id.ed_pwd)
    HidePwEditText mEdPwd;
    @Bind(R.id.ed_code)
    EditText mEdCode;
    @Bind(R.id.tv_get_code)
    GetCodeButton mTvGetCode;
    @Bind(R.id.btn_register)
    TextView mTvRegister;
    private boolean mIsforget;

    public static void start(Context context, boolean isForget) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra("isforget", isForget);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("isforget")) {
                mIsforget = intent.getBooleanExtra("isforget", false);
                initToolBar(mToolbar, mIsforget ? "忘记密码" : "注册");
                mTvRegister.setText(mIsforget ? "提交" : "注册");
            }
        }
    }

    @OnClick({R.id.btn_register, R.id.tv_get_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                if (mIsforget) {
                    mPresenter.resetPwd(mEdAccount.getText().toString().trim(),mEdPwd.getText().toString().trim(),mEdCode.getText().toString().trim());
                }else {
                    mPresenter.register(mEdAccount.getText().toString().trim(),mEdPwd.getText().toString().trim(),mEdCode.getText().toString().trim());
                }
                break;
            case R.id.tv_get_code:
                if (mIsforget) {
                    mPresenter.sendSms(mEdAccount.getText().toString().trim(),4,0);
                }else {
                    mPresenter.sendSms(mEdAccount.getText().toString().trim(),1,0);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void registerSuccess(String msg) {
        ToastUtil.show(msg);
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void resetPwd(String msg) {
        ToastUtil.show(msg);
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void resetPwdFailed(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void sendSmsCodeSuccess(String msg) {
        mTvGetCode.setText("重新获取");
        mTvGetCode.disableIn(60);
        ToastUtil.show(" 已发送短信至手机，请注意查收");
    }

    @Override
    public void sendSmsCodeFailed(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void registerFailed(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void showLoading() {
        DialogMaker.showProgressDialog(this, "加载中...");
    }

    @Override
    public void hideLoading() {
        DialogMaker.dismissProgressDialog();
    }

    @Override
    protected RegisterPresenter onLoadPresenter() {
        return new RegisterPresenter();
    }
}
