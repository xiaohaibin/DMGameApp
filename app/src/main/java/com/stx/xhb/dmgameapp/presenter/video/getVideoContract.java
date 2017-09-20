package com.stx.xhb.dmgameapp.presenter.video;

import com.stx.core.model.annotation.Implement;
import com.stx.core.mvp.BaseView;
import com.stx.xhb.dmgameapp.entity.VideoListEntity;

/**
 * Author: Mr.xiao on 2017/9/18
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
@Implement(getVideoListImpl.class)
public interface getVideoContract {

    void getVideoList(int page);

    interface getVideoListView extends BaseView {

        void getVideoListSuccess(VideoListEntity videoListEntity);

        void getVideoListFailed(String msg);
    }
}
