package com.stx.xhb.dmgameapp.utils;

import android.text.TextUtils;

import com.stx.core.utils.GsonUtil;
import com.stx.core.utils.SPUtils;
import com.stx.xhb.dmgameapp.DmgApplication;
import com.stx.xhb.dmgameapp.data.entity.UserInfoBean;

/**
 * Author：xiaohaibin
 * Time：2017/9/12
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：用户工具类
 */

public class AppUser {

    private static UserInfoBean sUserInfoBean;

    public static void init() {
        String userInfo = SPUtils.getString(DmgApplication.getInstance().getApplicationContext(), "userInfo", "");
        if (!TextUtils.isEmpty(userInfo)) {
            sUserInfoBean = GsonUtil.newGson().fromJson(userInfo, UserInfoBean.class);
        }
    }

    public static UserInfoBean getUserInfoBean() {
        if (sUserInfoBean == null) {
            return GsonUtil.newGson().fromJson("{}", UserInfoBean.class);
        }
        return sUserInfoBean;
    }

    public static boolean isLogin() {
        return sUserInfoBean != null;
    }


    /**
     * 登录成功时调用
     * @param userInfoBean
     */
    public static void login(UserInfoBean userInfoBean) {
        setUserInfo(userInfoBean);
    }

    /**
     * 刷新用户信息时调用
     * @param userInfoBean
     */
    public static void setUserInfo(UserInfoBean userInfoBean) {
        AppUser.sUserInfoBean = userInfoBean;
        SPUtils.putString(DmgApplication.getInstance().getApplicationContext(), "userInfo", GsonUtil.newGson().toJson(userInfoBean));
    }

    /**
     * 注销时调用
     */
    public static void logout() {
        sUserInfoBean = null;
        SPUtils.remove(DmgApplication.getInstance().getApplicationContext(), "userInfo");
    }

}
