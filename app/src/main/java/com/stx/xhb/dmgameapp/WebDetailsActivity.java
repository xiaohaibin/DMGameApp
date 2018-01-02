package com.stx.xhb.dmgameapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.stx.xhb.dmgameapp.base.BaseAppActitity;
import com.stx.xhb.dmgameapp.share.ShareDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 页面详情
 *
 * @author Mr.xiao
 */
public class WebDetailsActivity extends BaseAppActitity {

    @Bind(R.id.web_webview)
    WebView webView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.web_progress)
    ProgressBar progressBar;
    @Bind(R.id.web_go_top)
    ImageView mWebGoTop;
    private String url = "";
    private String content = "";
    private String imgUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_details);
        ButterKnife.bind(this);
        initToolBar(mToolbar, "详情");
        init();
        initWeb();
        setListener();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWeb() {
        WebSettings settings = webView.getSettings();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);

                } else {
                    if (progressBar.getVisibility() == View.GONE) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        settings.setDefaultTextEncodingName("utf-8");
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        //隐藏缩放控件
        settings.setDisplayZoomControls(false);
        //解决HTTPS协议下出现的mixed content问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCachePath(getCacheDir().getPath());
        settings.setAppCacheEnabled(true);

        showWebData();
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("url")) {
                url = bundle.getString("url");
            }
            if (bundle.containsKey("content")) {
                content = bundle.getString("content");
            }
            if (bundle.containsKey("imageUrl")) {
                imgUrl = bundle.getString("imageUrl");
            }
        }
    }

    private void showWebData() {
        webView.loadUrl(url);
    }

    private void setListener() {
        mWebGoTop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                webView.scrollTo(0, 0);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
        return true;
    }

    public static void start(Context context, String url, String content, String imageUrl) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(context, WebDetailsActivity.class);
        bundle.putString("url", url);
        bundle.putString("content", content);
        bundle.putString("imageUrl", imageUrl);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    //点击分享
    @OnClick(R.id.article_share)
    public void onClick() {
        ShareDialog.share(getSupportFragmentManager(), webView.getTitle(), url, content, imgUrl);
    }
}
