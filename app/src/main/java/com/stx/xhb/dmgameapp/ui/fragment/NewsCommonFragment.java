package com.stx.xhb.dmgameapp.ui.fragment;


import android.os.Bundle;

import com.stx.core.widget.dialog.DialogMaker;
import com.stx.xhb.dmgameapp.adapter.NewsCommonAdapter;
import com.stx.xhb.dmgameapp.base.BaseListFragment;
import com.stx.xhb.dmgameapp.entity.NewsListEntity;
import com.stx.xhb.dmgameapp.presenter.news.getNewsListContract;
import com.stx.xhb.dmgameapp.presenter.news.getNewsListImpl;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

/**
 * 通用的Fragment
 */
public class NewsCommonFragment extends BaseListFragment implements getNewsListContract.getNewListView {

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
            }
        }
    }

    @Override
    protected Class getLogicClazz() {
        return getNewsListContract.class;
    }

    @Override
    protected void onRefreshLoadData(int currentpage) {
        ((getNewsListImpl) mPresenter).getNewsList(mAppId, currentpage);
    }

    @Override
    public void getNewListSuccess(NewsListEntity listEntity) {
        if (page_size == 1) {
            mNewsCommonAdapter = new NewsCommonAdapter(getActivity());
            setRecyclerViewAdapterAttribute(mNewsCommonAdapter, true, true);
            setEmptyView("暂无推荐");
            mNewsCommonAdapter.setAdList(listEntity.getBanner().getHtml());
            mNewsCommonAdapter.clear();
        }
        mNewsCommonAdapter.addAll(listEntity.getChannel().getHtml());
        if (mNewsCommonAdapter.getCount() < page_size) {
            mNewsCommonAdapter.stopMore();
        }

    }

    @Override
    public void getNewListFailed(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void showLoading() {
        DialogMaker.showProgressDialog(getActivity(), "正在加载");
    }

    @Override
    public void hideLoading() {
        DialogMaker.dismissProgressDialog();
    }
}
