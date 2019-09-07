package com.qf.ssmexam.utils;

import org.springframework.util.DigestUtils;

/**
 * 密码的加解密
 */
public class PasswdCodec {

    /**
     *  密码加盐(salt)处理。
     *   123456 -> e1 0a dc 39 49 ba59abbe56e057f20f883e
     *   然后再生产16位随机字符串，abcde0123456789a
     *   e1 a 0a b dc c .....  -> 得到的长度为48位
     *   接着对48位的字符串再次md5.
     *
     *   将得到的字符串存入到数据库
     */
    public static String encryPwd(String salt, String password) {
        if(null != salt && salt.length() != 16) {
            return null;
        }

        // 对密码做md5加密
        String pwdMd5 = DigestUtils.md5DigestAsHex(password.getBytes());

        char[] pwdMd5CharArray  = pwdMd5.toCharArray();  // 长度是32
        char[] saltCharArray = salt.toCharArray(); //长度16

        char[] newCharArray = new char[48];  //生产新的长度为48位的字符数组

        /**
         * c4 ca 42 38 a0b923820dcc509a6f75849b
         * abcde0123456789a
         * c4a cab
         * c4acab42c38da0eb902318220d3cc45059a66f77588499ba
         */
        for (int i = 0; i < 16; i++) {
            newCharArray[i*3] = pwdMd5CharArray[i*2];
            newCharArray[i*3 + 1] = pwdMd5CharArray[i*2 + 1];
            newCharArray[i*3 + 2] = saltCharArray[i];
        }

        // new ArrayList<>(10010);
        String newPwd = new String(newCharArray).intern();  // 性能优化

        return DigestUtils.md5DigestAsHex(newPwd.getBytes()); //再做md5
    }

    public static void main(String[] args) {
//        System.out.println(DigestUtils.md5DigestAsHex("1".getBytes()));
//
//        System.out.println(encryPwd("abcde0123456789a", "1"));

        String str = "/exam/";
        System.out.println(str.substring(0, str.length() - 1));
    }
}
