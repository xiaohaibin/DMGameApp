package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.data.entity.CommentListBean;
import com.stx.xhb.dmgameapp.data.entity.VideoListBean;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/1/31
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public interface GetVideoDetailsContract {

    interface View extends IView {

        void setVideoDetailsData(List<VideoListBean.VideoBean> listEntity);

        void setCommentListData(CommentListBean commentListData);

        void getVideoDetailsDataFailed();

        void getCommentListDataFailed();

        void showLoading();

        void hideLoading();
    }

    interface Model extends IModel {

        void getVideoDetailsData(String id, String key);

        void getCommentListData(String id);

    }
}
