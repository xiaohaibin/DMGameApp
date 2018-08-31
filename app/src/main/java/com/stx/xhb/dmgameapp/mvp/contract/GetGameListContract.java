package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.data.entity.GameListBean;

import java.util.List;

/**
 * Author：xiaohaibin
 * Time：2017/9/20
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
public interface GetGameListContract {

    interface getGameListModel extends IModel {
        void getGameListData(String appId, int page);
    }


    interface getGameListDataView extends IView {

        void getGameListDataSuccess(List<GameListBean.HtmlEntity> listEntity);

        void getGameListDataFailed(String msg);

        void showLoading();

        void hideLoading();

    }
}
