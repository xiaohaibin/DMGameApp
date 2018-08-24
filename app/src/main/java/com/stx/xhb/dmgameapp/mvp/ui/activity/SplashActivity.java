package com.stx.xhb.dmgameapp.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;
import com.qq.e.comm.util.AdError;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.config.Constants;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.utils.Log;

/**
 * 启动页
 */
public class SplashActivity extends AppCompatActivity implements SplashADListener {

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_spalsh);
        LinearLayout llAd = (LinearLayout) findViewById(R.id.ll_ad);
        SplashAD splashAD = new SplashAD(this, llAd, Constants.APPID, Constants.SplashPosID, this, 3000);
    }

    @Override
    public void onADDismissed() {
        jumpToMain();
    }

    private void jumpToMain() {
        //使用Handler发送一个延迟1000ms的消息
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);//设定睡眠时间1000ms
    }

    @Override
    public void onNoAD(AdError adError) {
        jumpToMain();
    }

    /**
     * 倒计时回调，返回广告还将被展示的剩余时间。
     * 通过这个接口，开发者可以自行决定是否显示倒计时提示，或者还剩几秒的时候显示倒计时
     *
     * @param millisUntilFinished 剩余毫秒数
     */
    @Override
    public void onADTick(long millisUntilFinished) {

    }

    @Override
    public void onADPresent() {

    }

    @Override
    public void onADClicked() {
        Log.i("3DMGAME", "SplashADClicked");
    }

    /**
     * 防止用户返回键退出APP
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME || super.onKeyDown(keyCode, event);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
