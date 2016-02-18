package com.stx.xhb.dmgameapp.cache;

/**
 * Created by xhb on 2016/1/9.
 * 磁盘缓存
 * 方法：
 * get  获取到数据
 * put  放入数据
 */
public interface IDiskCache {

    /**
     * 放入数据
     *
     * @param url  地址
     * @param data 数据
     */
    void put(String url, byte[] data);

    /**
     * 获取数据
     *
     * @param url 地址
     * @return 数据
     */
    byte[] get(String url);

}
