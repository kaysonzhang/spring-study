package com.kayson.api.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author by kayson
 * @data 2018/7/13 9:47
 * @description 日期常用处理类
 */
public class DateUtil {

    private DateUtil(){}

    //java时间戳 13位
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    //正常10位时间戳
    public static int getUnixTime() {
        long unixTime = System.currentTimeMillis() / 1000L;
        return (int)unixTime;
    }
    //日期转换成10位时间戳
    public static int getUnixTime(Date date) {
        long unixTime = date.getTime() / 1000L;
        return (int)unixTime;
    }

    public static int getUnixTimeByString(String stringTime, String stringFormat) throws ParseException {
        Date date = (new SimpleDateFormat(stringFormat)).parse(stringTime);
        return (int)(date.getTime() / 1000L);
    }

    public static int getUnixTimeByString(String stringTime) throws ParseException {
        return getUnixTimeByString(stringTime, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDateByUnixTime(int time) {
        return getDateByUnixTime((long)time * 1000L);
    }

    public static String getDateByUnixTime(int time, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        String date = dateFormat.format((long)time * 1000L);
        return date;
    }

    public static String getDateByUnixTime(long time) {
        return getDateByUnixTimeMs(time, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDateByUnixTimeMs(long time, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        String date = dateFormat.format(time);
        return date;
    }

}
