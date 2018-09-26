package com.stx.xhb.dmgameapp.data.body;

import com.stx.core.utils.StringUtils;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/26
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 获取热门评论
 */
public class GetHotCommentContent {

    private int uid=0;
    private String arcurl="";
    private int c_sid=0;
    private int pagesize=10;
    private int page=1;
    private long time;
    private String sign;

    public GetHotCommentContent(int currentPage,String arcurl,int uid) {
        this.time = System.currentTimeMillis();
        this.arcurl=arcurl;
        this.uid=uid;
        this.sign = StringUtils.getMD5(uid+arcurl+c_sid+String.valueOf(pagesize) + currentPage+time);
        this.page = currentPage;
    }

}
