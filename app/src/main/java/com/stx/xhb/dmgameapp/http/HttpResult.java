package com.stx.xhb.dmgameapp.http;

/**
 * @author: Mr.xiao on 2017/3/15
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 网络请求的实体基类
 */
public class HttpResult<T> extends BaseResponse {

    public int code;

    private String msg;

    private T data;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    /**
     * 连接服务器是否成功
     *
     * @return
     */
    public boolean isHttpSuccess() {
        return code == 1;
    }

    public T getData() {
        return data;
    }

}
