package com.stx.xhb.dmgameapp.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.activities.GameDetailActivity;
import com.stx.xhb.dmgameapp.adapter.GridViewAdapter;
import com.stx.xhb.dmgameapp.entity.GameListItem;
import com.stx.xhb.dmgameapp.utils.HttpAdress;
import com.stx.xhb.dmgameapp.utils.HttpUtils;
import com.stx.xhb.dmgameapp.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频的Fragment
 */
public class GameFragment extends Fragment implements HttpUtils.OnFetchDataListener, AdapterView.OnItemSelectedListener, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    //游戏类型集合
    private static final String[] GAME_NAME = new String[]{
            "游戏", "动作", "射击", "角色扮演", "养成", "益智", "即时策略", "策略", "体育", "模拟经营", "赛车", "冒险"
    };
    //游戏typeid
    private static final int[] GAME_TYPE_ID = new int[]{
            179, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192
    };
    private View view;
    private GridView game_grid;
    private Spinner sp;
    private List<GameListItem> gameListItems = new ArrayList<>();
    private SwipeRefreshLayout refreshLayout;
    //GridView的适配器
    private GridViewAdapter gridViewAdapter;
    private int typeid = 179;
    private List<GameListItem> data;
    private int currentPage = 1;//当前页
    private String game_url;//游戏请求地址
    private TextView tv_empty;
    private LayoutInflater mInflater;
    private View mFootView;//底部控件
    private boolean isBottom;//是否滑动到底部了
    private boolean isLoadData;//是否在加载数据

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_game, container, false);
        initView();
        setLstener();
        setAdapter();
        return view;
    }

    //初始化控件
    private void initView() {
        TextView tv_title = (TextView) view.findViewById(R.id.title);
        tv_title.setText("游戏");
        sp = (Spinner) view.findViewById(R.id.game_spinner);
        game_grid = (GridView) view.findViewById(R.id.game_grid);
        tv_empty = (TextView) view.findViewById(R.id.tv_empty);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        //设置下拉刷新控件的颜色
        refreshLayout.setColorSchemeColors(Color.BLUE, Color.YELLOW);
    }

    //初始化数据
    private void initData() {

        //游戏列表地址
        game_url = String.format(HttpAdress.GAME_URL, typeid, currentPage);
        HttpUtils.downLoadData(game_url, this);
    }

    //设置适配器
    private void setAdapter() {
        //实例化适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, GAME_NAME);
        gridViewAdapter = new GridViewAdapter(getContext(), gameListItems);
        //设置适配器
        sp.setAdapter(adapter);
        game_grid.setAdapter(gridViewAdapter);
        //当没有数据时，展示tv_empty的内容
        game_grid.setEmptyView(tv_empty);

    }

    //设置事件监听
    private void setLstener() {
        //spinner的点击事件
        sp.setOnItemSelectedListener(this);
        //设置下拉刷新事件监听
        refreshLayout.setOnRefreshListener(this);
        //Gridview的点击事件
        game_grid.setOnItemClickListener(this);
        //GridView的滚动事件监听
        game_grid.setOnScrollListener(this);
    }

    //网络下载数据的回调
    @Override
    public void OnFetch(String url, byte[] result) {
        //获取到json数据
        String json = new String(result);
        //由于游戏列表的json数据格式有误，需要进行截取
        //获取到“ {” 开始的位置
        int index = json.indexOf("{");
        //从“{” 开始的位置进行截取
        String strjson = json.substring(index, json.length());
        //开始json解析
        data = JsonUtils.parseGameJson(strjson);
        if (data != null) {
            gameListItems.clear();
            gameListItems.addAll(data);
            gridViewAdapter.notifyDataSetChanged();
        }
    }

    //spinner的事件监听
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        typeid = GAME_TYPE_ID[position];
        initData();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //下拉刷新的事件方法
    @Override
    public void onRefresh() {

        //加载新数据
        game_url = String.format(HttpAdress.GAME_URL, typeid, currentPage);
        HttpUtils.downLoadData(game_url, new HttpUtils.OnFetchDataListener() {
            @Override
            public void OnFetch(String url, byte[] result) {
                //获取到json数据
                String json = new String(result);
                //由于游戏列表的json数据格式有误，需要进行截取
                //获取到“ {” 开始的位置
                int index = json.indexOf("{");
                //从“{” 开始的位置进行截取
                String strjson = json.substring(index, json.length());
                //开始json解析
                data = JsonUtils.parseGameJson(strjson);
                if (data != null) {
                    gameListItems.clear();
                    gameListItems.addAll(data);
                    gridViewAdapter.notifyDataSetChanged();
                }
            }
        });
        refreshLayout.setRefreshing(false);
    }

    //gridview的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String game_ID = data.get(position).getId();//获取游戏id
        String typeid = data.get(position).getTypeid();//获取游戏分类id
        Bundle bundle = new Bundle();
        bundle.putString("id", game_ID);
        bundle.putString("typeid", typeid);
        //跳转到游戏详情界面
        Intent intent = new Intent(getContext(), GameDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    ////////////////////////////////GridView的滚动事件监听方法////////////////////////

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //isBottom是自定义的boolean变量，用于标记是否滑动到底部
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && isBottom && !isLoadData) {
            //开始加载下一页的数据
            currentPage++;
            //加载新数据
            game_url = String.format(HttpAdress.GAME_URL, typeid, currentPage);
            HttpUtils.downLoadData(game_url, new HttpUtils.OnFetchDataListener() {
                @Override
                public void OnFetch(String url, byte[] result) {
                    //获取到json数据
                    String json = new String(result);
                    //由于游戏列表的json数据格式有误，需要进行截取
                    //获取到“ {” 开始的位置
                    int index = json.indexOf("{");
                    //从“{” 开始的位置进行截取
                    String strjson = json.substring(index, json.length());
                    //开始json解析
                    data = JsonUtils.parseGameJson(strjson);
                    if (data != null) {
                        gameListItems.addAll(data);
                        gridViewAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //若第一个可见的item的下标+可见的条目的数量=当前listview中总的条目数量，则说明已经到达底部
        isBottom = firstVisibleItem + visibleItemCount == totalItemCount;
    }
}
