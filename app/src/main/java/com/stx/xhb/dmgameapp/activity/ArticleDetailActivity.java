package com.stx.xhb.dmgameapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.software.shell.fab.ActionButton;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.entity.DetailEntity;
import com.stx.xhb.dmgameapp.utils.API;
import com.stx.xhb.dmgameapp.utils.DateUtils;
import com.stx.xhb.dmgameapp.utils.JsonUtils;
import com.stx.xhb.dmgameapp.utils.SystemBarTintManager;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import okhttp3.Call;

/**
 * 文章详情界面
 */
public class ArticleDetailActivity extends ActionBarActivity implements View.OnClickListener {

    private WebView comment_web;
    private Toolbar toolbar;
    private String body;
    private String id;
    private String typeid;
    private String title;//标题
    private String writer;//作者
    private String senddate;//发布时间
    private SmoothProgressBar webProgress;//进度条
    private ActionButton actionButton;//评论按钮

    final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
            {
                    SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                    SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
            };
    private String decode;
    private String arcurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initWindow();
        initView();
        id = getIntent().getStringExtra("id");
        typeid = getIntent().getStringExtra("typeid");
        String url = String.format(API.ChapterContent_URL, id, typeid);

        OkHttpUtils.get()
                   .url(url)
                   .build()
                   .execute(new StringCallback() {
                       @Override
                       public void onError(Call call, Exception e, int id) {

                       }

                       @Override
                       public void onResponse(String response, int id) {
                           //json解析
                           DetailEntity detailEntity = new Gson().fromJson(JsonUtils.removeBOM(response), DetailEntity.class);
                           body = detailEntity.getBody();//文章内容
                           title = detailEntity.getTitle();//文章标题
                           writer = detailEntity.getWriter();//文章作者
                           senddate = detailEntity.getSenddate();//文章发布时间
                           arcurl = detailEntity.getArcurl();
                           initData();
                       }
                   });
        initListener();
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
        comment_web = (WebView) findViewById(R.id.coment_web);
        actionButton = (ActionButton) findViewById(R.id.action_button);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        webProgress = (SmoothProgressBar) findViewById(R.id.web_progress);
        //2.替代
        setSupportActionBar(toolbar);
        //加载背景颜色
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorBackground)));
        //设置显示返回上一级的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //设置标题
        getSupportActionBar().setTitle("文章详情");
        //设置标题栏字体颜色
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        //设置悬浮按钮的背景图片
        actionButton.setImageResource(R.drawable.note_publish_img_unpressed);//设置按钮资源文件
        actionButton.setImageSize(65);//设置图片按钮的大小
        //修改友盟分享对话框
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("分享中...");
        Config.dialog = dialog;

    }


    //初始化数据
    private void initData() {
        //启用支持javascript
        WebSettings settings = comment_web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setTextSize(WebSettings.TextSize.LARGEST);//设置字体大小
        settings.setDefaultTextEncodingName("utf-8");//设置默认编码格式
        //自适应屏幕
        settings.setUseWideViewPort(true);
        comment_web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                webProgress.setProgress(newProgress);
                if (webProgress != null && newProgress != 100) {
                    webProgress.setVisibility(View.VISIBLE);
                } else if (webProgress != null) {
                    webProgress.setVisibility(View.GONE);
                }
            }
        });
        //加载网络资源
        if (body != null) {
            try {
                //由于body的数据进行了URLEncode编码，所以需要我们再进行URLDecoder解码
                //否则只能显示图片
                decode = URLDecoder.decode(body, "utf-8");
                String date = DateUtils.dateFromat(senddate);//发布时间
                String html = "<html><body>"
                        + "<h3>"
                        + title
                        + "</h3>"
                        + "<p>"
                        + "作者:" + writer
                        + "&nbsp&nbsp"
                        + "发布时间:" + date
                        + "</p>"
                        + "<style>"
                        + "img{width:100%;height:auto;}"//自定义样式，设置图片显示大小
                        + "</style>"
                        + decode
                        + "</body></html>";
                //使用这种方法，前面添加网站的地址 http://www.3dmgame.com，可以解决，有些图片前面乜有完整请求地址的问题
                comment_web.loadDataWithBaseURL(API.DMGEAME_URL, html, "text/html", "charset=UTF-8", null);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            comment_web.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return false;
                }
            });
        }

    }

    //改写物理按键——返回的逻辑
    //返回无效是重定向的原因
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK && comment_web.canGoBack()) {
               goBack();
                return true;
            }
        }
        return false;

    }


    private void goBack() {
        if (comment_web != null && comment_web.canGoBack()) {
            comment_web.goBack();
        } else {
            finish();
        }
    }

    //设置事件监听
    private void initListener() {
        //toolbard的返回按钮事件监听
        toolbar.setNavigationOnClickListener(this);
        //点击按钮跳转到评论界面
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArticleDetailActivity.this, CommentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("typeid", typeid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    //toolbar事件监听方法
    @Override
    public void onClick(View v) {
        //返回上一页
        finish();
    }

    //点击分享
    @OnClick(R.id.article_share)
    public void onClick() {
        new ShareAction(this).setDisplayList(displaylist)
                .withText(title)
                .withTitle(title)
                .withTargetUrl(arcurl)
                .withMedia(new UMImage(this, R.mipmap.ic_logo))
                .setListenerList(getUmShareListener(), getUmShareListener()).open();
    }

    @NonNull
    private UMShareListener getUmShareListener() {
        return new UMShareListener() {
            @Override
            public void onResult(SHARE_MEDIA share_media) {
                ToastUtil.showAtCenter(ArticleDetailActivity.this, "分享成功");
            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                ToastUtil.showAtCenter(ArticleDetailActivity.this, "分享失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                ToastUtil.showAtCenter(ArticleDetailActivity.this, "取消分享");
            }
        };
    }
}
