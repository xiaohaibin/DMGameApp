package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
public interface GetNewsListContract {

    interface getNewsListModel extends IModel {

        void getHotNewsList(int currentPage);

        void getNewsList(int currentPage);

        void getOriginalPage(int currentPage);

        void getAmusePage(int currentPage);
    }

    interface getNewListView extends IView {

        void getNewListSuccess(NewsPageBean data);

        void getNewListFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
