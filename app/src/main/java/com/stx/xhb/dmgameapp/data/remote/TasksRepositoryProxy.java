package com.stx.xhb.dmgameapp.data.remote;

import com.stx.xhb.dmgameapp.config.ApiService;
import com.stx.xhb.dmgameapp.data.callback.LoadTaskCallback;
import com.stx.xhb.dmgameapp.data.entity.GameContent;
import com.stx.xhb.dmgameapp.data.entity.GameListBean;
import com.stx.xhb.dmgameapp.data.entity.SaleGameBean;
import com.stx.xhb.dmgameapp.http.HttpResultSubscriber;
import com.stx.xhb.dmgameapp.utils.RequestBodyHelper;
import com.stx.xhb.dmgameapp.data.TasksDataSource;
import com.stx.xhb.dmgameapp.data.entity.NewsContent;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.http.HttpManager;
import com.stx.xhb.dmgameapp.http.HttpResult;
import com.stx.xhb.dmgameapp.http.TransformUtils;

import okhttp3.RequestBody;
import rx.Subscription;

/**
 * Author: Mr.xiao on 2018/9/5
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 数据仓库代理类，用于封装从不同来源处获取数据
 */
public class TasksRepositoryProxy implements TasksDataSource {

    private static TasksRepositoryProxy INSTANCE = null;

    private TasksRepositoryProxy() {
    }

    public static TasksRepositoryProxy getInstance() {
        if (INSTANCE == null) {
            synchronized (TasksRepositoryProxy.class) {
                if (INSTANCE == null) {
                    return new TasksRepositoryProxy();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void release() {
        INSTANCE = null;
    }

    @Override
    public Subscription getHowNews(int currentPage, final LoadTaskCallback<NewsPageBean> callback) {
        return HttpManager.getInstance().createService(ApiService.class)
                .getHotNews(RequestBodyHelper.creatRequestBody(new NewsContent(currentPage)))
                .compose(TransformUtils.<HttpResult<NewsPageBean>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<NewsPageBean>() {
                    @Override
                    public void onStart() {
                        callback.onStart();
                    }

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
                        callback.onCompleted();
                    }
                });
    }

    @Override
    public Subscription getNews(int currentPage, final LoadTaskCallback<NewsPageBean> callback) {
        return HttpManager.getInstance().createService(ApiService.class)
                .getNews(RequestBodyHelper.creatRequestBody(new NewsContent(currentPage)))
                .compose(TransformUtils.<HttpResult<NewsPageBean>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<NewsPageBean>() {
                    @Override
                    public void onStart() {
                        callback.onStart();
                    }

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
                        callback.onCompleted();
                    }
                });
    }

    @Override
    public Subscription getOriginalPage(int currentPage, final LoadTaskCallback<NewsPageBean> callback) {
        return HttpManager.getInstance().createService(ApiService.class)
                .getOriginalPage(RequestBodyHelper.creatRequestBody(new NewsContent(currentPage)))
                .compose(TransformUtils.<HttpResult<NewsPageBean>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<NewsPageBean>() {

                    @Override
                    public void onStart() {
                        callback.onStart();
                    }

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
                        callback.onCompleted();
                    }
                });
    }

    @Override
    public Subscription getVideoPage(int currentPage, final LoadTaskCallback<NewsPageBean> callback) {
        return HttpManager.getInstance().createService(ApiService.class)
                .getVideoPage(RequestBodyHelper.creatRequestBody(new NewsContent(currentPage)))
                .compose(TransformUtils.<HttpResult<NewsPageBean>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<NewsPageBean>() {

                    @Override
                    public void onStart() {
                        callback.onStart();
                    }

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
                        callback.onCompleted();
                    }
                });
    }

    @Override
    public Subscription getAmusePage(int currentPage, final LoadTaskCallback<NewsPageBean> callback) {
        return HttpManager.getInstance().createService(ApiService.class)
                .getAmusePage(RequestBodyHelper.creatRequestBody(new NewsContent(currentPage)))
                .compose(TransformUtils.<HttpResult<NewsPageBean>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<NewsPageBean>() {
                    @Override
                    public void onStart() {
                        callback.onStart();
                    }

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
                        callback.onCompleted();
                    }
                });
    }

    @Override
    public Subscription getHotGame(final LoadTaskCallback<GameListBean> callback) {
        return HttpManager.getInstance().createService(ApiService.class)
                .getHotGame(RequestBodyHelper.creatRequestBody(new GameContent()))
                .compose(TransformUtils.<HttpResult<GameListBean>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<GameListBean>() {
                    @Override
                    public void onStart() {
                        callback.onStart();
                    }

                    @Override
                    public void onSuccess(GameListBean gameListBean) {
                        callback.onTaskLoaded(gameListBean);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        callback.onDataNotAvailable(msg);
                    }

                    @Override
                    public void onFinished() {
                        callback.onCompleted();
                    }
                });
    }

    @Override
    public Subscription getSaleGame(int currentPage, final LoadTaskCallback<SaleGameBean> callback) {
        return HttpManager.getInstance().createService(ApiService.class)
                .getGameSale(RequestBodyHelper.creatRequestBody(new NewsContent(currentPage)))
                .compose(TransformUtils.<HttpResult<SaleGameBean>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<SaleGameBean>() {
                    @Override
                    public void onStart() {
                        callback.onStart();
                    }

                    @Override
                    public void onSuccess(SaleGameBean saleGameBean) {
                        callback.onTaskLoaded(saleGameBean);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        callback.onDataNotAvailable(msg);
                    }

                    @Override
                    public void onFinished() {
                        callback.onCompleted();
                    }
                });
    }

    @Override
    public Subscription getUnSaleGame(int currentPage, final LoadTaskCallback<SaleGameBean> callback) {
        return HttpManager.getInstance().createService(ApiService.class)
                .getGameUnSale(RequestBodyHelper.creatRequestBody(new NewsContent(currentPage)))
                .compose(TransformUtils.<HttpResult<SaleGameBean>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<SaleGameBean>() {
                    @Override
                    public void onStart() {
                        callback.onStart();
                    }

                    @Override
                    public void onSuccess(SaleGameBean saleGameBean) {
                        callback.onTaskLoaded(saleGameBean);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        callback.onDataNotAvailable(msg);
                    }

                    @Override
                    public void onFinished() {
                        callback.onCompleted();
                    }
                });
    }
}
