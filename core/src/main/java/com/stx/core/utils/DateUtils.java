package com.stx.core.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by xhb on 2016/1/19.
 * 日期转换工具类
 */
public class DateUtils {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String dateFromat(String timeMills) {
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        int time = Integer.parseInt(timeMills);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date((time * 1000L)));
    }

    public static String dateFromat(long timeMills) {
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date((timeMills*1000L)));
    }

    public static String getFriendlyTime(String paramString)
    {
        return getFriendlyTime(paramString, DEFAULT_DATE_FORMAT);
    }

    public static String getDefaultTime(long timeMills){
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date((timeMills*1000L)));
    }

    public static String getFriendlyTime(String paramString, SimpleDateFormat paramSimpleDateFormat)
    {
        if ((paramString != null) && (!paramString.equals("null")) && (!paramString.equals("")))
        {
            java.util.Date localDate = new java.util.Date();
            long l1 = Long.parseLong(paramString);
            long l2 = localDate.getTime() - l1;
            if (l2 > 0L)
            {
                if (l2 < 60000L) {
                    return "刚刚";
                }
                if ((l2 > 60000L) && (l2 < 3600000L)) {
                    return l2 / 60000L + "分钟前";
                }
                if ((l2 > 3600000L) && (l2 < 86400000L)) {
                    return l2 / 3600000L + "小时前";
                }
                if ((l2 > 86400000L) && (l2 < 172800000L)) {
                    return l2 / 86400000L + "天前";
                }
                return paramSimpleDateFormat.format(new java.util.Date(l1));
            }
            return paramSimpleDateFormat.format(new java.util.Date(l1));
        }
        return "";
    }


}
