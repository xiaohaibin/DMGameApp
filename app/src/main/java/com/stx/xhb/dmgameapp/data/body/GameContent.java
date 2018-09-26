package com.stx.xhb.dmgameapp.data.body;

import com.stx.core.utils.StringUtils;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/11
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class GameContent {
    private long time;
    private String sign = "";

    public GameContent() {
        this.time = System.currentTimeMillis();
        this.sign = StringUtils.getMD5(String.valueOf(time));
    }
}
