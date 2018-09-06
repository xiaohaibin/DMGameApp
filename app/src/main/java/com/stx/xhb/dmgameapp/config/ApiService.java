package com.stx.xhb.dmgameapp.config;

import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.http.HttpResult;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/6
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: api接口
 */
public interface ApiService {


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("hotnewspage")
    Observable<HttpResult<NewsPageBean>> getHotNews(@Body RequestBody body);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("newspage")
    Observable<HttpResult<NewsPageBean>> getNews(@Body RequestBody body);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("originalpage")
    Observable<HttpResult<NewsPageBean>> getOriginalPage(@Body RequestBody body);


}
