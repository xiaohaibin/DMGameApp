package com.stx.xhb.dmgameapp.presenter.video;

import com.stx.core.model.annotation.Implement;
import com.stx.core.mvp.BaseView;
import com.stx.xhb.dmgameapp.entity.GameVideoEntity;

/**
 * Author: Mr.xiao on 2017/9/18
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
@Implement(getGameVideoListImpl.class)
public interface getGameVideoContract {

    void getVideoList(String id,String key,String type,int page);

    interface getVideoListView extends BaseView {

        void getVideoListSuccess(GameVideoEntity videoListEntity);

        void getVideoListFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
