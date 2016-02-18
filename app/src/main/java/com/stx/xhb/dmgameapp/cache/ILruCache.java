package com.stx.xhb.dmgameapp.cache;

import android.graphics.Bitmap;

/**
 * Created by xhb on 2016/1/9.
 * 内存缓存
 * 这个方法里面，只需要get 和put方法即可
 */
public interface ILruCache {

    //get方法，获取磁盘缓存里面的Bitmap
    Bitmap get(String url);

    //put方法，将其放在内存缓存里面
    void put(String url, Bitmap bitmap);
}
