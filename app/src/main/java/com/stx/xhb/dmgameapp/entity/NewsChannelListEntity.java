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

public class NewsChannelListEntity extends BaseEntity{

    /**
     * code : 1
     * channels_version : 1.0
     * html : [{"appid":"1","title":"最新","type":"0"},{"appid":"3","title":"游戏","type":"0"},{"appid":"4","title":"热点","type":"0"},{"appid":"2","title":"视频","type":"1"},{"appid":"5","title":"杂谈","type":"0"},{"appid":"6","title":"评测","type":"0"},{"appid":"7","title":"原创","type":"0"},{"appid":"8","title":"前瞻","type":"0"},{"appid":"9","title":"盘点","type":"0"},{"appid":"10","title":"时事","type":"0"},{"appid":"11","title":"发售","type":"0"},{"appid":"12","title":"汉化","type":"0"},{"appid":"13","title":"攻略","type":"0"}]
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
         * title : 最新
         * type : 0
         */

        private String appid;
        private String title;
        private String type;

        public String getAppid() {
            return appid;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }
    }
}
