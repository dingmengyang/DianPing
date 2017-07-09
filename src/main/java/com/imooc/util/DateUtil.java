package com.imooc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将java的date转换成mysql的date
 */
public class DateUtil {

    private static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String dateFormat(Date date){
        return format.format(date);
    }
}
