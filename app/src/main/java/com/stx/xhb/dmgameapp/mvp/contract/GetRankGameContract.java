package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.data.entity.GameRankBean;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/20
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public interface GetRankGameContract {

    interface Model extends IModel {
        void getRankGame(int currentpage,String uid);
    }

    interface View extends IView {
        void getDataSuccess(GameRankBean gameRankBean);

        void getFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
