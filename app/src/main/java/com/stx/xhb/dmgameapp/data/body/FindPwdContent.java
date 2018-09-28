package com.stx.xhb.dmgameapp.data.body;

import com.stx.core.utils.StringUtils;
import com.stx.xhb.dmgameapp.utils.AppUser;
import com.zhy.http.okhttp.utils.L;

import javax.xml.transform.sax.TemplatesHandler;

/**
 * @author: xiaohaibin.
 * @time: 2018/9/28
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */
public class FindPwdContent {

    /**
     * mobile : 13730693919
     * validate : 484083
     * passwd : xhbxysy98662377
     * checkpasswd : xhbxysy98662377
     * time : 1538106472248
     * sign : 3aa832829895ad73f6621564585d24dc
     */

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
        this.sign = StringUtils.getMD5(AppUser.getUserInfoBean().getUid() + validate + passwd + passwd + time);
    }
}
