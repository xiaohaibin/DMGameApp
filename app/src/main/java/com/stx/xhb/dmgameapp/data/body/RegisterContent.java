package com.stx.xhb.dmgameapp.data.body;

import com.stx.core.utils.StringUtils;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/28
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class RegisterContent {

    /**
     * mobile : 13730693919
     * passwd : hjjj
     * validate : 5566
     * time : 1538105744276
     * sign : 6166ce9a8f194b1bd8a66dda9ca96a09
     */

    private String mobile;
    private String passwd;
    private String validate;
    private long time;
    private String sign;

    public RegisterContent(String mobile, String passwd, String validate) {
        this.mobile = mobile;
        this.passwd = passwd;
        this.validate = validate;
        this.time=System.currentTimeMillis();
        this.sign=StringUtils.getMD5(mobile+passwd+validate+time);
    }
}
