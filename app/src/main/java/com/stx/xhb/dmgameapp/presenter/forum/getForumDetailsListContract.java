package com.stx.xhb.dmgameapp.presenter.forum;

import com.stx.core.model.annotation.Implement;
import com.stx.core.mvp.BaseView;
import com.stx.xhb.dmgameapp.entity.ForumListEntity;

import java.util.List;

/**
 * Author : jxnk25
 * Time: 2017/11/1 0001
 * Email:xhb_199409@163.com
 * Email:xhb_199409@163.com
 * Github:https://github.com/xiaohaibin/
 * Drscribe:
 */
@Implement(getForumDetailsListImpl.class)
public interface getForumDetailsListContract {

    void getForumListData(String fid,int page);

    interface getForumListDataView extends BaseView {

        void getForumListDataSuccess(List<ForumListEntity.HtmlBean> data);

        void getForumListDataFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
