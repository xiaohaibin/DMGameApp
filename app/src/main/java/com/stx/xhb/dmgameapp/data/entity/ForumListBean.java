package com.stx.xhb.dmgameapp.data.entity;

import com.stx.xhb.dmgameapp.base.BaseEntity;

import java.util.List;

/**
 * Author : jxnk25
 * Time: 2017/11/1 0001
 * Email:xhb_199409@163.com
 * Email:xhb_199409@163.com
 * Github:https://github.com/xiaohaibin/
 * Drscribe:
 */

public class ForumListBean extends BaseEntity{

    private String totalrow;
    private List<HtmlBean> html;

    public String getTotalrow() {
        return totalrow;
    }


    public List<HtmlBean> getHtml() {
        return html;
    }

    public static class HtmlBean {

        private String tid;
        private String author;
        private String authorid;
        private String subject;
        private String dateline;
        private String highlight;
        private String digest;
        private String displayorder;
        private String rate;
        private String heats;
        private String replies;
        private String authimg;
        private int arttype;
        private List<String> litpic;

        public String getTid() {
            return tid;
        }

        public String getAuthor() {
            return author;
        }

        public String getAuthorid() {
            return authorid;
        }

        public String getSubject() {
            return subject;
        }

        public String getDateline() {
            return dateline;
        }

        public String getHighlight() {
            return highlight;
        }

        public String getDigest() {
            return digest;
        }

        public String getDisplayorder() {
            return displayorder;
        }

        public String getRate() {
            return rate;
        }

        public String getHeats() {
            return heats;
        }

        public String getReplies() {
            return replies;
        }

        public String getAuthimg() {
            return authimg;
        }

        public int getArttype() {
            return arttype;
        }

        public List<String> getLitpic() {
            return litpic;
        }
    }
}
