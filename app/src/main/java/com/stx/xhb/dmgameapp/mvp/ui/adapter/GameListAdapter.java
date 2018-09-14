package com.stx.xhb.dmgameapp.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.GameBean;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder.GameListViewHolder;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder.NewsAdViewHolder;

import java.util.List;

/**
 * @author：xiaohaibin
 * @time：2017/9/20
 * @emil：xhb_199409@163.com
 * @Github：https://github.com/xiaohaibin/
 * @Describe： 游戏列表适配
 */

public class GameListAdapter extends RecyclerArrayAdapter{

    private LayoutInflater mLayoutInflater;
    private List<NewsPageBean.SlidesBean> mAdList;
    private List<GameBean> newgame;
    private List<GameBean> expectgame;
    private List<GameBean> classicgame;
    private List<GameBean> hotgame;

    public GameListAdapter(Context context) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setAdList(List<NewsPageBean.SlidesBean> adList) {
        this.mAdList = adList;
        if (mAdList != null && !mAdList.isEmpty()&& getHeaderCount() == 0) {
            addHeader(new ItemView() {
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

    public void setNewGameList(List<GameBean> newGameList) {
        this.newgame = newGameList;
        if (newgame != null && !newgame.isEmpty()) {
            addHeader(new ItemView() {
                GameListViewHolder viewHolder;

                @Override
                public View onCreateView(ViewGroup parent) {
                    View view = mLayoutInflater.inflate(R.layout.list_item_hot_game_list, parent, false);
                    viewHolder = new GameListViewHolder(view, getContext(),"最新大作");
                    return view;
                }

                @Override
                public void onBindView(View headerView) {
                    if (viewHolder != null) {
                        viewHolder.setData(newgame);
                    }
                }
            });
        }
    }


    public void setMostExpected(List<GameBean> expectgameBeanList) {
        this.expectgame = expectgameBeanList;
        if (expectgame != null && !expectgame.isEmpty()) {
            addHeader(new ItemView() {
                GameListViewHolder viewHolder;

                @Override
                public View onCreateView(ViewGroup parent) {
                    View view = mLayoutInflater.inflate(R.layout.list_item_hot_game_list, parent, false);
                    viewHolder = new GameListViewHolder(view, getContext(),"最期待游戏");
                    return view;
                }

                @Override
                public void onBindView(View headerView) {
                    if (viewHolder != null) {
                        viewHolder.setData(expectgame);
                    }
                }
            });
        }
    }

    public void setClassicGame(List<GameBean> classicgameBeanList) {
        this.classicgame = classicgameBeanList;
        if (classicgame != null && !classicgame.isEmpty()) {
            addHeader(new ItemView() {
                GameListViewHolder viewHolder;

                @Override
                public View onCreateView(ViewGroup parent) {
                    View view = mLayoutInflater.inflate(R.layout.list_item_hot_game_list, parent, false);
                    viewHolder = new GameListViewHolder(view, getContext(),"经典大作");
                    return view;
                }

                @Override
                public void onBindView(View headerView) {
                    if (viewHolder != null) {
                        viewHolder.setData(classicgame);
                    }
                }
            });
        }
    }


    public void setHotGame(List<GameBean> hotgameBeanList) {
        this.hotgame = hotgameBeanList;
        if (hotgame != null &&!hotgame.isEmpty()) {
            addHeader(new ItemView() {
                GameListViewHolder viewHolder;

                @Override
                public View onCreateView(ViewGroup parent) {
                    View view = mLayoutInflater.inflate(R.layout.list_item_hot_game_list, parent, false);
                    viewHolder = new GameListViewHolder(view, getContext(),"热门游戏");
                    return view;
                }

                @Override
                public void onBindView(View headerView) {
                    if (viewHolder != null) {
                        viewHolder.setData(hotgame);
                    }
                }
            });
        }
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}
