package com.stx.xhb.dmgameapp.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.stx.core.base.BaseMvpActivity;
import com.stx.core.mvp.IPresenter;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.MainFragmentPageAdapter;
import com.stx.xhb.dmgameapp.mvp.view.fragment.CommentListFragment;
import com.stx.xhb.dmgameapp.mvp.view.fragment.NewsDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Author：xiaohaibin
 * Time：2017/9/21
 * Emil：xhb_199409@163.com
 * Github：https://github.com/xiaohaibin/
 * Describe：资讯详情
 */

public class NewsDetailsActivity extends BaseMvpActivity {

    @Bind(R.id.vp_container)
    ViewPager vpContainer;
    private String mId;
    private String mKey;
    private String mAppId;
    private String mUrl;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_news_detail;
    }

    public static void start(Context context, String url, String id, String key) {
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        bundle.putString("id", id);
        bundle.putString("key", key);
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("id")){
                mId = extras.getString("id");
            }
            if (extras.containsKey("key")){
                mKey = extras.getString("key");
            }
            if (extras.containsKey("url")){
                mUrl = extras.getString("url");
            }
        }
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(NewsDetailsFragment.newInstance(mUrl,mId, mKey));
        fragmentList.add(CommentListFragment.newInstance());
        vpContainer.setAdapter(new MainFragmentPageAdapter(getSupportFragmentManager(), fragmentList));
    }

    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }
}
