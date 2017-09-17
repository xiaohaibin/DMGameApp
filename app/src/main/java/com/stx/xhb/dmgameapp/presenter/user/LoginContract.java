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
@Implement(LoginImpl.class)
public interface LoginContract {

    void login(String username, String pwd, String questionid, String answer);

    interface loginView extends BaseView {

        void loginSuccess(UserInfoEntity infoEntity);

        void loginFailed(String msg);

    }

}
