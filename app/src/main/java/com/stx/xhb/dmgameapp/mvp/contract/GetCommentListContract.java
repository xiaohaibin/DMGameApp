package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.data.entity.CommentListBean;

/**
 * @author: xiaohaibin.
 * @time: 2018/2/11
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public interface GetCommentListContract {

        interface Model extends IModel{
                void getCommentListData(int currentPage, String arcurl, int uid);
        }

        interface View extends IView{

                void setCommentListData(CommentListBean commentListData);

                void getCommentListDataFailed();

                void showLoading();

                void hideLoading();
        }
}
