package com.stx.xhb.dmgameapp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.stx.xhb.dmgameapp.utils.ScreenUtil;


/**
 * Author:jxnk25
 * <p>
 * CrateTime:2016-12-07 18:21
 * <p>
 * Description:RecyclerView Adapter基类
 */
public abstract class BaseAdapter<ViewHolder extends BaseAdapter.Item> extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener {

    private Context context;

    public BaseAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //监听item点击事件
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
        bindItem(holder, position);
    }

    /**
     * 绑定item
     *
     * @param item
     * @param position
     */
    public abstract void bindItem(ViewHolder item, int position);

    /**
     * 重载此方法处理item点击事件
     *
     * @param position
     */
    public abstract void onItemClick(int position);

    @Override
    public void onClick(View view) {
        onItemClick((Integer) view.getTag());
    }

    /**
     * Author:jxnk25
     * <p>
     * CrateTime:2016-12-07 19:08
     * <p>
     * Description:RecyclerView Item基类，主要设置一些布局参数
     */
    public static class Item extends RecyclerView.ViewHolder {

        public Item(Context context, View itemView) {
            super(itemView);
            itemView.setLayoutParams(getItemParams(context));
        }

        public ViewGroup.LayoutParams getItemParams(Context context) {
            return new ViewGroup.LayoutParams(ScreenUtil.getScreenWidth(context), ViewGroup.LayoutParams.WRAP_CONTENT);
        }

    }

}