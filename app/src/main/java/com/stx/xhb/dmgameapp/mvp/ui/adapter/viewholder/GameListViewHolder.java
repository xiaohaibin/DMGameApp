package com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.core.utils.ScreenUtil;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.GameBean;
import com.stx.xhb.dmgameapp.data.entity.GameListBean;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.mvp.ui.activity.WebDetailsActivity;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.GameAdapter;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Author：xiaohaibin
 * @Time：2017/9/18
 * @Emil：xhb_199409@163.com
 * @Github：https://github.com/xiaohaibin/
 * @Describe：
 */
public class GameListViewHolder {

    private Context mContext;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_more)
    TextView mTvMore;
    @Bind(R.id.rv_list)
    RecyclerView mRvList;

    public GameListViewHolder(View itemView, Context context,String title) {
        mContext = context;
        ButterKnife.bind(this, itemView);
        mTvTitle.setText(title);
    }

    public void setData(final List<GameBean> gameBeanList) {
        mRvList.setLayoutManager(new GridLayoutManager(mContext,3));
        GameAdapter gameAdapter = new GameAdapter(mContext);
        mRvList.setAdapter(gameAdapter);
        gameAdapter.addAll(gameBeanList);
        mTvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("更多");
            }
        });
    }
}
