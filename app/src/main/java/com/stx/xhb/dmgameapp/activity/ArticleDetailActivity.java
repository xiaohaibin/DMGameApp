package com.stx.xhb.dmgameapp.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import com.google.gson.Gson;
import com.stx.core.utils.DateUtils;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.base.BaseAppActitity;
import com.stx.xhb.dmgameapp.entity.DetailEntity;
import com.stx.xhb.dmgameapp.share.ShareDialog;
import com.stx.xhb.dmgameapp.utils.API;
import com.stx.xhb.dmgameapp.utils.JsonUtils;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
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
public class ArticleDetailActivity extends BaseAppActitity {

    private WebView comment_web;
    private Toolbar toolbar;
    private String body;
    private String id;
    private String typeid;
    private String title;//标题
    private String writer;//作者
    private String senddate;//发布时间
    private SmoothProgressBar webProgress;//进度条
    private String decode;
    private String aurl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSatusBar();
        ButterKnife.bind(this);
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
                        aurl = detailEntity.getArcurl();
                        initData();
                    }
                });
    }

    //获取控件
    private void initView() {
        comment_web = (WebView) findViewById(R.id.coment_web);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        webProgress = (SmoothProgressBar) findViewById(R.id.web_progress);
        initToolBar(toolbar,"文章详情");
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
        return super.onKeyDown(keyCode, event);

    }


    private void goBack() {
        if (comment_web != null && comment_web.canGoBack()) {
            comment_web.goBack();
        } else {
            finish();
        }
    }

    //点击分享
    @OnClick(R.id.article_share)
    public void onClick() {
        ShareDialog.share(getSupportFragmentManager(), title, aurl, title,"");
    }
}
