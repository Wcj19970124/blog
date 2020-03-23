package com.wcj.pojo;

import lombok.Data;

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
     * 评论帖子id
     */
    private String commentBlog;

    /**
     * 点赞数
     */
    private Integer commentGood;

    /**
     * 评论时间
     */
    private String createdTime;

    /**
     * 逻辑删除
     */
    private Integer deleted;
}
