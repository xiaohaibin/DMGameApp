package com.stx.xhb.dmgameapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.viewholder.GameListViewHolder;
import com.stx.xhb.dmgameapp.data.entity.GameListBean;

/**
 * @author：xiaohaibin
 * @time：2017/9/20
 * @emil：xhb_199409@163.com
 * @Github：https://github.com/xiaohaibin/
 * @Describe：
 */

public class GameListAdapter extends RecyclerArrayAdapter<GameListBean.HtmlEntity> {

    private LayoutInflater mLayoutInflater;

    public GameListAdapter(Context context) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new GameListViewHolder(mLayoutInflater.inflate(R.layout.list_item_game, parent, false));
    }
}
