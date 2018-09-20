package com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder;

import android.graphics.drawable.ClipDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    @Bind(R.id.iv_level)
    ImageView mIvLevel;
    @Bind(R.id.tv_level)
    TextView tvLevel;
    @Bind(R.id.ll_level)
    LinearLayout mLlLevel;

    public GameViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final GameBean data) {
        Glide.with(mIvGameImg.getContext()).load(data.getLitpic()).into(mIvGameImg);
        mTvGameTitle.setText(data.getTitle());
        if (data.getPubdate_at() != 0) {
            mLlLevel.setVisibility(View.GONE);
            mTvGameTime.setVisibility(View.VISIBLE);
            mTvGameTime.setText(DateUtils.dateFromat(data.getPubdate_at()));
        } else {
            mTvGameTime.setVisibility(View.GONE);
            mLlLevel.setVisibility(View.VISIBLE);
            ((ClipDrawable) mIvLevel.getDrawable()).setLevel((int) (data.getScore() * 1000.0d));
            tvLevel.setText(String.valueOf(data.getScore()));
        }
    }
}
