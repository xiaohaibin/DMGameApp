package com.stx.xhb.dmgameapp.entity;

/**
 * Author：xiaohaibin
 * Time：2017/9/9
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：资讯详情提交内容实体类
 */
public class NewsDetailsContentBean {

    private String page="";
    private String id="";
    private String key="";
    private String appid="";

    public NewsDetailsContentBean(String page, String id, String key, String appid) {
        this.page = page;
        this.id = id;
        this.key = key;
        this.appid = appid;
    }
}
