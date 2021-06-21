package com.zhanghao.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String getQQNumber(String email) {
        Pattern pattern = Pattern.compile("(.*?)(?=@)");
        Matcher matcher = pattern.matcher(email);
        String qq = "";
        if (matcher.find()) {
            qq = matcher.group(1);
        }
        return qq;
    }

    public static void main(String[] args) {
        System.out.println(getQQNumber("844541@qq.com"));
    }
}
