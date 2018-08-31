package com.stx.xhb.dmgameapp.data.entity;

/**
 * Author：xiaohaibin
 * Time：2017/9/21
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：论坛实体类
 */

public class ForumBean {

    private String fid="";
    private String name="";
    private String todayposts="";
    private String rank="";
    private String icon="";
    private String type="";

    public void setFid(String fid) {
        this.fid = fid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTodayposts(String todayposts) {
        this.todayposts = todayposts;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFid() {
        return fid;
    }

    public String getName() {
        return name;
    }

    public String getTodayposts() {
        return todayposts;
    }

    public String getRank() {
        return rank;
    }

    public String getIcon() {
        return icon;
    }

    public String getType() {
        return type;
    }
}
