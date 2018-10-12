package com.stx.core.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.stx.core.R;


/**
 * 
 * 一个半透明窗口,包含一个Progressbar 和 Message部分. 其中Message部分可选. 可单独使用,也可以使用
 * {@link DialogMaker} 进行相关窗口显示.
 * 
 * @author Qijun
 * 
 */
public class EasyProgressDialog extends Dialog {

	private String mMessage;
	private int mLayoutId;
	private TextView message;

	public EasyProgressDialog(Context context, @StyleRes int style, @LayoutRes int layout) {
		super(context, style);
		LayoutParams params = getWindow().getAttributes();
		params.width = LayoutParams.MATCH_PARENT;
		params.height = LayoutParams.MATCH_PARENT;
		getWindow().setAttributes(params);
		mLayoutId = layout;
	}

	public EasyProgressDialog(Context context, @LayoutRes int layout, String msg) {
		this(context, R.style.easy_dialog_style, layout);
		setMessage(msg);
	}

	public EasyProgressDialog(Context context, String msg) {
		this(context, R.style.easy_dialog_style, R.layout.easy_progress_dialog);
		setMessage(msg);
	}

	public EasyProgressDialog(Context context) {
		this(context, R.style.easy_dialog_style, R.layout.easy_progress_dialog);
	}

	public void setMessage(String msg) {
		mMessage = msg;
	}

	public void updateLoadingMessage(String msg) {
		mMessage = msg;
		showMessage();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(mLayoutId);
		message = findViewById(R.id.easy_progress_dialog_message);
		showMessage();
	}

	private void showMessage() {
		if (message != null && !TextUtils.isEmpty(mMessage)) {
			message.setVisibility(View.VISIBLE);
			message.setText(mMessage);
		}
	}
}