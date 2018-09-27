package com.stx.xhb.dmgameapp.data.entity;

import com.stx.xhb.dmgameapp.base.BaseEntity;

/**
 * Author：xiaohaibin
 * Time：2017/9/12
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：用户信息实体类
 */

public class UserInfoBean extends BaseEntity{

    private String nickname;
    private String avatarstr;
    private int uid;
    private String username;
    private String mobile;
    private int integral;
    private String title;
    private int title_level;
    private int level2;
    private int level3;
    private int level4;
    private int up_nick;
    private int app_login;
    private String integralmsg;

    public String getNickname() {
        return nickname;
    }

    public String getAvatarstr() {
        return avatarstr;
    }

    public int getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getMobile() {
        return mobile;
    }

    public int getIntegral() {
        return integral;
    }

    public String getTitle() {
        return title;
    }

    public int getTitle_level() {
        return title_level;
    }

    public int getLevel2() {
        return level2;
    }

    public int getLevel3() {
        return level3;
    }

    public int getLevel4() {
        return level4;
    }


    public int getUp_nick() {
        return up_nick;
    }

    public int getApp_login() {
        return app_login;
    }

    public String getIntegralmsg() {
        return integralmsg;
    }

}

