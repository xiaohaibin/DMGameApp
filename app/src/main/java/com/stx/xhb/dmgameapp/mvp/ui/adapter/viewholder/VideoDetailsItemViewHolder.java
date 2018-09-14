package com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.VideoListBean;
import com.stx.xhb.dmgameapp.mvp.ui.activity.NewsDetailsActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author：xiaohaibin
 * @time：2017/9/18
 * @emil：xhb_199409@163.com
 * @Github：https://github.com/xiaohaibin/
 * @Describe：
 */
public class VideoDetailsItemViewHolder extends BaseViewHolder<VideoListBean.VideoBean> {

    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.date)
    TextView mDate;
    @Bind(R.id.tv_play_count)
    TextView mTvPlayCount;
    @Bind(R.id.iv)
    ImageView mIv;

    public VideoDetailsItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final VideoListBean.VideoBean data) {
        mTitle.setText(data.getTitle());
        mDate.setText(data.getSenddate());
        mTvPlayCount.setText(data.getClick() + "次播放");
        Glide.with(getContext()).load(data.getVideopic()).into(mIv);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailsActivity.start(getContext(),data.getVideourl(),data.getId(),data.getTitle(),data.getVideopic(),true);
            }
        });
    }
}
