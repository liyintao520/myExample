package com.lyt.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 通用的函数(日期相关函数)
 *
 * @author 北京市宏天信业科技发展有限公司
 * @version 3.0
 */

public class DateUtil {
    // 每个月的天数
    static private int[] daysInMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * 最后一秒时间
     */
    public static String LAST_TIME_SECOND = "23:59:59";

    public static String DEFAULT_DATE_FROMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期/时间格式化显示（年、月、日、时、分、秒、毫秒、星期）
     *
     * @param strDate      需要格式化的日期
     * @param fromFormat 该日期的格式串
     * @param toFormat 需要格式化的格式串
     * @return 格式化后的字符串（String）
     * @throws ParseException
     */
    public static String transformDateTime(String strDate, String fromFormat, String toFormat) throws ParseException {
        if (StringUtils.isEmpty(fromFormat)) {
            fromFormat = DEFAULT_DATE_FROMAT;
        }
        if (StringUtils.isEmpty(toFormat)) {
            toFormat = DEFAULT_DATE_FROMAT;
        }
        SimpleDateFormat myFormatter = new SimpleDateFormat(toFormat);
        myFormatter.setLenient(false);
        Date dtDate = new SimpleDateFormat(fromFormat).parse(strDate);
        return myFormatter.format(dtDate.getTime());
    }

    /**
     * 日期/时间格式化显示（年、月、日、时、分、秒、毫秒、星期）
     *
     * @param dtmDate   需要格式化的日期（java.util.Date）
     * @param strFormat 该日期的格式串
     * @return 格式化后的字符串（String）
     */
    public static String transformDateTime(Date dtmDate, String strFormat) {

        if (StringUtils.isEmpty(strFormat)) strFormat = "yyyy-MM-dd HH:mm:ss";

        SimpleDateFormat myFormat = new SimpleDateFormat(strFormat);

        return myFormat.format(dtmDate.getTime());
    }
    /**
     * 获取系统当前日期和时间，格式为yyyy-MM-dd HH:mm:ss
     *
     * @return 返回计算后的日期时间（String）
     */
    public static String getCurrentDateTime() {
        return DateUtil.getFormatCurrentDate("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取系统当前时间，格式为HH:mm:ss
     *
     * @return 返回计算后的时间（String）
     */
    public static String getCurrentTime() {
        return DateUtil.getFormatCurrentDate("HH:mm:ss");
    }

    /**
     * 获取系统当前日期，格式为yyyy-MM-dd
     *
     * @return 返回计算后的日期（String）
     */
    public static String getCurrentDate() {
        return DateUtil.getFormatCurrentDate("yyyy-MM-dd");
    }

    /**
     * 返回格式化的当前日期/时间
     *
     * @param strFormat 格式串
     * @return 当前日期/时间格式化后的字符串（String）
     */
    public static String getFormatCurrentDate(String strFormat) {
        return transformDateTime(getNow(), strFormat);
    }

    /**
     * 获取当前日期、时间
     *
     * @return 系统当前的日期/时间（Date）
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * String日期转换为Long
     *
     * @param formatDate
     * @param date
     * @return Long
     * @throws ParseException
     */
    public static Long transferStringDateToLong(String formatDate, String date)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        Date dt = new Date();
        dt = sdf.parse(date);
        return dt.getTime();
    }
    /**
     * 取得给定日期数天前（后）的日期函数
     *
     * @param dateStr
     *            需要进行加减的日期("yyyy-MM-dd hh:mm:ss")
     * @param amount
     *            需要计算的间隔天数
     * @return 返回计算后的日期（String）
     * @throws ParseException
     */
    public static String addMinutes(String dateStr, int amount) {
        String strFormat = "yyyy-MM-dd HH:mm:ss";
        Date date = null;
        try {
            date = DateUtils.parseDate(dateStr, strFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = DateUtils.addMinutes(date, amount);
        return DateFormatUtils.format(date, strFormat);
    }

    /**
     * 日期比较-精确到时分秒
     *
     * @param strDate1
     *            需要进行计较的日期1(yyyy-MM-dd HH:mm:ss)
     * @param strDate2
     *            需要进行计较的日期2(yyyy-MM-dd HH:mm:ss)
     * @return 比较的结果（int） -1：strDate1 < strDate2 0：strDate1 = strDate2
     *         1：strDate1 > strDate2
     * @throws ParseException
     */
    public static int msCompareDateToSecond(String strDate1, String strDate2)
            throws ParseException {
        String strFormat = "yyyy-MM-dd HH:mm:ss";
        Date dtDate1 = null;
        Date dtDate2 = null;
        int intCom = 0;
        SimpleDateFormat myFormatter = new SimpleDateFormat(strFormat);
        myFormatter.setLenient(false);
        dtDate1 = myFormatter.parse(strDate1);
        dtDate2 = myFormatter.parse(strDate2);

        intCom = dtDate1.compareTo(dtDate2);
        if (intCom > 0)
            return 1;
        if (intCom < 0)
            return -1;
        return 0;
    }

    /**
     * 转换为日期
     *
     * @param dateStr
     * @return
     */
    public static Date toDate(String dateStr, String format) {
        Date date = new Date();
        if (StringUtils.isEmpty(dateStr)) {
            return date;
        }
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            date = formater.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取每月天数
     * @return
     */
    public static int getMonthDays() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    public static int getDayOfMonth() {
        String date = getFormatCurrentDate("yyyy-MM-dd");
        String today = date.split("-")[2];
        return Integer.valueOf(today);
    }

    public static void main(String[] args) {
        System.out.println(getMonthDays());
        System.out.println(getDayOfMonth());
        System.out.println(getCurrentTimeByMinute(-30));

        Set<String> ids = new HashSet<>();
        ids.add("1");
        ids.add("2");
        ids.add("3");
        System.out.println(ids.toString());
    }

    /**
     * 获取当前时间前30分钟
     * @param minute
     * @return  String类型
     */
    public static String getCurrentTimeByMinute(int minute){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar beforeTime = Calendar.getInstance();
            beforeTime.add(Calendar.MINUTE, minute);// 30分钟之前的时间
            Date beforeD = beforeTime.getTime();
            String time = sdf.format(beforeD);
            return time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前时间前30分钟
     * @param minute
     * @return Date类型
     */
    public static Date getCurrentDateByMinute(int minute){
        try {
            Calendar beforeTime = Calendar.getInstance();
            beforeTime.add(Calendar.MINUTE, minute);// 30分钟之前的时间
            Date beforeD = beforeTime.getTime();
            return beforeD;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}