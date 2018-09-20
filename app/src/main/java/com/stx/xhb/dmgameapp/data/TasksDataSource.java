package com.stx.xhb.dmgameapp.data;

import com.stx.xhb.dmgameapp.data.callback.LoadTaskCallback;
import com.stx.xhb.dmgameapp.data.entity.GameListBean;
import com.stx.xhb.dmgameapp.data.entity.GameRankBean;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.data.entity.SaleGameBean;

import rx.Subscription;

/**
 * @author: xiaohaibin.
 * @time: 2018/8/31
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 数据层抽象接口，数据来源大体上分为三层：缓存，DB，网络
 */
public interface TasksDataSource {


    /**
     * 释放资源
     */
    void release();

    /**
     * 加载热点新闻
     */
    Subscription getHowNews(int currentPage, LoadTaskCallback<NewsPageBean> callback);

    /**
     * 加载新闻
     */
    Subscription getNews(int currentPage,  LoadTaskCallback<NewsPageBean> callback);

    /**
     * 原创
     */
    Subscription getOriginalPage(int currentPage,  LoadTaskCallback<NewsPageBean> callback);

    /**
     * 原创
     */
    Subscription getVideoPage(int currentPage,  LoadTaskCallback<NewsPageBean> callback);

    /**
     * 原创
     */
    Subscription getAmusePage(int currentPage,  LoadTaskCallback<NewsPageBean> callback);


    /**
     * 热门游戏
     */
    Subscription getHotGame(LoadTaskCallback<GameListBean> callback);

    /**
     * 已售游戏
     */
    Subscription getSaleGame(int currentPage,  LoadTaskCallback<SaleGameBean> callback);

    /**
     * 未发售游戏
     */
    Subscription getUnSaleGame(int currentPage,  LoadTaskCallback<SaleGameBean> callback);

    /**
     * 汉化游戏
     */
    Subscription getChinesizeGame(int currentPage,  int order,LoadTaskCallback<SaleGameBean> callback);

    /**
     * 汉化游戏
     */
    Subscription getRankGame(int currentPage,  String uid,LoadTaskCallback<GameRankBean> callback);


}
