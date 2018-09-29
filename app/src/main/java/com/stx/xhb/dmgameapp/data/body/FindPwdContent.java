package com.stx.xhb.dmgameapp.data.body;

import com.stx.core.utils.StringUtils;
import com.stx.xhb.dmgameapp.utils.AppUser;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/28
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class FindPwdContent {

    private String mobile;
    private String validate;
    private String passwd;
    private String checkpasswd;
    private long time;
    private String sign;

    public FindPwdContent(String mobile, String validate, String passwd) {
        this.mobile = mobile;
        this.validate = validate;
        this.passwd = passwd;
        this.checkpasswd = passwd;
        this.time = System.currentTimeMillis();
        this.sign = StringUtils.getMD5( mobile+validate + passwd + passwd + time);
    }
}
