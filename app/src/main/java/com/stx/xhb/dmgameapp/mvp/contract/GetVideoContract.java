package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.entity.VideoListEntity;

/**
 * Author: Mr.xiao on 2017/9/18
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public interface GetVideoContract {

    interface getVideoModel extends IModel {
        void getVideoList(int page);
    }

    interface getVideoListView extends IView {

        void getVideoListSuccess(VideoListEntity videoListEntity);

        void getVideoListFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
