package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.data.entity.SaleGameBean;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/20
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 汉化
 */
public interface GetChinesizeListContract {

    interface Model extends IModel {
        void getChinesizeGame(int currentPage, int order);
    }

    interface View extends IView {
        void getNewGame(SaleGameBean saleGameBean);

        void getHotGame(SaleGameBean saleGameBean);

        void getFailed(String msg);

        void showLoading();

        void hideLoading();
    }


}
