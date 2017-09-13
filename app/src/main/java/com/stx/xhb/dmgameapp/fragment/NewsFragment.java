package com.stx.xhb.dmgameapp.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.classic.common.MultipleStatusView;
import com.stx.core.utils.GsonUtil;
import com.stx.core.utils.NetUtils;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.activity.ArticleDetailActivity;
import com.stx.xhb.dmgameapp.adapter.NewsListAdapter;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.entity.NewsContentEntity;
import com.stx.xhb.dmgameapp.entity.NewsListEntity;
import com.stx.xhb.xbanner.XBanner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.loadingviewfinal.ListViewFinal;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import okhttp3.Call;
import okhttp3.Request;


/**
 * 新闻类的Fragemnt
 */
public class NewsFragment extends Fragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.multiplestatusview)
    MultipleStatusView multiplestatusview;
    @Bind(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    private View view;
    private ListViewFinal news_lv;
    private NewsListAdapter adapter;
    private LayoutInflater mInflater;
    private int currenPage = 1;//当前页
    private List<NewsListEntity.ChannelEntity.HtmlEntity> newsList;
    private List<NewsListEntity.BannerEntity.HtmlEntity> bannerList;
    private final int LIMIT = 20;
    private XBanner mBanner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        setAdapter();
        setListener();
        setSwipeRefreshInfo();
        return view;
    }


    //获取控件
    private void initView() {
        if (mInflater == null) {
            mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        news_lv = (ListViewFinal) view.findViewById(R.id.content_view);
        // 初始化HeaderView
        View headerView = View.inflate(getActivity(), R.layout.layout_ad_head, null);
        mBanner = (XBanner) headerView.findViewById(R.id.xbanner);
        news_lv.addHeaderView(headerView);
        adapter = new NewsListAdapter(getActivity(), newsList);
    }

    private void initData() {
        newsList = new ArrayList<>();
        bannerList = new ArrayList<>();
    }

    private void downloadData(final int page) {
        OkHttpUtils.postString()
                .url(API.NEWS_CHANNEL)
                .content(GsonUtil.newGson().toJson(new NewsContentEntity("1", page)))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request, int id) {
                        if (page == 1) {
                            multiplestatusview.showLoading();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (NetUtils.isNetConnected(getActivity())) {
                            multiplestatusview.showError();
                        } else {
                            multiplestatusview.showNoNetwork();
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        multiplestatusview.showContent();
                        NewsListEntity newsListEntity = GsonUtil.newGson().fromJson(response, NewsListEntity.class);
                        if (newsListEntity.getChannel().getHtml().isEmpty()) {
                            multiplestatusview.showEmpty();
                        }
                        if (page == 1) {
                            bannerList.clear();
                            newsList.clear();
                            bannerList.addAll(newsListEntity.getBanner().getHtml());
                        }
                        newsList.addAll(newsListEntity.getChannel().getHtml());

                        if (newsListEntity.getChannel().getHtml().size() < LIMIT) {
                            news_lv.setHasLoadMore(false);
                        } else {
                            news_lv.setHasLoadMore(true);
                        }
                    }

                    @Override
                    public void onAfter(int id) {
                        if (page == 1) {
                            ptrLayout.onRefreshComplete();
                            initBanner();
                        }
                        ptrLayout.onRefreshComplete();
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 初始化广告轮播图数据
     */
    private void initBanner() {
        List<String> tips = new ArrayList<String>();
        for (int i = 0; i < bannerList.size(); i++) {
            tips.add(bannerList.get(i).getTitle());
        }
        mBanner.setData(bannerList, tips);
    }

    private void setAdapter() {
        news_lv.setAdapter(adapter);
        mBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(bannerList.get(position).getLitpic()).placeholder(R.drawable.default_image).error(R.drawable.default_image).into((ImageView) view);
            }
        });
    }

    //设置监听事件
    private void setListener() {
        //listview的条目事件点击
        news_lv.setOnItemClickListener(this);
        //点击重试
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadData(currenPage);
            }
        });
        mBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                //点击跳转到文章详情界面
                Bundle bundle = new Bundle();
                bundle.putString("typeid", "2");
                bundle.putString("id", bannerList.get(position).getId());
                //跳转到文章详情界面
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    /**
     * listview的点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //点击条目跳转到详情界面
        Intent intent = new Intent(getContext(), ArticleDetailActivity.class);
        //将Url地址获取到
        Bundle bundle = new Bundle();
        String typeid = newsList.get(position - 1).getTypeid();//获取到文章分类id
        String ariticleId = newsList.get(position - 1).getId();//获取文章id
        bundle.putString("typeid", typeid);
        bundle.putString("id", ariticleId);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setSwipeRefreshInfo() {
        ptrLayout.setOnRefreshListener(new OnDefaultRefreshListener() {

            //判断是否可以下拉刷新
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return !isChildScrollUp();
            }


            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                currenPage=1;
                downloadData(currenPage);
            }
        });
        news_lv.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                currenPage++;
                downloadData(currenPage);
            }
        });
        news_lv.setNoLoadMoreHideView(false);
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.autoRefresh();//设置是否自动更新
    }


    /**
     * 判断是否滑动到顶端
     *
     * @return
     */
    public boolean isChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (news_lv instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) news_lv;
                return absListView.getChildCount() > 0 &&
                        (absListView.getFirstVisiblePosition() > 0 ||
                                absListView.getChildAt(0).getTop() < absListView.getPaddingTop());

            } else {
                return ViewCompat.canScrollVertically(news_lv, -1) || news_lv.getScrollY() > 0;
            }

        } else {

            return ViewCompat.canScrollVertically(news_lv, -1);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }
}
