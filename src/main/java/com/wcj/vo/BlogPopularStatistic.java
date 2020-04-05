package com.wcj.vo;

import lombok.Data;

/**
 * 最受欢迎博客统计vo类
 *
 * @author wcj
 * @Date 2020/4/5 20:45
 * @Version 1.0
 */
@Data
public class BlogPopularStatistic {

    private static final Integer STR_LENGTH = 10;

    /**
     * 排名前五博客标题
     */
    private String blogTitle;

    /**
     * 博客阅读量，收藏量，点赞量总和
     */
    private Integer count;

    /**
     * 将博客标题截断为前10个字符
     * @return
     */
    public String getBlogTitle() {
        if(this.blogTitle.length()>STR_LENGTH){
            return blogTitle.substring(0,10);
        }
        return blogTitle.substring(0);
    }
}
