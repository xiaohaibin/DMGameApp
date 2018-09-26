package com.stx.xhb.dmgameapp.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.base.BaseAppActitity;
import com.stx.xhb.dmgameapp.mvp.ui.adapter.MainFragmentPageAdapter;
import com.stx.xhb.dmgameapp.mvp.ui.fragment.CommentListFragment;
import com.stx.xhb.dmgameapp.mvp.ui.fragment.NewsDetailsFragment;

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

public class NewsDetailsActivity extends BaseAppActitity {

    @Bind(R.id.vp_container)
    public ViewPager vpContainer;
    private String mKey;
    private String mUrl;
    private String mImgUrl;
    private String mArcurl;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_news_detail;
    }

    public static void start(Context context, String weburl, String arcurl,String title, String imgUrl) {
        Bundle bundle = new Bundle();
        bundle.putString("weburl", weburl);
        bundle.putString("arcurl", arcurl);
        bundle.putString("key", title);
        bundle.putString("imgUrl", imgUrl);
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("key")) {
                mKey = extras.getString("key");
            }
            if (extras.containsKey("weburl")) {
                mUrl = extras.getString("weburl");
            }
            if (extras.containsKey("arcurl")){
                mArcurl = extras.getString("arcurl");
            }
            if (extras.containsKey("imgUrl")) {
                mImgUrl = extras.getString("imgUrl");
            }
        }
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(NewsDetailsFragment.newInstance(mUrl, mArcurl,mKey, mImgUrl));
        fragmentList.add(CommentListFragment.newInstance(mArcurl));
        vpContainer.setAdapter(new MainFragmentPageAdapter(getSupportFragmentManager(), fragmentList));
    }
}
