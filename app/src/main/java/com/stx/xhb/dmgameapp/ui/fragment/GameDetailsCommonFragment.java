package com.stx.xhb.dmgameapp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.stx.core.base.BaseFragment;
import com.stx.core.utils.GsonUtil;
import com.stx.core.utils.ScreenUtil;
import com.stx.core.widget.dialog.DialogMaker;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.GameNewsListAdapter;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.entity.GameDetailsContent;
import com.stx.xhb.dmgameapp.entity.GameNewsListEntity;
import com.stx.xhb.dmgameapp.entity.NewsListEntity;
import com.stx.xhb.dmgameapp.presenter.game.getGameDetailsCommonContract;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.stx.xhb.dmgameapp.widget.CustomLoadMoreView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Author : jxnk25
 * Time: 2017/10/19 0019
 * Email:xhb_199409@163.com
 * Email:xhb_199409@163.com
 * Github:https://github.com/xiaohaibin/
 * Drscribe:游戏资讯
 */

public class GameDetailsCommonFragment extends BaseFragment implements getGameDetailsCommonContract.getGameDetailsDataView, BaseQuickAdapter.RequestLoadMoreListener {

    @Bind(R.id.id_stickynavlayout_innerscrollview)
    RecyclerView mRecyclerView;
    private String type = "";
    private String id = "";
    private String key = "";
    private GameNewsListAdapter gameNewsListAdapter;

    public static GameDetailsCommonFragment newInstance(String type, String id, String key) {
        Bundle args = new Bundle();
        args.putString("type", type);
        args.putString("id", id);
        args.putString("key", key);
        GameDetailsCommonFragment fragment = new GameDetailsCommonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_common_recyclerview;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initVariable();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.color_eeeeee),
                ScreenUtil.dip2px(getContext(), 1), ScreenUtil.dip2px(getContext(), 12), ScreenUtil.dip2px(getContext(), 12));
        dividerDecoration.setDrawLastItem(false);
        mRecyclerView.addItemDecoration(dividerDecoration);
        setAdapter();
    }

    @Override
    protected Class getLogicClazz() {
        return getGameDetailsCommonContract.class;
    }

    private void initVariable() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey("type")) {
                type = bundle.getString("type");
            }
            if (bundle.containsKey("id")) {
                id = bundle.getString("id");
            }
            if (bundle.containsKey("key")) {
                key = bundle.getString("key");
            }
        }
    }


    @Override
    protected void lazyLoad() {
        currentpage = 1;
        getListData();
    }

    private void getListData() {
        switch (type) {
            case "1"://新闻
                getGameNewsList();
//                ((getGameDetailsCommonImpl) mPresenter).getGameNewsListData("1", id, key, currentpage);
                break;
            case "2"://攻略
//                ((getGameDetailsCommonImpl) mPresenter).getGameToolsListData("2", id, key, currentpage);
                getGameToolsList();
                break;
            default:
//                ((getGameDetailsCommonImpl) mPresenter).getGameNewsListData("1", id, key, currentpage);
                getGameNewsList();
                break;
        }
    }

    private void setAdapter() {
        gameNewsListAdapter = new GameNewsListAdapter(getActivity());
        gameNewsListAdapter.setOnLoadMoreListener(this, mRecyclerView);
        gameNewsListAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        gameNewsListAdapter.setLoadMoreView(new CustomLoadMoreView());
        mRecyclerView.setAdapter(gameNewsListAdapter);
    }

    @Override
    public void showLoading() {
        if (currentpage == 1) {
            DialogMaker.showProgressDialog(getActivity(), "正在加载...");
        }
    }

    @Override
    public void hideLoading() {
        if (currentpage == 1) {
            DialogMaker.dismissProgressDialog();
        }
        gameNewsListAdapter.loadMoreComplete();
    }

    @Override
    public void getGameNewsListDataSuccess(List<NewsListEntity.ChannelEntity.HtmlEntity> list) {
        if (list != null) {
            if (currentpage == 1) {
                gameNewsListAdapter.setNewData(list);
            } else {
                gameNewsListAdapter.addData(list);
            }
            if (list.isEmpty()) {
                gameNewsListAdapter.setEmptyView(R.layout.view_empty);
            }
        }
    }

    @Override
    public void getGameNewsListFailed(String msg) {
        ToastUtil.show(msg);
        gameNewsListAdapter.loadMoreFail();
    }

    @Override
    public void getGameToolsListDataSuccess(List<NewsListEntity.ChannelEntity.HtmlEntity> list) {
        if (list != null) {
            if (currentpage == 1) {
                gameNewsListAdapter.setNewData(list);
            } else {
                gameNewsListAdapter.addData(list);
            }
            if (list.isEmpty()) {
                gameNewsListAdapter.setEmptyView(R.layout.view_empty);
            }
            if (gameNewsListAdapter.getData().size() < page_size) {
                gameNewsListAdapter.loadMoreEnd(true);
            } else {
                gameNewsListAdapter.loadMoreEnd(false);
            }
        }
    }

    @Override
    public void getGameToolsListFailed(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void onLoadMoreRequested() {
        currentpage++;
        getListData();
    }

    private void getGameNewsList() {
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new GameDetailsContent(currentpage, id, key, "1")))
                .url(API.GET_GAME_DETAILS)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        showLoading();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getGameNewsListFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            GameNewsListEntity newsListEntity = GsonUtil.newGson().fromJson(response, GameNewsListEntity.class);
                            if (newsListEntity.getCode() == Constants.SERVER_SUCCESS) {
                                getGameNewsListDataSuccess(newsListEntity.getHtml());
                            } else {
                                hideLoading();
                                getGameNewsListFailed(newsListEntity.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onAfter(int id) {
                        hideLoading();
                    }
                });
    }

    private void getGameToolsList() {
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new GameDetailsContent(currentpage, id, key, "2")))
                .url(API.GET_GAME_DETAILS)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        showLoading();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getGameToolsListFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            GameNewsListEntity newsListEntity = GsonUtil.newGson().fromJson(response, GameNewsListEntity.class);
                            if (newsListEntity.getCode() == Constants.SERVER_SUCCESS) {
                                getGameToolsListDataSuccess(newsListEntity.getHtml());
                            } else {
                                hideLoading();
                                getGameToolsListFailed(newsListEntity.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onAfter(int id) {
                        hideLoading();
                    }
                });
    }
}
