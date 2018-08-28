package com.stx.xhb.dmgameapp.entity;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/3/1
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 评论列表实体类
 */

public class CommentListBean {

    private int cmt_sum;
    private int mode;
    private int outer_cmt_sum;
    private int participation_sum;
    private String topic_id;
    private int total_page_no;
    private List<CommentsBean> comments;

    public int getCmt_sum() {
        return cmt_sum;
    }

    public int getMode() {
        return mode;
    }

    public int getOuter_cmt_sum() {
        return outer_cmt_sum;
    }

    public int getParticipation_sum() {
        return participation_sum;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public int getTotal_page_no() {
        return total_page_no;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }
}
