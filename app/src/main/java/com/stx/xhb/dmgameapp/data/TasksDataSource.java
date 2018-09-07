package com.stx.xhb.dmgameapp.data;

import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.http.HttpResult;

import rx.Subscriber;
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
    Subscription getHowNews(int currentPage, final Subscriber<? super HttpResult<NewsPageBean>> subscriber);

    /**
     * 加载新闻
     */
    Subscription getNews(int currentPage, final Subscriber<? super HttpResult<NewsPageBean>> subscriber);

    /**
     * 原创
     */
    Subscription getOriginalPage(int currentPage, final Subscriber<? super HttpResult<NewsPageBean>> subscriber);

    /**
     * 原创
     */
    Subscription getVideoPage(int currentPage, final Subscriber<? super HttpResult<NewsPageBean>> subscriber);

    /**
     * 原创
     */
    Subscription getAmusePage(int currentPage, final Subscriber<? super HttpResult<NewsPageBean>> subscriber);


}
