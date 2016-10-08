package com.stx.xhb.dmgameapp.fragment.innerFragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.classic.common.MultipleStatusView;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.activities.ArticleDetailActivity;
import com.stx.xhb.dmgameapp.adapter.ListViewAdapter;
import com.stx.xhb.dmgameapp.entity.ChapterListItem;
import com.stx.xhb.dmgameapp.utils.API;
import com.stx.xhb.dmgameapp.utils.JsonUtils;
import com.stx.xhb.dmgameapp.utils.NetUtils;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.stx.xhb.dmgameapp.view.ImageCycleView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 新闻类的Fragemnt
 */
public class NewsFragment extends Fragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    @Bind(R.id.multiplestatusview)
    MultipleStatusView multiplestatusview;
    @Bind(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    private View view;
    private ImageCycleView mImageCycleView;
    private ListView news_lv;
    private ListViewAdapter adapter;
    private LayoutInflater mInflater;
    private View mHeadView;
    private View mFootView;
    private List<ChapterListItem> data = new ArrayList<>();
    //Android自带下拉刷新控件
    private List<ChapterListItem> chapterListItems;
    private int currenPage = 1;//当前页
    private boolean isBottom;//是否到底部的标记
    private boolean isLoadData = false;//判断是否已经在加载数据
    private String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
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
        news_lv = (ListView) view.findViewById(R.id.content_view);

        //添加listview头部控件
        mHeadView = mInflater.inflate(R.layout.banner_view, null);
        mImageCycleView = (ImageCycleView) mHeadView.findViewById(R.id.icv_topView);
        news_lv.addHeaderView(mHeadView);
        //添加底部控件
        mFootView = mInflater.inflate(R.layout.listview_footer, null);
        news_lv.addFooterView(mFootView, null, false);
        adapter = new ListViewAdapter(getActivity(), data);
        //初始化图片轮播数据
        initBanner();
    }

    //初始化图片轮播数据
    private void initBanner() {
        List<ImageCycleView.ImageInfo> list = new ArrayList<>();
        //使用网络加载数据，最后一个参数为图片新闻的id
        list.add(new ImageCycleView.ImageInfo("http://www.3dmgame.com/uploads/allimg/130124/11111111111111111111111111-130124144424.jpg", "新年首款大作 《龙之信条：黑暗觉者》破解版发布", "3542051"));
        list.add(new ImageCycleView.ImageInfo("http://www.3dmgame.com/uploads/allimg/130124/11111111111111111111-130124145Q3.jpg", "3DM轩辕组制作 《暗黑地牢》正式版汉化补丁发布", "3544323"));
        list.add(new ImageCycleView.ImageInfo("http://www.3dmgame.com/uploads/allimg/130124/1111111111-130124145Q7.jpg", "育碧旗下射击游戏《全境封锁》PC版详细配置公布", "3544277"));
        list.add(new ImageCycleView.ImageInfo("http://www.3dmgame.com/uploads/allimg/130124/11111111111-130124145R1.jpg", "PS4《侠盗猎车5》重制版新截图展示华丽视觉效果", "3395945"));
        list.add(new ImageCycleView.ImageInfo("http://www.3dmgame.com/uploads/allimg/130124/1111111111111111111111111111111-130124145R1-50.jpg", "1月31日3DM新游评测与推荐 老司机是时候开车了", "3545703"));
        mImageCycleView.setOnPageClickListener(new ImageCycleView.OnPageClickListener() {
            @Override
            public void onClick(View imageView, ImageCycleView.ImageInfo imageInfo) {
                //点击跳转到文章详情界面
                Bundle bundle = new Bundle();
                bundle.putString("typeid", "2");
                bundle.putString("id", imageInfo.value.toString());
                //跳转到文章详情界面
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        mImageCycleView.loadData(list, new ImageCycleView.LoadImageCallBack() {
            @Override
            public ImageView loadAndDisplay(ImageCycleView.ImageInfo imageInfo) {

                //使用BitmapUtils,只能使用网络图片
                x.view().inject(view);
                Context context = getContext();
                ImageView imageView = null;
                if (context != null) {
                    imageView = new ImageView(context);
                    x.image().bind(imageView, imageInfo.image.toString());
                }
                return imageView;
            }
        });

    }

    //下载网络数据
    private void downloadData(final int page) {
        multiplestatusview.showLoading();
        //使用xutils请求网络数据
        String stUrl = String.format(API.NEWS_URL, page);
        x.http().get(new RequestParams(stUrl), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                multiplestatusview.showContent();
                String json = new String(result);
                chapterListItems = JsonUtils.parseChapterJson(json);
                if (page == 1) {
                    data.clear();
                }
                if (chapterListItems.isEmpty()){
                    multiplestatusview.showEmpty();
                }
                data.addAll(chapterListItems);
                ptrLayout.refreshComplete();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (NetUtils.isNetConnected(getActivity())){
                    multiplestatusview.showError();
                }else {
                    multiplestatusview.showNoNetwork();
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

                if (page == 1) {
                    ptrLayout.refreshComplete();
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    //设置适配器
    private void setAdapter() {
        news_lv.setAdapter(adapter);
    }

    //设置监听事件
    private void setListener() {
        //listview的条目事件点击
        news_lv.setOnItemClickListener(this);
        //listview的滑动监听
        news_lv.setOnScrollListener(this);
        //点击重试
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadData(1);
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
        ///////////此处减-1是因为在listview头部添加了一个viewpager，
        // 造成所有listview的条目的位置都往下移了一个
        String typeid = data.get(position - 1).getTypeid();//获取到文章分类id
        String ariticleId = data.get(position - 1).getId();//获取文章id
        bundle.putString("typeid", typeid);
        bundle.putString("id", ariticleId);
        Log.i("=====>", "" + typeid + "=====》" + ariticleId);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //////////////////////listview的滑动事件监听/////////////////
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //isBottom是自定义的boolean变量，用于标记是否滑动到底部
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && isBottom && !isLoadData) {
            //如果加载到底部则加载下一页的数据显示到listview中
            currenPage++;
            //加载新数据
            isLoadData = true;//将加载数据的状态设置为true
            url = String.format(API.NEWS_URL, currenPage);
            mFootView.setVisibility(View.VISIBLE);//设置进度条出现
            //xutils加载网络数据
            x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    String json = new String(result);
                    chapterListItems = JsonUtils.parseChapterJson(json);
                    if (chapterListItems != null) {
                        mFootView.setVisibility(View.GONE);//设置隐藏进度条
                        data.addAll(chapterListItems);
                        adapter.notifyDataSetChanged();
                        isLoadData = false;//下载完数据之后，将状态设为false
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    ToastUtil.showShort(getActivity(),"加载失败");
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });

        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //若第一个可见的item的下标+可见的条目的数量=当前listview中总的条目数量，则说明已经到达底部
        isBottom = firstVisibleItem + visibleItemCount == totalItemCount;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private void setSwipeRefreshInfo() {
        ptrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(in.srain.cube.views.ptr.PtrFrameLayout frame, View content, View header) {
                return !canChildScrollUp();
            }

            @Override
            public void onRefreshBegin(in.srain.cube.views.ptr.PtrFrameLayout frame) {
                   downloadData(1);
            }
        });
        ptrLayout.setLastUpdateTimeRelateObject(this);//设置是否显示上次更新时间
        ptrLayout.autoRefresh();//设置是否自动更新
    }

    /**
     * 判断是否滑动到顶端
     * @return
     */
    public boolean canChildScrollUp() {
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
}
