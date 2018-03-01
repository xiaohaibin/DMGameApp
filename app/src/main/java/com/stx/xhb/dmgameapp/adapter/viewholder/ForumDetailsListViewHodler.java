package com.stx.xhb.dmgameapp.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.WebDetailsActivity;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.entity.ForumListBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Mr.xiao
 * Time: 2017/11/1 0001
 * Email:xhb_199409@163.com
 * Email:xhb_199409@163.com
 * Github:https://github.com/xiaohaibin/
 * Drscribe:
 */

public class ForumDetailsListViewHodler extends BaseViewHolder<ForumListBean.HtmlBean> {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.date)
    TextView mDate;
    @Bind(R.id.author)
    TextView tvAuthor;
    @Bind(R.id.tv_count)
    TextView tvCount;
    @Bind(R.id.iv)
    ImageView mIv;
    private String imgUrl = "";

    public ForumDetailsListViewHodler(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final ForumListBean.HtmlBean data) {
        mTitle.setText(data.getSubject());
        tvAuthor.setText(data.getAuthor());
        mDate.setText(data.getDateline());
        tvCount.setText(data.getReplies()+"评论");
        List<String> litpic = data.getLitpic();
        if (litpic != null && !litpic.isEmpty()) {
            imgUrl = litpic.get(0);
            Glide.with(getContext()).load(litpic.get(0)).into(mIv);
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebDetailsActivity.start(getContext(), String.format(API.GET_3DM_WEB_FORUM, data.getTid()), data.getSubject(), imgUrl);
            }
        });
    }
}


