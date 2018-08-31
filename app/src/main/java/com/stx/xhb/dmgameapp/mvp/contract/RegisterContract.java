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
        void register(String username, String passwd, String ckpasswd, String email);
    }

    interface registerView extends IView {

        void registerSuccess(UserInfoBean infoEntity);

        void registerFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
