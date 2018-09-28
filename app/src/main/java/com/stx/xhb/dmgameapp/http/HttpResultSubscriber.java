package com.stx.xhb.dmgameapp.http;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.stx.core.log.Logger;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * @author: Mr.xiao on 2017/3/15
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 网络请求结果订阅
 */
public abstract class HttpResultSubscriber<T> extends Subscriber<HttpResult<T>> {

    /**
     * 网络错误
     */
    public static final int NETWORK_ERROR = 1002;

    /**
     * 证书出错
     */
    public static final int SSL_ERROR = 1005;

    @Override
    public void onNext(HttpResult<T> t) {
        if (isUnsubscribed()) {
            return;
        }
        if (t.isHttpSuccess()) {
            T data = t.getData();
            if (data != null) {
                onSuccess(data);
            } else {
                onSuccess((T) t.getMsg());
            }
        } else {
            throw new ApiException(t.getCode(), t.getMsg());
        }
    }

    @Override
    public void onCompleted() {
        if (!isUnsubscribed()) {
            onFinished();
        }
    }

    @Override
    public void onError(Throwable e) {
        Logger.e("==error==", e.getMessage() + "===");
        if (isUnsubscribed()) {
            return;
        }
        //在这里做全局的错误处理
        if (e instanceof HttpException ||
                e instanceof ConnectException ||
                e instanceof SocketTimeoutException ||
                e instanceof TimeoutException ||
                e instanceof UnknownHostException) {
            //网络错误
            onError("服务器连接失败，请稍后再试", NETWORK_ERROR);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //数据解析错误
            onError("数据解析错误", ApiException.PARSE_ERROR);
        } else if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            //自定义的ApiException
            onError(apiException.getErrorMsg(), apiException.getErrCode());
        } else if (e instanceof javax.net.ssl.SSLException) {
            onError("证书验证失败", SSL_ERROR);
        } else {
            onError("未知错误", ApiException.UNKNOWN);
        }
        onFinished();
    }

    public abstract void onSuccess(T t);

    public abstract void onError(String msg, int code);

    /**
     * 成功或失败到最后都会调用
     */
    public abstract void onFinished();

}
