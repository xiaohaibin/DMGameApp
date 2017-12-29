package com.stx.xhb.dmgameapp;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.stx.core.base.BaseApplication;
import com.stx.xhb.dmgameapp.utils.AppUser;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

/**
 * Created by xhb on 2016/1/19.
 * 程序主入口，当程序启动的时候，会调用这个方法
 */
public class DmgApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Toast
        ToastManager.instance.init(this);
        //用户数据初始化
        AppUser.init();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("==3dm==", BuildConfig.DEBUG))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);

        initUMShare();

        initBugly();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public enum ToastManager {

        instance;

        private TextView contentTextView;
        private Toast toast;

        public void init(Context context) {
            contentTextView = (TextView) LayoutInflater.from(context).inflate(R.layout.view_toast, null);
            toast = new Toast(context);
            toast.setView(contentTextView);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }

        public void show(CharSequence charSequence, int duration) {
            if (!TextUtils.isEmpty(charSequence)) {
                contentTextView.setText(charSequence);
                toast.setDuration(duration);
                toast.show();
            }
        }
    }


    /**
     * 配置友盟相关设置
     */
    private void initUMShare() {
        UMShareAPI.get(this);

        MobclickAgent.setSessionContinueMillis(2 * 60 * 1000);

        MobclickAgent.openActivityDurationTrack(false);

        Config.DEBUG = BuildConfig.DEBUG;
        MobclickAgent.setDebugMode(BuildConfig.DEBUG);

        //微信 appid appsecret
        PlatformConfig.setWeixin("wx152334f54a39c3b0", "24949aef9a179c253fdd55f12a576632");
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone("1105172050", "0QeUxsghHOHfZzap");
    }

    private void initBugly() {
        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
         * 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(MainActivity.class);
        Bugly.init(getApplicationContext(), "1105172050", false);
    }

}
