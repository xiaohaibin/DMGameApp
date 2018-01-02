package com.stx.xhb.dmgameapp.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.core.base.BaseMvpActivity;
import com.stx.core.mvp.IPresenter;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.MainFragmentPageAdapter;
import com.stx.xhb.dmgameapp.mvp.view.fragment.GameDetailsCommonFragment;
import com.stx.xhb.dmgameapp.mvp.view.fragment.GameVideoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 游戏详情
 * 新闻  type 1    攻略   2   视频  3
 */
public class GameDetailsActivity extends BaseMvpActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.id_stickynavlayout_indicator)
    TabLayout tabLayout;
    @Bind(R.id.id_stickynavlayout_viewpager)
    ViewPager viewPager;
    @Bind(R.id.iv_game_img)
    ImageView ivGameImg;
    @Bind(R.id.tv_game_title)
    TextView tvGameTitle;
    @Bind(R.id.tv_game_details)
    TextView tvGameDetails;
    private String[] titleList = new String[]{"新闻", "攻略", "视频"};
    private String gameId;
    private String gameName;

    public static void start(Context context, String gameId, String img, String name, String gameDetails) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(context, GameDetailsActivity.class);
        bundle.putString("id", gameId);
        bundle.putString("img", img);
        bundle.putString("name", name);
        bundle.putString("game_details", gameDetails);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_game_details;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        List<Fragment> fragemnts = new ArrayList<>();
        initToolBar(mToolbar, "游戏详情");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("id")) {
                gameId = extras.getString("id");
            }
            if (extras.containsKey("img")) {
                String img = extras.getString("img");
                Glide.with(this).load(img).placeholder(getResources().getDrawable(R.drawable.icon_gamed_efault)).into(ivGameImg);
            }
            if (extras.containsKey("name")) {
                gameName = extras.getString("name");
                tvGameTitle.setText(gameName);
            }
            if (extras.containsKey("game_details")) {
                String gameDetails = extras.getString("game_details");
                tvGameDetails.setText(gameDetails);
            }
        }
        fragemnts.add(GameDetailsCommonFragment.newInstance("1", gameId, gameName));
        fragemnts.add(GameDetailsCommonFragment.newInstance("2", gameId, gameName));
        fragemnts.add(GameVideoFragment.newInstance(gameId, gameName));
        MainFragmentPageAdapter adapter = new MainFragmentPageAdapter(getSupportFragmentManager(), fragemnts, titleList);
        viewPager.setOffscreenPageLimit(fragemnts.size());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }
}
