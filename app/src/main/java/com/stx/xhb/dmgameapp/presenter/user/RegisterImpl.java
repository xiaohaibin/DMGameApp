package com.stx.xhb.dmgameapp.presenter.user;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.entity.UserInfoEntity;
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

public class RegisterImpl extends BasePresenter<RegisterContract.registerView> implements RegisterContract {

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
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new RegisterContentEntity("userregister", email, username, passwd, ckpasswd)))
                .url(API.USER_API)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        getView().showLoading();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getView().hideLoading();
                        getView().registerFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            UserInfoEntity userInfoEntity = GsonUtil.newGson().fromJson(response, UserInfoEntity.class);
                            if (userInfoEntity.getCode() == Constants.SERVER_SUCCESS) {
                                toLogin(username,passwd,"0","");
                            } else {
                                getView().hideLoading();
                                getView().registerFailed(userInfoEntity.getMsg());
                            }
                        }
                    }
                });
    }

    public void toLogin(String username, String pwd, String questionid, String answer) {
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new LoginImpl.LoginContentEntity("userlogin", username, pwd, answer, questionid)))
                .url(API.USER_API)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getView().hideLoading();
                        getView().registerFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            UserInfoEntity userInfoEntity = GsonUtil.newGson().fromJson(response, UserInfoEntity.class);
                            if (userInfoEntity.getCode() == Constants.SERVER_SUCCESS) {
                                getUserInfo(userInfoEntity);
                            } else {
                                getView().hideLoading();
                                getView().registerFailed(userInfoEntity.getMsg());
                            }
                        }
                    }
                });
    }

    public void getUserInfo(UserInfoEntity infoEntity) {
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new LoginImpl.getUserInfoContentEntity("userinfo", infoEntity.getUid())))
                .url(API.USER_API)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getView().registerFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            UserInfoEntity userInfoEntity = GsonUtil.newGson().fromJson(response, UserInfoEntity.class);
                            if (userInfoEntity.getCode() == Constants.SERVER_SUCCESS) {
                                getView().registerSuccess(userInfoEntity);
                            } else {
                                getView().registerFailed(userInfoEntity.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onAfter(int id) {
                        getView().hideLoading();
                    }
                });
    }


    private class RegisterContentEntity {

        /**
         * module : userregister
         * email : 846035385@qq.com
         * username : jxnk18
         * passwd : xhbxysy9862377
         * ckpasswd : xhbxysy9862377
         */

        private String module;
        private String email;
        private String username;
        private String passwd;
        private String ckpasswd;

        RegisterContentEntity(String module, String email, String username, String passwd, String ckpasswd) {
            this.module = module;
            this.email = email;
            this.username = username;
            this.passwd = passwd;
            this.ckpasswd = ckpasswd;
        }
    }
}
