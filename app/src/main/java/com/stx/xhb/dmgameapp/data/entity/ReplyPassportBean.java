package com.stx.xhb.dmgameapp.data.entity;

/**
 * @author: xiaohaibin.
 * @time: 2018/3/1
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class ReplyPassportBean {

    /**
     * expired : false
     * fee : 0
     * followers_count : 0
     * from :
     * grant : false
     * img_url : http://user.3dmgame.com/avatar.php?uid=1712669&size=small
     * is_official : false
     * is_shared : false
     * isv_refer_id : 1712669
     * nickname : miharu0901
     * platform_id : 14
     * profile_url : http://bbs.3dmgame.com/space-uid-1712669.html
     * user_id : -339699349
     */

    private boolean expired;
    private int fee;
    private int followers_count;
    private String from;
    private boolean grant;
    private String img_url;
    private boolean is_official;
    private boolean is_shared;
    private String isv_refer_id;
    private String nickname;
    private int platform_id;
    private String profile_url;
    private String user_id;

    public String getFrom() {
        return from;
    }

}
