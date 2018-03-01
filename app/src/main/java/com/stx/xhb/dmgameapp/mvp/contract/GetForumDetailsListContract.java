package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.entity.ForumListBean;

import java.util.List;

/**
 * Author : jxnk25
 * Time: 2017/11/1 0001
 * Email:xhb_199409@163.com
 * Email:xhb_199409@163.com
 * Github:https://github.com/xiaohaibin/
 * Drscribe:
 */
public interface GetForumDetailsListContract {

    interface getForumListModel extends IModel {
        void getForumListData(String fid, int page);
    }

    interface getForumListDataView extends IView {

        void getForumListDataSuccess(List<ForumListBean.HtmlBean> data);

        void getForumListDataFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
