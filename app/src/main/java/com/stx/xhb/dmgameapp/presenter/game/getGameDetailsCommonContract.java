package com.stx.xhb.dmgameapp.presenter.game;

import com.stx.core.model.annotation.Implement;
import com.stx.core.mvp.BaseView;
import com.stx.xhb.dmgameapp.entity.NewsListEntity;

import java.util.List;

/**
 * Author : jxnk25
 * Time: 2017/10/19 0019
 * Email:xhb_199409@163.com
 * Github:https://github.com/xiaohaibin/
 * Drscribe:
 */
@Implement(getGameDetailsCommonImpl.class)
public interface getGameDetailsCommonContract {

    void getGameNewsListData(String type, String id, String key, int page);

    void getGameToolsListData(String type, String id, String key, int page);

    interface getGameDetailsDataView extends BaseView{

        void getGameNewsListDataSuccess(List<NewsListEntity.ChannelEntity.HtmlEntity> list);

        void getGameNewsListFailed(String msg);

        void getGameToolsListDataSuccess(List<NewsListEntity.ChannelEntity.HtmlEntity> list);

        void getGameToolsListFailed(String msg);

        void showLoading();

        void hideLoading();
    }

}
