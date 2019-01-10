package com.stx.xhb.dmgameapp.data.body;

import com.stx.core.utils.StringUtils;

/**
 * PostCommentContent
 */
public class PostCommentContent {


    private String uid;
    private String arcurl;
    private int c_sid = 0;
    private String content;
    private long time;
    private String sign;

    public PostCommentContent(String uid, String arcurl, String content) {
        this.uid = uid;
        this.arcurl = arcurl;
        this.content = content;
        this.time = System.currentTimeMillis();
        this.sign = StringUtils.getMD5(uid + arcurl + c_sid + time);
    }
}
