package com.stx.xhb.dmgameapp.mvp.presenter;

import com.stx.core.mvp.BasePresenter;
import com.stx.xhb.dmgameapp.data.callback.LoadTaskCallback;
import com.stx.xhb.dmgameapp.data.entity.SaleGameBean;
import com.stx.xhb.dmgameapp.data.remote.TasksRepositoryProxy;
import com.stx.xhb.dmgameapp.mvp.contract.GetSaleListContract;

import rx.Subscription;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/14
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class GetSaleListPresenter extends BasePresenter<GetSaleListContract.View> implements GetSaleListContract.Model {

    @Override
    public void getSaleList(int currentPage) {
        if (getView() == null) {
            return;
        }
        Subscription saleGame = TasksRepositoryProxy.getInstance().getSaleGame(currentPage, new LoadTaskCallback<SaleGameBean>() {
            @Override
            public void onTaskLoaded(SaleGameBean data) {
                getView().getSaleList(data);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().getFailed(msg);
            }
        });

        addSubscription(saleGame);
    }

    @Override
    public void getUnSaleList(int currentPage) {
        TasksRepositoryProxy.getInstance().getUnSaleGame(currentPage, new LoadTaskCallback<SaleGameBean>() {
            @Override
            public void onTaskLoaded(SaleGameBean data) {
                getView().getUnSaleList(data);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().getFailed(msg);
            }
        });
    }
}
