package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.entity.NewsChannelListBean;

import java.util.List;

/**
 * Author：xiaohaibin
 * Time：2017/9/17
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
public interface GetNewsChannelContract {


    interface getNewsChannelModel extends IModel {
        void getChannelList();
    }


    interface getChannelListView extends IView {

        void getChannelSuccess(List<NewsChannelListBean.HtmlEntity> channelList);

        void getChanelFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
