package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;
import android.widget.TextView;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.data.callback.LoadTaskCallback;
import com.stx.xhb.dmgameapp.data.entity.UserInfoBean;
import com.stx.xhb.dmgameapp.data.remote.TasksRepositoryProxy;
import com.stx.xhb.dmgameapp.http.BaseResponse;
import com.stx.xhb.dmgameapp.mvp.contract.RegisterContract;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;
import rx.Subscription;

/**
 * Author：xiaohaibin
 * Time：2017/9/16
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.registerView> implements RegisterContract.registerModel {

    @Override
    public void register(String username, String passwd, String code) {
        if (TextUtils.isEmpty(username)) {
            ToastUtil.show("请填写手机号");
            return;
        }
        if (TextUtils.isEmpty(passwd)) {
            ToastUtil.show("请填写密码");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtil.show("请填写验证码");
            return;
        }
        if (getView() == null) {
            return;
        }
        Subscription subscription = TasksRepositoryProxy.getInstance().register(username, passwd, code, new LoadTaskCallback<String>() {
            @Override
            public void onStart() {
                getView().showLoading();
            }

            @Override
            public void onCompleted() {
                getView().hideLoading();
            }

            @Override
            public void onTaskLoaded(String data) {
                getView().registerSuccess(data);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().registerFailed(msg);
            }
        });
        addSubscription(subscription);
    }

    @Override
    public void resetPwd(String username, String passwd, String code) {
        if (TextUtils.isEmpty(username)) {
            ToastUtil.show("请填写手机号");
            return;
        }
        if (TextUtils.isEmpty(passwd)) {
            ToastUtil.show("请填写密码");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtil.show("请填写验证码");
            return;
        }
        if (getView() == null) {
            return;
        }
        Subscription subscription = TasksRepositoryProxy.getInstance().findPwd(username, code, passwd, new LoadTaskCallback<String>() {
            @Override
            public void onStart() {
                getView().showLoading();
            }

            @Override
            public void onCompleted() {
                getView().hideLoading();
            }

            @Override
            public void onTaskLoaded(String data) {
                getView().resetPwd(data);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().resetPwdFailed(msg);
            }
        });
        addSubscription(subscription);
    }

    @Override
    public void sendSms(String mobile, int act, int uid) {
        if (TextUtils.isEmpty(mobile)) {
            ToastUtil.show("请输入手机号");
            return;
        }
        TasksRepositoryProxy.getInstance().sendSms(mobile, act, uid, new LoadTaskCallback<String>() {
            @Override
            public void onStart() {
                getView().showLoading();
            }

            @Override
            public void onCompleted() {
                getView().hideLoading();
            }

            @Override
            public void onTaskLoaded(String data) {
                getView().sendSmsCodeSuccess(data);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().sendSmsCodeFailed(msg);
            }
        });
    }
}
