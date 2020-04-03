package com.wcj.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 评论点赞实体
 * @author wcj
 * @Date 2020/4/3 15:22
 * @Version 1.0
 */
@Data
public class CommentGoods {

    /**
     * 评论点赞id
     */
    @Id
    private String id;

    /**
     * 点赞人
     */
    private Integer userId;

    /**
     * 评论
     */
    private String commentId;
}
