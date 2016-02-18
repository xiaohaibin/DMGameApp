package com.stx.xhb.dmgameapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.CommentListviewAdapter;
import com.stx.xhb.dmgameapp.entity.ChapterCommentListItem;
import com.stx.xhb.dmgameapp.utils.HttpAdress;
import com.stx.xhb.dmgameapp.utils.HttpUtils;
import com.stx.xhb.dmgameapp.utils.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评论详情界面
 */
public class CommentActivity extends ActionBarActivity implements View.OnClickListener, HttpUtils.OnFetchDataListener, SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener {
    private ListView lv_comment;
    private Toolbar toolbar;
    private Button comment_btn;
    private EditText ed_comment;
    private TextView tv_empty;
    private List<ChapterCommentListItem.DescriptionEntity.DataEntity> dataEntities=new ArrayList<>();
    private CommentListviewAdapter adapter;
    private int currentPage=1;//当前页
    private boolean isBottom;//是否到底部的标记
    private boolean isLoadData=false;//判断是否已经在加载数据
    private SwipeRefreshLayout refreshLayout;
    private List<ChapterCommentListItem.DescriptionEntity.DataEntity> data;
    private ProgressDialog pd;//进度对话框
    private String id;
    private String typeid;
    private String url;
    private View mFootView;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initWindow();
        initView();
        initData();
        setAdapter();
        setListener();
    }

    //初始化窗体布局
    private void initWindow() {
        SystemBarTintManager tintManager;
        //由于沉浸式状态栏需要在Android4.4.4以上才能使用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorBackground));
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    //获取控件
    private void initView() {
        if (mInflater == null) {
            mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        lv_comment= (ListView) findViewById(R.id.lv_comment);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        comment_btn= (Button) findViewById(R.id.comment_btn);
        ed_comment= (EditText) findViewById(R.id.ed_comment);
        tv_empty= (TextView) findViewById(R.id.tv_empty);
        //2.替代
        setSupportActionBar(toolbar);
        //加载背景颜色
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorBackground)));
        //设置显示返回上一级的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置标题
        getSupportActionBar().setTitle("评论");
        //设置标题栏字体颜色
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.backup));

        refreshLayout= (SwipeRefreshLayout) findViewById(R.id.comment_refresh);
        //设置下拉刷新控件的颜色
        refreshLayout.setColorSchemeColors(Color.BLUE, Color.YELLOW);
        //添加底部控件
        mFootView = mInflater.inflate(R.layout.listview_footer, null);
        mFootView.setVisibility(View.GONE);//默认隐藏
        lv_comment.addFooterView(mFootView,null,false);
    }

    //初始化数据
    private void initData() {
        //获取到文章id
        id = getIntent().getStringExtra("id");
        typeid = getIntent().getStringExtra("typeid");
        //评论列表请求地址
        url = String.format(HttpAdress.COMMENT_URL, id,currentPage);
        HttpUtils.downLoadData(url,this);
    }

    //设置适配器
    private void setAdapter() {
            //实例化适配器
            adapter=new CommentListviewAdapter(this,dataEntities);
            //设置适配器
            lv_comment.setAdapter(adapter);
            //当listview中没有数据，就显示tv_empty的内容
            lv_comment.setEmptyView(tv_empty);
    }
    //设置事件监听
    private void setListener() {
            toolbar.setNavigationOnClickListener(this);
            refreshLayout.setOnRefreshListener(this);
            lv_comment.setOnScrollListener(this);
            comment_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String commentContent = ed_comment.getText().toString();//获取到输入框中的评论内容
                    if (TextUtils.isEmpty(commentContent)){
                        Toast.makeText(CommentActivity.this, "评论内容不能为空！", Toast.LENGTH_SHORT).show();
                        return;
                    }
//                   String id = getIntent().getStringExtra("id");//获取到文章的id，需要传到评论列表中
                    final Map<String,String> params=new HashMap<String, String>();
                    params.put("aid",id);
                    params.put("msg", commentContent);
                    HttpUtils.submitPostData(params, "utf-8", new HttpUtils.OnFetchResponseListener() {
                        @Override
                        public void OnFechResponse(String reponse) {
                            Log.i("responseResult====>", reponse);
                            //json解析，获取到响应状态码
                            try {
                                JSONObject object = new JSONObject(reponse);
                                String code = object.getString("code");//响应状态码
                                if ("1".equals(code)) {
                                    ed_comment.setText("");//评论成功后，清空输入框
                                    Toast.makeText(CommentActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                                    //重新加载数据
                                    url = String.format(HttpAdress.COMMENT_URL, id,currentPage);
                                    HttpUtils.downLoadData(url, new HttpUtils.OnFetchDataListener() {
                                        @Override
                                        public void OnFetch(String url, byte[] result) {
                                            String json = new String(result);
                                            ChapterCommentListItem chapterCommentListItem = new Gson().fromJson(json, ChapterCommentListItem.class);
                                            data = chapterCommentListItem.getDescription().getData();
                                            if (chapterCommentListItem!=null&& data !=null){
                                                dataEntities.clear();
                                                dataEntities.addAll(data);
                                                adapter.notifyDataSetChanged();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(CommentActivity.this, "评论失败", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });

                }
            });
    }
    //toolbar的返回事件
    @Override
    public void onClick(View v) {
        finish();
    }
    //网络数据回调方法
    @Override
    public void OnFetch(String url, byte[] result) {
        String json = new String(result);
        ChapterCommentListItem chapterCommentListItem = new Gson().fromJson(json, ChapterCommentListItem.class);
        data = chapterCommentListItem.getDescription().getData();
        if (chapterCommentListItem!=null&& data !=null){
//            dataEntities.clear();
            dataEntities.addAll(data);
            adapter.notifyDataSetChanged();
        }

    }
    //下拉刷新方法
    @Override
    public void onRefresh() {
           url = String.format(HttpAdress.COMMENT_URL, id,currentPage);
           HttpUtils.downLoadData(url, this);
           refreshLayout.setRefreshing(false);
    }

    //////////////////////////////listview的滚动监听事件/////////////////////////////
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //isBottom是自定义的boolean变量，用于标记是否滑动到底部
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && isBottom && !isLoadData) {
            //如果加载到底部则加载下一页的数据显示到listview中
            currentPage++;
            //加载新数据
            isLoadData = true;//将加载数据的状态设置为true
            //评论列表请求地址
            url = String.format(HttpAdress.COMMENT_URL, id,currentPage);
            mFootView.setVisibility(View.VISIBLE);//设置进度条出现
            HttpUtils.downLoadData(url, new HttpUtils.OnFetchDataListener() {
                @Override
                public void OnFetch(String url, byte[] result) {
                    String json = new String(result);
                    ChapterCommentListItem chapterCommentListItem = new Gson().fromJson(json, ChapterCommentListItem.class);
                    data = chapterCommentListItem.getDescription().getData();
                    if (chapterCommentListItem!=null&& data !=null){
                        mFootView.setVisibility(View.GONE);//设置隐藏进度条
                        dataEntities.addAll(data);
                        adapter.notifyDataSetChanged();
                        isLoadData=false;//下载完数据之后，将状态设为false
                    }
                }
            });
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //若第一个可见的item的下标+可见的条目的数量=当前listview中总的条目数量，则说明已经到达底部
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            isBottom = true;
        } else {
            isBottom = false;
        }
    }
}
