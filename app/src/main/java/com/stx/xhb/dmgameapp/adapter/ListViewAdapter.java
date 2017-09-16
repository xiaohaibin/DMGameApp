package com.stx.xhb.dmgameapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.core.utils.DateUtils;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.entity.ChapterListEntity;

import java.util.List;

/**
 * Created by xhb on 2016/1/19.
 * Listview的自定义适配器
 */
public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<ChapterListEntity> mChapterListItemEntities;
    private LayoutInflater mLayoutInflater;

    public ListViewAdapter(Context context, List<ChapterListEntity> chapterListItemEntities) {
        this.context = context;
        this.mChapterListItemEntities = chapterListItemEntities;
        if (mLayoutInflater == null) {
            mLayoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    @Override
    public int getCount() {
        return mChapterListItemEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return mChapterListItemEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.listview_item_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
            //设置tag
            convertView.setTag(viewHolder);
        } else {
            //获取缓存布局
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ChapterListEntity chapterListEntity = mChapterListItemEntities.get(position);
        viewHolder.title.setText(chapterListEntity.getTitle());
        //格式化时间
        String senddate = chapterListEntity.getSenddate();
        String time = DateUtils.dateFromat(senddate);
        viewHolder.date.setText(time);//文章发布时间
        viewHolder.iv.setImageResource(R.drawable.product_default);//设置默认图片
        final ImageView iv = viewHolder.iv;
        //获取到图片地址
        String litpic = chapterListEntity.getLitpic();
        //如果图片地址为空，则设置默认图片
        if (litpic == null) {
            iv.setImageResource(R.drawable.product_default);
        }
        //地址拼接
        String imageUrl = API.DMGEAME_URL + litpic;
        //下载图片，优先使用本地缓存图片
        Glide.with(context).load(imageUrl).into(iv);
        return convertView;
    }

    //创建一个ViewHolder保存converview的布局
    class ViewHolder {
        ImageView iv;
        TextView title, date;
    }
}
