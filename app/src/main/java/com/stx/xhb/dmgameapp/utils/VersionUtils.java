package com.stx.xhb.dmgameapp.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by xhb on 2016/3/9.
 * 版本工具类
 */
public class VersionUtils {
    /**
     * 获取版本号
     *
     * @return 当前应用版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return "V"+version;
        } catch (Exception e) {
            e.printStackTrace();
            return "获取程序版本失败";
        }
    }
}
