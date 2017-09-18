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
    public static final String NEWS_CHANNEL_DATA="http://m.3dmgame.com/y3wap/ajaxappchannel.php";

    /**
     * 用户登录、注册、论坛相关接口
     */
    public static final String USER_API="http://bbs.3dmgame.com/api/3dmapp/index.php";

    /**
     * 获取文章频道列表
     * 方式：GET
     */
    public static final String GET_NEWS_CHANNEL="http://m.3dmgame.com/y3wap/ajaxappchannels.php";

    /**
     * 获取游戏频道列表
     * 方式：GET
     */
    public static final String GET_GAME_CHANNEL="http://m.3dmgame.com/y3wap/appgamechannels.php";

    /**
     * 获取热门搜索关键词
     * 方式:POST
     */
    public static final String GET_SERACH_HOT_KEYWORD="http://m.3dmgame.com/y3wap/ajaxappsearch.php";

    /**
     * 获取游戏各个频道数据
     * 方式：POST
     * 参数：{"page":"1","appid":"1"}
     */
    public static final String GET_GAME_CHANNEL_DATA="http://m.3dmgame.com/y3wap/appgamechannel.php";

}
