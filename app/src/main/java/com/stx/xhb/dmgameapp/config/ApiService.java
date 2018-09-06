package com.stx.xhb.dmgameapp.config;

import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.http.HttpResult;

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

    @POST("hotnewspage")
    Observable<HttpResult<NewsPageBean>> getHotNews();


}
