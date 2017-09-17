package com.stx.xhb.dmgameapp.presenter.user;

import com.stx.core.model.annotation.Implement;
import com.stx.core.mvp.BaseView;
import com.stx.xhb.dmgameapp.entity.UserInfoEntity;

/**
 * Author：xiaohaibin
 * Time：2017/9/16
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
@Implement(RegisterImpl.class)
public interface RegisterContract {

    void register(String username, String passwd, String ckpasswd, String email);

    interface registerView extends BaseView {

        void registerSuccess(UserInfoEntity infoEntity);

        void registerFailed(String msg);
    }
}
