package com.stx.xhb.dmgameapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.NewsListEntity;

import java.util.List;

/**
 * Created by xhb on 2016/1/19.
 * Listview的自定义适配器
 */
public class NewsListAdapter extends BaseAdapter {

    private Context mContext;
    private List<NewsListEntity.ChannelEntity.HtmlEntity> mChapterListItemEntities;
    private LayoutInflater mLayoutInflater;

    public NewsListAdapter(Context context, List<NewsListEntity.ChannelEntity.HtmlEntity> chapterListItemEntities) {
        this.mContext = context;
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
        NewsListEntity.ChannelEntity.HtmlEntity chapterListEntity = mChapterListItemEntities.get(position);
        viewHolder.title.setText(chapterListEntity.getTitle());
        //格式化时间
        String senddate = chapterListEntity.getSenddate();
        viewHolder.date.setText(senddate);//文章发布时间
        viewHolder.iv.setImageResource(R.drawable.product_default);//设置默认图片
        final ImageView iv = viewHolder.iv;
        //获取到图片地址
        //如果图片地址为空，则设置默认图片
        if (chapterListEntity.getLitpic() != null && !chapterListEntity.getLitpic().isEmpty()) {
            List<String> stringList = chapterListEntity.getLitpic().get(0);
            if (stringList != null && !stringList.isEmpty()) {
                String imageUrl = stringList.get(0);
                Glide.with(mContext).load(imageUrl).into(iv);
            } else {
                iv.setImageResource(R.drawable.product_default);
            }
        } else {
            iv.setImageResource(R.drawable.product_default);
        }
        return convertView;
    }

    //创建一个ViewHolder保存converview的布局
    class ViewHolder {
        ImageView iv;
        TextView title, date;
    }
}
