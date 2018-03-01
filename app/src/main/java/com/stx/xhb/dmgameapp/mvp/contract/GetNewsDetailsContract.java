package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.entity.CommentListBean;
import com.stx.xhb.dmgameapp.entity.NewsListBean;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/2/27
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 获取资讯详情协议类
 */

public interface GetNewsDetailsContract {

    interface View extends IView {

        void setNewsDetailsData(List<NewsListBean.ChannelEntity.HtmlEntity> listEntity);

        void setCommentListData(CommentListBean commentListData);

        void getNewsDetailsDataFailed();

        void getCommentListDataFailed();

        void showLoading();

        void hideLoading();
    }

    interface Model extends IModel {

        void getNewsDetailsData(String id, String key);

        void getCommentListData(String id);

    }
}
