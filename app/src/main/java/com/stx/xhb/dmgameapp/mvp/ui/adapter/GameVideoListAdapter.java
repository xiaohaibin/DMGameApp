package com.stx.xhb.dmgameapp.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.mvp.ui.activity.WebDetailsActivity;
import com.stx.xhb.dmgameapp.data.entity.VideoListBean;

/**
 * Author : jxnk25
 * Time: 2017/10/23 0023
 * Email:xhb_199409@163.com
 * Email:xhb_199409@163.com
 * Github:https://github.com/xiaohaibin/
 * Drscribe:
 */

public class GameVideoListAdapter extends BaseQuickAdapter<VideoListBean.VideoBean, BaseViewHolder> {
    private Context context;

    public GameVideoListAdapter(Context context) {
        super(R.layout.list_item_video);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final VideoListBean.VideoBean data) {
        helper.setText(R.id.tv_video_title, data.getTitle());
        helper.setText(R.id.tv_video_time, data.getSenddate());
        helper.setText(R.id.tv_play_count, data.getClick() + "次播放");
        ImageView mIvVideoImg = helper.getView(R.id.iv_video_img);
        Glide.with(context).load(data.getVideopic()).into(mIvVideoImg);
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebDetailsActivity.start(context, data.getVideourl(),data.getDescription(),data.getVideopic());
            }
        });
    }
}
