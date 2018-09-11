package com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.stx.core.utils.DateUtils;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.GameBean;
import com.stx.xhb.dmgameapp.data.entity.GameListBean;
import com.stx.xhb.dmgameapp.mvp.ui.activity.GameDetailsActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Mr.xiao
 * @time 2017/9/20
 * @Emil：xhb_199409@163.com
 * @Github：https://github.com/xiaohaibin/
 * @Describe：
 */

public class GameViewHolder extends BaseViewHolder<GameBean> {
    @Bind(R.id.iv_game_img)
    ImageView mIvGameImg;
    @Bind(R.id.tv_game_title)
    TextView mTvGameTitle;
    @Bind(R.id.tv_game_time)
    TextView mTvGameTime;

    public GameViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final GameBean data) {
        Glide.with(mIvGameImg.getContext()).load(data.getLitpic()).into(mIvGameImg);
        mTvGameTitle.setText(data.getTitle());
        mTvGameTime.setText(DateUtils.dateFromat(data.getPubdate_at()));
    }
}
