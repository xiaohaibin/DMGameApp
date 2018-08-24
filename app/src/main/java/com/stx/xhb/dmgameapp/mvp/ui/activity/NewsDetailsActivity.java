package com.stx.xhb.dmgameapp.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.stx.core.base.BaseMvpActivity;
import com.stx.core.mvp.IPresenter;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.MainFragmentPageAdapter;
import com.stx.xhb.dmgameapp.mvp.ui.fragment.CommentListFragment;
import com.stx.xhb.dmgameapp.mvp.ui.fragment.NewsDetailsFragment;
import com.stx.xhb.dmgameapp.mvp.ui.fragment.VideoDetailsFragment;

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
    private String mUrl;
    private String mImgUrl;
    private boolean mIsVideo;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_news_detail;
    }

    public static void start(Context context, String url, String id, String key, String imgUrl, boolean isVideo) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("id", id);
        bundle.putString("key", key);
        bundle.putString("imgUrl", imgUrl);
        bundle.putBoolean("isVideo", isVideo);
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("id")) {
                mId = extras.getString("id");
            }
            if (extras.containsKey("changyanId")) {
                String changyanId = extras.getString("changyanId");
            }
            if (extras.containsKey("key")) {
                mKey = extras.getString("key");
            }
            if (extras.containsKey("url")) {
                mUrl = extras.getString("url");
            }
            if (extras.containsKey("imgUrl")) {
                mImgUrl = extras.getString("imgUrl");
            }
            if (extras.containsKey("isVideo")) {
                mIsVideo = extras.getBoolean("isVideo", false);
            }
        }
        List<Fragment> fragmentList = new ArrayList<>();
        if (mIsVideo) {
            fragmentList.add(VideoDetailsFragment.newInstance(mUrl, mId, mKey, mImgUrl));
        } else {
            fragmentList.add(NewsDetailsFragment.newInstance(mUrl, mId, mKey, mImgUrl));
        }
        fragmentList.add(CommentListFragment.newInstance(mId));
        vpContainer.setAdapter(new MainFragmentPageAdapter(getSupportFragmentManager(), fragmentList));
    }

    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }
}
