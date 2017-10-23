package com.stx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.stx.core.R;


/**
 * Author: Mr.xiao on 2017/6/4
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class CustomRefreshListview extends ListView implements AbsListView.OnScrollListener {

    /**
     * 底部布局
     */
    private View footerView;


    private int downY;

    /**
     * 当前是否在加载数据
     */
    private boolean isLoadingMore = false;

    /**
     * 是否可以上拉加载更多
     */
    private boolean canLoadeMore = false;

    public CustomRefreshListview(Context context) {
        this(context, null);
    }

    public CustomRefreshListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //设置滑动监听
        setOnScrollListener(this);
        //初始化为尾布局
        initFooterView();
    }

    private void initFooterView() {
        footerView = View.inflate(getContext(), R.layout.foot_custom_listview, null);
        footerView.setVisibility(GONE);
        addFooterView(footerView);
    }


    /**
     * 完成刷新操作，重置状态
     */
    public void completeRefresh() {
        if (isLoadingMore) {
            //重置footerView状态
            footerView.setVisibility(GONE);
            isLoadingMore = false;
        }
    }

    /**
     * 260      * SCROLL_STATE_IDLE:闲置状态，就是手指松开
     * 261      * SCROLL_STATE_TOUCH_SCROLL：手指触摸滑动，就是按着来滑动
     * 262      * SCROLL_STATE_FLING：快速滑动后松开
     * 263
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
                && getLastVisiblePosition() == (getCount() - 1) && !isLoadingMore && canLoadeMore) {
            isLoadingMore = true;
            footerView.setVisibility(VISIBLE);
            setSelection(getCount());//让listview最后一条显示出来，在页面完全显示出底布局

            if (listener != null) {
                listener.onLoadingMore();
            }
        }
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
    }

    private OnRefreshListener listener;

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.listener = listener;
    }

    public interface OnRefreshListener {
        void onLoadingMore();
    }

    public void setCanLoadeMore(boolean isLoadingMore) {
        canLoadeMore = isLoadingMore;
    }
}
