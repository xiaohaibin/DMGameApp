package com.stx.xhb.dmgameapp.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.stx.xhb.dmgameapp.R;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * 论坛的Fragment
 */
public class ForumFragment extends Fragment {

    public static final String HTTP_FORM_URL = "http://bbs.3dmgame.com/forum.php";
    private WebView web_view;
    private View view;
    private ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_forum, container, false);
        initView();
        return view;
    }

    //获取控件
    private void initView() {
        //隐藏toolbar menu控件
        ImageButton main_action_menu= (ImageButton) view.findViewById(R.id.main_action_menu);
        main_action_menu.setVisibility(View.GONE);
        TextView tv_title = (TextView) view.findViewById(R.id.title);
        tv_title.setText("论坛");
        web_view = (WebView) view.findViewById(R.id.web_view);
        //加载网络资源
        web_view.loadUrl(HTTP_FORM_URL);
        //设置可以在webview中加载网页中的连接
        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);//设置为上面的url
                return true;
            }
        });
        web_view.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    //网页加载完成，关闭对话框
//                    closeDialog();
                } else {
                    //网页加载中，打开进度对话框
//                    openDialog(newProgress);
                }
            }
        });
        //启用支持javascript
        WebSettings settings = web_view.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
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
            dialog = new ProgressDialog(getContext());
            dialog.setProgress(newProgress);
            dialog.setMessage("加载中。。。");
            dialog.setCancelable(false);
            dialog.show();
        } else {
            dialog.setProgress(newProgress);
        }
    }

}
