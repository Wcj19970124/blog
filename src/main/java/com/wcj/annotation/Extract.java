package com.wcj.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 抽取爬取的结果注入到爬虫实体类的属性中
 *
 * @author wcj
 * @Date 2020/9/13 10:49
 * @Version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Extract {

    /**
     * CSS选择器
     *
     * @return
     */
    String cssQuery();

    /**
     * 爬取数据类型
     * 1标识text
     * 2标识html
     *
     * @return
     */
    int contentType() default 1;

    /**
     * 标识爬取添有此注解的某个属性
     *
     * @return
     */
    String attr() default "";

    /**
     * 获取字段需要截取的长度，默认长度为30
     * @return
     */
    int length() default 30;

    //http://106.52.198.68/group1/M00/00/04/rBAACV9eGSmASVJNAAB7xzvOmqg347.png

}
