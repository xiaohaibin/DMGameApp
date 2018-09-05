package com.stx.xhb.dmgameapp.data.entity;

import com.stx.xhb.dmgameapp.base.BaseEntity;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/8/24
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 资讯
 */
public class NewsPageBean extends BaseEntity{

    /**
     * code : 1
     * data : {"list":[{"aid":12855,"arcurl":"https://ol.3dmgame.com/news/201808/12855.html","title":"《剑网3》联动云裳羽衣 Sing女团动捕特效","litpic":"https://ol.3dmgame.com/uploads/images/thumbnews/2018/0824/1535082658296.jpg","showtype":1,"click":14,"total_ct":0,"pubdate_at":1535082666,"webviewurl":"https://m.3dmgame.com/ol/webview/news/201808/12855.html","user":{"id":14,"nickname":"泰可爱勒","avatarstr":"https://work.3dmgame.com/uploads/images/users/14.jpg"},"type":"新闻"},{"aid":3743972,"arcurl":"https://www.3dmgame.com/news/201808/3743972.html","title":"《小龙咖啡馆》IGN 6.4分 低风险低回报拖了后腿","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/2018/0824/1535081957378.jpg","showtype":1,"click":116,"total_ct":0,"pubdate_at":1535082232,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743972.html","user":{"id":189,"nickname":"勒布朗·詹姆斯","avatarstr":"https://work.3dmgame.com/uploads/images/users/189.jpg"},"type":"新闻"},{"aid":3743971,"arcurl":"https://www.3dmgame.com/news/201808/3743971.html","title":"动作新作《恶魔狩猎》新演示公布 用拳头和魔爪杀恶魔","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180824/1535081242_838927.jpg","showtype":1,"click":410,"total_ct":1,"pubdate_at":1535081245,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743971.html","user":{"id":48,"nickname":"liyunfei","avatarstr":"https://work.3dmgame.com/uploads/images/users/48.jpg"},"type":"新闻"},{"aid":3743969,"arcurl":"https://www.3dmgame.com/news/201808/3743969.html","title":"日本妹子Cos美图合集欣赏：穿开胸皮衣清凉上阵 颜值逆天","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180824/1535079640_739743.jpg","showtype":1,"click":1512,"total_ct":0,"pubdate_at":1535079659,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743969.html","user":{"id":48,"nickname":"liyunfei","avatarstr":"https://work.3dmgame.com/uploads/images/users/48.jpg"},"type":"新闻"},{"aid":3743968,"arcurl":"https://www.3dmgame.com/news/201808/3743968.html","title":"画风唯美意境清新！国游精品《雨纪》登陆Switch","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180824/1535079319_440584.jpg","showtype":1,"click":622,"total_ct":1,"pubdate_at":1535079360,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743968.html","user":{"id":190,"nickname":"陶笛","avatarstr":"https://work.3dmgame.com/uploads/images/users/190.jpg"},"type":"新闻"},{"aid":3743967,"arcurl":"https://www.3dmgame.com/news/201808/3743967.html","title":"《如龙：极2》IGN 8.0分 比如龙6更好带玩家\u201c入坑\u201d","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/2018/0824/1535078879706.jpg","showtype":1,"click":1379,"total_ct":1,"pubdate_at":1535079143,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743967.html","user":{"id":189,"nickname":"勒布朗·詹姆斯","avatarstr":"https://work.3dmgame.com/uploads/images/users/189.jpg"},"type":"新闻"},{"aid":3743966,"arcurl":"https://www.3dmgame.com/news/201808/3743966.html","title":"突破千万纪念赠礼！《怪物猎人：世界》新任务公布","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180824/1535078457_380797.png","showtype":1,"click":1620,"total_ct":3,"pubdate_at":1535078460,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743966.html","user":{"id":190,"nickname":"陶笛","avatarstr":"https://work.3dmgame.com/uploads/images/users/190.jpg"},"type":"新闻"},{"aid":3743965,"arcurl":"https://www.3dmgame.com/news/201808/3743965.html","title":"GC 2018：《克苏鲁的呼唤》新演示 男主被触手蹂躏","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180824/1535078201_740898.jpg","showtype":1,"click":750,"total_ct":0,"pubdate_at":1535078283,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743965.html","user":{"id":48,"nickname":"liyunfei","avatarstr":"https://work.3dmgame.com/uploads/images/users/48.jpg"},"type":"新闻"},{"aid":29342,"arcurl":"https://shouyou.3dmgame.com/news/29342.html","title":"505Games宣布获得《人类：一败涂地》手游全球发行权","litpic":"https://shouyou.3dmgame.com/uploadimg/thumb/2018/0824/1535078018611.png","showtype":1,"click":623,"total_ct":0,"pubdate_at":1535077843,"webviewurl":"https://app.3dmgame.com/webview/news/29342.html","user":{"id":211,"nickname":"永远的18岁","avatarstr":"https://work.3dmgame.com/uploads/images/users/211.jpg"},"type":"新闻"},{"aid":3743964,"arcurl":"https://www.3dmgame.com/news/201808/3743964.html","title":"北欧女神融入ATB战斗 手绘独立游戏《不可分割》试玩演示","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180824/1535076880_535597.jpg","showtype":1,"click":1134,"total_ct":2,"pubdate_at":1535077040,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743964.html","user":{"id":73,"nickname":"某某","avatarstr":"https://work.3dmgame.com/uploads/images/users/73.jpg"},"type":"新闻"}],"total":1000,"slides":[{"aid":3740840,"arcurl":"https://www.3dmgame.com/news/201808/3743832.html","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180822/1534932604_287077.jpg","title":"《骑马与砍杀2》技能等级系统丰富 玩家战场体验提升","showtype":1,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743832.html"},{"aid":3740843,"arcurl":"https://www.3dmgame.com/news/201808/3743874.html","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180823/1535018823_324017.jpg","title":"GC 2018：《骑马与砍杀2》单人战役演示 探索地图挑战","showtype":1,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743874.html"},{"aid":3740844,"arcurl":"https://www.3dmgame.com/news/201808/3743862.html","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180823/1535018762_896096.jpg","title":"GC 2018：东方雅楠欢迎您 《只狼》官方高清视频","showtype":1,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743862.html"},{"aid":3733223,"arcurl":"https://www.3dmgame.com/news/201808/3743901.html","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180823/1535018517_760733.jpg","title":"《鬼泣5》新情报艺术原画 尼禄拥有8种不同形态鬼手","showtype":1,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743901.html"},{"aid":3733224,"arcurl":"https://www.3dmgame.com/news/201808/3743861.html","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180823/1535018662_800917.jpg","title":"GC 2018：《生化危机2：重制版》PC版4K高清展示","showtype":2,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743861.html"}]}
     * msg : 成功
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public static class DataBean {
        /**
         * list : [{"aid":12855,"arcurl":"https://ol.3dmgame.com/news/201808/12855.html","title":"《剑网3》联动云裳羽衣 Sing女团动捕特效","litpic":"https://ol.3dmgame.com/uploads/images/thumbnews/2018/0824/1535082658296.jpg","showtype":1,"click":14,"total_ct":0,"pubdate_at":1535082666,"webviewurl":"https://m.3dmgame.com/ol/webview/news/201808/12855.html","user":{"id":14,"nickname":"泰可爱勒","avatarstr":"https://work.3dmgame.com/uploads/images/users/14.jpg"},"type":"新闻"},{"aid":3743972,"arcurl":"https://www.3dmgame.com/news/201808/3743972.html","title":"《小龙咖啡馆》IGN 6.4分 低风险低回报拖了后腿","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/2018/0824/1535081957378.jpg","showtype":1,"click":116,"total_ct":0,"pubdate_at":1535082232,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743972.html","user":{"id":189,"nickname":"勒布朗·詹姆斯","avatarstr":"https://work.3dmgame.com/uploads/images/users/189.jpg"},"type":"新闻"},{"aid":3743971,"arcurl":"https://www.3dmgame.com/news/201808/3743971.html","title":"动作新作《恶魔狩猎》新演示公布 用拳头和魔爪杀恶魔","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180824/1535081242_838927.jpg","showtype":1,"click":410,"total_ct":1,"pubdate_at":1535081245,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743971.html","user":{"id":48,"nickname":"liyunfei","avatarstr":"https://work.3dmgame.com/uploads/images/users/48.jpg"},"type":"新闻"},{"aid":3743969,"arcurl":"https://www.3dmgame.com/news/201808/3743969.html","title":"日本妹子Cos美图合集欣赏：穿开胸皮衣清凉上阵 颜值逆天","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180824/1535079640_739743.jpg","showtype":1,"click":1512,"total_ct":0,"pubdate_at":1535079659,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743969.html","user":{"id":48,"nickname":"liyunfei","avatarstr":"https://work.3dmgame.com/uploads/images/users/48.jpg"},"type":"新闻"},{"aid":3743968,"arcurl":"https://www.3dmgame.com/news/201808/3743968.html","title":"画风唯美意境清新！国游精品《雨纪》登陆Switch","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180824/1535079319_440584.jpg","showtype":1,"click":622,"total_ct":1,"pubdate_at":1535079360,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743968.html","user":{"id":190,"nickname":"陶笛","avatarstr":"https://work.3dmgame.com/uploads/images/users/190.jpg"},"type":"新闻"},{"aid":3743967,"arcurl":"https://www.3dmgame.com/news/201808/3743967.html","title":"《如龙：极2》IGN 8.0分 比如龙6更好带玩家\u201c入坑\u201d","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/2018/0824/1535078879706.jpg","showtype":1,"click":1379,"total_ct":1,"pubdate_at":1535079143,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743967.html","user":{"id":189,"nickname":"勒布朗·詹姆斯","avatarstr":"https://work.3dmgame.com/uploads/images/users/189.jpg"},"type":"新闻"},{"aid":3743966,"arcurl":"https://www.3dmgame.com/news/201808/3743966.html","title":"突破千万纪念赠礼！《怪物猎人：世界》新任务公布","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180824/1535078457_380797.png","showtype":1,"click":1620,"total_ct":3,"pubdate_at":1535078460,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743966.html","user":{"id":190,"nickname":"陶笛","avatarstr":"https://work.3dmgame.com/uploads/images/users/190.jpg"},"type":"新闻"},{"aid":3743965,"arcurl":"https://www.3dmgame.com/news/201808/3743965.html","title":"GC 2018：《克苏鲁的呼唤》新演示 男主被触手蹂躏","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180824/1535078201_740898.jpg","showtype":1,"click":750,"total_ct":0,"pubdate_at":1535078283,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743965.html","user":{"id":48,"nickname":"liyunfei","avatarstr":"https://work.3dmgame.com/uploads/images/users/48.jpg"},"type":"新闻"},{"aid":29342,"arcurl":"https://shouyou.3dmgame.com/news/29342.html","title":"505Games宣布获得《人类：一败涂地》手游全球发行权","litpic":"https://shouyou.3dmgame.com/uploadimg/thumb/2018/0824/1535078018611.png","showtype":1,"click":623,"total_ct":0,"pubdate_at":1535077843,"webviewurl":"https://app.3dmgame.com/webview/news/29342.html","user":{"id":211,"nickname":"永远的18岁","avatarstr":"https://work.3dmgame.com/uploads/images/users/211.jpg"},"type":"新闻"},{"aid":3743964,"arcurl":"https://www.3dmgame.com/news/201808/3743964.html","title":"北欧女神融入ATB战斗 手绘独立游戏《不可分割》试玩演示","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180824/1535076880_535597.jpg","showtype":1,"click":1134,"total_ct":2,"pubdate_at":1535077040,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743964.html","user":{"id":73,"nickname":"某某","avatarstr":"https://work.3dmgame.com/uploads/images/users/73.jpg"},"type":"新闻"}]
         * total : 1000
         * slides : [{"aid":3740840,"arcurl":"https://www.3dmgame.com/news/201808/3743832.html","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180822/1534932604_287077.jpg","title":"《骑马与砍杀2》技能等级系统丰富 玩家战场体验提升","showtype":1,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743832.html"},{"aid":3740843,"arcurl":"https://www.3dmgame.com/news/201808/3743874.html","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180823/1535018823_324017.jpg","title":"GC 2018：《骑马与砍杀2》单人战役演示 探索地图挑战","showtype":1,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743874.html"},{"aid":3740844,"arcurl":"https://www.3dmgame.com/news/201808/3743862.html","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180823/1535018762_896096.jpg","title":"GC 2018：东方雅楠欢迎您 《只狼》官方高清视频","showtype":1,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743862.html"},{"aid":3733223,"arcurl":"https://www.3dmgame.com/news/201808/3743901.html","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180823/1535018517_760733.jpg","title":"《鬼泣5》新情报艺术原画 尼禄拥有8种不同形态鬼手","showtype":1,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743901.html"},{"aid":3733224,"arcurl":"https://www.3dmgame.com/news/201808/3743861.html","litpic":"https://img.3dmgame.com/uploads/images/thumbnews/20180823/1535018662_800917.jpg","title":"GC 2018：《生化危机2：重制版》PC版4K高清展示","showtype":2,"webviewurl":"https://m.3dmgame.com/webview/news/201808/3743861.html"}]
         */

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

        public static class ListBean    {
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
}
