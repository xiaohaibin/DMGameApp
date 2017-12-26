package com.stx.xhb.dmgameapp.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stx.core.base.BaseActivity;
import com.stx.core.widget.ClearEditText;
import com.stx.core.widget.HidePwEditText;
import com.stx.core.widget.dialog.DialogMaker;
import com.stx.xhb.dmgameapp.MainActivity;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.UserInfoEntity;
import com.stx.xhb.dmgameapp.presenter.user.LoginContract;
import com.stx.xhb.dmgameapp.presenter.user.LoginImpl;
import com.stx.xhb.dmgameapp.utils.AppUser;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

import static com.stx.xhb.dmgameapp.R.id.btn_question;

/**
 * Author：xiaohaibin
 * Time：2017/9/13
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：登录页面
 */

public class LoginActivity extends BaseActivity implements LoginImpl.loginView {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.ed_account)
    ClearEditText mEdAccount;
    @Bind(R.id.ed_pwd)
    HidePwEditText mEdPwd;
    @Bind(R.id.ed_answer)
    HidePwEditText mEdAnswer;
    @Bind(R.id.ll_answer)
    LinearLayout mLlAnswer;
    @Bind(R.id.btn_login)
    TextView mBtnLogin;
    @Bind(R.id.btn_register)
    TextView mBtnRegister;
    @Bind(R.id.btn_question)
    TextView mBtnQuestion;
    private int questionsId = 0;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        initToolBar(mToolbar, "登录");
    }

    @OnClick({btn_question, R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case btn_question://选择问题
                showQuestionList();
                break;
            case R.id.btn_login://登录
                ((LoginImpl) mPresenter).login(mEdAccount.getText().toString(), mEdPwd.getText().toString(), String.valueOf(questionsId), mEdAnswer.getText().toString());
                break;
            case R.id.btn_register://注册
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
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
    protected Class getLogicClazz() {
        return LoginContract.class;
    }

    @Override
    public void loginSuccess(UserInfoEntity infoEntity) {
        AppUser.login(infoEntity);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void loginFailed(String msg) {
        ToastUtil.show(msg);
    }

    private void showQuestionList() {
        final String[] items = new String[]{"安全问题未设置", "母亲的名字", "爷爷的名字", "父亲出生的城市", "您其中一位老师的名字", "您个人计算机的型号", "您最喜欢的餐馆的名字", "驾驶证最后四位数字"};
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("请选择安全问题")
                .setSingleChoiceItems(items, questionsId, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mBtnQuestion.setText(items[which]);
                        questionsId = which;
                        if (questionsId != 0) {
                            mLlAnswer.setVisibility(View.VISIBLE);
                        } else {
                            mLlAnswer.setVisibility(View.GONE);
                        }
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

}
