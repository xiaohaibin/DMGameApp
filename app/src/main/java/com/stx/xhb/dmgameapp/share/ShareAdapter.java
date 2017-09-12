package com.stx.xhb.dmgameapp.share;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.base.BaseAdapter;
import com.stx.xhb.dmgameapp.utils.ScreenUtil;


/**
 * Author:xiaohaibin
 * <p>
 * CrateTime:2016-12-15 11:11
 * <p>
 * Description:分享适配器
 */
public abstract class ShareAdapter extends BaseAdapter<ShareAdapter.Item> {

    public ShareAdapter(Context context) {
        super(context);
    }

    @Override
    public Item onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Item(getContext(), LayoutInflater.from(getContext()).inflate(R.layout.list_item_share, null));
    }

    public static class Item extends BaseAdapter.Item {

        public ImageView iconImageView;
        public TextView nameTextView;

        public Item(Context context, View itemView) {
            super(context, itemView);
            iconImageView = (ImageView) itemView.findViewById(R.id.share_icon);
            nameTextView = (TextView) itemView.findViewById(R.id.share_name);
        }

        @Override
        public ViewGroup.LayoutParams getItemParams(Context context) {
            return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.dip2px(context, 96));
        }
    }

}
