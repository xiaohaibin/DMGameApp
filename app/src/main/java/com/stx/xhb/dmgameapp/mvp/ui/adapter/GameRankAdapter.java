package com.stx.xhb.dmgameapp.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.GameRankBean;
import com.stx.xhb.dmgameapp.data.entity.SaleGameBean;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder.GameCommonViewHolder;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder.GameRankViewHolder;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */
public class GameRankAdapter extends RecyclerArrayAdapter<GameRankBean.ListBean> {

    private LayoutInflater mLayoutInflater;

    public GameRankAdapter(Context context) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getViewType(int position) {
        return super.getViewType(position);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new GameRankViewHolder(mLayoutInflater.inflate(R.layout.list_item_rank_game, parent, false));
    }
}
