package com.wcj;


import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author wcj
 * @Date 2020/3/24 10:46
 * @Version 1.0
 */
public class test {
    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("admin","admin",2);
        System.out.println(md5Hash);
    }
}
