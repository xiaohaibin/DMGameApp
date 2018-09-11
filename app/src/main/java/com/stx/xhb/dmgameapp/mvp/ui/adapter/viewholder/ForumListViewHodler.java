package com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.ForumBean;
import com.stx.xhb.dmgameapp.mvp.ui.activity.ForumListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Mr.xiao
 *         Time：2017/9/21
 *         Emil：xhb_199409@163.com
 *         Github：https://github.com/xiaohaibin/
 *         Describe：
 */

public class ForumListViewHodler extends BaseViewHolder<ForumBean> {
    @Bind(R.id.iv_forum)
    ImageView mIvForum;
    @Bind(R.id.tv_forum_title)
    TextView mTvForumTitle;
    @Bind(R.id.tv_count)
    TextView mTvCount;
    @Bind(R.id.tv_sort)
    TextView mTvSort;
    @Bind(R.id.btn_collect)
    TextView mBtnCollect;

    public ForumListViewHodler(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final ForumBean data) {
        Glide.with(getContext()).load(data.getIcon()).placeholder(R.drawable.product_default).error(R.drawable.product_default).into(mIvForum);
        mTvForumTitle.setText(data.getName());
        mTvCount.setText("今日：" + data.getTodayposts());
        mTvSort.setText("排名：" + data.getRank());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForumListActivity.start(getContext(), data.getFid(), data.getName());
            }
        });
    }
}
