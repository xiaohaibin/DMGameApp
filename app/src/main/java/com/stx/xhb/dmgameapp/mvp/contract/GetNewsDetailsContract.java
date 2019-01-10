package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.data.entity.CommentListBean;
import com.stx.xhb.dmgameapp.data.entity.NewsAboutBean;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;

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

        void setNewsDetailsData(NewsAboutBean newsAboutBean);

        void setCommentListData(CommentListBean commentListData);

        void getNewsDetailsDataFailed(String msg);

        void getCommentListDataFailed(String msg);

        void postCommentSuccess();

        void postCommentFailed(String msg);

        void showLoading();

        void hideLoading();
    }

    interface Model extends IModel {

        void getNewsDetailsData(String url);

        void getCommentListData(int currentPage, String arcurl, int uid);

        void postComment(String arcurl,String comment, int uid);
    }
}
