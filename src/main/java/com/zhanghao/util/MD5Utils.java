package com.zhanghao.util;

import org.springframework.util.DigestUtils;

public class MD5Utils {

    public static String encode(String inStr){
        return DigestUtils.md5DigestAsHex(inStr.getBytes());
    }

}
