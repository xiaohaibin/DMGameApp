package com.stx.xhb.dmgameapp.entity;

/**
 * @author: xiaohaibin.
 * @time: 2018/3/1
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class PassportBean {

    private boolean expired;
    private int fee;
    private int followers_count;
    private String from;
    private boolean grant;
    private String img_url;
    private boolean is_official;
    private boolean is_shared;
    private String nickname;
    private int platform_id;
    private String user_id;

    public boolean isExpired() {
        return expired;
    }

    public int getFee() {
        return fee;
    }


    public int getFollowers_count() {
        return followers_count;
    }

    public String getFrom() {
        return from;
    }

    public boolean isGrant() {
        return grant;
    }

    public String getImg_url() {
        return img_url;
    }

    public boolean isIs_official() {
        return is_official;
    }

    public boolean isIs_shared() {
        return is_shared;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPlatform_id() {
        return platform_id;
    }

    public String getUser_id() {
        return user_id;
    }
}
