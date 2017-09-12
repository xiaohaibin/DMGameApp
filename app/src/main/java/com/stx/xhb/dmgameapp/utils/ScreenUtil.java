package com.stx.xhb.dmgameapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.stx.xhb.dmgameapp.R;

import java.io.FileOutputStream;



public class ScreenUtil {

    public static int dip2px(Context context, float dipValue) {
        return (int) (dipValue * context.getResources().getDisplayMetrics().density);
    }

    /**
     * 描述：px转换为dip.
     *
     * @param context the context
     * @param pxValue the px value
     * @return dip值
     */
    public static int px2dip(Context context, float pxValue) {
        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        return (int) (pxValue / mDisplayMetrics.density);
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }


    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;

    }

    private static final int[] APPCOMPAT_CHECK_ATTRS = {R.attr.colorPrimary};

    public static void checkAppCompatTheme(Context context) {
        TypedArray a = context.obtainStyledAttributes(APPCOMPAT_CHECK_ATTRS);
        final boolean failed = !a.hasValue(0);
        if (a != null) {
            a.recycle();
        }
        if (failed) {
            throw new IllegalArgumentException("You need to use a Theme.AppCompat theme "
                    + "(or descendant) with the design library.");
        }
    }

    /**
     * 将Android布局生成图片保存
     *
     * @param v
     * @return
     */
    public static Bitmap loadBitmapFromView(View v) {
        if (v == null) {
            return null;
        }
        Bitmap screenshot;
        screenshot = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.RGB_565);
        Canvas c = new Canvas(screenshot);
        v.draw(c);
        return screenshot;
    }

    /**
     * 将Android布局生成屏幕截图并保存
     *
     * @param context
     * @param v
     */
    public static void saveAsImg(Context context, View v) {

        try {
            FileOutputStream fos = new FileOutputStream("/mnt/sdcard/Download/"
                    + "屏幕截图" + System.currentTimeMillis() + ".png");
            Bitmap bitmap = loadBitmapFromView(v);
            boolean b = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            bitmap.recycle();
            fos.close();
            Toast.makeText(context, b ? "生成图片成功 " : "生成图片失败", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "生成图片失败", Toast.LENGTH_SHORT).show();
            Log.i("生成图片失败", e.toString());
        }
    }

}