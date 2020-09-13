package com.wcj.annotation;

import org.jsoup.Connection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识该类是一个爬虫实体类（接收爬取结果）
 *
 * @author wcj
 * @Date 2020/9/13 10:46
 * @Version 1.0
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsoupDocument {

    /**
     * 域名
     *
     * @return
     */
    String domain();

    /**
     * 待爬取的目标Url
     *
     * @return
     */
    String targetUrl() default "";

    /**
     * Css选择器
     *
     * @return
     */
    String cssQuery() default "";

    /**
     * 通用爬取方法：GET
     *
     * @return
     */
    Connection.Method method() default Connection.Method.GET;

    /**
     * 当该注解用于字段上的时候
     * 标识从该字段上获取目标的Url
     * @return
     */
    String hrefAttr() default "";

}