package com.stx.xhb.dmgameapp.mvp.presenter;

import com.stx.core.mvp.BasePresenter;
import com.stx.xhb.dmgameapp.data.callback.LoadTaskCallback;
import com.stx.xhb.dmgameapp.data.entity.SaleGameBean;
import com.stx.xhb.dmgameapp.data.remote.TasksRepositoryProxy;
import com.stx.xhb.dmgameapp.mvp.contract.GetChinesizeListContract;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/20
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 汉化游戏
 */
public class GetChinesizeListPresenter extends BasePresenter<GetChinesizeListContract.View> implements GetChinesizeListContract.Model{
    @Override
    public void getChinesizeGame(int currentPage, final int order) {
        if (getView()==null){
            return;
        }
        TasksRepositoryProxy.getInstance().getChinesizeGame(currentPage, order,new LoadTaskCallback<SaleGameBean>() {
            @Override
            public void onTaskLoaded(SaleGameBean data) {
                if (order==1) {
                    getView().getNewGame(data);
                }else {
                    getView().getHotGame(data);
                }
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().getFailed(msg);
            }
        });
    }
}
