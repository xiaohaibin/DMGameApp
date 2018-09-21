package com.stx.xhb.dmgameapp.mvp.presenter;

import android.text.TextUtils;

import com.stx.core.mvp.BasePresenter;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.data.entity.CommentListBean;
import com.stx.xhb.dmgameapp.mvp.contract.GetCommentListContract;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * @author: xiaohaibin.
 * @time: 2018/3/2
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class GetCommentListPresenter extends BasePresenter<GetCommentListContract.View> implements GetCommentListContract.Model{

    @Override
    public void getCommentListData(String id) {
        // TODO: 2018/9/21 获取评论列表
    }
}
