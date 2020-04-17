package com.wcj.service;

import com.wcj.pojo.Blog;
import com.wcj.pojo.BlogCollection;
import com.wcj.pojo.Goods;
import com.wcj.utils.Page;
import com.wcj.vo.BlogPopularStatistic;
import com.wcj.vo.BlogStatistic;
import com.wcj.vo.BlogVo;
import com.wcj.vo.TimeLineVo;

import java.util.List;

/**
 * @author wcj
 * @Date 2020/3/23 15:05
 * @Version 1.0
 */
public interface BlogService {

    /**
     * 添加博客
     * @param blog
     */
    void addBlog(Blog blog);

    /**
     * 修改博客
     * @param blog
     */
    void updateBlog(Blog blog);

    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    Blog getBlog(String id);

    /**
     * 删除博客
     * @param id
     */
    void deleteBlog(String id);

    /**
     * 分页查询博客列表
     * @param page
     * @return
     */
    Page<BlogVo> getBlogList(Page<BlogVo> page);

    /**
     * 按照id阅读博客
     * @param id
     * @return
     */
    BlogVo readBlog(String id);

    /**
     * 前台推荐阅读
     * @return
     */
    List<BlogVo> recommendRead();

    /**
     * 获取时间轴
     * @return
     */
    List<TimeLineVo> getTimeLine();

    /**
     * 博客点赞
     * @param goods
     */
    void goodBlog(Goods goods);

    /**
     * 根据用户id和博客id查询点赞数
     * @param blogId
     * @return
     */
    int getGoods(String blogId);

    /**
     * 收藏博客
     * @param blogCollection
     */
    void blogCollection(BlogCollection blogCollection);

    /**
     * 根据用户id和博客id查询收藏
     * @param blogId
     * @return
     */
    int getCollection(String blogId);

    /**
     * 统计每月的发表博客数
     * @return
     */
    List<BlogStatistic> getBlogStatistic();

    /**
     * 统计最受欢迎前五博客
     * @return
     */
    List<BlogPopularStatistic> getBlogPopularStatistic();

    /**
     * 获取前台展示博客总数
     * @return
     */
    Integer getTotal();
}
