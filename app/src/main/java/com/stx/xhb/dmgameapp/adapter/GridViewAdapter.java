package com.stx.xhb.dmgameapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.cache.ImageLoader;
import com.stx.xhb.dmgameapp.entity.GameListItem;
import com.stx.xhb.dmgameapp.utils.HttpAdress;

import java.util.List;

/**
 * Created by xhb on 2016/1/21.
 * GridView的适配器
 */
public class GridViewAdapter extends BaseAdapter{
    private Context context;
    private List<GameListItem> gameListItems;

    public GridViewAdapter(Context context, List<GameListItem> gameListItems) {
        this.context = context;
        this.gameListItems = gameListItems;
    }

    @Override
    public int getCount() {
        return gameListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return gameListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        ViewHodler viewHodler=null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.grid_item_layout,parent,false);
            viewHodler=new ViewHodler();
            viewHodler.game_iv= (ImageView) convertView.findViewById(R.id.game_iv);
            viewHodler.game_tv= (TextView) convertView.findViewById(R.id.tv_game);
            viewHodler.game_id= (TextView) convertView.findViewById(R.id.game_id);
            viewHodler.game_typeid= (TextView) convertView.findViewById(R.id.game_typeid);
            convertView.setTag(viewHodler);
        }else {
            viewHodler= (ViewHodler) convertView.getTag();
        }
        //给缓存布局加载新的数据
        GameListItem gameListItem = gameListItems.get(position);
        viewHodler.game_tv.setText(gameListItem.getTitle());
        viewHodler.game_id.setText(gameListItem.getId());
        viewHodler.game_typeid.setText(gameListItem.getTypeid());
        viewHodler.game_iv.setImageResource(R.drawable.gamedefault);//设置默认图片
        ImageView game_iv = viewHodler.game_iv;
        //获取到图片地址
        String litpic = gameListItem.getLitpic();
        //如果图片地址为空，则设置默认图片
        if (litpic==null){
            game_iv.setImageResource(R.drawable.gamedefault);
        }
        //地址拼接
        String imageUrl= HttpAdress.DMGEAME_URL+litpic;
        //将图片与imageview绑定在一起
        game_iv.setTag(imageUrl);
        //下载图片,优先使用本地缓存图片
        ImageLoader.getInstance().display(game_iv,imageUrl,R.drawable.gamedefault);

        return convertView;
    }
    //创建ViewHolder保存缓存布局
    class ViewHodler{
          ImageView game_iv;
          TextView game_tv,game_id,game_typeid;
    }
}
