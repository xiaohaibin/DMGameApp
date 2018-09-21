package com.stx.xhb.dmgameapp.mvp.presenter;

import com.stx.core.mvp.BasePresenter;
import com.stx.xhb.dmgameapp.data.callback.LoadTaskCallback;
import com.stx.xhb.dmgameapp.data.entity.GameRankBean;
import com.stx.xhb.dmgameapp.data.remote.TasksRepositoryProxy;
import com.stx.xhb.dmgameapp.mvp.contract.GetRankGameContract;

import rx.Subscription;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/20
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class GetRankGamePresenter extends BasePresenter<GetRankGameContract.View> implements GetRankGameContract.Model{
    @Override
    public void getRankGame(int currentpage, String uid) {
        if (getView()==null){
            return;
        }
        Subscription subscription = TasksRepositoryProxy.getInstance().getRankGame(currentpage, uid, new LoadTaskCallback<GameRankBean>() {
            @Override
            public void onStart() {
                getView().showLoading();
            }

            @Override
            public void onTaskLoaded(GameRankBean data) {
                getView().getDataSuccess(data);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                getView().getFailed(msg);
            }

            @Override
            public void onCompleted() {
                getView().hideLoading();
            }
        });
        addSubscription(subscription);
    }
}
