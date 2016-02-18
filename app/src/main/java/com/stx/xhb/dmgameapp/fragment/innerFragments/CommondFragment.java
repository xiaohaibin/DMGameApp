package com.stx.xhb.dmgameapp.fragment.innerFragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.activities.ArticleDetailActivity;
import com.stx.xhb.dmgameapp.activities.VideoDetailActivity;
import com.stx.xhb.dmgameapp.adapter.ListViewAdapter;
import com.stx.xhb.dmgameapp.entity.ChapterListItem;
import com.stx.xhb.dmgameapp.utils.HttpAdress;
import com.stx.xhb.dmgameapp.utils.HttpUtils;
import com.stx.xhb.dmgameapp.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用的Fragment
 */
public class CommondFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    private SwipeRefreshLayout refreshLayout;
    private ListView lv_data;
    private View inflate;
    private List<ChapterListItem> chapterListItems = new ArrayList<>();
    //加载的新数据
    private List<ChapterListItem> datachapter;
    private ListViewAdapter adapter;
    private int currenPage = 1;//当前页
    private int typeid;
    private View mFootView;//底部控件
    private LayoutInflater mInflater;
    private boolean isBottom;//是否滑动到底部的标记
    private boolean isLoadData = false;//是否正在加载新数据的标记
    private String url;
    //视频分类id集合
    private static final int[] VIDEO_TYPE_ID = new int[]{
            194, 180, 221, 214, 213, 212, 211, 210, 203, 256, 201
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_commond, container, false);
        if (mInflater == null) {
            mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        //获取到控件
        refreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.refresh_layout);
        lv_data = (ListView) inflate.findViewById(R.id.lv_data);
        //添加listview底部控件
        mFootView = mInflater.inflate(R.layout.listview_footer, null);
//        mFootView.setVisibility(View.GONE);//默认隐藏进度条
        lv_data.addFooterView(mFootView, null, false);//设置为false 则不可点击，否则会报错
        //设置下拉刷新控件的颜色
        refreshLayout.setColorSchemeColors(Color.BLUE, Color.YELLOW);
        setListener();
        return inflate;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //实例化Adapter
        adapter = new ListViewAdapter(getActivity(), chapterListItems);
        //获取到文章分类id
        typeid = getArguments().getInt("typeid");
        //网络请求地址
        url = String.format(HttpAdress.ARTICLE_URL, typeid, currenPage);
        //接口回调获取数据
        HttpUtils.downLoadData(url, new HttpUtils.OnFetchDataListener() {
            @Override
            public void OnFetch(String url, byte[] data) {
                String json = new String(data);
                //解析json数据
                datachapter = JsonUtils.parseChapterJson(json);
                if (datachapter != null) {
                    chapterListItems.clear();
                    chapterListItems.addAll(datachapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        //给listview绑定适配器
        lv_data.setAdapter(adapter);
    }

    //设置事件监听
    private void setListener() {
        //设置下拉刷新事件监听
        refreshLayout.setOnRefreshListener(this);
        //设置listview的点击事件
        lv_data.setOnItemClickListener(this);
        //listview的滑动监听
        lv_data.setOnScrollListener(this);
    }

    //下拉刷新方法
    @Override
    public void onRefresh() {
        //加载新数据
        //网络请求地址
        url = String.format(HttpAdress.ARTICLE_URL, typeid, currenPage);
        //接口回调获取数据
        HttpUtils.downLoadData(url, new HttpUtils.OnFetchDataListener() {
            @Override
            public void OnFetch(String url, byte[] data) {
                String json = new String(data);
                //解析json数据
                datachapter = JsonUtils.parseChapterJson(json);
                if (datachapter != null) {
                    chapterListItems.clear();
                    chapterListItems.addAll(datachapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        refreshLayout.setRefreshing(false);
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

        //将Url地址获取到
        Bundle bundle = new Bundle();
        String typeid = chapterListItems.get(position).getTypeid();//获取到文章的分类id
        String ariticleId = chapterListItems.get(position).getId();//文章id
        Intent intent = new Intent();
        //遍历一下，判断是否为文章
        for (Integer item : VIDEO_TYPE_ID) {
            if (item == Integer.parseInt(typeid)) {
                //点击条目跳转到视频详情界面
                intent.setClass(getContext(), VideoDetailActivity.class);
            } else {
                //点击条目跳转到文章详情界面
                intent.setClass(getContext(), ArticleDetailActivity.class);
            }
        }
        bundle.putString("typeid", typeid);
        bundle.putString("id", ariticleId);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    ///////////////////////////listview滑动监听方法/////////////////////
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //isBottom是自定义的boolean变量，用于标记是否滑动到底部
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && isBottom && !isLoadData) {
            //如果加载到底部则开始加载下一页的数据显示到listview中
            currenPage++;
            mFootView.setVisibility(View.VISIBLE);//设置底部控件显示
            //开始加载数据
            isLoadData = true;//将加载新数据的标记设置为true
            //网络请求地址
            url = String.format(HttpAdress.ARTICLE_URL, typeid, currenPage);
            //接口回调获取数据
            HttpUtils.downLoadData(url, new HttpUtils.OnFetchDataListener() {
                @Override
                public void OnFetch(String url, byte[] data) {
                    String json = new String(data);
                    //解析json数据
                    datachapter = JsonUtils.parseChapterJson(json);
                    if (datachapter != null) {
                        mFootView.setVisibility(View.GONE);//设置底部控件隐藏
                        chapterListItems.addAll(datachapter);
                        adapter.notifyDataSetChanged();
                        //加载完数据之后，将标记设置为false
                        isLoadData = false;
                    }
                }
            });
        }
    }

    /***
     * 滚动的方法
     *
     * @param view
     * @param firstVisibleItem 第一个可见的item的下标
     * @param visibleItemCount 可见的item的数量
     * @param totalItemCount   listview中总的item数量
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //若第一个可见的item的下标+可见的条目的数量=当前listview中总的条目数量，则说明已经到达底部
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            isBottom = true;
        } else {
            isBottom = false;
        }
    }
}
