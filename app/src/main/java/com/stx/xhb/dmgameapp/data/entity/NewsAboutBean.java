package com.stx.xhb.dmgameapp.data.entity;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/26
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class NewsAboutBean {

    /**
     * game : {"aid":"3575016","arcurl":"https://www.3dmgame.com/games/mountblade2b/","title":"骑马与砍杀2：领主","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180723/1532333197_237093.jpg","system":"PC PS4 XBOXONE","type":"角色扮演","score":6.7,"pubdate_at":-28800,"showdate_at":"未知","showtype":3,"labels":[{"id":"2","name":"战争"},{"id":"8","name":"写实"},{"id":"27","name":"中世纪"},{"id":"67","name":"经商"}]}
     * list : [{"aid":"3745529","arcurl":"https://www.3dmgame.com/news/201809/3745529.html","title":"《骑马与砍杀2》新引擎介绍：优化给力 粒子效果棒","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/2018/0917/1537145278256.jpg","pubdate_at":1537145334,"showtype":1,"webviewurl":"https://m.3dmgame.com/webview/news/201809/3745529.html","total_ct":16},{"aid":"3744895","arcurl":"https://www.3dmgame.com/news/201809/3744895.html","title":"《骑马与砍杀2》或将开启抢先体验 暂时没有多人合作","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180907/1536298375_319188.jpg","pubdate_at":1536298418,"showtype":1,"webviewurl":"https://m.3dmgame.com/webview/news/201809/3744895.html","total_ct":10},{"aid":"3743983","arcurl":"https://www.3dmgame.com/news/201808/3743983.html","title":"《骑马与砍杀2》角色成长系统介绍 升级机制详解","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180824/1535093884_728439.jpg","pubdate_at":1535093891,"showtype":1,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743983.html","total_ct":8}]
     */

    private GameBean game;
    private List<ListBean> list;

    public GameBean getGame() {
        return game;
    }

    public void setGame(GameBean game) {
        this.game = game;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class GameBean {
        /**
         * aid : 3575016
         * arcurl : https://www.3dmgame.com/games/mountblade2b/
         * title : 骑马与砍杀2：领主
         * litpic : https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180723/1532333197_237093.jpg
         * system : PC PS4 XBOXONE
         * type : 角色扮演
         * score : 6.7
         * pubdate_at : -28800
         * showdate_at : 未知
         * showtype : 3
         * labels : [{"id":"2","name":"战争"},{"id":"8","name":"写实"},{"id":"27","name":"中世纪"},{"id":"67","name":"经商"}]
         */

        private String aid;
        private String arcurl;
        private String title;
        private String litpic;
        private String system;
        private String type;
        private double score;
        private int pubdate_at;
        private String showdate_at;
        private int showtype;
        private List<LabelsBean> labels;

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getArcurl() {
            return arcurl;
        }

        public void setArcurl(String arcurl) {
            this.arcurl = arcurl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }

        public String getSystem() {
            return system;
        }

        public void setSystem(String system) {
            this.system = system;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getPubdate_at() {
            return pubdate_at;
        }

        public void setPubdate_at(int pubdate_at) {
            this.pubdate_at = pubdate_at;
        }

        public String getShowdate_at() {
            return showdate_at;
        }

        public void setShowdate_at(String showdate_at) {
            this.showdate_at = showdate_at;
        }

        public int getShowtype() {
            return showtype;
        }

        public void setShowtype(int showtype) {
            this.showtype = showtype;
        }

        public List<LabelsBean> getLabels() {
            return labels;
        }

        public void setLabels(List<LabelsBean> labels) {
            this.labels = labels;
        }

        public static class LabelsBean {
            /**
             * id : 2
             * name : 战争
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class ListBean {
        /**
         * aid : 3745529
         * arcurl : https://www.3dmgame.com/news/201809/3745529.html
         * title : 《骑马与砍杀2》新引擎介绍：优化给力 粒子效果棒
         * litpic : https://img.3dmgame.com/uploads/images/thumbnews/2018/0917/1537145278256.jpg
         * pubdate_at : 1537145334
         * showtype : 1
         * webviewurl : https://m.3dmgame.com/webview/news/201809/3745529.html
         * total_ct : 16
         */

        private String aid;
        private String arcurl;
        private String title;
        private String litpic;
        private int pubdate_at;
        private int showtype;
        private String webviewurl;
        private int total_ct;

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getArcurl() {
            return arcurl;
        }

        public void setArcurl(String arcurl) {
            this.arcurl = arcurl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLitpic() {
            return litpic;
        }

        public void setLitpic(String litpic) {
            this.litpic = litpic;
        }

        public int getPubdate_at() {
            return pubdate_at;
        }

        public void setPubdate_at(int pubdate_at) {
            this.pubdate_at = pubdate_at;
        }

        public int getShowtype() {
            return showtype;
        }

        public void setShowtype(int showtype) {
            this.showtype = showtype;
        }

        public String getWebviewurl() {
            return webviewurl;
        }

        public void setWebviewurl(String webviewurl) {
            this.webviewurl = webviewurl;
        }

        public int getTotal_ct() {
            return total_ct;
        }

        public void setTotal_ct(int total_ct) {
            this.total_ct = total_ct;
        }
    }
}
