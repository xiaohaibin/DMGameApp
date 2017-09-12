package com.stx.xhb.dmgameapp.entity;

/**
 * Created by xhb on 2016/1/19.
 * 文章列表实体类
 */
public class ChapterListEntity {


    private String id;
    private String typeid;
    private String typeid2;
    private String sortrank;
    private String flag;
    private String ismake;
    private String channel;
    private String arcrank;
    private String click;
    private String money;
    private String title;
    private String shorttitle;
    private String color;
    private String writer;
    private String source;
    private String litpic;
    private String pubdate;
    private String senddate;
    private String mid;
    private String keywords;
    private String lastpost;
    private String scores;
    private String goodpost;
    private String badpost;
    private String voteid;
    private String notpost;
    private String description;
    private String filename;
    private String dutyadmin;
    private String tackid;
    private String mtype;
    private String weight;
    private String fby_id;
    private String game_id;
    private String feedback;
    private String typedir;
    private String typename;
    private String corank;
    private String isdefault;
    private String defaultname;
    private String namerule;
    private String namerule2;
    private String ispart;
    private String moresite;
    private String siteurl;
    private String sitepath;
    private String arcurl;
    private String typeurl;

    public ChapterListEntity(String id, String typeid, String title, String senddate, String litpic, String feedback, String arcurl) {
        this.id = id;//文章id
        this.typeid = typeid;//分类id
        this.title = title;//文章标题
        this.senddate = senddate;//发布时间
        this.litpic = litpic;//文章缩略图地址
        this.feedback = feedback;//评论数
        this.arcurl = arcurl;//文章网页地址
    }


    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }


    public String getLitpic() {
        return litpic;
    }


    public String getSenddate() {
        return senddate;
    }


    public String getSource() {
        return source;
    }


    public String getTitle() {
        return title;
    }


    public String getTypeid() {
        return typeid;
    }

}


