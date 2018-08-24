package com.stx.xhb.dmgameapp.base;

/**
 * @author Mr.xiao
 * @Time：2017/9/13
 * @Email：xhb_199409@163.com
 * @Github：https://github.com/xiaohaibin/
 * @Describe：
 */

public class BaseEntity {

    private int code = 0;
    private String msg = "";

    private boolean isSuccess;

    public boolean isSuccess() {
        return code == 1;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
