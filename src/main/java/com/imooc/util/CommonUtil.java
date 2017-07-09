package com.imooc.util;

import java.util.UUID;

/**
 * Created by Administrator on 2017/7/6.
 */
public class CommonUtil {

    //生产6位随机验证码
    public static int random(int number){
        return (int) ((Math.random() * 9 + 1) * Math.pow(10, number - 1));
    }

    public static boolean isEmpty(String str){
        return str == null || "".equals(str.trim());
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
