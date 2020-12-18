package org.example.common.util;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @version V1.0
 * @since 2020-12-07 11:32
 */
public class DateUtil {

    /**
     * 默认格式化: yyyy-MM-dd HH:mm:ss
     */
    public static final String DEF_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化(带毫秒):
     */
    public static final String DATE_FORMAT_WITH_MILL = "yyyyMMddHHmmssSSS";

    /**
     * yyyyMMdd
     */
    public static final String DATE_FORMAT = "yyyyMMdd";

    /**
     * 格式化当前日期数据
     *
     * @param format 格式化
     * @return String
     */
    public static String formatNow(String format) {
        return format(new Date(), format);
    }

    /**
     * 格式化日期数据
     *
     * @param date   日期数据
     * @param format 格式化
     * @return String
     */
    public static String format(Date date, String format) {
        if (Objects.isNull(date)) {
            return null;
        }
        if (StringUtils.isEmpty(format)) {
            format = DEF_FORMAT;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 秒转时间
     *
     * @param second 秒
     * @return 时间
     */
    public static String secondToTime(long second) {
        long days = second / 86400;
        second = second % 86400;
        long hours = second / 3600;
        second = second % 3600;
        long minutes = second / 60;
//        second = second % 60;
        if (0 < days){
            return days + "d " + hours + "h " + minutes + "m ";
        }else {
            return hours + "h " + minutes + "m ";
        }
    }

}
