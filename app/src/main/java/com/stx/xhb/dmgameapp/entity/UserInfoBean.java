package com.stx.xhb.dmgameapp.entity;

import com.stx.xhb.dmgameapp.base.BaseEntity;

/**
 * Author：xiaohaibin
 * Time：2017/9/12
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class UserInfoBean extends BaseEntity{

    /**
     * code : 1
     * uid : 9749488
     * username : jxnk25
     */

    private String uid;
    private String username;
    private HtmlEntity html;
    private String access_token;
    private String encrypt_sid;
    private String expires_in;

    public HtmlEntity getHtml() {
        return html;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getEncrypt_sid() {
        return encrypt_sid;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public static class HtmlEntity{

        /**
         * uid : 9749488
         * username : jxnk25
         * credits : 2
         * grouptitle : 新手玩家
         * posts : 2
         * threads : 0
         * authimg : http://user.3dmgame.com/avatar.php?uid=9749488&size=middle
         */

        private String uid;
        private String username;
        private String credits;
        private String grouptitle;
        private String posts;
        private String threads;
        private String authimg;

        public String getUid() {
            return uid;
        }

        public String getUsername() {
            return username;
        }

        public String getCredits() {
            return credits;
        }

        public String getGrouptitle() {
            return grouptitle;
        }

        public String getPosts() {
            return posts;
        }

        public String getThreads() {
            return threads;
        }

        public String getAuthimg() {
            return authimg;
        }
    }
}

