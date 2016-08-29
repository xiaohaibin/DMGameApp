package com.stx.xhb.dmgameapp.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by xhb on 2016/1/19.
 * 日期转换工具类
 */
public class DateUtils {

    public static String dateFromat(String timeMills) {
        //拿到上面的time你会发现比真正的时间少了八个小时，解决的办法把JAVA默认的时区改为东八区.
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        int time = Integer.parseInt(timeMills);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strtime = sdf.format(new Date((time * 1000L)));
        return strtime;
    }
}
