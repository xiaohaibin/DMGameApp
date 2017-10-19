package com.stx.xhb.dmgameapp.ui.activity;

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
import com.classic.common.MultipleStatusView;
import com.stx.core.base.BaseActivity;
import com.stx.core.widget.StickyNavLayout;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.adapter.MainFragmentPageAdapter;
import com.stx.xhb.dmgameapp.ui.fragment.GameDetailsCommonFragment;
import com.stx.xhb.dmgameapp.ui.fragment.GameVideoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 游戏详情
 * 新闻  type 1    攻略   2   视频  3
 */
public class GameDetailsActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.id_stickynavlayout_indicator)
    TabLayout tabLayout;
    @Bind(R.id.id_stickynavlayout_viewpager)
    ViewPager viewPager;
    @Bind(R.id.id_stick)
    StickyNavLayout stickyNavLayout;
    @Bind(R.id.multiplestatusview)
    MultipleStatusView multiplestatusview;
    @Bind(R.id.iv_game_img)
    ImageView ivGameImg;
    @Bind(R.id.tv_game_title)
    TextView tvGameTitle;
    @Bind(R.id.tv_game_details)
    TextView tvGameDetails;
    private List<Fragment> fragemnts ;
    private MainFragmentPageAdapter adapter;
    private String[] TITLE_LIST=new String[]{"新闻","攻略","视频"};
    private String game_id;
    private String gameName;

    public static void start(Context context, String gameId,String img, String name, String gameDetails) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(context, GameDetailsActivity.class);
        bundle.putString("id",gameId);
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
        fragemnts=new ArrayList<>();
        initToolBar(mToolbar, "游戏详情");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("id")){
                game_id = extras.getString("id");
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
                String game_details = extras.getString("game_details");
                tvGameDetails.setText(game_details);
            }
        }
        fragemnts.add(GameDetailsCommonFragment.newInstance());
        fragemnts.add(GameDetailsCommonFragment.newInstance());
        fragemnts.add(GameVideoFragment.newInstance(game_id,gameName));
        adapter=new MainFragmentPageAdapter(getSupportFragmentManager(),fragemnts,TITLE_LIST);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected Class getLogicClazz() {
        return null;
    }


    @Override
    public void showLoading() {
        if (multiplestatusview != null) {
            multiplestatusview.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (multiplestatusview != null) {
            multiplestatusview.showContent();
        }
    }
}
