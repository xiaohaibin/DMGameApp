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

public class GameChannelListEntity extends BaseEntity{

    /**
     * code : 1
     * channels_version : 1.0
     * html : [{"appid":"1","title":"热门"},{"appid":"3","title":"排行"},{"appid":"2","title":"最新"},{"appid":"4","title":"发售表"},{"appid":"5","title":"我关注的"}]
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
         * appid : 1
         * title : 热门
         */

        private String appid;
        private String title;

        public String getAppid() {
            return appid;
        }

        public String getTitle() {
            return title;
        }
    }
}
