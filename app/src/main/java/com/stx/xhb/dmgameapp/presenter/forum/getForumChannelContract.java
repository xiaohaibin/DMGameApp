package com.stx.xhb.dmgameapp.presenter.forum;

import com.stx.core.model.annotation.Implement;
import com.stx.core.mvp.BaseView;
import com.stx.xhb.dmgameapp.entity.ForumChannelListEntity;

import java.util.List;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
@Implement(getForumChannelImpl.class)
public interface getForumChannelContract {

    void getChannelList();

    interface getChannelListView extends BaseView {

        void getChannelSuccess(List<ForumChannelListEntity.HtmlEntity> channelList);

        void getChanelFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
