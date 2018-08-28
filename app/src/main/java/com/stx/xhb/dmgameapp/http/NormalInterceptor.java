package com.stx.xhb.dmgameapp.http;

import android.content.Context;

import com.stx.core.utils.NetUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author：xiaohaibin
 * Time：2017/6/21
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xaohaibin/
 * Describe：设置无网读取本地缓存
 */
public class NormalInterceptor implements Interceptor {
    private Context context;

    public NormalInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetUtils.isNetworkAvailable(context)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        return chain.proceed(request);
    }
}
