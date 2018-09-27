package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.data.entity.UserInfoBean;

/**
 * Author：xiaohaibin
 * Time：2017/9/16
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
public interface LoginContract {

    interface loginModel extends IModel {
        void login(String username, String pwd);
    }

    interface loginView extends IView {

        void loginSuccess(UserInfoBean infoEntity);

        void loginFailed(String msg);

        void showLoading();

        void hideLoading();

    }

}
