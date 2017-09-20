package com.stx.xhb.dmgameapp.presenter.game;

import com.stx.core.model.annotation.Implement;
import com.stx.core.mvp.BaseView;
import com.stx.xhb.dmgameapp.entity.GameChannelListEntity;

import java.util.List;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
@Implement(getGameChannelImpl.class)
public interface getGameChannelContract {

    void getChannelList();

    interface getChannelListView extends BaseView {

        void getChannelSuccess(List<GameChannelListEntity.HtmlEntity> channelList);

        void getChanelFailed(String msg);
    }
}
