package com.stx.xhb.dmgameapp.presenter.game;

import com.stx.core.model.annotation.Implement;
import com.stx.core.mvp.BaseView;
import com.stx.xhb.dmgameapp.entity.GameListEntity;

import java.util.List;

/**
 * Author：xiaohaibin
 * Time：2017/9/20
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
@Implement(getGameListImpl.class)
public interface getGameListContract {

    void getGameListData(String appId, int page);

    interface getGameListDataView extends BaseView {

        void getGameListDataSuccess(List<GameListEntity.HtmlEntity> listEntity);

        void getGameListDataFailed(String msg);

    }
}
