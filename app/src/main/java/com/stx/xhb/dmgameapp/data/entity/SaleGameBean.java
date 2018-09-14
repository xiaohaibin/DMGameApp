package com.stx.xhb.dmgameapp.data.entity;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/14
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:  发售游戏实体类
 */
public class SaleGameBean {

    /**
     * total : 500
     * list : [{"aid":"3736876","arcurl":"https://www.3dmgame.com/games/fifa19/","title":"FIFA 19","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180820/1534760690_699365.jpg","pubdate_at":1536768000,"showdate_at":"2018-09-28","showtype":3,"system":"PC PS4 XBOXONE","score":"8.1","type":"体育运动","labels":[{"id":"8","name":"写实"},{"id":"42","name":"足球"}]},{"aid":"3735974","arcurl":"https://www.3dmgame.com/games/nba2k19/","title":"NBA 2K19","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180716/1531719942_545732.jpg","pubdate_at":1536681600,"showdate_at":"2018-09-12","showtype":3,"system":"PC Switch PS4 XBOXONE","score":"5.8","type":"体育运动","labels":[{"id":"8","name":"写实"},{"id":"41","name":"篮球"}]},{"aid":"3712501","arcurl":"https://www.3dmgame.com/games/snkhttf/","title":"SNK女格斗家大乱斗","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180907/1536289976_982297.jpg","pubdate_at":1536249600,"showdate_at":"2018-09-07","showtype":3,"system":"Switch PS4","score":"6.3","type":"格斗游戏","labels":[{"id":"71","name":"美少女"}]},{"aid":"3682037","arcurl":"https://www.3dmgame.com/games/immortalu/","title":"众神：解放","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180908/1536370867_267132.jpg","pubdate_at":1536249600,"showdate_at":"2018-09-07","showtype":3,"system":"PC PS4 XBOXONE","score":"8.8","type":"角色扮演","labels":[{"id":"3","name":"魔幻"},{"id":"22","name":"探险"}]},{"aid":"3663151","arcurl":"https://www.3dmgame.com/games/spiderman/","title":"漫威蜘蛛侠","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180719/1531982214_509075.jpg","pubdate_at":1536249600,"showdate_at":"2018-09-07","showtype":3,"system":"PS4","score":"7.0","type":"动作游戏","labels":[{"id":"10","name":"都市"},{"id":"92","name":"漫威"},{"id":"96","name":"电影改"}]},{"aid":"3618782","arcurl":"https://www.3dmgame.com/games/dragonquestxi/","title":"勇者斗恶龙11","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180718/1531896157_536015.jpg","pubdate_at":1536076800,"showdate_at":"2018-09-05","showtype":3,"system":"PC Switch PS4 掌机","score":"6.4","type":"角色扮演","labels":[{"id":"3","name":"魔幻"},{"id":"20","name":"日系"},{"id":"102","name":"经典"}]},{"aid":"3732146","arcurl":"https://www.3dmgame.com/games/shadowsawakening/","title":"暗影：觉醒","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180707/1530954403_189177.jpg","pubdate_at":1535731200,"showdate_at":"2018-09-01","showtype":3,"system":"PC PS4 XBOXONE","score":"5.8","type":"动作角色","labels":[{"id":"3","name":"魔幻"},{"id":"60","name":"合作"},{"id":"69","name":"剧情"}]},{"aid":"3670289","arcurl":"https://www.3dmgame.com/games/narutotbss/","title":"火影忍者博人传：新忍出击","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180830/1535609142_999340.jpg","pubdate_at":1535644800,"showdate_at":"2018-08-31","showtype":3,"system":"PC PS4 XBOXONE","score":"5.3","type":"动作游戏","labels":[{"id":"20","name":"日系"},{"id":"34","name":"二次元"},{"id":"95","name":"漫改"}]},{"aid":"3741754","arcurl":"https://www.3dmgame.com/games/pes2019/","title":"实况足球2019","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180707/1530955919_575037.jpg","pubdate_at":1535558400,"showdate_at":"2018-08-30","showtype":3,"system":"PC PS4 XBOXONE","score":"7.3","type":"体育运动","labels":[{"id":"8","name":"写实"},{"id":"42","name":"足球"},{"id":"51","name":"对抗"}]},{"aid":"3713045","arcurl":"https://www.3dmgame.com/games/twopointhospital/","title":"双点医院","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180711/1531303432_957712.jpg","pubdate_at":1535558400,"showdate_at":"2018-08-30","showtype":3,"system":"PC","score":"8.6","type":"模拟经营","labels":[{"id":"67","name":"经商"},{"id":"87","name":"建造"}]}]
     */

    private int total;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * aid : 3736876
         * arcurl : https://www.3dmgame.com/games/fifa19/
         * title : FIFA 19
         * litpic : https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180820/1534760690_699365.jpg
         * pubdate_at : 1536768000
         * showdate_at : 2018-09-28
         * showtype : 3
         * system : PC PS4 XBOXONE
         * score : 8.1
         * type : 体育运动
         * labels : [{"id":"8","name":"写实"},{"id":"42","name":"足球"}]
         */

        private String aid;
        private String arcurl;
        private String title;
        private String litpic;
        private int pubdate_at;
        private String showdate_at;
        private int showtype;
        private String system;
        private String score;
        private String type;
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

        public String getSystem() {
            return system;
        }

        public void setSystem(String system) {
            this.system = system;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<LabelsBean> getLabels() {
            return labels;
        }

        public void setLabels(List<LabelsBean> labels) {
            this.labels = labels;
        }

        public static class LabelsBean {
            /**
             * id : 8
             * name : 写实
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
}
