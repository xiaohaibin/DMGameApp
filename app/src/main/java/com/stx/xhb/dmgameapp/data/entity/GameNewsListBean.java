package com.stx.xhb.dmgameapp.data.entity;

import com.stx.xhb.dmgameapp.base.BaseEntity;

import java.util.List;

/**
 * Author : jxnk25
 * Time: 2017/10/24 0024
 * Email:xhb_199409@163.com
 * Email:xhb_199409@163.com
 * Github:https://github.com/xiaohaibin/
 * Drscribe:
 */

public class GameNewsListBean extends BaseEntity{

    private String totalrow;
    private List<NewsListBean.ChannelEntity.HtmlEntity> html;

    public String getTotalrow() {
        return totalrow;
    }

    public List<NewsListBean.ChannelEntity.HtmlEntity> getHtml() {
        return html;
    }

}
