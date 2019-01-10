package com.stx.xhb.dmgameapp.data.body;

import com.stx.core.utils.StringUtils;

/**
 * ReplyCommentContent
 */
public class ReplyCommentContent {

    private String uid;
    private int id;
    private String arcurl;
    private String content;
    private long time;
    private String sign;

    public ReplyCommentContent(String uid, int id, String arcurl, String content) {
        this.uid = uid;
        this.id = id;
        this.arcurl = arcurl;
        this.content = content;
        this.time = System.currentTimeMillis();
        this.sign = StringUtils.getMD5(uid + id + time);
    }
}
