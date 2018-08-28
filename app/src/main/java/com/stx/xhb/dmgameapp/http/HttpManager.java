package com.stx.xhb.dmgameapp.http;

import com.stx.core.log.Logger;
import com.stx.xhb.dmgameapp.DmgApplication;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.http.https.HttpsUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: Mr.xiao on 2017/3/15
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: HTTP请求工厂类
 */
public class HttpManager {

    private volatile static HttpManager instance;
    private static boolean isDebug = false;

    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    public void debug(boolean isDebug) {
        HttpManager.isDebug = isDebug;
    }


    /**
     * 无缓存模式
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.IP)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);

    }

    /**
     * 有缓存模式
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public <S> S createServiceWithCache(Class<S> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.IP)
                .client(getOkHttpClientWithCache())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);

    }


    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            //打印retrofit日志
            Logger.i(message);
        }
    });

    private static final long DEFAULT_TIMEOUT = 5;

    private OkHttpClient getOkHttpClient() {
        final long currentTimeMillis = System.currentTimeMillis();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        //定制OkHttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //支持https
        builder.sslSocketFactory(sslParams.sSLSocketFactory);
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(loggingInterceptor);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder();
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return builder.build();
    }

    /**
     * 带缓存模式
     *
     * @return
     */
    private OkHttpClient getOkHttpClientWithCache() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        //定制OkHttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //支持https
        builder.sslSocketFactory(sslParams.sSLSocketFactory);
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        //debug模式开启日志输出
        loggingInterceptor.setLevel(isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        builder.addInterceptor(loggingInterceptor);

        //设置缓存
        File httpCacheDirectory = new File(DmgApplication.getInstance().getApplicationContext().getCacheDir(), "dmgAppcache");
        builder.cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024));
        builder.addNetworkInterceptor(new CacheInterceptor(DmgApplication.getInstance().getApplicationContext()));
        builder.addInterceptor(new NormalInterceptor(DmgApplication.getInstance().getApplicationContext()));

        return builder.build();
    }


}
