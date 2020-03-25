package com.wcj.service;

import com.wcj.pojo.Blog;
import com.wcj.utils.Page;
import com.wcj.vo.BlogVo;

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
}
