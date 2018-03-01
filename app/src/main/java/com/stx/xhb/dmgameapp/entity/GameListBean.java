package com.stx.xhb.dmgameapp.entity;

import com.stx.xhb.dmgameapp.base.BaseEntity;

import java.util.List;

/**
 * Author：xiaohaibin
 * Time：2017/9/20
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class GameListBean extends BaseEntity{

    private int totalrow;
    private List<HtmlEntity> html;

    public int getTotalrow() {
        return totalrow;
    }

    public List<HtmlEntity> getHtml() {
        return html;
    }

    public static class HtmlEntity {

        private String id;
        private String title;
        private String litpic;
        private String game_trans_name;
        private String tid;
        private String release_date;
        private String terrace;
        private String release_company;
        private String changyan_id;
        private String description;
        private String lmfl;


        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getLitpic() {
            return litpic;
        }

        public String getGame_trans_name() {
            return game_trans_name;
        }

        public String getTid() {
            return tid;
        }

        public String getRelease_date() {
            return release_date;
        }

        public String getTerrace() {
            return terrace;
        }

        public String getRelease_company() {
            return release_company;
        }

        public String getChangyan_id() {
            return changyan_id;
        }

        public String getDescription() {
            return description;
        }

        public String getLmfl() {
            return lmfl;
        }
    }
}
