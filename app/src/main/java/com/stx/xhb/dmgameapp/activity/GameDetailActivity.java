package com.stx.xhb.dmgameapp.activity;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.DetailEntity;
import com.stx.xhb.dmgameapp.utils.API;
import com.stx.xhb.dmgameapp.utils.JsonUtils;
import com.stx.xhb.dmgameapp.utils.SystemBarTintManager;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 游戏详情界面
 */
public class GameDetailActivity extends ActionBarActivity implements View.OnClickListener {


    @Bind(R.id.tv_game_name)
    TextView tvGameName;
    @Bind(R.id.tv_game_type)
    TextView tvGameType;
    @Bind(R.id.tv_game_product)
    TextView tvGameProduct;
    @Bind(R.id.tv_game_time)
    TextView tvGameTime;
    @Bind(R.id.tv_release_company)
    TextView tvReleaseCompany;
    @Bind(R.id.tv_game_url)
    TextView tvGameUrl;
    @Bind(R.id.tv_game_terrace)
    TextView tvGameTerrace;
    @Bind(R.id.tv_game_language)
    TextView tvGameLanguage;
    @Bind(R.id.tv_game_detail)
    TextView tvGameDetail;
    @Bind(R.id.iv_game)
    ImageView ivGame;
    private Toolbar toolbar;
    private String title;
    private String litpic;
    private String imageURl;
    private String description;
    private String typename;
    private String release_company;
    private String release_date;
    private String made_company;
    private String terrace;
    private String language;
    private String websit;
    private ProgressDialog pd;//进度对话框
    final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
            {
                    SHARE_MEDIA.WEIXIN,SHARE_MEDIA.SINA,
                    SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
            };
    private String arcurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        initWindow();
        ButterKnife.bind(this);
        initView();
        //初始化数据
        initData();
        //设置监听
        setListener();

    }

    //初始化控件，获取控件
    private void initView() {
        //获取控件
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //2.替代
        setSupportActionBar(toolbar);
        //加载背景颜色
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorBackground)));
        //设置显示返回上一级的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置标题
        getSupportActionBar().setTitle("游戏详情");
        //设置标题栏字体颜色
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
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

    //初始化数据
    private void initData() {
        //获取到从游戏列表中传递过来的参数
        String id = getIntent().getStringExtra("id");
        String typeid = getIntent().getStringExtra("typeid");
        String url = String.format(API.ChapterContent_URL, id, typeid);//游戏详情请求地址
        //下载网络数据
        pd = new ProgressDialog(this);
        pd.setMessage("游戏详情加载中。。。");
        pd.show();
        //加载网络数据
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showShort(GameDetailActivity.this, "游戏详情加载失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //json解析
                        DetailEntity detailEntity = new Gson().fromJson(JsonUtils.removeBOM(response), DetailEntity.class);
                        pd.dismiss();//隐藏进度对话框
                        title = detailEntity.getTitle();
                        description = detailEntity.getDescription();
                        typename = detailEntity.getTypename();
                        //游戏封面链接
                        litpic = detailEntity.getLitpic();
                        release_company = detailEntity.getRelease_company();
                        //发行时间
                        release_date = detailEntity.getRelease_date();
                        made_company = detailEntity.getMade_company();
                        terrace = detailEntity.getTerrace();
                        language = detailEntity.getLanguage();
                        websit = detailEntity.getWebsit();
                        arcurl = detailEntity.getArcurl();
                        tvGameName.setText(title);
                        //游戏封面链接
                        imageURl = API.DMGEAME_URL + litpic;
                        //下载图片，优先使用缓存图片
                        Glide.with(GameDetailActivity.this).load(imageURl).into(ivGame);
                        tvGameDetail.setText(description);
                        tvGameType.setText(typename);
                        tvReleaseCompany.setText(release_company);
                        //发行时间
                        tvGameTime.setText(release_date);
                        tvGameProduct.setText(made_company);
                        tvGameTerrace.setText(terrace);
                        tvGameLanguage.setText(language);
                        //设置官网链接
                        tvGameUrl.setText(
                                Html.fromHtml("<a href=" + websit + ">点击进入</a> "));
                        tvGameUrl.setMovementMethod(LinkMovementMethod.getInstance());
                    }
                });
    }

    //设置监听
    private void setListener() {
        //设置toolbar返回上一级的事件监听
        toolbar.setNavigationOnClickListener(this);
    }

    //toolbar的事件监听
    @Override
    public void onClick(View v) {
        //返回上一级
        finish();
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
    @OnClick(R.id.game_share)
    public void onClick() {
        new ShareAction(this).setDisplayList( displaylist )
                .withText(description)
                .withTitle(title)
                .withTargetUrl(arcurl)
                .withMedia(new UMImage(this,R.mipmap.ic_logo))
                .setListenerList(new UMShareListener() {
                    @Override
                    public void onResult(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                    }
                }, new UMShareListener() {
                    @Override
                    public void onResult(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                    }
                })
                .open();
    }
}
