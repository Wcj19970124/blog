package com.wcj.utils;

import com.wcj.pojo.Admin;
import com.wcj.pojo.User;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密明文密码
 * @author wcj
 * @Date 2020/3/28 12:41
 * @Version 1.0
 */
public class Md5 {

    public static String toMd5(Admin admin){
        Md5Hash md5Hash = new Md5Hash(admin.getPassword(),admin.getUsername(),2);
        return String.valueOf(md5Hash);
    }

    public static String toMd5(User user){
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),user.getUsername(),2);
        return String.valueOf(md5Hash);
    }

    public static String toMd5(User user,String password){
        Md5Hash md5Hash = new Md5Hash(password,user.getUsername(),2);
        return String.valueOf(md5Hash);
    }

}
