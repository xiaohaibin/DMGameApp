package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.entity.GameVideoBean;

/**
 * Author: Mr.xiao on 2017/9/18
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public interface GetGameVideoContract {

    interface getGameVideoModel extends IModel {
        void getVideoList(String id, String key, String type, int page);
    }


    interface getVideoListView extends IView {

        void getVideoListSuccess(GameVideoBean videoListEntity);

        void getVideoListFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
