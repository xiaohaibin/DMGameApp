package com.stx.xhb.dmgameapp.data;

import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.tencent.connect.UserInfo;

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

    interface LoadTaskCallback<T> {
        void onTaskLoaded(T data);

        void onDataNotAvailable(String msg);
    }

    /**
     * 释放资源
     */
    void release();

    /**
     * 加载热点新闻
     * @param currentPage
     */
    Subscription getHowNews(int currentPage, final LoadTaskCallback<NewsPageBean> callback);

}
