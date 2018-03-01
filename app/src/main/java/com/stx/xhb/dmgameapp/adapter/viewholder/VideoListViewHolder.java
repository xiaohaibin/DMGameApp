package com.stx.xhb.dmgameapp.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.WebDetailsActivity;
import com.stx.xhb.dmgameapp.entity.VideoListBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Author：xiaohaibin
 * @Time：2017/9/19
 * @Emil：xhb_199409@163.com
 * @Github：https://github.com/xiaohaibin/
 * @Describe：
 */
public class VideoListViewHolder extends BaseViewHolder<VideoListBean.VideoBean> {

    @Bind(R.id.iv_video_img)
    ImageView mIvVideoImg;
    @Bind(R.id.tv_video_title)
    TextView mTvVideoTitle;
    @Bind(R.id.tv_video_time)
    TextView mTvVideoTime;
    @Bind(R.id.tv_play_count)
    TextView mTvPlayCount;

    public VideoListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final VideoListBean.VideoBean data) {
        super.setData(data);
        mTvVideoTitle.setText(data.getTitle());
        mTvVideoTime.setText(data.getSenddate());
        mTvPlayCount.setText(data.getClick() + "次播放");
        Glide.with(getContext()).load(data.getVideopic()).into(mIvVideoImg);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebDetailsActivity.start(getContext(), data.getVideourl(), data.getDescription(), data.getVideopic());
            }
        });
    }
}
