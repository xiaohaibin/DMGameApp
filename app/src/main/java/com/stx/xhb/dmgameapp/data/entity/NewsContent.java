package com.stx.xhb.dmgameapp.data.entity;

import com.stx.core.utils.StringUtils;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/5
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class NewsContent {

    /**
     * pagesize : 10
     * page : 1
     * time : 1535076178701
     * sign : d2fa5047f53cccd99ade57edeaf10ca5
     */

    private int pagesize = 10;
    private int page = 1;
    private long time;
    private String sign = "";

    public NewsContent(int currentPage) {
        this.time = System.currentTimeMillis();
        this.sign = StringUtils.getMD5(String.valueOf(pagesize) + currentPage + time);
        this.page = currentPage;
    }
}
