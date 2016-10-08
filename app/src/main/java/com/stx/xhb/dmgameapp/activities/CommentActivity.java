package com.stx.xhb.dmgameapp.activities;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
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

import com.classic.common.MultipleStatusView;
import com.google.gson.Gson;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.CommentListviewAdapter;
import com.stx.xhb.dmgameapp.entity.ChapterCommentListItem;
import com.stx.xhb.dmgameapp.utils.API;
import com.stx.xhb.dmgameapp.utils.HttpUtils;
import com.stx.xhb.dmgameapp.utils.JsonUtils;
import com.stx.xhb.dmgameapp.utils.NetUtils;
import com.stx.xhb.dmgameapp.utils.SoftKeyBoardUtils;
import com.stx.xhb.dmgameapp.utils.SystemBarTintManager;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.loadingviewfinal.ListViewFinal;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;

/**
 * 评论详情界面
 */
public class CommentActivity extends ActionBarActivity implements View.OnClickListener {
    @Bind(R.id.multiplestatusview)
    MultipleStatusView multiplestatusview;
    @Bind(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    private ListViewFinal lv_comment;
    private Toolbar toolbar;
    private Button comment_btn;
    private EditText ed_comment;
    private List<ChapterCommentListItem.DescriptionEntity.DataEntity> dataEntities = new ArrayList<>();
    private CommentListviewAdapter adapter;
    private int currentPage = 1;//当前页
    private List<ChapterCommentListItem.DescriptionEntity.DataEntity> data;
    private String id;
    private LayoutInflater mInflater;
    private final int LIMIT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        initWindow();
        initView();
        initData();
        setAdapter();
        setListener();
        setSwipeRefreshInfo();
    }

    //初始化窗体布局
    private void initWindow() {
        SystemBarTintManager tintManager;
        //由于沉浸式状态栏需要在Android4.4.4以上才能使用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
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
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        lv_comment = (ListViewFinal) findViewById(R.id.content_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        comment_btn = (Button) findViewById(R.id.comment_btn);
        ed_comment = (EditText) findViewById(R.id.ed_comment);
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
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
    }

    //初始化数据
    private void initData() {
        //获取到文章id
        id = getIntent().getStringExtra("id");
    }

    //设置适配器
    private void setAdapter() {
        //实例化适配器
        adapter = new CommentListviewAdapter(this, dataEntities);
        //设置适配器
        lv_comment.setAdapter(adapter);
    }

    //设置事件监听
    private void setListener() {
        toolbar.setNavigationOnClickListener(this);
        comment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentContent = ed_comment.getText().toString();//获取到输入框中的评论内容
                if (TextUtils.isEmpty(commentContent)) {
                    ToastUtil.showAtCenter(CommentActivity.this, "评论内容不能为空！");
                    return;
                }
                comment_btn.setEnabled(false);
                final Map<String, String> params = new HashMap<String, String>();
                params.put("aid", id);
                params.put("msg", commentContent);
                HttpUtils.submitPostData(params, "utf-8", new HttpUtils.OnFetchResponseListener() {
                    @Override
                    public void OnFechResponse(String reponse) {
                        Log.i("responseResult====>", reponse);
                        //json解析，获取到响应状态码
                        try {
                            JSONObject object = new JSONObject(JsonUtils.removeBOM(reponse));
                            String code = object.getString("code");//响应状态码
                            if ("1".equals(code)) {
                                ed_comment.setText("");//评论成功后，清空输入框
                                SoftKeyBoardUtils.closeSoftInputMethod(CommentActivity.this, ed_comment);
                                ToastUtil.showAtCenter(CommentActivity.this, "评论成功");
                                comment_btn.setEnabled(true);
                                //重新加载数据
                                downLoad(1);
                            } else {
                                SoftKeyBoardUtils.closeSoftInputMethod(CommentActivity.this,ed_comment);
                                comment_btn.setEnabled(true);
                                ToastUtil.showAtCenter(CommentActivity.this, "评论失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });
        //重新加载事件监听
        multiplestatusview.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   downLoad(1);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onResume(this);       //统计时长
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPause(this);
    }
    //toolbar的返回事件
    @Override
    public void onClick(View v) {
        finish();
    }

    //加载网络数据
    private void downLoad(final int page) {
        multiplestatusview.showLoading();
        String strUrl = String.format(API.COMMENT_URL, id, page);
        x.http().get(new RequestParams(strUrl), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                multiplestatusview.showContent();
                String json = new String(result);
                ChapterCommentListItem chapterCommentListItem = new Gson().fromJson(JsonUtils.removeBOM(json), ChapterCommentListItem.class);
                data = chapterCommentListItem.getDescription().getData();
                if (page == 1) {
                    dataEntities.clear();
                }
                currentPage = page + 1;
                if (data.size()!=0) {
                    dataEntities.addAll(data);
                    adapter.notifyDataSetChanged();
                } else {
                    multiplestatusview.showEmpty();
                }
                if (data.size() < LIMIT) {
                    lv_comment.setHasLoadMore(false);
                } else {
                    lv_comment.setHasLoadMore(true);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (NetUtils.isNetConnected(getApplication())) {
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
                    lv_comment.onLoadMoreComplete();
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


    private void setSwipeRefreshInfo() {
        ptrLayout.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                multiplestatusview.showLoading();
                downLoad(1);
            }

            //判断是否可以下拉刷新
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return !isChildScrollUp();
            }
        });
        ptrLayout.setLastUpdateTimeRelateObject(this);
        lv_comment.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                downLoad(currentPage);
            }
        });
        lv_comment.setNoLoadMoreHideView(false);
        ptrLayout.autoRefresh();
    }

    /**
     * 判断是否滑动到顶端
     *
     * @return
     */
    public boolean isChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (lv_comment instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) lv_comment;
                return absListView.getChildCount() > 0 &&
                        (absListView.getFirstVisiblePosition() > 0 ||
                                absListView.getChildAt(0).getTop() < absListView.getPaddingTop());

            } else {
                return ViewCompat.canScrollVertically(lv_comment, -1) || lv_comment.getScrollY() > 0;
            }

        } else {

            return ViewCompat.canScrollVertically(lv_comment, -1);

        }

    }

}
