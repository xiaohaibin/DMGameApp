package com.stx.xhb.dmgameapp.data.remote;

import android.support.annotation.NonNull;

import com.stx.xhb.dmgameapp.config.ApiService;
import com.stx.xhb.dmgameapp.data.TasksDataSource;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.http.HttpManager;
import com.stx.xhb.dmgameapp.http.HttpResult;
import com.stx.xhb.dmgameapp.http.HttpResultSubscriber;
import com.stx.xhb.dmgameapp.http.TransformUtils;

import rx.Subscription;

/**
 * Author: Mr.xiao on 2018/9/5
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 数据仓库代理类，用于封装从不同来源处获取数据
 */
public class TasksRepositoryProxy implements TasksDataSource {

    private static TasksRepositoryProxy INSTANCE = null;
    private final TasksDataSource mRemoteDataSource;

    private TasksRepositoryProxy(@NonNull TasksDataSource remote) {
        this.mRemoteDataSource = remote;
    }

    public static TasksRepositoryProxy getInstance(@NonNull TasksDataSource remote) {
        if (INSTANCE == null) {
            synchronized (TasksRepositoryProxy.class) {
                if (INSTANCE == null) {
                    return new TasksRepositoryProxy(remote);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void release() {
        mRemoteDataSource.release();
        destroyInstance();
    }

    @Override
    public Subscription getHowNews(int currentPage, final LoadTaskCallback<NewsPageBean> callback) {
        return HttpManager.getInstance().createService(ApiService.class)
                .getHotNews()
                .compose(TransformUtils.<HttpResult<NewsPageBean>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<NewsPageBean>() {

                    @Override
                    public void onSuccess(NewsPageBean newsPageBean) {
                        callback.onTaskLoaded(newsPageBean);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        callback.onDataNotAvailable(msg);
                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }
}
