package com.stx.xhb.dmgameapp.entity;

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

public class GameVideoEntity extends BaseEntity{

    /**
     * code : 1
     * html : [{"typeid":"211","id":"3686111","title":"《南方公园：完整破碎》新预告2","click":"411","feedback":"0","senddate":"2017-09-08","videopic":"http://aimg.3dmgame.com/uploads/video/201709/r_1504884888QrDg.jpg","videourl":"http://v.3dmgame.com/201709/3686111_app.html","changyan_id":"news_3686111","description":"","lmfl":"游戏库"},{"typeid":"211","id":"3686110","title":"《南方公园：完整破碎》新预告1","click":"233","feedback":"0","senddate":"2017-09-08","videopic":"http://aimg.3dmgame.com/uploads/video/201709/r_15048847912rft.jpg","videourl":"http://v.3dmgame.com/201709/3686110_app.html","changyan_id":"news_3686110","description":"","lmfl":"游戏库"},{"typeid":"211","id":"3682911","title":"《南方公园：完整破碎》科隆游戏展2017演示视频","click":"723","feedback":"0","senddate":"2017-08-27","videopic":"http://aimg.3dmgame.com/uploads/video/201708/r_1503763451uLsM.jpg","videourl":"http://v.3dmgame.com/201708/3682911_app.html","changyan_id":"news_3682911","description":"","lmfl":"游戏库"},{"typeid":"180","id":"3584254","title":"《南方公园：完整破碎》鼻部虚拟现实体验视频","click":"1041","feedback":"0","senddate":"2016-08-13","videopic":"http://aimg.3dmgame.com/uploads/video/201608/r_1471084987ZIJt.jpg","videourl":"http://v.3dmgame.com/201608/3584254_app.html","changyan_id":"news_3584254","description":"","lmfl":"游戏库"},{"typeid":"180","id":"3579407","title":"《南方公园：完整破碎》幕后介绍视频","click":"215","feedback":"1","senddate":"2016-07-24","videopic":"http://aimg.3dmgame.com/uploads/video/201607/r_1469341665IIrk.jpg","videourl":"http://v.3dmgame.com/201607/3579407_app.html","changyan_id":"news_3579407","description":"","lmfl":"游戏库"}]
     * totalrow : 5
     */

    private String totalrow;
    private List<VideoListEntity.VideoBean> html;

    public String getTotalrow() {
        return totalrow;
    }

    public List<VideoListEntity.VideoBean> getHtml() {
        return html;
    }
}
