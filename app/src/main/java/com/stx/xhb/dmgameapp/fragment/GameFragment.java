package com.stx.xhb.dmgameapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.classic.common.MultipleStatusView;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.activities.GameDetailActivity;
import com.stx.xhb.dmgameapp.adapter.GridViewAdapter;
import com.stx.xhb.dmgameapp.entity.GameListItem;
import com.stx.xhb.dmgameapp.utils.API;
import com.stx.xhb.dmgameapp.utils.JsonUtils;
import com.stx.xhb.dmgameapp.utils.NetUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.loadingviewfinal.GridViewFinal;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;

/**
 * 视频的Fragment
 */
public class GameFragment extends Fragment implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    //游戏类型集合
    private static final String[] GAME_NAME = new String[]{
            "游戏", "动作", "射击", "角色扮演", "养成", "益智", "即时策略", "策略", "体育", "模拟经营", "赛车", "冒险"
    };
    //游戏typeid
    private static final int[] GAME_TYPE_ID = new int[]{
            179, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192
    };
    @Bind(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @Bind(R.id.multiplestatusview)
    MultipleStatusView multiplestatusview;
    private View view;
    private GridViewFinal game_grid;
    private Spinner sp;
    private List<GameListItem> gameListItems = new ArrayList<>();
    //GridView的适配器
    private GridViewAdapter gridViewAdapter;
    private int typeid = 179;
    private List<GameListItem> data;
    private int currentPage = 1;//当前页
    private final int LIMIT = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_game, container, false);
        ButterKnife.bind(this, view);
        initView();
        setLstener();
        setAdapter();
        setSwipeRefreshInfo();
        return view;

    }

    //初始化控件
    private void initView() {
        //隐藏toolbar menu控件
        ImageButton main_action_menu = (ImageButton) view.findViewById(R.id.main_action_menu);
        main_action_menu.setVisibility(View.GONE);
        TextView tv_title = (TextView) view.findViewById(R.id.title);
        tv_title.setText("游戏");
        sp = (Spinner) view.findViewById(R.id.game_spinner);
        game_grid = (GridViewFinal) view.findViewById(R.id.content_view);
    }

    //设置适配器
    private void setAdapter() {
        //实例化适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, GAME_NAME);
        gridViewAdapter = new GridViewAdapter(getActivity(), gameListItems);
        //设置适配器
        sp.setAdapter(adapter);
        game_grid.setAdapter(gridViewAdapter);

    }

    //设置事件监听
    private void setLstener() {
        //spinner的点击事件
        sp.setOnItemSelectedListener(this);
        //Gridview的点击事件
        game_grid.setOnItemClickListener(this);
        //点击重试
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadData(1,typeid);
            }
        });
    }

    //spinner的事件监听
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        typeid = GAME_TYPE_ID[position];
        downloadData(1, typeid);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //加载网络数据
    private void downloadData(final int page, int id) {
        String strUrl = String.format(API.GAME_URL, id, page);
        x.http().get(new RequestParams(strUrl), new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                multiplestatusview.showContent();
                //获取到json数据
                String json = new String(result);
                //由于游戏列表的json数据格式有误，需要进行截取
                //获取到“ {” 开始的位置
                int index = json.indexOf("{");
                //从“{” 开始的位置进行截取
                String strjson = json.substring(index, json.length());
                //开始json解析
                data = JsonUtils.parseGameJson(strjson);
                if (page == 1) {
                    gameListItems.clear();
                }
                currentPage = page + 1;
                if (data != null) {
                    gameListItems.addAll(data);
                }else {
                    multiplestatusview.showEmpty();
                }
                if (data.size() < LIMIT) {
                    game_grid.setHasLoadMore(false);
                } else {
                    game_grid.setHasLoadMore(true);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (NetUtils.isNetConnected(getActivity())) {
                    multiplestatusview.showError();
                } else {
                    multiplestatusview.showNoNetwork();
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (page == 1) {
                    ptrLayout.onRefreshComplete();
                } else {
                    game_grid.onLoadMoreComplete();
                }

                gridViewAdapter.notifyDataSetChanged();
            }
        });
    }

    //gridview的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String game_ID = gameListItems.get(position).getId();//获取游戏id
        String typeid = gameListItems.get(position).getTypeid();//获取游戏分类id
        Bundle bundle = new Bundle();
        bundle.putString("id", game_ID);
        bundle.putString("typeid", typeid);
        //跳转到游戏详情界面
        Intent intent = new Intent(getActivity(), GameDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setSwipeRefreshInfo() {
        ptrLayout.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                multiplestatusview.showLoading();
                downloadData(1, typeid);
            }

            //判断是否可以下拉刷新
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return !isChildScrollUp();
            }
        });

        ptrLayout.setLastUpdateTimeRelateObject(this);
        game_grid.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                downloadData(currentPage, typeid);
            }
        });
        game_grid.setNoLoadMoreHideView(true);
        ptrLayout.autoRefresh();
    }

    /**
     * 判断是否滑动到顶端
     *
     * @return
     */
    public boolean isChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (game_grid instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) game_grid;
                return absListView.getChildCount() > 0 &&
                        (absListView.getFirstVisiblePosition() > 0 ||
                                absListView.getChildAt(0).getTop() < absListView.getPaddingTop());

            } else {
                return ViewCompat.canScrollVertically(game_grid, -1) || game_grid.getScrollY() > 0;
            }

        } else {

            return ViewCompat.canScrollVertically(game_grid, -1);

        }

    }
}
