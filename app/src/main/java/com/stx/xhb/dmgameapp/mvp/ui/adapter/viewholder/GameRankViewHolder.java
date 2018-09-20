package com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder;

import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.stx.core.utils.DateUtils;
import com.stx.core.utils.ScreenUtil;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.GameRankBean;
import com.stx.xhb.dmgameapp.data.entity.SaleGameBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author：xiaohaibin
 * @time：2017/9/18
 * @emil：xhb_199409@163.com
 * @Github：https://github.com/xiaohaibin/
 * @Describe： 游戏通用
 */

public class GameRankViewHolder extends BaseViewHolder<GameRankBean.ListBean> {
    @Bind(R.id.iv_game_img)
    ImageView mIvGameImg;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_desc)
    TextView mTvDesc;

    public GameRankViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final GameRankBean.ListBean data) {
        Glide.with(getContext()).load(data.getLitpic()).into(mIvGameImg);
        mTvTitle.setText(data.getTitle());
        mTvDesc.setText("类型：" + data.getType() + "\n"
                + "发售：" + DateUtils.dateFromat(data.getPubdate_at())+"\n"
                + "平台：" + data.getSystem()+"\n人气值："+data.getClick());
    }
}
