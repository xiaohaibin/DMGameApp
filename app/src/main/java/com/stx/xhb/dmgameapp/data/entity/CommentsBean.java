package com.stx.xhb.dmgameapp.data.entity;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/3/1
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 评论实体类
 */

public class CommentsBean {

    private String comment_id;
    private String content;
    private long create_time;
    private boolean elite;
    private int floor_count;
    private String from;
    private boolean hide;
    private boolean hide_floor;
    private boolean highlight;
    private String ip;
    private String ip_location;
    private String metadata;
    private int oppose_count;
    private PassportBean passport;
    private boolean quick;
    private int reply_count;
    private String reply_id;
    private ReplyPassportBean reply_passport;
    private int score;
    private int status;
    private int support_count;
    private boolean top;
    private String user_id;
    private List<CommentsBean> comments;

    public String getComment_id() {
        return comment_id;
    }

    public String getContent() {
        return content;
    }

    public long getCreate_time() {
        return create_time;
    }

    public boolean isElite() {
        return elite;
    }

    public int getFloor_count() {
        return floor_count;
    }

    public String getFrom() {
        return from;
    }

    public boolean isHide() {
        return hide;
    }

    public boolean isHide_floor() {
        return hide_floor;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public String getIp() {
        return ip;
    }

    public String getIp_location() {
        return ip_location;
    }

    public String getMetadata() {
        return metadata;
    }

    public int getOppose_count() {
        return oppose_count;
    }

    public PassportBean getPassport() {
        return passport;
    }

    public boolean isQuick() {
        return quick;
    }

    public int getReply_count() {
        return reply_count;
    }

    public String getReply_id() {
        return reply_id;
    }

    public ReplyPassportBean getReply_passport() {
        return reply_passport;
    }

    public int getSupport_count() {
        return support_count;
    }

    public boolean isTop() {
        return top;
    }

    public String getUser_id() {
        return user_id;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }
}
