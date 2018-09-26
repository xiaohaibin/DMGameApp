package com.stx.xhb.dmgameapp.data.body;

import android.util.Log;

import com.stx.core.utils.StringUtils;
import com.zhy.http.okhttp.utils.L;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/26
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class NewsAboutContent {

    /**
     * arcurl : https://www.3dmgame.com/news/201808/3743832.html
     * pagesize : 3
     * time : 1535078536940
     * sign : 36fa74931c8b019dfeb79e57ade25ce0
     */

    private String arcurl;
    private int pagesize;
    private long time;
    private String sign="";

    public NewsAboutContent(String arcurl) {
           this.arcurl=arcurl;
           this.pagesize=3;
           this.time=System.currentTimeMillis();
           this.sign=StringUtils.getMD5(arcurl+pagesize+time);
    }
}
