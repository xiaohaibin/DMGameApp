package com.stx.xhb.dmgameapp.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.stx.xhb.dmgameapp.R;


/**
 * @describe Toast统一管理类
 */
public class ToastUtil
{

	private static Toast toast = null;

	/**
	 * 短时间显示Toast
	 * @param context
	 * @param msg
     */
	public static void showShort(Context context, String msg) {
		try {
			if (TextUtils.isEmpty(msg)) {
				return;
			}
			initToast(context, msg);
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 长时间显示Toast
	 * @param context
	 * @param msg
     */
	public static void showLong(Context context, String msg) {
		try {
			if (TextUtils.isEmpty(msg)) {
				return;
			}
			initToast(context, msg);
			toast.setDuration(Toast.LENGTH_LONG);
			toast.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 自定义Toast显示时间
	 * @param context
	 * @param msg
	 * @param time
     */
	public static void show(Context context, String msg, int time) {
		try {
			if (TextUtils.isEmpty(msg)) {
				return;
			}
			initToast(context, msg, time);
			toast.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 自定义Toast显示布局
	 * @param context 上下文
	 * @param resId   布局
     */
	public static void show(Context context, int resId) {

		try {
			if (TextUtils.isEmpty(context.getString(resId))) {
				return;
			}
			initToast(context, resId);
			toast.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 自定义在屏幕中间显示Toast
	 * @param context
	 * @param resId
     */
	public static void showAtCenter(Context context, int resId) {
		try {
			if (TextUtils.isEmpty(context.getString(resId))) {
				return;
			}
			initToast(context, resId);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 在屏幕中间显示Toast
	 * @param context
	 * @param msg
     */
	public static void showAtCenter(Context context, String msg) {

		try {
			if (TextUtils.isEmpty(msg)) {
				return;
			}
			initToast(context, msg);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化
	 * @param context
	 * @param msgRes
	 */
	private static void initToast(Context context, int msgRes) {
		initToast(context, context.getString(msgRes));
	}

	/**
	 * 初始化自定义Toast
	 *
	 * @param context
	 * @param msg
	 */
	private static void initToast(Context context, String msg) {
		if (toast != null) {
			toast.cancel();
		}
		View toastRoot = LayoutInflater.from(context).inflate(R.layout.view_layout_toast, null);
		toast = new Toast(context);
		toast.setView(toastRoot);
		toast.setGravity(Gravity.BOTTOM, 0, 100);
		TextView tv_message = (TextView) toast.getView().findViewById(R.id.message);
		tv_message.setText(msg);

	}

	/**
	 * 初始化自定义Toast
	 *
	 * @param context
	 * @param msg
	 * @param time
	 */
	private static void initToast(Context context, String msg, int time) {
		if (null != toast) {
			toast.cancel();
		}
		View toastRoot = LayoutInflater.from(context).inflate(R.layout.view_layout_toast, null);
		toast = new Toast(context);
		toast.setView(toastRoot);
		toast.setGravity(Gravity.BOTTOM, 0, 100);
		toast.setDuration(time);
		TextView tv_message = (TextView) toast.getView().findViewById(R.id.message);
		tv_message.setText(msg);
	}
}