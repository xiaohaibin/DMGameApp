package com.stx.xhb.dmgameapp.presenter.forum;

import com.stx.core.model.annotation.Implement;
import com.stx.core.mvp.BaseView;
import com.stx.xhb.dmgameapp.entity.ForumEntity;

import java.util.List;

/**
 * Author：xiaohaibin
 * Time：2017/9/21
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
@Implement(getForumListImpl.class)
public interface getForumListContract {

    void getForumListData(String fid);

    interface getForumListView extends BaseView {

        void getForumListDataSuccess(List<ForumEntity> listData);

        void getForumListDataFailed(String msg);
    }
}
