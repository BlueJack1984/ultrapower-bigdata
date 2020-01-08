package com.example.core.utils;

import java.util.Random;
/**
 * @Author liuliying@ultrapower.com.cn
 * @Description 随机生成验证码工具类
 * @Date 11:05 2020/1/6
 **/
public class CharUtil {
    /**
     * @Author liuliying@ultrapower.com.cn
     * @Description 生成带字母数字混合的字符串验证码
     * @Date 11:15 2020/1/6
     * @Param 验证码长度
     * @return 验证码
     **/
    public static String getRandomString(Integer num) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    /**
     * @Author liuliying@ultrapower.com.cn
     * @Description 生成纯数字的验证码
     * @Date 11:15 2020/1/6
     * @Param 验证码长度
     * @return 验证码
     **/
    public static String getRandomNum(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
