package com.wcj.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 点赞实体
 * @author wcj
 * @Date 2020/4/3 12:52
 * @Version 1.0
 */
@Data
public class Goods implements Serializable {

    /**
     * 点赞表id
     */
    @Id
    private String id;

    /**
     * 点赞用户id
     */
    private Integer userId;

    /**
     * 点赞博客id
     */
    private String blogId;
}
