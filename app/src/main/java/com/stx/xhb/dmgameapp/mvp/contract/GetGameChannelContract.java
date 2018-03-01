package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.entity.GameChannelListBean;

import java.util.List;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
public interface GetGameChannelContract {

    interface getGameChannelModel extends IModel{
        void getChannelList();
    }

    interface getChannelListView extends IView {

        void getChannelSuccess(List<GameChannelListBean.HtmlEntity> channelList);

        void getChanelFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
