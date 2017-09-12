package com.stx.xhb.dmgameapp.utils;

import android.text.TextUtils;

import com.stx.core.utils.GsonUtil;
import com.stx.core.utils.SPUtils;
import com.stx.xhb.dmgameapp.DmgApplication;
import com.stx.xhb.dmgameapp.entity.UserEntity;

/**
 * Author：xiaohaibin
 * Time：2017/9/12
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：用户工具类
 */

public class AppUser {

    private static String token;
    private static UserEntity sUserEntity;

    public static void init(){
        String userInfo = SPUtils.getString(DmgApplication.getInstance().getContext(), "userInfo", "");
        token = SPUtils.getString(DmgApplication.getInstance().getContext(), "token", "");
        if (!TextUtils.isEmpty(userInfo)) {
            sUserEntity = GsonUtil.newGson().fromJson(userInfo, UserEntity.class);
        }
    }

    public static UserEntity getUserEntity() {
        if (sUserEntity == null) {
            return GsonUtil.newGson().fromJson("{}", UserEntity.class);
        }
        return sUserEntity;
    }

    public static String getToken() {
        return token;
    }

    public static boolean isLogin() {
        return sUserEntity != null && !TextUtils.isEmpty(token);
    }


    /**
     * 登录成功时调用
     *
     * @param userEntity
     */
    public static void login(UserEntity userEntity) {
        AppUser.token = userEntity.getAccess_token();
        SPUtils.putString(DmgApplication.getInstance().getContext(), "token", token);
        setUserInfo(userEntity);
    }

    /**
     * 刷新用户信息时调用
     *
     * @param userEntity
     */
    public static void setUserInfo(UserEntity userEntity) {
        AppUser.sUserEntity = userEntity;
        SPUtils.putString(DmgApplication.getInstance().getContext(), "userInfo", GsonUtil.newGson().toJson(userEntity));
    }

    /**
     * 注销时调用
     */
    public static void logout() {
        sUserEntity = null;
        token = "";
    }

}
