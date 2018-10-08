package com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.core.utils.DateUtils;
import com.stx.core.utils.ScreenUtil;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.mvp.ui.activity.NewsDetailsActivity;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：xiaohaibin
 * @Time：2017/9/18
 * @Emil：xhb_199409@163.com
 * @Github：https://github.com/xiaohaibin/
 * @Describe：
 */

public class GameAdViewHolder {

    private Context mContext;
    private final ImageView mBanner;
    private final TextView mTvDesc;
    private final TextView mTvLevel;
    private final ImageView mIvLevel;

    public GameAdViewHolder(View itemView, Context context) {
        mContext = context;
        FrameLayout flBanner = itemView.findViewById(R.id.fl_banner);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, (int) ((float) ScreenUtil.getScreenWidth(mContext) / 2.0f));
        flBanner.setLayoutParams(layoutParams);
        mBanner = itemView.findViewById(R.id.iv_banner);
        mTvDesc = itemView.findViewById(R.id.tv_description);
        mIvLevel = itemView.findViewById(R.id.iv_level);
        mTvLevel = itemView.findViewById(R.id.tv_level);
    }

    public void setData(final List<NewsPageBean.SlidesBean> bannerList) {
        NewsPageBean.SlidesBean slidesBean = bannerList.get(0);
        if (slidesBean != null) {
            Glide.with(mContext).load(slidesBean.getLitpic()).placeholder(R.drawable.default_image).error(R.drawable.default_image).into(mBanner);
            mTvDesc.setText(String.valueOf(slidesBean.getTitle() + "\n"
                    + "发售：" + DateUtils.dateFromat(slidesBean.getPubdate_at()) + "\n"
                    + "平台：" + slidesBean.getSystem()));
            mTvLevel.setText(String.valueOf(slidesBean.getScore()));
            mIvLevel.getDrawable().setLevel((int) (slidesBean.getScore() * 1000.0d));
        }
    }
}
