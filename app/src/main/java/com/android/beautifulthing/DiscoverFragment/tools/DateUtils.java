package com.android.beautifulthing.DiscoverFragment.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ydy on 2016/9/8.
 * 时间戳
 */
public class DateUtils {

    private static SimpleDateFormat sf = null;
    private static SimpleDateFormat sdf = null;
    private static SimpleDateFormat stf = null;
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
    public static long getTimeStamp(String time) {// time==mDate
        sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        try{
            date = sdf.parse(time);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 时间戳转换成字符窜
     * @param timeStamp 时间戳
     * @return
     */
    public static String timeStampToDate(long timeStamp) {
        Date d = new Date(timeStamp);
        stf = new SimpleDateFormat("MM-dd");
        return stf.format(d);
    }

    public static String timeStampToDate2(long timeStamp) {
        Date d = new Date(timeStamp);
        stf = new SimpleDateFormat("yyyy.MM.dd");
        return stf.format(d);
    }

//    Calendar c = Calendar.getInstance();
//    Date date = c.getTime();
//    String s = DateToWeek(date);
    public static String[] WEEK = {"星期天","星期一","星期二","星期三","星期四","星期五","星期六"};
    public static final int WEEKDAYS = 7;

    public static String DateToWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null;
        }
        return WEEK[dayIndex - 1];
    }

}
