package com.stx.xhb.dmgameapp.mvp.ui.adapter.viewholder;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.stx.core.utils.DateUtils;
import com.stx.core.utils.ScreenUtil;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.NewsPageBean;
import com.stx.xhb.dmgameapp.data.entity.SaleGameBean;
import com.stx.xhb.dmgameapp.mvp.ui.activity.WebDetailsActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author：xiaohaibin
 * @time：2017/9/18
 * @emil：xhb_199409@163.com
 * @Github：https://github.com/xiaohaibin/
 * @Describe： 游戏通用
 */

public class GameCommonViewHolder extends BaseViewHolder<SaleGameBean.ListBean> {
    @Bind(R.id.iv_game_img)
    ImageView mIvGameImg;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_desc)
    TextView mTvDesc;
    @Bind(R.id.ll_label)
    LinearLayout mLlLabel;

    public GameCommonViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final SaleGameBean.ListBean data) {
        Glide.with(getContext()).load(data.getLitpic()).into(mIvGameImg);
        mTvTitle.setText(data.getTitle());
        mTvDesc.setText("类型：" + data.getType() + "\n"
                + "发售：" + DateUtils.dateFromat(data.getPubdate_at())+"\n"
                + "平台：" + data.getSystem());
        mLlLabel.removeAllViews();
        List<SaleGameBean.ListBean.LabelsBean> labels = data.getLabels();
        for (int i = 0; i < labels.size(); i++) {
            mLlLabel.addView(addText(labels.get(i).getName()));
        }
    }

    private TextView addText(String text) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.rightMargin = ScreenUtil.dip2px(getContext(), 10);
        layoutParams.gravity= Gravity.CENTER;
        TextView tv = new TextView(getContext());
        tv.setLayoutParams(layoutParams);
        tv.setText(text);
        tv.setTextColor(ContextCompat.getColor(getContext(), R.color.color_008cf0));
        tv.setBackgroundResource(R.drawable.shape_blue_storke);
        tv.setTextSize(8);
        return tv;
    }
}
