package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.entity.UserInfoEntity;
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

public class LoginPresenter extends BasePresenter<LoginContract.loginView,LoginContract.loginModel> implements LoginContract.loginModel {

    @Override
    public void login(String username, String pwd, String questionid, String answer) {
        if (TextUtils.isEmpty(username)) {
            ToastUtil.show("请填写用户名");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            ToastUtil.show("请填写密码");
            return;
        }
        if (!"0".equals(questionid) && TextUtils.isEmpty(answer)) {
            ToastUtil.show("请填写问题答案");
            return;
        }
        toLogin(username, pwd, questionid, answer);
    }

    public void toLogin(String username, String pwd, String questionid, String answer) {
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new LoginContentEntity("userlogin", username, pwd, answer, questionid)))
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
                        getView().loginFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            UserInfoEntity userInfoEntity = GsonUtil.newGson().fromJson(response, UserInfoEntity.class);
                            if (userInfoEntity.getCode() == Constants.SERVER_SUCCESS) {
                                getUserInfo(userInfoEntity);
                            } else {
                                getView().hideLoading();
                                getView().loginFailed(userInfoEntity.getMsg());
                            }
                        }
                    }
                });
    }

    public void getUserInfo(UserInfoEntity infoEntity) {
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new getUserInfoContentEntity("userinfo", infoEntity.getUid())))
                .url(API.USER_API)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getView().loginFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            UserInfoEntity userInfoEntity = GsonUtil.newGson().fromJson(response, UserInfoEntity.class);
                            if (userInfoEntity.getCode() == Constants.SERVER_SUCCESS) {
                                getView().loginSuccess(userInfoEntity);
                            } else {
                                getView().loginFailed(userInfoEntity.getMsg());
                            }
                        }

                    }

                    @Override
                    public void onAfter(int id) {
                        getView().hideLoading();
                    }
                });
    }


    public static class LoginContentEntity {
        /**
         * module : userlogin
         * username : jxnk25
         * passwd : xhbxysy9862377
         * answer :
         * questionid : 0
         */

        private String module;
        private String username;
        private String passwd;
        private String answer;
        private String questionid;

        public LoginContentEntity(String module, String username, String passwd, String answer, String questionid) {
            this.module = module;
            this.username = username;
            this.passwd = passwd;
            this.answer = answer;
            this.questionid = questionid;
        }
    }

    public static class getUserInfoContentEntity {

        /**
         * module : userinfo
         * uid : 12118093
         */

        private String module;
        private String uid;

        public getUserInfoContentEntity(String module, String uid) {
            this.module = module;
            this.uid = uid;
        }
    }
}
