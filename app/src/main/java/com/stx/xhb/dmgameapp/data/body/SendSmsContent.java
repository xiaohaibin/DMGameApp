package com.stx.xhb.dmgameapp.data.body;

import com.stx.core.utils.StringUtils;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/28
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class SendSmsContent {

    /**
     * mobile : 18502339836
     * act : 1 注册   4 忘记密码
     * uid : 0
     * time : 1538105804999
     * sign : 35f6498ada7f02e5c7e535a8a9bf9641
     */

    private String mobile;
    private int act;
    private int uid;
    private long time;
    private String sign;

    public SendSmsContent(String mobile, int act, int uid) {
        this.mobile = mobile;
        this.act = act;
        this.uid = uid;
        this.time = System.currentTimeMillis();
        this.sign = StringUtils.getMD5(mobile + act + uid + time);
    }
}
