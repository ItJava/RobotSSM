package com.ssm.promotion.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateUtil {

    public static String formatDate(Date date, String format) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            result = sdf.format(date);
        }
        return result;
    }


    public static Date formatString(String str, String format) throws Exception {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    public static String getCurrentDateStr() throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static final String FORMAT_ACTIVITY_TIME = "yyyy.MM.dd";
    public static final String FORMAT_LOTTERY_TIME = "MM/dd HH:mm";
    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm", Locale.CHINA);
        return sdf.format(new Date());
    }

    /**
     * 格式化时间
     *
     * @param inFormat
     * @param time
     * @return
     */
    public static String format(String inFormat, long time) {
        SimpleDateFormat format = new SimpleDateFormat(inFormat);
        Date date = new Date(time);
        return format.format(date);
    }

    public static long getTime(String inFormat, String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
            Date d = sdf.parse(time);
            return d.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 时间转化为日期
     *
     * @param inFormat
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String inFormat, String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
            Date d = sdf.parse(dateStr);
            return d;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String stringToTime(String timeStr) {
        if (timeStr == null) {
            return null;
        }
        String ret = "";
        int time = Integer.valueOf(timeStr);
        int m = time / 60;
        int s = time % 60;
        ret = (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s);
        return ret;
    }

    public static String secondToTimeString(long time) {
        // if (time <= 0) {
        // return "00:00";
        // }
        String ret = "";
        long m = time / 60;
        long s = time % 60;
        if (time >= 0) {
            ret = (m < 10 ? "0" + m : +m) + ":" + (s < 10 ? "0" + s : s);
        } else {
            ret = "-" + (m < 10 ? "0" + -m : -m) + ":" + (s < 10 ? "0" + s : s);
        }

        return ret;
    }

    public static String getNowTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        return sdf.format(date);
    }

    public static String getNowDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        return sdf.format(date);
    }

    public static String getNowUrlTime() {
        Long time = System.currentTimeMillis();
        Locale locale = Locale.getDefault();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", locale);
        Date date = new Date(time);
        return format.format(date);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        return sdf.format(date);
    }

    /**
     * 获取更新日期
     *
     * @return
     */
    public static String getRefreshDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
        return sdf.format(new Date());
    }

    /**
     * 获取普通时间
     *
     * @param time
     * @return
     */
    public static String getCommonDate(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        return format.format(date);
    }

    /**
     * @param date
     * @param formater
     * @return
     */
    public static String dateToString(Date date, String formater) {
        SimpleDateFormat format = new SimpleDateFormat(formater);
        return format.format(date);
    }

    /**
     * 以友好的方式显示时间
     *
     * @return
     */
    public static String getFriendlyTime(long time) {
        Date date = new Date(time);
        if (date == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(date);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - date.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - date.getTime()) / 60000, 1) + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        long lt = date.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - date.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - date.getTime()) / 60000, 1) + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days <= 10) {
            ftime = days + "天前";
        } else if (days > 10) {
            ftime = dateFormater2.get().format(date);
        }
        return ftime;
    }

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }


    public static String dateToString(Date date){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(sdf.format(date));
//        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(date));
        sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(date));

        return sdf.format(date);

    }



}
