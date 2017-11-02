package com.stx.core.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stx.core.log.Logger;
import com.stx.core.model.LogicProxy;
import com.stx.core.mvp.BasePresenter;
import com.stx.core.mvp.BaseView;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements BaseView {
    //是否可见
    protected boolean isViable = false;

    // 标志位，标志Fragment已经初始化完成。
    protected boolean isPrepared = false;

    //标记已加载完成，保证懒加载只能加载一次
    protected boolean hasLoaded = false;
    protected int currentpage = 1;//当前页码
    protected int page_size = 20;//页面数据量

    protected abstract int getLayoutResource();

    protected abstract void onInitView(Bundle savedInstanceState);

    protected Class getLogicClazz(){
        return null;
    }

    protected void onInitData2Remote() {
        if (getLogicClazz() != null) {
            mPresenter = getLogicImpl();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutResource() != 0) {
            rootView = inflater.inflate(getLayoutResource(), null);
        } else {
            rootView = super.onCreateView(inflater, container, savedInstanceState);
        }
        ButterKnife.bind(this, rootView);
        this.onInitView(savedInstanceState);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("name (%s.java:0)", getClass().getSimpleName());
        mContext = getActivity();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onInitData2Remote();
        if (!isPrepared && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isViable = true;
        }
    }

    //获得该页面的实例
    public <T> T getLogicImpl() {
        return LogicProxy.getInstance().bind(getLogicClazz(), this);
    }

    @Override
    public void onStart() {
        if (mPresenter != null && !mPresenter.isViewBind()) {
            LogicProxy.getInstance().bind(getLogicClazz(), this);
        }
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        OkHttpUtils.getInstance().cancelTag(this);
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isPrepared = false;
        hasLoaded = false;
    }

    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (rootView == null) {
            return;
        }
        isPrepared = true;
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isViable = true;
            return;
        }

        if (isViable) {
            onFragmentVisibleChange(false);
            isViable = false;
        }
    }

    /**
     * 当界面可见时的操作
     */
    protected void onVisible() {
        if (hasLoaded) {
            return;
        }
        lazyLoad();
        hasLoaded = true;
    }


    /**
     * 数据懒加载
     */
    protected void lazyLoad() {

    }

    /**
     * 当界面不可见时的操作
     */
    protected void onInVisible() {

    }

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     *
     * @param isVisible
     */
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    protected BasePresenter mPresenter;
    protected View rootView;
    protected Context mContext = null;

}
