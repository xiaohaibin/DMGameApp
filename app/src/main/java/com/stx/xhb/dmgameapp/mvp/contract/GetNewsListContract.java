package com.stx.xhb.dmgameapp.mvp.contract;

import android.content.Context;

import com.qq.e.ads.contentad.ContentAdData;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.qq.e.ads.nativ.NativeExpressADView;
import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;
import com.stx.xhb.dmgameapp.entity.NewsListBean;

import java.util.List;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
public interface GetNewsListContract {

    interface getNewsListModel extends IModel {
        void getNewsList(String appId, int page);

        void loadAD(Context context);
    }

    interface getNewListView extends IView {

        void getNewListSuccess(NewsListBean listEntity);

        void getADData(NativeExpressADView nativeADDataRef);

        void getNewListFailed(String msg);

        void showLoading();

        void hideLoading();
    }
}
