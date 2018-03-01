package com.stx.xhb.dmgameapp.adapter.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.stx.core.utils.DateUtils;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.SubCommentListAdapter;
import com.stx.xhb.dmgameapp.entity.CommentsBean;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: xiaohaibin.
 * @time: 2018/3/1
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class NewsCommentViewHolder extends BaseViewHolder<CommentsBean> {

    @Bind(R.id.iv_user_img)
    ImageView mIvUserImg;
    @Bind(R.id.tv_nick_name)
    TextView mTvNickName;
    @Bind(R.id.tv_comment_time)
    TextView mTvCommentTime;
    @Bind(R.id.tv_click_good)
    TextView mTvClickGood;
    @Bind(R.id.btn_comment)
    TextView mBtnComment;
    @Bind(R.id.rv_sub_comment)
    RecyclerView mRvSubComment;
    @Bind(R.id.tv_comment_content)
    TextView mTvCommentContent;

    public NewsCommentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(CommentsBean data) {
        Glide.with(getContext()).load(data.getPassport().getImg_url()).placeholder(R.drawable.default_image).into(mIvUserImg);
        mTvNickName.setText(data.getPassport().getNickname());
        mTvCommentContent.setText(data.getContent());
        mTvCommentTime.setText(DateUtils.dateFromat(data.getCreate_time()));
        mTvClickGood.setText(String.valueOf(data.getSupport_count()));
        mBtnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("回复");
            }
        });
        if (data.getComments() != null && !data.getComments().isEmpty()) {
            mRvSubComment.setVisibility(View.VISIBLE);
            mRvSubComment.setLayoutManager(new LinearLayoutManager(getContext()));
            mRvSubComment.setNestedScrollingEnabled(false);
            mRvSubComment.setAdapter(new SubCommentListAdapter(data.getComments()));
        } else {
            mRvSubComment.setVisibility(View.GONE);
        }
    }
}
