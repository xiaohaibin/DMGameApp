package com.stx.xhb.dmgameapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.ChapterCommentListItem;
import com.stx.xhb.dmgameapp.utils.DateUtils;

import java.util.List;

/**
 * Created by xhb on 2016/1/22.
 * 评论listview的适配器
 */
public class CommentListviewAdapter extends BaseAdapter {
    private Context context;
    private List<ChapterCommentListItem.DescriptionEntity.DataEntity> dataEntities;

    public CommentListviewAdapter(Context context, List<ChapterCommentListItem.DescriptionEntity.DataEntity> dataEntities) {
        this.context = context;
        this.dataEntities = dataEntities;
    }

    @Override
    public int getCount() {
        return dataEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return dataEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHodler viewHodler = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_lv_comment, parent, false);
            viewHodler = new ViewHodler();
            viewHodler.tv_username = (TextView) convertView.findViewById(R.id.tv_username);
            viewHodler.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            viewHodler.tv_floor = (TextView) convertView.findViewById(R.id.tv_floor);
            viewHodler.tv_comment = (TextView) convertView.findViewById(R.id.tv_comment);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        //给缓存布局设置新数据
        ChapterCommentListItem.DescriptionEntity.DataEntity entity = dataEntities.get(position);
        //判断是否是匿名发表
        if ("????".equals(entity.getUsername())) {
            viewHodler.tv_username.setText("匿名发表");
        } else {
            viewHodler.tv_username.setText(entity.getUsername());
        }
        String date = DateUtils.dateFromat(entity.getDtime());
        viewHodler.tv_date.setText(date);
        viewHodler.tv_floor.setText(entity.getFloor());
        viewHodler.tv_comment.setText(entity.getMsg());
        return convertView;
    }

    //创建一个ViewHolder保存缓存布局
    class ViewHodler {
        TextView tv_username, tv_date, tv_floor, tv_comment;
    }
}
