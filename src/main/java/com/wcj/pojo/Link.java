package com.wcj.pojo;

import lombok.Data;

/**
 * 友情链接
 *
 * @Author: wcj
 * @Date:
 * @Version 1.0
 */
@Data
public class Link {

    /**
     * 链接id
     */
    private Integer linkId;
    /**
     * 链接名
     */
    private String linkName;
    /**
     * 链接地址
     */
    private String linkUrl;
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
     * 逻辑删除
     */
    private Integer deleted;

}
