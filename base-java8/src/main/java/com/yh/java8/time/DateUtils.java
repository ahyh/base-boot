package com.yh.java8.time;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author yanhuan
 */
public final class DateUtils {

    /**
     * 返回当周的第几天
     * 星期一，返回1
     * 星期二，返回2
     * ......
     */
    public static Integer dayOfWeek(Date date) {
        return getLocalDate(date).getDayOfWeek().getValue();
    }

    /**
     * 返回现在日期是当周的第几天
     */
    public static Integer dayOfWeek() {
        return LocalDate.now().getDayOfWeek().getValue();
    }

    /**
     * 返回日期是当月的第几天
     */
    public static Integer dayOfMonth(Date date) {
        return getLocalDate(date).getDayOfMonth();
    }

    /**
     * 现在是所在月份的第几天
     */
    public static Integer dayOfMonth() {
        return LocalDate.now().getDayOfMonth();
    }

    /**
     * 返回日期是当年的第几天
     */
    public static Integer dayOfYear(Date date) {
        return getLocalDate(date).getDayOfYear();
    }

    /**
     * 现在是所在年的第几天
     */
    public static Integer dayOfYear() {
        return LocalDate.now().getDayOfYear();
    }

    /**
     * 现在所在月份有多少天
     */
    public static Integer lengthOfMonth() {
        return LocalDate.now().lengthOfMonth();
    }

    /**
     * date所在月份有多少天
     */
    public static Integer lengthOfMonth(Date date) {
        return getLocalDate(date).lengthOfMonth();
    }

    /**
     * 两个日期相隔的秒数
     */
    public static Long secondsBetween(Date fromDate, Date toDate) {
        return getDuration(fromDate, toDate).getSeconds();
    }

    /**
     * 两个日期相隔的小时数，不足一小时取0，向下取整
     */
    public static Long hoursBetween(Date fromDate, Date toDate) {
        return getDuration(fromDate, toDate).toHours();
    }

    /**
     * 两个日期相隔的分钟数
     */
    public static Long minutesBetween(Date fromDate, Date toDate) {
        return getDuration(fromDate, toDate).toMinutes();
    }

    /**
     * 两个日期相隔的天数
     */
    public static Long daysBetween(Date fromDate, Date toDate) {
        return getDuration(fromDate, toDate).toDays();
    }

    /**
     * 返回日期当年是否是闰年
     */
    public static Boolean isLeap(Date date) {
        return getLocalDate(date).isLeapYear();
    }

    public static LocalDate getLocalDate(Date date) {
        String format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
        String[] split = format.split(" ");
        return LocalDate.parse(split[0]);
    }

    public static LocalTime getLocalTime(Date date) {
        String format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
        String[] split = format.split(" ");
        return LocalTime.parse(split[1]);
    }

    public static LocalDateTime getLocalDateTime(Date date) {
        String format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
        String[] split = format.split(" ");
        return LocalDateTime.of(LocalDate.parse(split[0]), LocalTime.parse(split[1]));
    }

    public static Duration getDuration(Date fromDate, Date toDate) {
        LocalDateTime fromDateTime = getLocalDateTime(fromDate);
        LocalDateTime toDateTime = getLocalDateTime(toDate);
        return Duration.between(fromDateTime, toDateTime);
    }

    public static Period getPeriod(Date fromDate, Date toDate) {
        LocalDate fromDateTime = getLocalDate(fromDate);
        LocalDate toDateTime = getLocalDate(toDate);
        return Period.between(fromDateTime, toDateTime);
    }
}
