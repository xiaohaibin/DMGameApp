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
public interface RegisterContract {

    interface registerModel extends IModel {
        void register(String username, String passwd, String code);

        void resetPwd(String username, String passwd, String code);

        void sendSms(String mobile, int act, int uid);
    }

    interface registerView extends IView {

        void registerSuccess(String msg);

        void resetPwd(String msg);

        void resetPwdFailed(String msg);

        void registerFailed(String msg);

        void sendSmsCodeSuccess(String msg);

        void sendSmsCodeFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
