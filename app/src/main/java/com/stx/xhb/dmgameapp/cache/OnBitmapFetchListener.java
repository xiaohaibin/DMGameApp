package com.stx.xhb.dmgameapp.cache;

import android.graphics.Bitmap;

/**
 * Created by xhb on 2016/1/9.
 * 当图片获取到之后，会回调这个接口
 */
public interface OnBitmapFetchListener {

    void onFetch(Bitmap bitmap);

}
