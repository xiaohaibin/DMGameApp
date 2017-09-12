package com.stx.core.base;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.stx.core.utils.ScreenUtil;


/**
 * Author:xiaohaibin
 * <p>
 * CrateTime:2016-12-09 19:45
 * <p>
 * Description: Dialog基类
 */
public class BaseDialogFragment extends DialogFragment {

    protected View contentView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        if (window!=null) {
            window.setGravity(getGravity());
            window.setBackgroundDrawable(new ColorDrawable(Color.argb(0, 0, 0, 0)));
        }
        dialog.setContentView(contentView, getLayoutParams());
        return dialog;
    }

    public int getGravity() {
        return Gravity.CENTER;
    }

    public ViewGroup.LayoutParams getLayoutParams() {
        return new ViewGroup.LayoutParams((ScreenUtil.getScreenWidth(getActivity())) - ScreenUtil.dip2px(getActivity(), 100),
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void setContentView(@LayoutRes int layoutId) {
        contentView = LayoutInflater.from(getActivity()).inflate(layoutId, null);
    }

    public View findViewById(@IdRes int id) {
        return contentView.findViewById(id);
    }

    /**
     * 省略findViewById的强制类型转换
     *
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T findView(@IdRes int id) {
        return (T) findViewById(id);
    }

    protected void findViews() {
    }

    protected void initViews() {
    }

    protected void registerViews() {
    }

}
