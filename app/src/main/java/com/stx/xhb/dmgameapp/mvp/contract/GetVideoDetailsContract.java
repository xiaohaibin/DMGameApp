package com.stx.xhb.dmgameapp.mvp.contract;

import com.stx.core.mvp.IModel;
import com.stx.core.mvp.IView;

/**
 * @author: xiaohaibin.
 * @time: 2018/1/31
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public interface GetVideoDetailsContract {

    interface Model extends IModel {

        void getVideoDetails();

    }

    interface View extends IView {

    }
}
