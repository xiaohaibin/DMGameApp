package com.stx.xhb.dmgameapp.presenter.news;

import com.stx.core.model.annotation.Implement;
import com.stx.core.mvp.BaseView;
import com.stx.xhb.dmgameapp.entity.NewsListEntity;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
@Implement(getNewsListImpl.class)
public interface getNewsListContract {

    void getNewsList(String appId, int page);

    interface getNewListView extends BaseView {
        void getNewListSuccess(NewsListEntity listEntity);

        void getNewListFailed(String msg);
    }
}
