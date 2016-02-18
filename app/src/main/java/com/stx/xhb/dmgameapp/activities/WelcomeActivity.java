package com.stx.xhb.dmgameapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.stx.xhb.dmgameapp.MainActivity;
import com.stx.xhb.dmgameapp.R;

/**
 * 启动页
 */
public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //使用Handler发送一个延迟1000ms的消息
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent=new Intent(WelcomeActivity.this, MainActivity.class);
               startActivity(intent);
               finish();
           }
       },1000);//设定睡眠时间1000ms
    }
}
