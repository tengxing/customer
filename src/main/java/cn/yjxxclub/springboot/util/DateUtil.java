package cn.yjxxclub.springboot.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-26
 * Time: 下午3:21
 * Describe: 时间工具类
 */
public class DateUtil {

    private DateUtil() {
    };

    // ---1.日期的基本组成------------
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取得当前日期是多少周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 返回当前日期是星期几
     *
     * @param date
     *            日期
     * @return 星期几
     */
    public static int getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    public static int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    // ------2、日期的计算----------
    /**
     * 日期加减天数
     *
     * @param sDate
     * @param days
     * @return
     */
    public static Date addSubDays(Date sDate, int days) {
        long subtime = (long) days * 24 * 60 * 60 * 1000;
        Date newdate = new Date(sDate.getTime() + subtime);
        return newdate;
    }

    /**
     * 日期加减分钟数
     *
     * @param sDate
     * @param mins
     * @return
     */
    public static java.util.Date addSubMins(java.util.Date sDate, int mins) {
        long subtime = (long) mins * 60 * 1000;
        Date newdate = new Date(sDate.getTime() + subtime);
        return newdate;
    }

    /**
     * 日期加减秒数
     *
     * @param sDate
     * @param secs
     * @return
     */
    public static Date addSubSeconds(java.util.Date sDate, int secs) {
        long subtime = (long) secs * 1000;
        Date newdate = new Date(sDate.getTime() + subtime);
        return newdate;
    }

    public static int getDiffSecs(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / 1000);
    }

    public static int getDiffMins(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (60 * 1000));
    }

    public static int getDiffHours(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (60 * 60 * 1000));
    }

    public static int getDiffDays(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000));
    }

    public static boolean isSameDay(Date date1, Date date2) {
        return 0 == getDiffDays(date1, date2);
    }

    /**
     * 得到某年某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    public static int getTotalDaysInMonth(int year, int month) {
        return getDay(getLastDayOfMonth(year, month));
    }

    // ----3 日期的格式化 ------------

    /**
     * 格式化输出日期
     *
     * @param date
     *            日期
     * @param format
     *            格式
     * @return 返回字符型日期
     */
    public static String format(Date date, String format) {
        String result = "";
        if (null == date) {
            return result;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            result = sdf.format(date);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     *
     * @param date
     * @return yyyy-MM-dd
     */
    public static String formatDate(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    /**
     *
     * @param date
     * @return HH:mm:ss
     */
    public static String formatTime(Date date) {
        return format(date, "HH:mm:ss");
    }

    /**
     *
     * @param date
     * @return yyyy-MM
     */
    public static String formatYM(Date date) {
        return format(date, "yyyy-MM");
    }

    // ----4 解析日期部分-------------
    /**
     * 生成日期
     *
     * @param year
     *            年
     * @param month
     *            月
     * @param day
     *            日
     * @param hour
     *            时
     * @param minute
     *            分
     * @param second
     *            秒
     * @return 日期
     */
    public static Date toDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day, hour, minute, second);
        return c.getTime();
    }

    /**
     * 日期字符串转换为日期
     *
     * @param strDate
     *            日期字符串
     * @param format
     *            字符串格式
     * @return 日期
     */
    public static Date parseDate(String strDate, String format) {
        Date result = null;
        if (null == strDate) {
            return result;
        }
        try {
            java.text.DateFormat df = new java.text.SimpleDateFormat(format);
            result = df.parse(strDate);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 获取指定时间的 年月日--时分秒为0
     *
     * @param date
     *            需要格式化的日期
     * @return
     */
    public static Date toDateYMD(Date date) {
        Date dateYMD = null;
        String format = "yyyy-MM-dd";
        if (null == date) {
            return dateYMD;
        }
        dateYMD = parseDate(format(date, format), format);
        return dateYMD;
    }
}
