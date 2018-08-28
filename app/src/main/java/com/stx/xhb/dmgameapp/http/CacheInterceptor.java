package com.stx.xhb.dmgameapp.http;

import android.content.Context;
import android.text.TextUtils;

import com.stx.core.utils.NetUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author：xiaohaibin
 * @Time：2017/6/20
 * @Emil：xhb_199409@163.com
 * @Github：https://github.com/xiaohaibin/
 * @Describe：缓存拦截器
 */
public class CacheInterceptor implements Interceptor {

    private Context context;

    public CacheInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder();
        Request request = requestBuilder.build();
        Response response = chain.proceed(request);
        if (NetUtils.isNetworkAvailable(context)) {
            // 有网络时 设置缓存超时时间0个小时
            int maxAge = 0;
            // 如果单个请求不同请在请求中写上Cache-control头则按照对应的配置进行本地缓存时间配置
            String cacheControl = request.cacheControl().toString();
            if (TextUtils.isEmpty(cacheControl)) {
                return response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        //清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Pragma")
                        .build();
            } else {
                return response.newBuilder()
                        .header("Cache-Control", cacheControl)
                        //清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Pragma")
                        .build();
            }
        } else {
            // 无网络时，设置超时为1周
            int maxStale = 60 * 60 * 24 * 7;
            return response.newBuilder()
                    //清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }
}
