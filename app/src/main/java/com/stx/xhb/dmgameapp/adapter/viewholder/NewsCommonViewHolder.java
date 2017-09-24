package com.stx.xhb.dmgameapp.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.WebDetailsActivity;
import com.stx.xhb.dmgameapp.entity.NewsListEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author：xiaohaibin
 * Time：2017/9/18
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：
 */

public class NewsCommonViewHolder extends BaseViewHolder<NewsListEntity.ChannelEntity.HtmlEntity> {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.date)
    TextView mDate;
    @Bind(R.id.iv)
    ImageView mIv;

    public NewsCommonViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final NewsListEntity.ChannelEntity.HtmlEntity data) {
        super.setData(data);
        mTitle.setText(data.getTitle());
        mDate.setText(data.getSenddate());
        List<List<String>> litpic = data.getLitpic();
        if (litpic != null && !litpic.isEmpty()) {
            List<String> stringList = litpic.get(0);
            if (stringList != null && !stringList.isEmpty()) {
                Glide.with(getContext()).load(stringList.get(0)).into(mIv);
            }
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebDetailsActivity.start(getContext(), data.getArcurl());
            }
        });
    }
}
