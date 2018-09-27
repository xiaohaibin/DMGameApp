package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.data.entity.UserInfoBean;
import com.stx.xhb.dmgameapp.mvp.contract.RegisterContract;
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

public class RegisterPresenter extends BasePresenter<RegisterContract.registerView> implements RegisterContract.registerModel {

    @Override
    public void register(final String username, final String passwd, String ckpasswd, String email) {
        if (TextUtils.isEmpty(username)) {
            ToastUtil.show("请填写用户名");
            return;
        }
        if (TextUtils.isEmpty(passwd)) {
            ToastUtil.show("请填写密码");
            return;
        }
        if (TextUtils.isEmpty(ckpasswd)) {
            ToastUtil.show("请填写确认密码");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            ToastUtil.show("请填写邮箱");
            return;
        }
        if (getView()==null){
            return;
        }
    }
}
