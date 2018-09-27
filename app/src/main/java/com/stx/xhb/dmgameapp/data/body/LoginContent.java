package com.stx.xhb.dmgameapp.data.body;

import com.stx.core.utils.StringUtils;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/27
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class LoginContent {

    /**
     * username : jjjj
     * passwd : hhhhhj
     * time : 1538039006164
     * sign : 249e890984003569ceccb458a4cdcbaa
     */

    private String username;
    private String passwd;
    private long time;
    private String sign;

    public LoginContent(String username,String passwd) {
        this.passwd=passwd;
        this.username = username;
        this.time=System.currentTimeMillis();
        this.sign=StringUtils.getMD5(username+passwd+time);
    }
}
