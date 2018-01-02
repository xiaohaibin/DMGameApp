package com.stx.xhb.dmgameapp.utils;

import android.text.TextUtils;

import com.stx.core.utils.GsonUtil;
import com.stx.core.utils.SPUtils;
import com.stx.xhb.dmgameapp.DmgApplication;
import com.stx.xhb.dmgameapp.entity.UserInfoEntity;

/**
 * Author：xiaohaibin
 * Time：2017/9/12
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：用户工具类
 */

public class AppUser {

    private static UserInfoEntity sUserInfoEntity;

    public static void init(){
        String userInfo = SPUtils.getString(DmgApplication.getInstance().getApplicationContext(), "userInfo", "");
        if (!TextUtils.isEmpty(userInfo)) {
            sUserInfoEntity = GsonUtil.newGson().fromJson(userInfo, UserInfoEntity.class);
        }
    }

    public static UserInfoEntity getUserInfoEntity() {
        if (sUserInfoEntity == null) {
            return GsonUtil.newGson().fromJson("{}", UserInfoEntity.class);
        }
        return sUserInfoEntity;
    }

    public static boolean isLogin() {
        return sUserInfoEntity != null;
    }


    /**
     * 登录成功时调用
     *
     * @param userInfoEntity
     */
    public static void login(UserInfoEntity userInfoEntity) {
        setUserInfo(userInfoEntity);
    }

    /**
     * 刷新用户信息时调用
     *
     * @param userInfoEntity
     */
    public static void setUserInfo(UserInfoEntity userInfoEntity) {
        AppUser.sUserInfoEntity = userInfoEntity;
        SPUtils.putString(DmgApplication.getInstance().getApplicationContext(), "userInfo", GsonUtil.newGson().toJson(userInfoEntity));
    }

    /**
     * 注销时调用
     */
    public static void logout() {
        sUserInfoEntity = null;
    }

}
