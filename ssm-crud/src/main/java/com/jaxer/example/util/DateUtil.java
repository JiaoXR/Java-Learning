package com.jaxer.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 * <p>
 * Created by jaxer on 09/09/2018
 */
public class DateUtil {

    private static final String FORMATTER = "YYYY-MM-dd HH:mm:ss";

    public static String formatDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER);
        return localDateTime.format(formatter);
    }
}
