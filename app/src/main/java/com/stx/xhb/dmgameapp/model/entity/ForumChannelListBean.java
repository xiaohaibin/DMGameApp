package com.stx.xhb.dmgameapp.entity;

import com.stx.xhb.dmgameapp.base.BaseEntity;

import java.util.List;

/**
 * Author：xiaohaibin
 * Time：2017/9/17
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class ForumChannelListBean extends BaseEntity{

    /**
     * code : 1
     * channels_version : 1.0
     * html : [{"fid":"441","name":"热门游戏","is_ty":0},{"fid":"1009","name":"最新资源","is_ty":0},{"fid":"438","name":"即将发行","is_ty":0},{"fid":"3","name":"游戏综合","is_ty":0},{"fid":"502","name":"经典游戏","is_ty":0},{"fid":"353","name":"角色扮演","is_ty":0},{"fid":"354","name":"动作游戏","is_ty":0},{"fid":"355","name":"射击游戏","is_ty":0},{"fid":"504","name":"即时战略","is_ty":0},{"fid":"357","name":"策略战棋","is_ty":0},{"fid":"387","name":"模拟经营","is_ty":0},{"fid":"360","name":"体育竞速","is_ty":0},{"fid":"229","name":"电玩专题","is_ty":0},{"fid":"1962","name":"热门手游","is_ty":1},{"fid":"1982","name":"手游汉化","is_ty":1},{"fid":"1981","name":"手游最新","is_ty":1},{"fid":"2082","name":"手游推荐","is_ty":1},{"fid":"2017","name":"经典手游","is_ty":1},{"fid":"2027","name":"最热手游","is_ty":1},{"fid":"1978","name":"原创交流","is_ty":1},{"fid":"542","name":"动漫专题","is_ty":0},{"fid":"201","name":"娱乐休闲","is_ty":0},{"fid":"2279","name":"独立游戏","is_ty":0},{"fid":"2399","name":"论坛活动","is_ty":0}]
     */

    private String channels_version;
    private List<HtmlEntity> html;


    public String getChannels_version() {
        return channels_version;
    }

    public List<HtmlEntity> getHtml() {
        return html;
    }

    public static class HtmlEntity {
        /**
         * fid : 441
         * name : 热门游戏
         * is_ty : 0
         */

        private String fid;
        private String name;
        private int is_ty;

        public String getFid() {
            return fid;
        }

        public String getName() {
            return name;
        }

        public int getIs_ty() {
            return is_ty;
        }
    }
}
