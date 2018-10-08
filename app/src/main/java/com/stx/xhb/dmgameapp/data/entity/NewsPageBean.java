package com.stx.xhb.dmgameapp.data.entity;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/8/24
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 资讯
 */
public class NewsPageBean {


    private int total;
    private List<ListBean> list;
    private List<SlidesBean> slides;

    public int getTotal() {
        return total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public List<SlidesBean> getSlides() {
        return slides;
    }

    public static class ListBean {
        /**
         * aid : 12855
         * arcurl : https://ol.3dmgame.com/news/201808/12855.html
         * title : 《剑网3》联动云裳羽衣 Sing女团动捕特效
         * litpic : https://ol.3dmgame.com/uploads/images/thumbnews/2018/0824/1535082658296.jpg
         * showtype : 1
         * click : 14
         * total_ct : 0
         * pubdate_at : 1535082666
         * webviewurl : https://m.3dmgame.com/ol/webview/news/201808/12855.html
         * user : {"id":14,"nickname":"泰可爱勒","avatarstr":"https://work.3dmgame.com/uploads/images/users/14.jpg"}
         * type : 新闻
         */

        private int aid;
        private String arcurl;
        private String title;
        private String litpic;
        private int showtype;
        private int click;
        private int total_ct;
        private long pubdate_at;
        private String webviewurl;
        private UserBean user;
        private String type;

        public int getAid() {
            return aid;
        }

        public String getArcurl() {
            return arcurl;
        }

        public String getTitle() {
            return title;
        }

        public String getLitpic() {
            return litpic;
        }

        public int getShowtype() {
            return showtype;
        }

        public int getClick() {
            return click;
        }

        public int getTotal_ct() {
            return total_ct;
        }

        public long getPubdate_at() {
            return pubdate_at;
        }

        public String getWebviewurl() {
            return webviewurl;
        }

        public UserBean getUser() {
            return user;
        }

        public String getType() {
            return type;
        }

        public static class UserBean {
            /**
             * id : 14
             * nickname : 泰可爱勒
             * avatarstr : https://work.3dmgame.com/uploads/images/users/14.jpg
             */

            private int id;
            private String nickname;
            private String avatarstr;

            public int getId() {
                return id;
            }

            public String getNickname() {
                return nickname;
            }

            public String getAvatarstr() {
                return avatarstr;
            }

        }
    }

    public static class SlidesBean {
        /**
         * aid : 3740840
         * arcurl : https://www.3dmgame.com/news/201808/3743832.html
         * litpic : https://img.3dmgame.com/uploads/images/thumbnews/20180822/1534932604_287077.jpg
         * title : 《骑马与砍杀2》技能等级系统丰富 玩家战场体验提升
         * showtype : 1
         * webviewurl : https://m.3dmgame.com/webview/news/201808/3743832.html
         */

        private int aid;
        private String arcurl;
        private String litpic;
        private String title;
        private int showtype;
        private String webviewurl;
        private String system;
        private float score;
        private int pubdate_at;

        public int getPubdate_at() {
            return pubdate_at;
        }

        public String getSystem() {
            return system;
        }

        public float getScore() {
            return score;
        }

        public int getAid() {
            return aid;
        }

        public String getArcurl() {
            return arcurl;
        }

        public String getLitpic() {
            return litpic;
        }

        public String getTitle() {
            return title;
        }

        public int getShowtype() {
            return showtype;
        }

        public String getWebviewurl() {
            return webviewurl;
        }
    }
}
