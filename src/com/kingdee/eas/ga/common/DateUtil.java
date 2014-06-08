package com.kingdee.eas.ga.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类。<br>
 *
 * @author XBB
 * 
 */
public class DateUtil {

    public static String formatDate(Date date, String pattern) {
        if (date == null)
            return "";
        if (pattern == null)
            pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return (sdf.format(date));
    }

    public static String formatDateTime(Date date) {
        return (formatDate(date, "yyyy-MM-dd HH:mm:ss"));
    }

    public static String formatDateTime() {
        return (formatDate(now(), "yyyy-MM-dd HH:mm:ss"));
    }

    public static String formatDate(Date date) {
        return (formatDate(date, "yyyy-MM-dd"));
    }

    public static String formatDate() {
        return (formatDate(now(), "yyyy-MM-dd"));
    }

    public static String formatTime(Date date) {
        return (formatDate(date, "HH:mm:ss"));
    }

    public static String formatTime() {
        return (formatDate(now(), "HH:mm:ss"));
    }

    public static String formatTime2() {
        return (formatDate(now(), "HHmmss"));
    }

    public static Date now() {
        return (new Date());
    }

    public static Date parseDateTime(String datetime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if ((datetime == null) || (datetime.equals(""))) {
            return null;
        } else {
            try {
                return formatter.parse(datetime);
            } catch (ParseException e) {
                return parseDate(datetime);
            }
        }
    }

    public static Date parseDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if ((date == null) || (date.equals(""))) {
            return null;
        } else {
            try {
                return formatter.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    public static Date parseDate(Date datetime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (datetime == null) {
            return null;
        } else {
            try {
                return formatter.parse(formatter.format(datetime));
            } catch (ParseException e) {
                return null;
            }
        }
    }

    public static String formatDate(Object o) {
        if (o == null)
            return "";
        if (o.getClass() == String.class)
            return formatDate((String) o);
        else if (o.getClass() == Date.class)
            return formatDate((Date) o);
        else if (o.getClass() == Timestamp.class) {
            return formatDate(new Date(((Timestamp) o).getTime()));
        } else
            return o.toString();
    }

    public static String formatDateTime(Object o) {
        if (o.getClass() == String.class)
            return formatDateTime((String) o);
        else if (o.getClass() == Date.class)
            return formatDateTime((Date) o);
        else if (o.getClass() == Timestamp.class) {
            return formatDateTime(new Date(((Timestamp) o).getTime()));
        } else
            return o.toString();
    }

    /**
     * 给时间加上或减去指定毫秒，秒，分，时，天、月或年等，返回变动后的时间
     *
     * @param date   要加减前的时间，如果不传，则为当前日期
     * @param field  时间域，有Calendar.MILLISECOND,Calendar.SECOND,Calendar.MINUTE,<br>
     *               Calendar.HOUR,Calendar.DATE, Calendar.MONTH,Calendar.YEAR
     * @param amount 按指定时间域加减的时间数量，正数为加，负数为减。
     * @return 变动后的时间
     */
    public static Date add(Date date, int field, int amount) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, amount);

        return cal.getTime();
    }

    public static Date addMilliSecond(Date date, int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }

    public static Date addSecond(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    public static Date addMiunte(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    public static Date addHour(Date date, int amount) {
        return add(date, Calendar.HOUR, amount);
    }

    public static Date addDay(Date date, int amount) {
        return add(date, Calendar.DATE, amount);
    }

    public static Date addMonth(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    public static Date addYear(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    public static Date getDate() {
        return parseDate(formatDate());
    }

    public static Date getDateTime() {
        return parseDateTime(formatDateTime());
    }

    public static void printNow4Test() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(sdf.format(new Date()));
    }
}
