package com.stx.xhb.dmgameapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.viewholder.NewsAdViewHolder;
import com.stx.xhb.dmgameapp.adapter.viewholder.NewsCommonViewHolder;
import com.stx.xhb.dmgameapp.entity.NewsListEntity;

import java.util.List;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class NewsCommonAdapter extends RecyclerArrayAdapter<NewsListEntity.ChannelEntity.HtmlEntity> {

    private List<NewsListEntity.BannerEntity.HtmlEntity> mAdList;
    private LayoutInflater mLayoutInflater;

    public NewsCommonAdapter(Context contex) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setAdList(List<NewsListEntity.BannerEntity.HtmlEntity> adList) {
        this.mAdList = adList;
        if (mAdList != null && mAdList.size() > 0 && getHeaderCount() == 0) {
            addHeader(new RecyclerArrayAdapter.ItemView() {
                NewsAdViewHolder viewHolder;

                @Override
                public View onCreateView(ViewGroup parent) {
                    View view = mLayoutInflater.inflate(R.layout.layout_ad_head, parent, false);
                    viewHolder = new NewsAdViewHolder(view, getContext());
                    return view;
                }

                @Override
                public void onBindView(View headerView) {
                    if (viewHolder != null) {
                        viewHolder.setData(mAdList);
                    }
                }
            });
        }
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsCommonViewHolder(mLayoutInflater.inflate(R.layout.list_item_news, parent, false));
    }
}
