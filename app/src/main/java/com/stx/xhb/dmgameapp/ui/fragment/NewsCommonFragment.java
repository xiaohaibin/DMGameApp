package com.stx.xhb.dmgameapp.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.stx.core.base.BaseFragment;
import com.stx.core.utils.GsonUtil;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.NewsCommonAdapter;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.config.Constants;
import com.stx.xhb.dmgameapp.entity.NewsContentEntity;
import com.stx.xhb.dmgameapp.entity.NewsListEntity;
import com.stx.xhb.dmgameapp.presenter.news.getNewsListContract;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Request;

/**
 * 通用的Fragment
 */
public class NewsCommonFragment extends BaseFragment implements getNewsListContract.getNewListView, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.id_stickynavlayout_innerscrollview)
    EasyRecyclerView mRecyclerView;
    private String mAppId = "0";
    private NewsCommonAdapter mNewsCommonAdapter;

    public static NewsCommonFragment newInstance(String typeId) {
        Bundle args = new Bundle();
        NewsCommonFragment fragment = new NewsCommonFragment();
        args.putString("id", typeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey("id")) {
                mAppId = bundle.getString("id");
                Log.i("===>mAppId", mAppId);
            }
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setRefreshingColor(Color.rgb(255, 99, 71), Color.rgb(255, 99, 71), Color.rgb(255, 99, 71));
        mRecyclerView.setRefreshListener(this);
        mNewsCommonAdapter = new NewsCommonAdapter(getActivity());
        mNewsCommonAdapter.setMore(R.layout.view_more, this);
        mNewsCommonAdapter.setNoMore(R.layout.view_nomore);
        mNewsCommonAdapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                mNewsCommonAdapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                mNewsCommonAdapter.resumeMore();
            }
        });
        mRecyclerView.setAdapter(mNewsCommonAdapter);
    }

    @Override
    protected Class getLogicClazz() {
        return getNewsListContract.class;
    }

    @Override
    protected void lazyLoad() {
        if (mNewsCommonAdapter != null && mNewsCommonAdapter.getCount() <= 0) {
            onRefresh();
        }
    }

    @Override
    public void getNewListFailed(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void getNewListSuccess(NewsListEntity listEntity) {
//        if (listEntity != null) {
//            if (currentpage == 1) {
//                mNewsCommonAdapter.clear();
//                if ("1".equals(mAppId) && listEntity.getBanner() != null) {
//                    mNewsCommonAdapter.setAdList(listEntity.getBanner().getHtml());
//                }
//            }
//            if (listEntity.getChannel() != null) {
//                mNewsCommonAdapter.addAll(listEntity.getChannel().getHtml());
//            }
//            if (mNewsCommonAdapter.getCount() < page_size) {
//                mNewsCommonAdapter.stopMore();
//            }
//            if (mNewsCommonAdapter.getCount() == 0) {
//                mRecyclerView.showEmpty();
//            }
//        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common;
    }


    @Override
    public void onRefresh() {
        currentpage = 1;
        getData();
//        ((getNewsListImpl) mPresenter).getNewsList(mAppId, currentpage);
    }

    @Override
    public void onLoadMore() {
        currentpage++;
        getData();
//        ((getNewsListImpl) mPresenter).getNewsList(mAppId, currentpage);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    private void getData() {
        OkHttpUtils.postString()
                .content(GsonUtil.newGson().toJson(new NewsContentEntity(mAppId, currentpage)))
                .url(API.NEWS_CHANNEL_DATA)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        if (currentpage == 1) {
                            mRecyclerView.setRefreshing(true);
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.show(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            NewsListEntity listEntity = GsonUtil.newGson().fromJson(response, NewsListEntity.class);
                            if (listEntity.getCode() == Constants.SERVER_SUCCESS) {
                                if (currentpage == 1) {
                                    mNewsCommonAdapter.clear();
                                    if ("1".equals(mAppId) && listEntity.getBanner() != null) {
                                        mNewsCommonAdapter.setAdList(listEntity.getBanner().getHtml());
                                    }
                                }
                                if (listEntity.getChannel() != null) {
                                    mNewsCommonAdapter.addAll(listEntity.getChannel().getHtml());
                                }
                                if (mNewsCommonAdapter.getCount() >= listEntity.getChannel().getTotalrow()) {
                                    mNewsCommonAdapter.stopMore();
                                }
                                if (mNewsCommonAdapter.getCount() == 0) {
                                    mRecyclerView.showEmpty();
                                }
                            } else {
                                mRecyclerView.setRefreshing(false);
                                ToastUtil.show(listEntity.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onAfter(int id) {
                        mRecyclerView.setRefreshing(false);
                    }
                });
    }
}
