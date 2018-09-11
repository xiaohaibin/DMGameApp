package com.stx.xhb.dmgameapp.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.VideoListBean;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder.VideoDetailsItemViewHolder;

/**
 * Author：xiaohaibin
 * Time：2017/9/19
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：视频详情列表适配器
 */

public class VideoDetailsListAdapter extends RecyclerArrayAdapter<VideoListBean.VideoBean> {

    private LayoutInflater mLayoutInflater;

    public VideoDetailsListAdapter(Context context) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoDetailsItemViewHolder(mLayoutInflater.inflate(R.layout.list_item_video_details, parent, false));
    }
}
