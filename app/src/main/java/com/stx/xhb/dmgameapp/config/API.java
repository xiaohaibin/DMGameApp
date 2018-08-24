package com.stx.xhb.dmgameapp.config;

import com.zhy.http.okhttp.utils.L;

/**
 * Author：xiaohaibin
 * Time：2017/9/9
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class API {

    /**
     * 请求：POST
     * 参数：appid：1   2为视频
     * page  1   页码
     * key
     * id
     */
    public static final String NEWS_CHANNEL_DATA = "http://m.3dmgame.com/y3wap/ajaxappchannel.php";

    /**
     * 用户登录、注册、论坛相关接口
     */
    public static final String USER_API = "http://bbs.3dmgame.com/api/3dmapp/index.php";
    /**
     * 获取文章频道列表
     * 方式：GET
     */
    public static final String GET_NEWS_CHANNEL = "http://m.3dmgame.com/y3wap/ajaxappchannels.php";

    /**
     * 获取游戏频道列表
     * 方式：GET
     */
    public static final String GET_GAME_CHANNEL = "http://m.3dmgame.com/y3wap/appgamechannels.php";

    /**
     * 获取热门搜索关键词
     * 方式:POST
     */
    public static final String GET_SERACH_HOT_KEYWORD = "http://m.3dmgame.com/y3wap/ajaxappsearch.php";

    /**
     * 获取游戏各个频道数据
     * 方式：POST
     * 参数：{"page":"1","appid":"1"}
     */
    public static final String GET_GAME_CHANNEL_DATA = "http://m.3dmgame.com/y3wap/appgamechannel.php";

    /**
     * 获取游戏详情
     * 方式：post
     * 参数：{"appid":"1","page":"1","id":"3505044","key":"南方公园：完整破碎","type":"1"}
     */
    public static final String GET_GAME_DETAILS = "http://m.3dmgame.com/y3wap/appgamesmsearch.php";

    /**
     * web论坛链接
     */
    public static final String GET_3DM_WEB_FORUM = "http://bbs.3dmgame.com/forum.php?mod=viewthread&tid=%s&page=1&mobile=3dmapp";

    /**
     * 获取评论列表
     */
    public static final String GET_COMMENT_LIST = "http://changyan.sohu.com/api/2/topic/load?client_id=cy2x2lUJoEMz&depth=2&page_size=20&topic_source_id=%s&topic_url=20&sub_size=20&hot_size=0&order_by=floor";

    /**
     * 提交评论
     * 方式：post
     * 参数：client_id 畅言appid
     * topic_id  文章的id
     * content  评论内容
     * reply_id 回复的评论id
     * access_token 采用OAuth授权方式为必填参数
     */
    public static final String POST_COMMENT = "http://changyan.sohu.com/api/2/comment/submit";

    /**
     * 获取畅言的AccessToken
     * 方式：post
     */
    public static final String GET_CHANG_YAN_AccessToken = "http://changyan.sohu.com/api/oauth2/sso/token";

    /**
     * 获取畅言用户信息
     * 方式：get
     * access_token
     * client_id
     */
    public static final String GET_CHANG_YAN_USER_INFO = "http://changyan.sohu.com/api/2/user/info";


    /////////////////////////////////////新API//////////////////////////////////////////////////////////////

    /**
     * 热点
     * {"pagesize":10,"page":1,"time":1535076178701,"sign":"d2fa5047f53cccd99ade57edeaf10ca5"}
     */
    public static final String POST_NEW_HOT_NEWS_PAGE = "https://my.3dmgame.com/app/hotnewspage";

    /**
     * 新闻
     * {"pagesize":10,"page":1,"time":1535078255062,"sign":"9a19c5a6c3d10cc270ebabb17a98d3d2"}
     */
    public static final String POST_NEWS = "https://my.3dmgame.com/app/newspage";

    /**
     * 原创
     * {"pagesize":10,"page":1,"time":1535078302502,"sign":"e572532c81e762d22f5def0ed0009df6"}
     */
    public static final String POST_ORIGINAL_PAGE = "https://my.3dmgame.com/app/originalpage";


    /**
     * 视频
     * {"pagesize":10,"page":1,"time":1535078362384,"sign":"c6ca3dc80679dfcd9e1e1fd16a9cc7eb"}
     */
    public static final String POST_VIDEO_PAGE="https://my.3dmgame.com/app/videopage";

    /**
     * 娱乐
     * {"pagesize":10,"page":1,"time":1535078425411,"sign":"4fd861bc19dd96f6d44c166a96c8486d"}
     */
    public static final String POST_AMUSE_PAGE="https://my.3dmgame.com/app/amusepage";


    /**
     * 新闻相关内容
     *   {"arcurl":"https:\/\/www.3dmgame.com\/news\/201808\/3743832.html","pagesize":3,"time":1535078536940,"sign":"36fa74931c8b019dfeb79e57ade25ce0"}
     */
    public static final String POST_NEWS_ABOUT="https://my.3dmgame.com/app/newsabout";


    /**
     * 获取最新评论
     * {"uid":9749488,"arcurl":"https:\/\/www.3dmgame.com\/news\/201808\/3743832.html","c_sid":0,"pagesize":5,"page":1,"time":1535078536959,"sign":"ea2208d6606e5e88f5c395206d1b0640"}
     */
    public static final String POST_GET_HOT_COMMENT="https://my.3dmgame.com/app/gethotcomment";


    /**
     * 热门游戏
     * {"time":1535078924859,"sign":"67cf6e8b2d267be306625a2e44094ebc"}
     */
    public static final String GET_HOT_GAME="https://my.3dmgame.com/app/hotgame";


    /**
     * 游戏已发售
     * 方式：POST
     * 参数：
     *{"pagesize":10,"page":1,"time":1535079469639,"sign":"f67570763e9c594ddd66bfd36f02f649"}
     */
    public static final String GAEM_SALE="https://my.3dmgame.com/app/gamesale";


    /**
     * 游戏未发售
     * 方式：post
     * 参数：
     *  {"pagesize":10,"page":1,"time":1535079498746,"sign":"91517acee7e636950ec34541cc4a8990"}
     */
    public static final String GAME_UNSALE="https://my.3dmgame.com/app/gameunsale";

    /**
     * 游戏详情
     * 方式：post
     * 参数：
     * {"uid":9749488,"aid":3663564,"time":1535079549140,"sign":"7e220ecd88398c3e5100c233ebabf208"}
     */
    public static final String GAME_DETAILS="https://my.3dmgame.com/app/gamedetail";


    /**
     * 方式：post
     * 参数：
     * {"uid":"9749488","arcurl":"https:\/\/www.3dmgame.com\/games\/departmentoa\/","f_sid":0,"time":1535079549141,"sign":"43c4c2d75f15426c333c936afe4ae560"}
     */
    public static final String GET_ARCFAVORITE="https://my.3dmgame.com/app/getarcfavorite";


    /**
     * 获取游戏详情评论
     * 方式：post
     * 参数：
     * {"uid":9749488,"arcurl":"https:\/\/www.3dmgame.com\/games\/departmentoa\/","c_sid":201,"pagesize":100,"page":1,"time":1535079549745,"sign":"99d0f5ca4df2728afc52e788e2850fd8"}
     */
    public static final String GET_COMMENT="https://my.3dmgame.com/app/getcomment";


    /**
     * 汉化游戏
     * 最新---order  1   热门：2
     * 方式：post
     * 参数： {"pagesize":10,"page":1,"order":1,"time":1535080062635,"sign":"00e4d02397f0559cba3bdd48a2fa0596"}
     */
    public static final String GAME_CHINESE="https://my.3dmgame.com/app/gamechine";

    /**
     * 游戏排行
     * 方式：post
     * 参数：
     *  {"uid":"9749488","pagesize":10,"page":1,"time":1535080165458,"sign":"ca548b6f7db903f574b13a2c248b18ff"}
     */
    public static final String GAME_RANK="https://my.3dmgame.com/app/gamerank";

    /**
     * 手游汉化
     * 方式：post
     * order 1 最新   2 热门
     * 参数：
     * {"pagesize":10,"page":1,"order":1,"time":1535080763713,"sign":"7de864f8a9618b1f353c4e7cad6b6f81"}
     */
    public static final String SY_GAME_HH="https://my.3dmgame.com/app/sysdmhh";


    /**
     * 手游分类
     * 方式：post
     * 参数：
     *  {"time":1535080881481,"sign":"e48b7d3dd40c54c65ba6ded30e906d01"}
     */
    public static final String SY_TYPES="https://my.3dmgame.com/app/sytypes";


    /**
     * 论坛排行
     * 方式：post
     * 参数
     *   {"uid":"9749488","time":1535080979458,"sign":"940f9443fc733341bce27b3c4427301e"}
     */
    public static final String FORUM_RANK="https://my.3dmgame.com/app/forumrank";


    /**
     * 论坛版块
     * 方式：
     * post
     * 参数：
     *   {"time":1535081189468,"sign":"ab482978e04b6fe8252a3c4768a40e52"}
     */
    public static final String FORUM_GID="https://my.3dmgame.com/app/forumgid";


    /**
     * 全站置顶
     * 方式：post
     * 参数：
     * {"time":1535081307616,"sign":"dc0210c472ad7ce92a4ed981fccc0f22"}
     */
    public static final String TOP_THREAD="https://my.3dmgame.com/app/topthread";


    /**
     * 站内公告
     * 方式：post
     * 参数：
     *  {"time":1535081322843,"sign":"ba6fe6cf44df98f57313eaee4936e612"}
     */
    public static final String TOP_NOTICE_BBS="https://my.3dmgame.com/app/topnoticebbs";


    /**
     *  精华推荐
     *  方式：post
     *  参数： {"time":1535081588228,"sign":"0ef74d9f95879709f6c45ac8b329c70e"}
     */
    public static final String TOP_DIGES_BBS="https://my.3dmgame.com/app/topdigestbbs";

}
