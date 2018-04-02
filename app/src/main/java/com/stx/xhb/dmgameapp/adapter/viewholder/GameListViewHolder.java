package com.stx.xhb.dmgameapp.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.GameListBean;
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

public class GameListViewHolder extends BaseViewHolder<GameListBean.HtmlEntity> {
    @Bind(R.id.tv_game_title)
    TextView mTvGameTitle;
    @Bind(R.id.tv_game_details)
    TextView mTvGameDetails;
    @Bind(R.id.iv_game_img)
    ImageView mIvGameImg;

    public GameListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final GameListBean.HtmlEntity data) {
        super.setData(data);
        Glide.with(getContext()).load(data.getLitpic()).placeholder(R.drawable.icon_gamed_efault).error(R.drawable.icon_gamed_efault).into(mIvGameImg);
        mTvGameTitle.setText(data.getTitle());
        mTvGameDetails.setText("官方名称：" + data.getGame_trans_name() + "\n"
                + "游戏名称：" + data.getTid() + "\n"
                + "发售日期：" + data.getRelease_date() + "\n"
                + "游戏平台：" + data.getTerrace() + "\n"
                + "发行商家：" + data.getRelease_company());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameDetailsActivity.start(getContext(), data.getId(), data.getLitpic(), data.getTitle(), mTvGameDetails.getText().toString());
            }
        });
    }
}
