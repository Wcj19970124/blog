package com.wcj.vo;

import lombok.Data;

/**
 * 每月份发表博客数vo类
 * @author wcj
 * @Date 2020/4/5 19:33
 * @Version 1.0
 */
@Data
public class BlogStatistic {

    /**
     * 博客发表月份
     */
    private String Month;

    /**
     * 每月发表的博客数
     */
    private Integer blogNumber;


}
