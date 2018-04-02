package com.stx.xhb.dmgameapp.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.NewsListBean;
import com.stx.xhb.dmgameapp.mvp.ui.activity.NewsDetailsActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author：xiaohaibin
 * @time：2017/9/18
 * @emil：xhb_199409@163.com
 * @Github：https://github.com/xiaohaibin/
 * @Describe：
 */

public class NewsCommonViewHolder extends BaseViewHolder<NewsListBean.ChannelEntity.HtmlEntity> {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.date)
    TextView mDate;
    @Bind(R.id.iv)
    ImageView mIv;
    private String imgUrl = "";

    public NewsCommonViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final NewsListBean.ChannelEntity.HtmlEntity data) {
        mTitle.setText(data.getTitle());
        mDate.setText(data.getSenddate());
        List<List<String>> litpic = data.getLitpic();
        if (litpic != null && !litpic.isEmpty()) {
            List<String> stringList = litpic.get(0);
            if (stringList != null && !stringList.isEmpty()) {
                imgUrl = stringList.get(0);
                Glide.with(getContext()).load(stringList.get(0)).into(mIv);
            }
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailsActivity.start(getContext(),data.getArcurl(),data.getId(),data.getTitle(),imgUrl,false);
            }
        });
    }
}
