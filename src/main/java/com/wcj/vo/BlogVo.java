package com.wcj.vo;

import lombok.Data;

/**
 * 博客vo类
 * @author wcj
 * @Date 2020/3/25 9:20
 * @Version 1.0
 */
@Data
public class BlogVo {

    /**
     * 帖子id
     */
    private String blogId;

    /**
     * 标题
     */
    private String blogTitle;

    /**
     * 封面
     */
    private String blogImage;

    /**
     * 帖子内容
     */
    private String blogContent;

    /**
     * 点赞数
     */
    private Integer blogGoods;

    /**
     * 阅读数
     */
    private Integer blogRead;

    /**
     * 收藏数
     */
    private Integer blogCollection;

    /**
     * 博客分类名
     */
    private String typeName;

    /**
     * 简介
     */
    private String blogRemark;

    /**
     * 评论数
     */
    private Integer blogComment;

    /**
     * 文章来源（爬虫时使用）
     */
    private String blogSource;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 博客对应月份
     */
    private String blogMonth;

    /**
     * 更新时间
     */
    private String updateTime;
}
