package com.stx.xhb.dmgameapp.http;

/**
 * @author: Mr.xiao on 2017/3/15
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 捕获服务器约定的错误类型
 */
public class ApiException extends RuntimeException {
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 数据解析错误
     */
    public static final int PARSE_ERROR = 1001;

    private int errCode;
    private String errorMsg;

    public ApiException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
        this.errorMsg=msg;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
