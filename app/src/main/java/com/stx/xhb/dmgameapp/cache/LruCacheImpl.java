package com.stx.xhb.dmgameapp.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.text.TextUtils;

/**
 * Created by xhb on 2016/1/9.
 * 实现了ILruCacher接口，作为内存缓存
 * LruCache
 */
public class LruCacheImpl implements ILruCache {
    //用于内存缓存
    private LruCache<String, Bitmap> lruCache;

    //初始化
    public LruCacheImpl() {
        //获取到当前App最大内存的几分之几
        //这个大小，根据app的图片存储多少来决定
        long totalSize = Runtime.getRuntime().maxMemory() / 8;
        //实例化LruCache
        lruCache = new LruCache<String, Bitmap>((int) totalSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //获取到每一张的图片占的字节数组
                return value.getByteCount();
            }
        };

    }

    /**
     * 通过url，获取到bitmap
     *
     * @param url
     * @return
     */
    @Override
    public Bitmap get(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return lruCache.get(url);
    }

    /**
     * @param url
     * @param bitmap
     */
    @Override
    public void put(String url, Bitmap bitmap) {
        //如果有一个为null，那么直接返回
        if (TextUtils.isEmpty(url) || bitmap == null) {
            return;
        }
        //如果以前没有这个东西，那么才添加
        if (lruCache.get(url)==null){
            lruCache.put(url,bitmap);
        }

    }
}
