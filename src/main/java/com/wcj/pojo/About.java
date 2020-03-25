package com.wcj.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wcj
 * @Date 2020/3/23 13:59
 * @Version 1.0
 */
@Data
public class About implements Serializable {

    /**
     * 帖子id
     */
    private Integer aboutId;
    /**
     * 帖子标题
     */
    private String aboutTitle;
    /**
     * 帖子内容
     */
    private String aboutContent;
    /**
     * 阅读数
     */
    private Integer aboutRead;
    /**
     * 创建时间
     */
    private String createdTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 是否启用，0否1是
     */
    private Integer enable;
    /**
     * 逻辑删除，1表示已删除，0表示未删除
     */
    private Integer deleted;
}
