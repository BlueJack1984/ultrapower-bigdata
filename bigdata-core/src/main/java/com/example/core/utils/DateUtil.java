package com.example.core.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author daniel
 * @date 2019-01-10
 */
@Component
@Slf4j
public final class DateUtil {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 字符串转日期
     * @param dateString
     * @param formatString
     * @return
     */
    public Date StringToDate(String dateString, String formatString) {

        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            log.info("【DateUtil---日期字符串参数转换为日期Date异常】");
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转日期
     * @param dateStr
     * @param formatStr
     * @return
     */
    public String get30day(int num){
        long time = System.currentTimeMillis() + (1000L * 60 * 60 * 24 * num);
        String pattern = "yyyy-MM-dd";
        Date date = new Date();
        if (time > 0) {
            date.setTime(time);
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param formatStr
     * @return
     */
    public String DateToString(Date date, String formatStr) {
        SimpleDateFormat dd = new SimpleDateFormat(formatStr);
        String currDate = dd.format(date);
        return currDate;
    }

    /**
     * 获取当月第一天
     *
     * @param
     * @param
     * @return
     */
    public String DateOfMonthFirst() {
        SimpleDateFormat monthformat = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        String first = monthformat.format(c.getTime());
        return first + " 00:00:00";
    }

    /**
     * 获取前月的第一天
     *
     * @param
     * @param
     * @return
     */
    public String DateOfLastMonthFirst() {
        SimpleDateFormat monthformat = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前月的第一天
        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        String format2 = monthformat.format(cal_1.getTime());
        return format2 + " 00:00:00";
    }

    /**
     * 获取当月最后一天
     *
     * @param
     * @param
     * @return
     */
    public String DateOfMonthEnd() {
        SimpleDateFormat monthformat = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = monthformat.format(ca.getTime());
        return last + " 23:59:59";
    }

    /**
     * 获取前月的最后一天
     *
     * @param
     * @return
     */
    public String DateOfLastMonthEnd() {
        SimpleDateFormat monthformat = new SimpleDateFormat("yyyy-MM-dd");
        // 获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
        String format3 = monthformat.format(cale.getTime());
        return format3 + " 23:59:59";
    }

    public Date beginTimeFormat(Date beginTime) {
        String begin = DateToString(beginTime, "yyyy-MM-dd");
        begin += " 00:00";
        return StringToDate(begin, "yyyy-MM-dd HH:mm");
    }

    public Date endTimeFormat(Date endTime) {
        String end = DateToString(endTime, "yyyy-MM-dd");
        end += " 23:59";
        return StringToDate(end, "yyyy-MM-dd HH:mm");
    }

    /**
     * 查询未来第3天日期
     *
     * @return
     */
    public Date DateOf3Day() {
        SimpleDateFormat monthformat = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当月最后一天
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_MONTH, 1);
        String last = monthformat.format(ca.getTime());
        Date stringToDate = StringToDate(last + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        return stringToDate;
    }

    /**
     * 查询未来第7天日期
     *
     * @return
     */
    public Date DateOfDay1(Date time, int day) {
        SimpleDateFormat monthformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取当月最后一天
        Calendar ca = Calendar.getInstance();
        ca.setTime(time);
        ca.add(Calendar.DAY_OF_MONTH, day);
        String last = monthformat.format(ca.getTime());
        Date stringToDate = StringToDate(last, "yyyy-MM-dd HH:mm:ss");
        return stringToDate;
    }

    /**
     * 比较两个日期的大小
     *
     * @return
     */
    public static int compare_date(Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 查询未来第7天日期
     *
     * @return
     */
    public Date DateOf7Day() {
        SimpleDateFormat monthformat = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当月最后一天
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_MONTH, 7);
        String last = monthformat.format(ca.getTime());
        Date stringToDate = StringToDate(last + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        return stringToDate;
    }

    public static Date mhkDate(int month) {
        Date date = new Date();// 当前日期
        Calendar calendar = Calendar.getInstance();// 日历对象
        calendar.setTime(date);// 设置当前日期
        calendar.add(Calendar.MONTH, month);// 月份减一
        return calendar.getTime();
    }

    // 按天计息 获取未来几天
    public static Date timeDate(int time) {
        Calendar c = Calendar.getInstance();
        // 当前的day_of_month加一就是明天时间
        c.add(Calendar.DAY_OF_MONTH, time);
        return c.getTime();
    }
}