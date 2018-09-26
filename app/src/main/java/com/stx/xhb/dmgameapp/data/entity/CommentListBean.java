package com.stx.xhb.dmgameapp.data.entity;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/3/1
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 评论列表实体类
 */

public class CommentListBean {


    /**
     * total : 77
     * hotlist : [{"id":42850,"content":"我家猫说话了，说了一堆东西我也听不懂，放出来大家帮忙参考一下... 4-3 062212-368 11-3 INCT-007 13-3 IPZ-566 14-3 SNIS-939 15-3 STAR-757 16-3 INU-047 17-3 IPX-104 18-3 IPX-104 19-3 ABP-356 20-3 g-area_pgm_633sara 22-3 URE-015 23-3 HUNTA-236 24-3 MIRD-108 25-3 EBOD259 28-3 日剧《特命系长只野仁2017》29-3 Heyzo-0648 30-3 IMBD-203 31-3 AKA-008 34-3 SHKD-595 35-3 IPZ-971 36-3 SNIS-967 37-3 HMPD-10052 38-3 SVDVD-662 40-3 ARM-510 43-3 SDMU-638 44-3 SOE-922 45-3 SNIS-419","position":29,"goodcount":53,"badcount":0,"pubdate_at":1537508149,"time":"09-21 13:35","user":{"uid":1067846,"nickname":"luckmomo","avatarstr":"https://my.3dmgame.com/uploads/images/avatar/20180810/1533888691_529275.jpg","gender":1,"regionstr":"上海市宝山区","title":"PC党","title_level":2},"replies":[],"praise":0}]
     * list : [{"id":46950,"position":77,"goodcount":0,"badcount":0,"content":"12-3有没有房号？","time":"09-26 10:50","pubdate_at":1537930256,"user":{"uid":2304758,"nickname":"小丑0930","avatarstr":"https://my.3dmgame.com/uploads/images/avatar/20180306/1520319732_470025.jpg","gender":1,"regionstr":"广东广州市","title":"初出茅庐","title_level":0},"replies":[],"praise":0},{"id":46491,"position":74,"goodcount":0,"badcount":0,"content":"Naughty Redhead Bunny","time":"09-25 19:54","pubdate_at":1537876499,"user":{"uid":6209402,"nickname":"RikinYip","avatarstr":"https://my.3dmgame.com/uploads/images/avatar/default.jpg","gender":0,"regionstr":"广东省阳江市","title":"初出茅庐","title_level":0},"replies":[{"id":45495,"position":72,"goodcount":1,"badcount":0,"pubdate_at":1537786327,"time":"09-24 18:52","content":"居然没人求33-3","user":{"uid":801395,"nickname":"体臭天王","avatarstr":"https://my.3dmgame.com/uploads/images/avatar/20180306/1520317609_864490.jpg","gender":1,"regionstr":"广西梧州市","title":"初出茅庐","title_level":0},"praise":0}],"praise":0},{"id":45495,"position":72,"goodcount":1,"badcount":0,"content":"居然没人求33-3","time":"09-24 18:52","pubdate_at":1537786327,"user":{"uid":"801395","nickname":"体臭天王","avatarstr":"https://my.3dmgame.com/uploads/images/avatar/20180306/1520317609_864490.jpg","gender":1,"regionstr":"广西梧州市","title":"初出茅庐","title_level":0},"replies":[],"praise":0}]
     * total_uid : 159
     * c_sid : 6392
     * f_sid : 6392
     */

    private int total;
    private int total_uid;
    private int c_sid;
    private int f_sid;
    private List<ListBean> hotlist;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public int getTotal_uid() {
        return total_uid;
    }

    public int getC_sid() {
        return c_sid;
    }

    public int getF_sid() {
        return f_sid;
    }

    public List<ListBean> getHotlist() {
        return hotlist;
    }

    public List<ListBean> getList() {
        return list;
    }

    public static class ListBean {
        /**
         * id : 46950
         * position : 77
         * goodcount : 0
         * badcount : 0
         * content : 12-3有没有房号？
         * time : 09-26 10:50
         * pubdate_at : 1537930256
         * user : {"uid":2304758,"nickname":"小丑0930","avatarstr":"https://my.3dmgame.com/uploads/images/avatar/20180306/1520319732_470025.jpg","gender":1,"regionstr":"广东广州市","title":"初出茅庐","title_level":0}
         * replies : []
         * praise : 0
         */

        private int id;
        private int position;
        private int goodcount;
        private int badcount;
        private String content;
        private String time;
        private int pubdate_at;
        private CommentsBean.UserBean user;
        private int praise;
        private List<CommentsBean> replies;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getPubdate_at() {
            return pubdate_at;
        }

        public void setPubdate_at(int pubdate_at) {
            this.pubdate_at = pubdate_at;
        }

        public CommentsBean.UserBean getUser() {
            return user;
        }

        public int getPraise() {
            return praise;
        }

        public List<CommentsBean> getReplies() {
            return replies;
        }
    }
}
