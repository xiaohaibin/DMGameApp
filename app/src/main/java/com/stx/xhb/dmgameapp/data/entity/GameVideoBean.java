package com.stx.xhb.dmgameapp.data.entity;

import com.stx.xhb.dmgameapp.base.BaseEntity;

import java.util.List;

/**
 * Author : jxnk25
 * Time: 2017/10/20 0020
 * Email:xhb_199409@163.com
 * Email:xhb_199409@163.com
 * Github:https://github.com/xiaohaibin/
 * Drscribe:
 */

public class GameVideoBean extends BaseEntity{

    private int totalrow;
    private List<VideoListBean.VideoBean> html;

    public int getTotalrow() {
        return totalrow;
    }

    public List<VideoListBean.VideoBean> getHtml() {
        return html;
    }
}
