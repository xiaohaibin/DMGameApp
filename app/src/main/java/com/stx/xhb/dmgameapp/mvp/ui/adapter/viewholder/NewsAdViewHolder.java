package com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.core.utils.ScreenUtil;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.mvp.ui.activity.NewsDetailsActivity;
import com.stx.xhb.dmgameapp.mvp.ui.activity.WebDetailsActivity;
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

public class NewsAdViewHolder {

    private Context mContext;
    private final XBanner mBanner;

    public NewsAdViewHolder(View itemView, Context context) {
        mContext = context;
        FrameLayout flBanner = itemView.findViewById(R.id.fl_banner);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, (int) ((float) ScreenUtil.getScreenWidth(mContext) / 2.0f));
        flBanner.setLayoutParams(layoutParams);
        mBanner = itemView.findViewById(R.id.xbanner);
    }

    public void setData(final List<NewsPageBean.SlidesBean> bannerList) {
        List<String> tips = new ArrayList<String>();
        for (int i = 0; i < bannerList.size(); i++) {
            tips.add(bannerList.get(i).getTitle());
        }
        mBanner.setData(bannerList, tips);
        mBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(mContext).load(bannerList.get(position).getLitpic()).placeholder(R.drawable.default_image).error(R.drawable.default_image).into((ImageView) view);
            }
        });
        mBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                NewsPageBean.SlidesBean slidesBean = bannerList.get(position);
                NewsDetailsActivity.start(mContext,slidesBean.getWebviewurl(),slidesBean.getArcurl(),slidesBean.getTitle(),slidesBean.getLitpic());
            }
        });
    }
}
