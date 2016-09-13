package com.android.beautifulthing.DiscoverFragment.http;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ydy on 2016/9/8.
 * 时间戳
 */
public class DateUtils {

    private static SimpleDateFormat sf = null;
    private static SimpleDateFormat sdf = null;
    private static String mDate;

    /**
     * 获取系统时间 格式为："yyyy.MM.dd"
     */
    public static String getCurrentDate() {
        Date date = new Date();
        sf = new SimpleDateFormat("yyyy.MM.dd");
        mDate = sf.format(date);
        return mDate;
    }

    /**
     * 将字符串转为时间戳
     */
    public static long getTimeStamp(String time) {
        sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        try{
            date = sdf.parse(time);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

}
