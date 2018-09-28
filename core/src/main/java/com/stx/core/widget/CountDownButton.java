package com.stx.core.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 短信验证码倒计时button
 * @author xiao.haibin
 */
@SuppressLint("AppCompatCustomView")
public class CountDownButton extends TextView {

    private long time = 60 * 1000;

    private String beforeText = "获取验证码";

    private String afterText = "重新获取";

    private CountDownTimer timer;


    public CountDownButton(Context context) {
        super(context);
        initView();
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CountDownButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    /**
     * 初始化操作
     */
    private void initView() {
        if (!TextUtils.isEmpty(getText())) {
            beforeText = getText().toString().trim();
        }
        setText(beforeText);
        initTimer();
    }

    /**
     * 开始倒计时
     */
    public void start() {
        timer.start();
        setText(String.valueOf(time / 1000 + "S后重试"));
    }

    /**
     * 清除倒计时
     */
    private void clearTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        clearTimer();
        super.onDetachedFromWindow();
    }

    private void initTimer() {
        if (timer == null) {
            timer = new CountDownTimer(time, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    setEnabled(false);
                    setText(String.valueOf(millisUntilFinished / 1000 + "秒后重发"));
                }

                @Override
                public void onFinish() {
                    setEnabled(true);
                    setText(afterText);
                }
            };
        }
    }

}
