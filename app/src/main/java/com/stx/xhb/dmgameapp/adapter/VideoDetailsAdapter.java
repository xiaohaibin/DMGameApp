package com.stx.xhb.dmgameapp.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.viewholder.NewsCommentViewHolder;
import com.stx.xhb.dmgameapp.entity.CommentsBean;
import com.stx.xhb.dmgameapp.data.entity.VideoListBean;

import java.util.List;

/**
 * @author: xiaohaibin.
 * @time: 2018/3/2
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class VideoDetailsAdapter extends RecyclerArrayAdapter<CommentsBean>{

    private List<VideoListBean.VideoBean> mVideoBeanList;
    private LayoutInflater mLayoutInflater;
    public VideoDetailsAdapter(Context context) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsCommentViewHolder(mLayoutInflater.inflate(R.layout.list_item_comment, parent, false));
    }

    public void addVideoList(List<VideoListBean.VideoBean> dataList) {
        this.mVideoBeanList = dataList;
        if (mVideoBeanList != null && !mVideoBeanList.isEmpty()) {
            addHeader(new ItemView() {

                @Override
                public View onCreateView(ViewGroup parent) {
                    EasyRecyclerView recyclerView = new EasyRecyclerView(getContext());
                    recyclerView.setRefreshing(false);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.getRecyclerView().setNestedScrollingEnabled(false);
                    VideoDetailsListAdapter adapter = new VideoDetailsListAdapter(getContext());
                    adapter.addAll(mVideoBeanList);
                    recyclerView.setAdapter(adapter);
                    return recyclerView;
                }

                @Override
                public void onBindView(View headerView) {
                    ((ViewGroup) headerView).requestDisallowInterceptTouchEvent(true);
                }
            });
        }
    }

    public void addCommentListLabel() {
        addHeader(new ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return mLayoutInflater.inflate(R.layout.layout_news_comment_label, parent, false);
            }

            @Override
            public void onBindView(View headerView) {
            }
        });
    }

    public void addEmptyCommentFooter() {
        addFooter(new ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return mLayoutInflater.inflate(R.layout.layout_empty_comments_footer, parent, false);
            }

            @Override
            public void onBindView(View headerView) {
            }
        });
    }


    public void addMoreCommentFooter() {
        addFooter(new ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return mLayoutInflater.inflate(R.layout.layout_comments_more_footer, parent, false);
            }

            @Override
            public void onBindView(View headerView) {
            }
        });
    }
}
