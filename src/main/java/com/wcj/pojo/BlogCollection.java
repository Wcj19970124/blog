package com.wcj.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 收藏时间实体类
 *
 * @author wcj
 * @date 2020-03-23
 * @Version 1.0
 */
@Data
public class BlogCollection implements Serializable {

    private static final long serialVersionUID = -535915810554536111L;

    /**
     * 收藏id
     */
    private String collectionId;

    /**
     * 帖子id
     */
    private String blogId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 收藏时间
     */
    private String collectionTime;

}
