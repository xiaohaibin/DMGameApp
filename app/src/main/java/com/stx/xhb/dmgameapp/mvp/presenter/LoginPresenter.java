package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.data.entity.UserInfoBean;
import com.stx.xhb.dmgameapp.mvp.contract.LoginContract;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Author：xiaohaibin
 * Time：2017/9/16
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class LoginPresenter extends BasePresenter<LoginContract.loginView> implements LoginContract.loginModel {

    @Override
    public void login(String username, String pwd) {
        if (TextUtils.isEmpty(username)) {
            ToastUtil.show("请填写用户名");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            ToastUtil.show("请填写密码");
            return;
        }
        if (getView() == null) {
            return;
        }
    }

}
