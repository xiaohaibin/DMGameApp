package com.stx.xhb.dmgameapp.utils;

import android.widget.Toast;

import com.stx.xhb.dmgameapp.DmgApplication;

/**
 * @author Mr.xiao
 */
public class ToastUtil {

    /**
     * @param charSequence 显示文字
     */
    public static void show(CharSequence charSequence) {
        show(charSequence, Toast.LENGTH_SHORT);
    }

    /**
     * @param charSequence 显示文字
     * @param duration     显示时长 Toast.LENGTH_LONG/LENGTH_SHORT
     */
    public static void show(CharSequence charSequence, int duration) {
        DmgApplication.ToastManager.instance.show(charSequence, duration);
    }

}
