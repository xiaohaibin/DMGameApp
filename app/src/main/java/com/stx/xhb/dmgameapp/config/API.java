package com.stx.xhb.dmgameapp.config;

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
     * 参数：appid：1
     *       page  1   页码
     */
    public static final String NEWS_CHANNEL="http://m.3dmgame.com/y3wap/ajaxappchannel.php";

    /**
     * 用户登录、注册相关接口
     */
    public static final String USER_API="http://bbs.3dmgame.com/api/3dmapp/index.php";

    /**
     * 获取文章频道列表
     * 方式：GET
     */
    public static final String GET_NEWS_CHANNEL="http://m.3dmgame.com/y3wap/ajaxappchannels.php";

    //3DMGame网站地址
    public static final String DMGEAME_URL = "http://www.3dmgame.com";

    //文章列表接口地址
    public static final String ARTICLE_URL = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=%s&paging=1&page=%s";

    //文章详情的接口地址
    public static final String ChapterContent_URL = "http://www.3dmgame.com/sitemap/api.php?id=%s&typeid=%s";
}
