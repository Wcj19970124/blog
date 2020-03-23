package com.wcj.pojo;

import lombok.Data;

/**
 * 音乐实体类
 *
 * @author wcj
 * @date
 * @version 1.0
 */
@Data
public class Music {

    /**
     * 音乐id
     */
    private Integer id;
    /**
     * 歌名
     */
    private String name;
    /**
     * 歌手
     */
    private String artist;
    /**
     * 歌曲链接
     */
    private String url;
    /**
     * 歌曲封面
     */
    private String cover;
    /**
     * 歌曲内容
     */
    private String lrc;
    /**
     * 创建时间
     */
    private String createdTime;
    /**
     * 是否启用，1表示已启用，0表示未启用
     */
    private Integer enabled;
    /**
     * 逻辑删除 1表示已删除，0表示未删除
     */
    private Integer deleted;

}
