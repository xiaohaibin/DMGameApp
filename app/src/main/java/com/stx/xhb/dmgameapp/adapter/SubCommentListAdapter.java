package com.stx.xhb.dmgameapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.CommentsBean;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: xiaohaibin.
 * @time: 2018/3/1
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class SubCommentListAdapter extends RecyclerView.Adapter<SubCommentListAdapter.ViewHolder> {

    private List<CommentsBean> mCommentsBeanList;

    public SubCommentListAdapter(List<CommentsBean> commentsBeanList) {
        mCommentsBeanList = commentsBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sub_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommentsBean commentsBean = mCommentsBeanList.get(position);
        holder.mTvNickName.setText(commentsBean.getPassport().getNickname());
        holder.mTvCommentContent.setText(commentsBean.getContent());
        holder.mBtnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("回复");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCommentsBeanList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_nick_name)
        TextView mTvNickName;
        @Bind(R.id.btn_comment)
        TextView mBtnComment;
        @Bind(R.id.tv_comment_content)
        TextView mTvCommentContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
