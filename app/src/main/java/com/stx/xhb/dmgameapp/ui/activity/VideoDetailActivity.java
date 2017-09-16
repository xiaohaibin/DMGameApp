package com.stx.xhb.dmgameapp.ui.activity;

import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.google.gson.Gson;
import com.stx.core.utils.DateUtils;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.base.BaseAppActitity;
import com.stx.xhb.dmgameapp.config.API;
import com.stx.xhb.dmgameapp.entity.VideoDeatilEntity;
import com.stx.xhb.dmgameapp.utils.JsonUtils;
import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import okhttp3.Call;

/**
 * 视频详情界面
 */
public class VideoDetailActivity extends BaseAppActitity{

    private WebView comment_web;
    private ProgressDialog dialog;
    private Toolbar toolbar;
    private String body;
    private String id;
    private String typeid;
    private String title;//标题
    private String writer;//作者
    private String senddate;//发布时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSatusBar();
        setContentView(R.layout.activity_video_detail);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        initView();
        id = getIntent().getStringExtra("id");
        typeid = getIntent().getStringExtra("typeid");
        String url = String.format(API.ChapterContent_URL, id, typeid);//文章详情请求地址
        //下载网络数据
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.show("加载失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //json解析
                        VideoDeatilEntity detail = new Gson().fromJson(JsonUtils.removeBOM(response), VideoDeatilEntity.class);
                        body = detail.getVideolist().getBody();//视频内容
                        title = detail.getTitle();//文章标题
                        writer = detail.getWriter();//文章作者
                        senddate = detail.getSenddate();//文章发布时间
                        initData();
                    }
                });
    }

    //获取控件
    private void initView() {
        comment_web = (WebView) findViewById(R.id.coment_web);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar, "视频详情");
    }

    //初始化数据
    private void initData() {
        comment_web.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        comment_web.setDrawingCacheEnabled(true);
        //启用支持javascript
        WebSettings settings = comment_web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setPluginsEnabled(true);//可以使用插件
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setTextSize(WebSettings.TextSize.LARGEST);//设置字体大小
        settings.setDefaultTextEncodingName("utf-8");//设置默认编码格式
//        //自适应屏幕
        settings.setUseWideViewPort(true);
        comment_web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    //网页加载完成，关闭对话框
                    closeDialog();
                } else {
                    //网页加载中，打开进度对话框
                    openDialog(newProgress);
                }
            }
        });
        //加载网络资源
        if (body != null) {
            try {
                //由于body的数据进行了URLEncode编码，所以需要我们再进行URLDecoder解码
                //否则只能显示图片
                String decode = URLDecoder.decode(body, "utf-8");
                Log.i("--------->decode", "" + decode);
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
                        + decode
                        + "</body></html>";
                //使用这种方法，前面添加网站的地址 http://www.3dmgame.com，可以解决，有些图片前面乜有完整请求地址的问题
                comment_web.loadDataWithBaseURL(API.DMGEAME_URL, html, "text/html", "charset=UTF-8", null);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.i("body------>>>>>>>", "" + body);
            //设置在同一个webview中打开新的网页
            comment_web.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });
        }

    }

    //改写物理按键——返回的逻辑
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

    //关闭进度对话框
    private void closeDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    //显示进度对话框
    private void openDialog(int newProgress) {
        if (dialog == null) {
            dialog = new ProgressDialog(VideoDetailActivity.this);
            dialog.setProgress(newProgress);
            dialog.setMessage("视频详情加载中。。。");
            dialog.show();
        } else {
            dialog.setProgress(newProgress);
        }
    }
}
