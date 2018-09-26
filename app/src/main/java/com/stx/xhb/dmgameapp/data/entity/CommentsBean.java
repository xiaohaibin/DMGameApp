package com.stx.xhb.dmgameapp.data.entity;

/**
 * @author: xiaohaibin.
 * @time: 2018/3/1
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 评论实体类
 */

public class CommentsBean {

    /**
     * id : 45495
     * position : 72
     * goodcount : 1
     * badcount : 0
     * pubdate_at : 1537786327
     * time : 09-24 18:52
     * content : 居然没人求33-3
     * user : {"uid":801395,"nickname":"体臭天王","avatarstr":"https://my.3dmgame.com/uploads/images/avatar/20180306/1520317609_864490.jpg","gender":1,"regionstr":"广西梧州市","title":"初出茅庐","title_level":0}
     * praise : 0
     */

    private int id;
    private int position;
    private int goodcount;
    private int badcount;
    private int pubdate_at;
    private String time;
    private String content;
    private UserBean user;
    private int praise;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getGoodcount() {
        return goodcount;
    }

    public void setGoodcount(int goodcount) {
        this.goodcount = goodcount;
    }

    public int getBadcount() {
        return badcount;
    }

    public void setBadcount(int badcount) {
        this.badcount = badcount;
    }

    public int getPubdate_at() {
        return pubdate_at;
    }

    public void setPubdate_at(int pubdate_at) {
        this.pubdate_at = pubdate_at;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public static class UserBean {
        /**
         * uid : 801395
         * nickname : 体臭天王
         * avatarstr : https://my.3dmgame.com/uploads/images/avatar/20180306/1520317609_864490.jpg
         * gender : 1
         * regionstr : 广西梧州市
         * title : 初出茅庐
         * title_level : 0
         */

        private int uid;
        private String nickname;
        private String avatarstr;
        private int gender;
        private String regionstr;
        private String title;
        private int title_level;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatarstr() {
            return avatarstr;
        }

        public void setAvatarstr(String avatarstr) {
            this.avatarstr = avatarstr;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getRegionstr() {
            return regionstr;
        }

        public void setRegionstr(String regionstr) {
            this.regionstr = regionstr;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTitle_level() {
            return title_level;
        }

        public void setTitle_level(int title_level) {
            this.title_level = title_level;
        }
    }
}
