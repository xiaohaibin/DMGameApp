package com.stx.xhb.dmgameapp.presenter.news;

import com.stx.core.model.annotation.Implement;
import com.stx.core.mvp.BaseView;
import com.stx.xhb.dmgameapp.entity.NewsChannelListEntity;

import java.util.List;

/**
 * Author：xiaohaibin
 * Time：2017/9/17
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
@Implement(NewsImpl.class)
public interface NewsContract {

    void getChannelList();

    interface getChannelListView extends BaseView {

        void getChannelSuccess(List<NewsChannelListEntity.HtmlEntity> channelList);

        void getChanelFailed(String msg);
    }
}
