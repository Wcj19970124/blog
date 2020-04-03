package com.wcj.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 评论表实体类
 *
 * @author wcj
 * @date
 * @Version 1.0
 */
@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = -262115810554538289L;

    /**
     * 评论id
     */
    @Id
    private String id;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评价人
     */
    private Integer commentUser;

    /**
     * 评价人
     */
    private User user;

    /**
     * 评论帖子id
     */
    private String commentBlog;

    /**
     * 博客
     */
    private Blog blog;

    /**
     * 点赞数
     */
    private Integer commentGood;

    /**
     * 评论时间
     */
    private String createdTime;

    /**
     * 评论标记,存库时不存该字段，查询评论点赞的时候使用该字段
     */
    private Boolean commentFlag = false;
}
