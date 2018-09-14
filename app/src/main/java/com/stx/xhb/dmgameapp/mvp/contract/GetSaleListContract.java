package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.data.entity.SaleGameBean;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/14
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 获取发售游戏
 */
public interface GetSaleListContract {

    interface Model extends IModel {

        void getSaleList(int currentPage);

        void getUnSaleList(int currentPage);

    }

    interface View extends IView {

        void getSaleList(SaleGameBean saleGameBean);

        void getUnSaleList(SaleGameBean saleGameBean);

        void getFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
