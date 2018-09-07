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

    /**
     * 热点
     * 方式：POST
     * 参数：
     * {"pagesize":10,"page":1,"time":1535076178701,"sign":"d2fa5047f53cccd99ade57edeaf10ca5"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("hotnewspage")
    Observable<HttpResult<NewsPageBean>> getHotNews(@Body RequestBody body);

    /**
     * 新闻
     * 方式：POST
     * 参数：
     * {"pagesize":10,"page":1,"time":1535078255062,"sign":"9a19c5a6c3d10cc270ebabb17a98d3d2"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("newspage")
    Observable<HttpResult<NewsPageBean>> getNews(@Body RequestBody body);

    /**
     * 原创
     * 方式:POST
     * 参数：
     * {"pagesize":10,"page":1,"time":1535078302502,"sign":"e572532c81e762d22f5def0ed0009df6"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("originalpage")
    Observable<HttpResult<NewsPageBean>> getOriginalPage(@Body RequestBody body);

    /**
     * 视频
     * 方式：POST
     * 参数：
     * {"pagesize":10,"page":1,"time":1535078362384,"sign":"c6ca3dc80679dfcd9e1e1fd16a9cc7eb"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("videopage")
    Observable<HttpResult<NewsPageBean>> getVideoPage(@Body RequestBody body);


    /**
     * 娱乐
     * 方式：POST
     * 参数：
     * {"pagesize":10,"page":1,"time":1535078425411,"sign":"4fd861bc19dd96f6d44c166a96c8486d"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("amusepage")
    Observable<HttpResult<NewsPageBean>> getAmusePage(@Body RequestBody body);

    /**
     * 新闻相关内容
     * 方式：POST
     * 参数：
     * {"arcurl":"https:\/\/www.3dmgame.com\/news\/201808\/3743832.html","pagesize":3,"time":1535078536940,"sign":"36fa74931c8b019dfeb79e57ade25ce0"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("newsabout")
    Observable<HttpResult<NewsPageBean>> getNewsDetails(@Body RequestBody body);

    /**
     * 获取最新评论
     * 方式:post
     * 参数
     * {"uid":9749488,"arcurl":"https:\/\/www.3dmgame.com\/news\/201808\/3743832.html","c_sid":0,"pagesize":5,"page":1,"time":1535078536959,"sign":"ea2208d6606e5e88f5c395206d1b0640"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("gethotcomment")
    Observable<HttpResult<NewsPageBean>> getHotComment(@Body RequestBody body);

    /**
     * 热门游戏
     * 方式:post
     * 参数：
     * {"time":1535078924859,"sign":"67cf6e8b2d267be306625a2e44094ebc"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("hotgame")
    Observable<HttpResult<NewsPageBean>> getHotGame(@Body RequestBody body);

    /**
     * 游戏已发售
     * 方式：POST
     * 参数：
     * {"pagesize":10,"page":1,"time":1535079469639,"sign":"f67570763e9c594ddd66bfd36f02f649"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("gamesale")
    Observable<HttpResult<NewsPageBean>> getGameSale(@Body RequestBody body);

    /**
     * 游戏未发售
     * 方式：post
     * 参数：
     * {"pagesize":10,"page":1,"time":1535079498746,"sign":"91517acee7e636950ec34541cc4a8990"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("gameunsale")
    Observable<HttpResult<NewsPageBean>> getGameUnSale(@Body RequestBody body);

    /**
     * 游戏详情
     * 方式：post
     * 参数：
     * {"uid":9749488,"aid":3663564,"time":1535079549140,"sign":"7e220ecd88398c3e5100c233ebabf208"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("gamedetail")
    Observable<HttpResult<NewsPageBean>> getGameDetails(@Body RequestBody body);

    /**
     * 方式：post
     * 参数：
     * {"uid":"9749488","arcurl":"https:\/\/www.3dmgame.com\/games\/departmentoa\/","f_sid":0,"time":1535079549141,"sign":"43c4c2d75f15426c333c936afe4ae560"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("getarcfavorite")
    Observable<HttpResult<NewsPageBean>> getArcFavorite(@Body RequestBody body);

    /**
     * 获取游戏详情评论
     * 方式：post
     * 参数：
     * {"uid":9749488,"arcurl":"https:\/\/www.3dmgame.com\/games\/departmentoa\/","c_sid":201,"pagesize":100,"page":1,"time":1535079549745,"sign":"99d0f5ca4df2728afc52e788e2850fd8"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("getcomment")
    Observable<HttpResult<NewsPageBean>> getGameComment(@Body RequestBody body);

    /**
     * 汉化游戏
     * 最新---order  1   热门：2
     * 方式：post
     * 参数： {"pagesize":10,"page":1,"order":1,"time":1535080062635,"sign":"00e4d02397f0559cba3bdd48a2fa0596"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("gamechine")
    Observable<HttpResult<NewsPageBean>> getGameChinese(@Body RequestBody body);


    /**
     * 游戏排行
     * 方式：post
     * 参数：
     * {"uid":"9749488","pagesize":10,"page":1,"time":1535080165458,"sign":"ca548b6f7db903f574b13a2c248b18ff"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("gamerank")
    Observable<HttpResult<NewsPageBean>> getGameRank(@Body RequestBody body);



    /**
     * 手游汉化
     * 方式：post
     * order 1 最新   2 热门
     * 参数：
     * {"pagesize":10,"page":1,"order":1,"time":1535080763713,"sign":"7de864f8a9618b1f353c4e7cad6b6f81"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("sysdmhh")
    Observable<HttpResult<NewsPageBean>> getSysdmhh(@Body RequestBody body);

    /**
     * 论坛排行
     * 方式：post
     * 参数
     * {"uid":"9749488","time":1535080979458,"sign":"940f9443fc733341bce27b3c4427301e"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("forumrank")
    Observable<HttpResult<NewsPageBean>> getForumgRank(@Body RequestBody body);


    /**
     * 论坛版块
     * 方式：
     * post
     * 参数：
     * {"time":1535081189468,"sign":"ab482978e04b6fe8252a3c4768a40e52"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("forumgid")
    Observable<HttpResult<NewsPageBean>> getForumgId(@Body RequestBody body);

    /**
     * 全站置顶
     * 方式：post
     * 参数：
     * {"time":1535081307616,"sign":"dc0210c472ad7ce92a4ed981fccc0f22"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("topthread")
    Observable<HttpResult<NewsPageBean>> getTopThread(@Body RequestBody body);

    /**
     * 站内公告
     * 方式：post
     * 参数：
     * {"time":1535081322843,"sign":"ba6fe6cf44df98f57313eaee4936e612"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("topnoticebbs")
    Observable<HttpResult<NewsPageBean>> getTopNoticeBBs(@Body RequestBody body);

    /**
     * 精华推荐
     * 方式：post
     * 参数： {"time":1535081588228,"sign":"0ef74d9f95879709f6c45ac8b329c70e"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("topdigestbbs")
    Observable<HttpResult<NewsPageBean>> getTopDigestBBs(@Body RequestBody body);

    /**
     * 发送验证码
     * 方式:POST
     * 参数：
     * {"mobile":"13730693919","act":3,"uid":"9749488","time":1535358435086,"sign":"b454ee950e096ff600d370883d8ee692"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("mysendsms")
    Observable<HttpResult<NewsPageBean>> sendSMS(@Body RequestBody body);

    /**
     * 绑定手机号
     * 方式：post
     * 参数：
     * {"mobile":"13730693919","validate":"18930","uid":"9749488","time":1535358628306,"sign":"e4cc1c6bc2aaaff209b8a85d6decbcc0"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("bindmobilebyuid")
    Observable<HttpResult<NewsPageBean>> bindPhone(@Body RequestBody body);

    /**
     * 用户评论
     * 方式：post
     * 参数：
     * {"uid":"9749488","pagesize":10,"page":1,"time":1535358852530,"sign":"2af88e6d605c921e9174f02a9604237f"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("usercomment")
    Observable<HttpResult<NewsPageBean>> getUserComment(@Body RequestBody body);

    /**
     * 用户收藏的新闻
     * 方式:POST
     * 参数
     * {"uid":"9749488","time":1535358963055,"sign":"37b396a8eeb1a05974847e85da5bf9a6"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("favoritenews")
    Observable<HttpResult<NewsPageBean>> getFavoriteNews(@Body RequestBody body);


    /**
     * 用户收藏的游戏
     * 方式:POST
     * 参数
     * {"uid":"9749488","time":1535358963055,"sign":"37b396a8eeb1a05974847e85da5bf9a6"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("favoritegame")
    Observable<HttpResult<NewsPageBean>> getFavoritegame(@Body RequestBody body);

    /**
     * 用户收藏的帖子
     * 方式:POST
     * 参数
     * {"uid":"9749488","time":1535359053952,"sign":"f148857a2ae6d8463d5b7994718e342a"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("threadfavorite")
    Observable<HttpResult<NewsPageBean>> getThreadFavorite(@Body RequestBody body);

    /**
     * 热搜
     * 方式:POST
     * 参数：
     * {"time":1535359169765,"sign":"407c8d2b17b2b68ba759d01e5d22976a"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("hotso")
    Observable<HttpResult<NewsPageBean>> getHotSerach(@Body RequestBody body);


    /**
     * 根据关键字搜索
     * 方式:POST
     * 参数:{"keyword":"遨游中国","type":1,"pagesize":10,"page":1,"time":1535359357836,"sign":"bf0364b33aa4b9af731a7239f4ffc720"}
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("allso")
    Observable<HttpResult<NewsPageBean>> serachByKeyWord(@Body RequestBody body);


}
