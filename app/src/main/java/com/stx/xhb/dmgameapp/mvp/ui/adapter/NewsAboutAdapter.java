package com.stx.xhb.dmgameapp.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.NewsAboutBean;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder.NewsAboutViewHolder;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder.NewsAdViewHolder;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder.NewsCommonViewHolder;

import java.util.List;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
public class NewsAboutAdapter extends RecyclerArrayAdapter<NewsAboutBean.ListBean> {

    private LayoutInflater mLayoutInflater;

    public NewsAboutAdapter(Context context) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getViewType(int position) {
        return super.getViewType(position);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsAboutViewHolder(mLayoutInflater.inflate(R.layout.list_item_news_about, parent, false));
    }
}
