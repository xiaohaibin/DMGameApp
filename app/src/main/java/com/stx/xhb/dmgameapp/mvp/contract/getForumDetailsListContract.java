package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.entity.ForumListEntity;
import com.stx.xhb.dmgameapp.mvp.presenter.getForumDetailsListPresenter;

import java.util.List;

/**
 * Author : jxnk25
 * Time: 2017/11/1 0001
 * Email:xhb_199409@163.com
 * Email:xhb_199409@163.com
 * Github:https://github.com/xiaohaibin/
 * Drscribe:
 */
public interface getForumDetailsListContract {

    interface getForumListModel extends IModel {
        void getForumListData(String fid, int page);
    }

    interface getForumListDataView extends IView {

        void getForumListDataSuccess(List<ForumListEntity.HtmlBean> data);

        void getForumListDataFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
