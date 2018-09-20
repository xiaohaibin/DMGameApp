package com.stx.xhb.dmgameapp.data.entity;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/20
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 游戏排行
 */
public class GameRankBean {

    /**
     * total : 500
     * list : [{"aid":"244303","arcurl":"https://www.3dmgame.com/games/gta5/","title":"侠盗猎车5","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180728/1532770547_615081.jpg","pubdate_at":1428940800,"showdate_at":"2015-04-14","click":"31466660","showtype":3,"isfavorite":0,"score":"8.6","system":"PC PS4 XBOXONE PS3 XBOX360","type":"动作角色"},{"aid":"268454","arcurl":"https://www.3dmgame.com/games/elderscrolls5skyrim/","title":"上古卷轴5：天际","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180529/1527578329_606706.jpg","pubdate_at":1320940800,"showdate_at":"2011-11-11","click":"16471160","showtype":3,"isfavorite":0,"score":"8.9","system":"PC PS4 XBOXONE PS3 XBOX360","type":"角色扮演"},{"aid":"2281579","arcurl":"https://www.3dmgame.com/games/needforspeed18/","title":"极品飞车18：宿敌","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180522/1526960029_838372.jpg","pubdate_at":1384790400,"showdate_at":"2013-11-19","click":"12582074","showtype":3,"isfavorite":0,"score":"6.8","system":"PC PS4 XBOXONE PS3 XBOX360","type":"赛车游戏"},{"aid":"3577515","arcurl":"https://www.3dmgame.com/games/nierautomata/","title":"尼尔：机械纪元","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180723/1532327846_923833.jpg","pubdate_at":1489680000,"showdate_at":"2017-03-17","click":"7168367","showtype":3,"isfavorite":0,"score":"8.4","system":"PC PS4 XBOXONE","type":"动作游戏"},{"aid":"2290818","arcurl":"https://www.3dmgame.com/games/witcher3/","title":"巫师3：狂猎","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180728/1532770815_753424.jpg","pubdate_at":1431964800,"showdate_at":"2015-05-19","click":"5153011","showtype":3,"isfavorite":0,"score":"9.3","system":"PC PS4 XBOXONE","type":"角色扮演"},{"aid":"3563620","arcurl":"https://www.3dmgame.com/games/civilization6/","title":"文明6","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180724/1532412242_238190.jpg","pubdate_at":1476979200,"showdate_at":"2016-10-21","click":"5090968","showtype":3,"isfavorite":0,"score":"8.0","system":"PC","type":"策略游戏"},{"aid":"3569371","arcurl":"https://www.3dmgame.com/games/watchdogs2/","title":"看门狗2","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180724/1532400033_147508.jpg","pubdate_at":1480262400,"showdate_at":"2016-11-28","click":"4352369","showtype":3,"isfavorite":0,"score":"8.2","system":"PC PS4 XBOXONE","type":"动作角色"},{"aid":"3295199","arcurl":"https://www.3dmgame.com/games/thesims4/","title":"模拟人生4","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180728/1532744119_757316.jpg","pubdate_at":1409760000,"showdate_at":"2014-09-04","click":"4021549","showtype":3,"isfavorite":0,"score":"7.7","system":"PC","type":"模拟经营"},{"aid":"2299638","arcurl":"https://www.3dmgame.com/games/mgs5/","title":"合金装备5：幻痛","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180522/1526976864_120464.jpg","pubdate_at":1441036800,"showdate_at":"2015-09-01","click":"3927626","showtype":3,"isfavorite":0,"score":"8.5","system":"PC PS4 XBOXONE","type":"动作游戏"},{"aid":"3671195","arcurl":"https://www.3dmgame.com/games/bloodborne/","title":"血源诅咒","litpic":"https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180718/1531909627_312947.jpg","pubdate_at":1427126400,"showdate_at":"2015-03-24","click":"3791851","showtype":3,"isfavorite":0,"score":"8.5","system":"PS4","type":"动作角色"}]
     */

    private int total;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public static class ListBean {
        /**
         * aid : 244303
         * arcurl : https://www.3dmgame.com/games/gta5/
         * title : 侠盗猎车5
         * litpic : https://img.3dmgame.com/uploads/images/thumbkwdfirst/20180728/1532770547_615081.jpg
         * pubdate_at : 1428940800
         * showdate_at : 2015-04-14
         * click : 31466660
         * showtype : 3
         * isfavorite : 0
         * score : 8.6
         * system : PC PS4 XBOXONE PS3 XBOX360
         * type : 动作角色
         */

        private String aid;
        private String arcurl;
        private String title;
        private String litpic;
        private int pubdate_at;
        private String showdate_at;
        private String click;
        private int showtype;
        private int isfavorite;
        private String score;
        private String system;
        private String type;

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

        public String getClick() {
            return click;
        }

        public void setClick(String click) {
            this.click = click;
        }

        public int getShowtype() {
            return showtype;
        }

        public void setShowtype(int showtype) {
            this.showtype = showtype;
        }

        public int getIsfavorite() {
            return isfavorite;
        }

        public void setIsfavorite(int isfavorite) {
            this.isfavorite = isfavorite;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
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
    }
}
