package com.stx.xhb.dmgameapp.utils;

/**
 * Created by xhb on 2016/1/19.
 * 接口地址工具类
 */
public class API {
    //3DMGame网站地址
    public static final String DMGEAME_URL = "http://www.3dmgame.com";
    //服务器接口地址
    public static final String API_URL = "http://www.3dmgame.com/sitemap/api.php";
    //新闻列表接口地址
    public static final String NEWS_URL = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=1&paging=1&page=%s";
    //文章列表接口地址
    public static final String ARTICLE_URL = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=%s&paging=1&page=%s";
    //文章详情的接口地址
    public static final String ChapterContent_URL = "http://www.3dmgame.com/sitemap/api.php?id=%s&typeid=%s";
    //评论列表接口
    public static final String COMMENT_URL = "http://www.3dmgame.com/sitemap/api.php?type=1&aid=%s&pageno=%s";
    //评论提交接口
    public static final String COMMENT_COMMIT_URL = "http://www.3dmgame.com/sitemap/api.php?type=2";
    //游戏列表获取接口
    public static final String GAME_URL = "http://www.3dmgame.com/sitemap/api.php?row=10&typeid=%s&paging=1&page=%s";

}
